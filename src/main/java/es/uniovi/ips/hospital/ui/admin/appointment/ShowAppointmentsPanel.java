package es.uniovi.ips.hospital.ui.admin.appointment;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.filter.AppointmentTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.AppointmentTableCellRenderer;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.GridLayout;

@Component
public class ShowAppointmentsPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 8580603940209784804L;
	
	@Autowired	private AppointmentService appointmentService;
	@Autowired	private AdminDialog adminDialog;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearch;
	private TextMatcherEditor<Appointment> textMatcherEditor;
	private FilterList<Appointment> filterList;
	private JScrollPane spTable;
	private JTable tblAppointments;
	private JPanel pnBottom;
	private JButton btnEdit;
	private JButton btnProcess;

	/**
	 * Create the dialog.
	 */
	public ShowAppointmentsPanel() {
		addFocusListener(new ListReloader());
		setBounds(100, 100, 650, 700);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.add(getTxtSearch(), BorderLayout.NORTH);
		contentPanel.add(getSpTable());
		contentPanel.add(getPnBottom(), BorderLayout.SOUTH);
	}

	private JTextField getTxtSearch() {
		if (txtSearch == null) {
			txtSearch = new JTextField();
			txtSearch.setColumns(20);
            textMatcherEditor = new TextComponentMatcherEditor<Appointment>(txtSearch, new AppointmentTextFilterator());
		}
		return txtSearch;
	}
	private JScrollPane getSpTable() {
		if (spTable == null) {
			spTable = new JScrollPane();
			spTable.getViewport().setBackground(PaletteFactory.getBaseDark());
			spTable.setViewportView(getTblAppointments());
		}
		return spTable;
	}
	private JTable getTblAppointments() {
		if (tblAppointments == null) {
			tblAppointments = new JTable();
			tblAppointments.getSelectionModel().addListSelectionListener(e -> enableButtons());
		}
		return tblAppointments;
	}
	private JPanel getPnBottom() {
		if (pnBottom == null) {
			pnBottom = new JPanel();
			pnBottom.setLayout(new GridLayout(0, 2, 0, 0));
			pnBottom.add(getBtnProcess());
			pnBottom.add(getBtnEdit());
		}
		return pnBottom;
	}
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new MyButton("Edit");
			btnEdit.setEnabled(false);
			btnEdit.addActionListener(actionEvent -> launchEditAppointment());
		}
		return btnEdit;
	}
	private JButton getBtnProcess() {
		if (btnProcess == null) {
			btnProcess = new MyButton("Process");
			btnProcess.setEnabled(false);
			btnProcess.addActionListener(actionEvent -> launchProcessAppointment());
		}
		return btnProcess;
	}
	
	// FORMAT Y DATOS DE LA TABLA ---------------------------------------------------------------------------------
	
	public void showAppointments() {
		EventList<Appointment> eventList = new BasicEventList<Appointment>();
		eventList.addAll(appointmentService.findAllAppointments());
		SortedList<Appointment> sortedList = new SortedList<Appointment>(eventList, new AppointmentComparator());
        filterList = new FilterList<Appointment>(sortedList, textMatcherEditor);
		AdvancedTableModel<Appointment> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(filterList, new AppointmentTableFormat());
		tblAppointments.setModel(tableModel);
		tblAppointments.setDefaultRenderer(Object.class, new AppointmentTableCellRenderer());
		tblAppointments.getColumnModel().getColumn(0).setMinWidth(150);
		tblAppointments.getColumnModel().getColumn(0).setMaxWidth(150);
		tblAppointments.getColumnModel().getColumn(2).setMaxWidth(75);
		tblAppointments.getColumnModel().getColumn(3).setMaxWidth(75);
		TableComparatorChooser.install(tblAppointments, sortedList, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);
	}
	
	private class AppointmentTableFormat implements TableFormat<Appointment> {

	    public int getColumnCount() {
	        return 4;
	    }
	    public String getColumnName(int column) {
	        if(column == 0)      return "Date";
	        else if(column == 1) return "Patient";
	        else if(column == 2) return "Room";
	        else if(column == 3) return "Urgent";

	        throw new IllegalStateException();
	    }
	    public Object getColumnValue(Appointment appointment, int column) {
	        if(column == 0)      return appointment.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	        else if(column == 1) return appointment.getPatient().guiToString();
	        else if(column == 2) return appointment.getRoom().guiToString();
	        else if(column == 3) return (appointment.isUrgent()) ? "YES" : "";
	        throw new IllegalStateException();
	    }
	}
	
	@SuppressWarnings("unchecked")
	private void enableButtons() {
		btnEdit.setEnabled(false);
		btnProcess.setEnabled(false);
		Appointment appointment;
		if (tblAppointments.getSelectedRow() != -1) {
			appointment = ((AdvancedTableModel<Appointment>) tblAppointments.getModel()).getElementAt(tblAppointments.getSelectedRow());
			if (appointment.getStartTime().isAfter(LocalDateTime.now()))
				if (appointment.isConfirmed())
					btnEdit.setEnabled(true);
				else
					btnProcess.setEnabled(true);
		}
		
	}
	
	private class ListReloader extends FocusAdapter {
		
		public void focusGained(FocusEvent arg0) {
			filterList.clear();
			filterList.addAll(appointmentService.findAllAppointments());
		}
	}
	
	// LANZAMIENTO DE LA EDICION DE LA CITA ----------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	public void launchEditAppointment() {
		if (tblAppointments.getSelectedRow() != -1)
			adminDialog.launchEditAppointment(((AdvancedTableModel<Appointment>) tblAppointments.getModel()).getElementAt(tblAppointments.getSelectedRow()));
	}
	
	@SuppressWarnings("unchecked")
	public void launchProcessAppointment() {
		if (tblAppointments.getSelectedRow() != -1)
			adminDialog.launchProcessAppointment(((AdvancedTableModel<Appointment>) tblAppointments.getModel()).getElementAt(tblAppointments.getSelectedRow()));
	}

	@Override
	public void setFocus() {
		btnEdit.requestFocus();
	}
}
