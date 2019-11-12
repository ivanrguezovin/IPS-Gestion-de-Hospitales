package es.uniovi.ips.hospital.ui.doctor.appointment;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.ui.util.filter.AppointmentTextFilterator;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;

@Component
public class MyAppointmentsDialog extends JDialog {

    private final JPanel contentPanel;

    private JTextField searchField;
    private TextMatcherEditor<Appointment> textMatcherEditor;
    private FilterList<Appointment> filterList;
    private JScrollPane scrollPane;
    private JTable myAppointmentsTable;
    private JPanel bottomPanel;
    private JButton openAppointmentButton;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentDialog appointmentDialog;
    private Doctor myself;

    public MyAppointmentsDialog() {
        myself = new Doctor();
        contentPanel = new JPanel();
        addWindowFocusListener(new ListReloader());
        this.setTitle("My Appointments");
        this.setBounds(100, 100, 450, 700);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getSearchField(), BorderLayout.NORTH);
        contentPanel.add(getScrollPane());
        contentPanel.add(getBottomPanel(), BorderLayout.SOUTH);
    }

    public void run(Doctor doctor) {
        this.myself = doctor;
        EventList<Appointment> myAppointments = new BasicEventList<>();
        myAppointments.addAll(appointmentService.findAllByDoctor(this.myself));
        SortedList<Appointment> sortedList = new SortedList<>(myAppointments, new AppointmentComparator());
        filterList = new FilterList<>(sortedList, textMatcherEditor);
        AdvancedTableModel<Appointment> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(filterList, new AppointmentTableFormat());
        myAppointmentsTable.setModel(tableModel);
        TableComparatorChooser.install(myAppointmentsTable, sortedList, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JTextField getSearchField() {
        if (searchField == null) {
            searchField = new JTextField();
            searchField.setColumns(10);
            textMatcherEditor = new TextComponentMatcherEditor<>(searchField, new AppointmentTextFilterator());
        }
        return searchField;
    }

    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(getMyAppointmentsTable());
        }
        return scrollPane;
    }

    private JTable getMyAppointmentsTable() {
        if (myAppointmentsTable == null) {
            myAppointmentsTable = new JTable();
        }
        return myAppointmentsTable;
    }

    private JPanel getBottomPanel() {
        if (bottomPanel == null) {
            bottomPanel = new JPanel();
            FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            bottomPanel.add(getOpenAppointmentButton());
        }
        return bottomPanel;
    }

    private JButton getOpenAppointmentButton() {
        if (openAppointmentButton == null) {
            openAppointmentButton = new JButton("Open");
            openAppointmentButton.addActionListener(actionEvent -> appointmentDialog.run(this.getSelectedAppointment(), this.myself));
        }
        return openAppointmentButton;
    }

    public Appointment getSelectedAppointment() {
        return ((AdvancedTableModel<Appointment>) myAppointmentsTable.getModel()).getElementAt(myAppointmentsTable.getSelectedRow());
    }

    private class AppointmentTableFormat implements TableFormat<Appointment> {

        public int getColumnCount() {
            return 4;
        }

        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Date";
                case 1:
                    return "Patient";
                case 2:
                    return "Room";
                case 3:
                    return "Urgent";
                default:
                    throw new IllegalStateException();
            }
        }

        public Object getColumnValue(Appointment appointment, int column) {
            switch (column) {
                case 0:
                    return appointment.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                case 1:
                    return appointment.getPatient().guiToString();
                case 2:
                    return appointment.getRoom().guiToString();
                case 3:
                    return (appointment.isUrgent()) ? "YES" : "";
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private class ListReloader extends WindowAdapter {

        public void windowGainedFocus(WindowEvent arg0) {
            filterList.clear();
            filterList.addAll(appointmentService.findAllByDoctor(myself));
        }
    }
}
