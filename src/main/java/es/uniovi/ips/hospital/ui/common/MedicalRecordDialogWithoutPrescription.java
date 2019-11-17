package es.uniovi.ips.hospital.ui.common;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Component
public class MedicalRecordDialogWithoutPrescription extends JDialog {

    private static final long serialVersionUID = 5633142202939076631L;
    private final JPanel contentPanel = new JPanel();
    private JPanel pnPatient;
    private JTable tblMedicalRecord;
    private JPanel pnPatientInfo;
    private JPanel pnPatientAddresses;
    private JLabel lblName;
    private JLabel lblId;
    private JLabel lblEmail;
    private JLabel lblAddress;
    private JScrollPane spMedicalRecord;
    private JPanel pnButton;
    private JButton btnAccept;
    @Autowired
    private AppointmentService appointmentService;

    /**
     * Create the dialog.
     */
    public MedicalRecordDialogWithoutPrescription() {
        setBounds(100, 100, 600, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getPnPatient(), BorderLayout.NORTH);
        contentPanel.add(getSpMedicalRecord(), BorderLayout.CENTER);
        contentPanel.add(getPnButton(), BorderLayout.SOUTH);
    }

    private JPanel getPnPatient() {
        if (pnPatient == null) {
            pnPatient = new JPanel();
            pnPatient.setLayout(new BorderLayout(0, 0));
            pnPatient.add(getPnPatientInfo(), BorderLayout.WEST);
            pnPatient.add(getPnPatientAddresses(), BorderLayout.EAST);
        }
        return pnPatient;
    }

    private JScrollPane getSpMedicalRecord() {
        if (spMedicalRecord == null) {
            spMedicalRecord = new JScrollPane();
            spMedicalRecord.setViewportView(getTblMedicalRecord());
        }
        return spMedicalRecord;
    }

    private JTable getTblMedicalRecord() {
        if (tblMedicalRecord == null) {
            tblMedicalRecord = new JTable();
        }
        return tblMedicalRecord;
    }

    private JPanel getPnPatientInfo() {
        if (pnPatientInfo == null) {
            pnPatientInfo = new JPanel();
            pnPatientInfo.setLayout(new BoxLayout(pnPatientInfo, BoxLayout.Y_AXIS));
            pnPatientInfo.add(getLblName());
            pnPatientInfo.add(getLblId());
        }
        return pnPatientInfo;
    }

    private JPanel getPnPatientAddresses() {
        if (pnPatientAddresses == null) {
            pnPatientAddresses = new JPanel();
            pnPatientAddresses.setLayout(new BoxLayout(pnPatientAddresses, BoxLayout.Y_AXIS));
            pnPatientAddresses.add(getLblEmail());
            pnPatientAddresses.add(getLblAddress());
        }
        return pnPatientAddresses;
    }

    private JLabel getLblName() {
        if (lblName == null) {
            lblName = new JLabel("Name");
            lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
        }
        return lblName;
    }

    private JLabel getLblId() {
        if (lblId == null) {
            lblId = new JLabel("Id");
            lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
        }
        return lblId;
    }

    private JLabel getLblEmail() {
        if (lblEmail == null) {
            lblEmail = new JLabel("Email");
            lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
        }
        return lblEmail;
    }

    private JLabel getLblAddress() {
        if (lblAddress == null) {
            lblAddress = new JLabel("Address");
            lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
        }
        return lblAddress;
    }

    // METODOS Y CLASES DE INICIALIZACION ------------------------------------------------------------

    public void showHistoryOf(Patient patient) {
        lblName.setText("Patient: " + patient.getName() + " " + patient.getSurname());
        lblId.setText("Identification: " + patient.getDni());
        lblEmail.setText("Email: " + patient.getEmail());
        lblAddress.setText("Address: " + patient.getAddress().guiToString());
        EventList<Appointment> eventList = new BasicEventList<>();
        eventList.addAll(appointmentService.findAllByPatient(patient));
        SortedList<Appointment> sortedList = new SortedList<>(eventList, new AppointmentComparator());
        AdvancedTableModel<Appointment> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(sortedList, new RecordTableFormat());
        tblMedicalRecord.setModel(tableModel);
        this.setModal(true);
        this.setVisible(true);
    }

    private JPanel getPnButton() {
        if (pnButton == null) {
            pnButton = new JPanel();
            FlowLayout flowLayout = (FlowLayout) pnButton.getLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            pnButton.add(getBtnAccept());
        }
        return pnButton;
    }

    private JButton getBtnAccept() {
        if (btnAccept == null) {
            btnAccept = new JButton("Accept");
        }
        return btnAccept;
    }

    private class RecordTableFormat implements TableFormat<Appointment> {

        public int getColumnCount() {
            return 3;
        }

        public String getColumnName(int column) {
            if (column == 0) return "Time";
            else if (column == 1) return "Doctor";
            else if (column == 2) return "Room";

            throw new IllegalStateException();
        }

        public Object getColumnValue(Appointment appointment, int column) {
            if (column == 0) return appointment.getStartTime().toString();
            else if (column == 1) return appointment.prettifyDoctors();
            else if (column == 2) return appointment.getRoom().getLocation();
            throw new IllegalStateException();
        }
    }
}
