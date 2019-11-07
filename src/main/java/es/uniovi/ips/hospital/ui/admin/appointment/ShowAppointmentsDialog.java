package es.uniovi.ips.hospital.ui.admin.appointment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.ui.util.filter.AppointmentTextFilterator;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@Component
public class ShowAppointmentsDialog extends JDialog {

	private static final long serialVersionUID = 8580603940209784804L;
	
	@Autowired	private AppointmentService appointmentService;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearch;
	private TextMatcherEditor<Appointment> textMatcherEditor;
	private JScrollPane spTable;
	private JTable tblAppointments;
	private JPanel pnBottom;
	private JButton btnEdit;

	/**
	 * Create the dialog.
	 */
	public ShowAppointmentsDialog() {
		setTitle("Administrator: choose an appointment to edit");
		setBounds(100, 100, 450, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getTxtSearch(), BorderLayout.NORTH);
		contentPanel.add(getSpTable());
		contentPanel.add(getPnBottom(), BorderLayout.SOUTH);
	}

	private JTextField getTxtSearch() {
		if (txtSearch == null) {
			txtSearch = new JTextField();
			txtSearch.setColumns(10);
            textMatcherEditor = new TextComponentMatcherEditor<Appointment>(txtSearch, new AppointmentTextFilterator());
		}
		return txtSearch;
	}
	private JScrollPane getSpTable() {
		if (spTable == null) {
			spTable = new JScrollPane();
			spTable.setViewportView(getTblAppointments());
		}
		return spTable;
	}
	private JTable getTblAppointments() {
		if (tblAppointments == null) {
			tblAppointments = new JTable();
		}
		return tblAppointments;
	}
	private JPanel getPnBottom() {
		if (pnBottom == null) {
			pnBottom = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBottom.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBottom.add(getBtnEdit());
		}
		return pnBottom;
	}
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
		}
		return btnEdit;
	}
	
	// FORMAT Y DATOS DE LA TABLA ---------------------------------------------------------------------------------
	
	public void showAppointments() {
		EventList<Appointment> eventList = new BasicEventList<Appointment>();
		eventList.addAll(appointmentService.findAllAppointments());
		SortedList<Appointment> sortedList = new SortedList<Appointment>(eventList, new AppointmentComparator());
        FilterList<Appointment> filterList = new FilterList<Appointment>(sortedList, textMatcherEditor);
		AdvancedTableModel<Appointment> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(filterList, new AppointmentTableFormat());
		tblAppointments.setModel(tableModel);
		this.setModal(true);
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
}
