package es.uniovi.ips.hospital.ui.admin.appointment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toedter.calendar.JDateChooser;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.RoomService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyComboBox;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.filter.AppointmentTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.RoomTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.StaffTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.AppointmentTableCellRenderer;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;
import es.uniovi.ips.hospital.util.format.PatientFormat;
import es.uniovi.ips.hospital.util.format.RoomFormat;
import es.uniovi.ips.hospital.util.format.StaffFormat;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

@Component
public class ShowAppointmentsPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 8580603940209784804L;

	@Autowired private AppointmentService appointmentService;
	@Autowired private AdminDialog adminDialog;
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;
    @Autowired private NurseService nurseService;
    @Autowired private RoomService roomService;
    @Autowired private PatientFormat patientFormat;
    @Autowired private StaffFormat staffFormat;
    @Autowired private RoomFormat roomFormat;

	private final JPanel contentPanel = new JPanel();
	private JPanel pnFilters;
	private JPanel pnTable;
	private JTextField txtSearch;
	private TextMatcherEditor<Appointment> textMatcherEditor;
	private FilterList<Appointment> filterList;
	private JScrollPane spTable;
	private JTable tblAppointments;
	private JPanel pnBottom;
	private JButton btnEdit;
	private JButton btnProcess;
	private JLabel lblFilter;
	private JPanel pnFrom;
	private JPanel pnTo;
	private JSeparator separatorDatePatient;
	private JPanel pnPatient;
	private JSeparator separatorPatientDoctor;
	private JPanel pnDoctor;
	private JPanel pnNurse;
	private JPanel pnRoom;
	private JPanel pnCheckList;
	private JSeparator separatorNurseRoom;
	private JSeparator separatorRoomCheckList;
	private JLabel lblFrom;
	private JDateChooser fromChooser;
	private JLabel lblTo;
	private JDateChooser toChooser;
	private JLabel lblPatient;
	private JComboBox<Patient> cbPatient;
	private JLabel lblDoctor;
	private JComboBox<Staff> cbDoctor;
	private JLabel lblNurse;
	private JComboBox<Staff> cbNurse;
	private JLabel lblRoom;
	private JComboBox<Room> cbRoom;
	private JCheckBox chckbxJustUrgents;
	private JCheckBox chckbxHidePast;
	private JCheckBox chckbxHideUnconfirmed;
	private AutoCompleteSupport<Patient> autoCompletePatient;
    private AutoCompleteSupport<Staff> autoCompleteDoctor;
    private AutoCompleteSupport<Staff> autoCompleteNurse;
    private AutoCompleteSupport<Room> autoCompleteRoom;
    private EventList<Patient> patientList;
    private EventList<Staff> doctorList;
    private EventList<Staff> nurseList;
    private EventList<Room> roomList;

	/**
	 * Create the dialog.
	 */
	public ShowAppointmentsPanel() {
		addFocusListener(new ListReloader());
		setBounds(100, 100, 650, 700);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		GridBagLayout gbl_pn = new GridBagLayout();
		gbl_pn.columnWidths = new int[] { 200, 450 };
		gbl_pn.rowHeights = new int[] { 700 };
		gbl_pn.columnWeights = new double[] { 0.0, 0.0 };
		gbl_pn.rowWeights = new double[] { 1.0 };
		contentPanel.setLayout(gbl_pn);
		GridBagConstraints gbc_pnFilters = new GridBagConstraints();
		gbc_pnFilters.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnFilters.insets = new Insets(5, 5, 5, 5);
		gbc_pnFilters.gridx = 0;
		gbc_pnFilters.gridy = 0;
		contentPanel.add(getPnFilters(), gbc_pnFilters);
		GridBagConstraints gbc_pnTable = new GridBagConstraints();
		gbc_pnTable.fill = GridBagConstraints.BOTH;
		gbc_pnTable.gridx = 1;
		gbc_pnTable.gridy = 0;
		contentPanel.add(getPnTable(), gbc_pnTable);
	}

	private JPanel getPnFilters() {
		if (pnFilters == null) {
			pnFilters = new MyFrontPanel();
			pnFilters.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
			pnFilters.setBorder(Designer.getBorder());
			pnFilters.setLayout(new BoxLayout(pnFilters, BoxLayout.Y_AXIS));
			pnFilters.add(getLblFilter());
			pnFilters.add(getPnFrom());
			pnFilters.add(getPnTo());
			pnFilters.add(Box.createRigidArea(new Dimension(10,10)));
			pnFilters.add(getSeparatorDatePatient());
			pnFilters.add(getPnPatient());
			pnFilters.add(getSeparatorPatientDoctor());
			pnFilters.add(getPnDoctor());
			pnFilters.add(getPnNurse());
			pnFilters.add(getSeparatorNurseRoom());
			pnFilters.add(getPnRoom());
			pnFilters.add(getSeparatorRoomCheckList());
			pnFilters.add(getPnCheckList());
		}
		return pnFilters;
	}

	private JLabel getLblFilter() {
		if (lblFilter == null) {
			lblFilter = new JLabel("Filter");
			lblFilter.setHorizontalTextPosition(SwingConstants.LEFT);
			lblFilter.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblFilter;
	}

	private JPanel getPnFrom() {
		if (pnFrom == null) {
			pnFrom = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnFrom.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnFrom.add(getLblFrom());
			pnFrom.add(getFromChooser());
		}
		return pnFrom;
	}

	private JLabel getLblFrom() {
		if (lblFrom == null) {
			lblFrom = new JLabel("From:");
		}
		return lblFrom;
	}

	private JDateChooser getFromChooser() {
		if (fromChooser == null) {
			fromChooser = new JDateChooser();
		}
		return fromChooser;
	}

	private JPanel getPnTo() {
		if (pnTo == null) {
			pnTo = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnTo.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnTo.add(getLblTo());
			pnTo.add(getToChooser());
		}
		return pnTo;
	}

	private JLabel getLblTo() {
		if (lblTo == null) {
			lblTo = new JLabel("To:     ");
		}
		return lblTo;
	}

	private JDateChooser getToChooser() {
		if (toChooser == null) {
			toChooser = new JDateChooser();
		}
		return toChooser;
	}

	private JSeparator getSeparatorDatePatient() {
		if (separatorDatePatient == null) {
			separatorDatePatient = new JSeparator();
		}
		return separatorDatePatient;
	}

	private JPanel getPnPatient() {
		if (pnPatient == null) {
			pnPatient = new JPanel();
			pnPatient.setLayout(new BoxLayout(pnPatient, BoxLayout.Y_AXIS));
			pnPatient.add(Box.createRigidArea(new Dimension(10,10)));
			pnPatient.add(getLblPatient());
			pnPatient.add(getCbPatient());
			pnPatient.add(Box.createRigidArea(new Dimension(10,10)));
		}
		return pnPatient;
	}

	private JSeparator getSeparatorPatientDoctor() {
		if (separatorPatientDoctor == null) {
			separatorPatientDoctor = new JSeparator();
		}
		return separatorPatientDoctor;
	}

	private JPanel getPnDoctor() {
		if (pnDoctor == null) {
			pnDoctor = new JPanel();
			pnDoctor.setLayout(new BoxLayout(pnDoctor, BoxLayout.Y_AXIS));
			pnDoctor.add(Box.createRigidArea(new Dimension(10,10)));
			pnDoctor.add(getLblDoctor());
			pnDoctor.add(getCbDoctor());
			pnDoctor.add(Box.createRigidArea(new Dimension(10,10)));
		}
		return pnDoctor;
	}

	private JPanel getPnNurse() {
		if (pnNurse == null) {
			pnNurse = new JPanel();
			pnNurse.setLayout(new BoxLayout(pnNurse, BoxLayout.Y_AXIS));
			pnNurse.add(getLblNurse());
			pnNurse.add(getCbNurse());
			pnNurse.add(Box.createRigidArea(new Dimension(10,10)));
		}
		return pnNurse;
	}

	private JSeparator getSeparatorNurseRoom() {
		if (separatorNurseRoom == null) {
			separatorNurseRoom = new JSeparator();
		}
		return separatorNurseRoom;
	}

	private JPanel getPnRoom() {
		if (pnRoom == null) {
			pnRoom = new JPanel();
			pnRoom.setLayout(new BoxLayout(pnRoom, BoxLayout.Y_AXIS));
			pnRoom.add(Box.createRigidArea(new Dimension(10,10)));
			pnRoom.add(getLblRoom());
			pnRoom.add(getCbRoom());
			pnRoom.add(Box.createRigidArea(new Dimension(10,10)));
		}
		return pnRoom;
	}

	private JSeparator getSeparatorRoomCheckList() {
		if (separatorRoomCheckList == null) {
			separatorRoomCheckList = new JSeparator();
		}
		return separatorRoomCheckList;
	}

	private JPanel getPnCheckList() {
		if (pnCheckList == null) {
			pnCheckList = new JPanel();
			pnCheckList.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
			pnCheckList.setLayout(new BoxLayout(pnCheckList, BoxLayout.Y_AXIS));
			pnCheckList.add(Box.createRigidArea(new Dimension(10,10)));
			pnCheckList.add(getChckbxJustUrgents());
			pnCheckList.add(getChckbxHidePast());
			pnCheckList.add(getChckbxHideUnconfirmed());
		}
		return pnCheckList;
	}

	private JLabel getLblPatient() {
		if (lblPatient == null) {
			lblPatient = new JLabel("Patient:");
		}
		return lblPatient;
	}

	private JComboBox<Patient> getCbPatient() {
		if (cbPatient == null) {
			cbPatient = new MyComboBox<Patient>();
			cbPatient.addItemListener(itemEvent -> filter());
			autoCompletePatient = null;
		}
		return cbPatient;
	}

	private JLabel getLblDoctor() {
		if (lblDoctor == null) {
			lblDoctor = new JLabel("Doctor:");
		}
		return lblDoctor;
	}

    private JComboBox<Staff> getCbDoctor() {
        if (cbDoctor == null) {
            cbDoctor = new MyComboBox<Staff>();
            cbDoctor.addItemListener(itemEvent -> filter());
            autoCompleteDoctor = null;
        }
        return cbDoctor;
    }

	private JLabel getLblNurse() {
		if (lblNurse == null) {
			lblNurse = new JLabel("Nurse:");
		}
		return lblNurse;
	}

    private JComboBox<Staff> getCbNurse() {
        if (cbNurse == null) {
        	cbNurse = new MyComboBox<Staff>();
            cbNurse.addItemListener(itemEvent -> filter());
            autoCompleteNurse = null;
        }
        return cbNurse;
    }

	private JLabel getLblRoom() {
		if (lblRoom == null) {
			lblRoom = new JLabel("Room:");
		}
		return lblRoom;
	}

    private JComboBox<Room> getCbRoom() {
        if (cbRoom == null) {
            cbRoom = new MyComboBox<Room>();
            autoCompleteRoom = null;
        }
        return cbRoom;
    }

	private JCheckBox getChckbxJustUrgents() {
		if (chckbxJustUrgents == null) {
			chckbxJustUrgents = new JCheckBox("Just urgents");
		}
		return chckbxJustUrgents;
	}

	private JCheckBox getChckbxHidePast() {
		if (chckbxHidePast == null) {
			chckbxHidePast = new JCheckBox("Hide past");
		}
		return chckbxHidePast;
	}

	private JCheckBox getChckbxHideUnconfirmed() {
		if (chckbxHideUnconfirmed == null) {
			chckbxHideUnconfirmed = new JCheckBox("Hide unconfirmed");
		}
		return chckbxHideUnconfirmed;
	}

	private JPanel getPnTable() {
		if (pnTable == null) {
			pnTable = new MyFrontPanel();
			pnTable.setLayout(new BorderLayout(0, 0));
			pnTable.setBorder(Designer.getBorder());
			pnTable.add(getTxtSearch(), BorderLayout.NORTH);
			pnTable.add(getSpTable());
			pnTable.add(getPnBottom(), BorderLayout.SOUTH);
		}
		return pnTable;
	}

	private JTextField getTxtSearch() {
		if (txtSearch == null) {
			txtSearch = new JTextField();
			txtSearch.setBackground(PaletteFactory.getMainDark());
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

	// FORMAT Y DATOS DE LA TABLA
	// ---------------------------------------------------------------------------------

	public void showAppointments() {
		EventList<Appointment> eventList = new BasicEventList<Appointment>();
		eventList.addAll(appointmentService.findAllAppointments());
		SortedList<Appointment> sortedList = new SortedList<Appointment>(eventList, new AppointmentComparator());
		filterList = new FilterList<Appointment>(sortedList, textMatcherEditor);
		AdvancedTableModel<Appointment> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(filterList,
				new AppointmentTableFormat());
		tblAppointments.setModel(tableModel);
		tblAppointments.setDefaultRenderer(Object.class, new AppointmentTableCellRenderer());
		tblAppointments.getColumnModel().getColumn(0).setMinWidth(125);
		tblAppointments.getColumnModel().getColumn(0).setMaxWidth(125);
		tblAppointments.getColumnModel().getColumn(2).setMaxWidth(50);
		tblAppointments.getColumnModel().getColumn(3).setMaxWidth(50);
		TableComparatorChooser.install(tblAppointments, sortedList, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);
	}

	private class AppointmentTableFormat implements TableFormat<Appointment> {

		public int getColumnCount() {
			return 4;
		}

		public String getColumnName(int column) {
			if (column == 0)
				return "Date";
			else if (column == 1)
				return "Patient";
			else if (column == 2)
				return "Room";
			else if (column == 3)
				return "Urgent";

			throw new IllegalStateException();
		}

		public Object getColumnValue(Appointment appointment, int column) {
			if (column == 0)
				return appointment.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			else if (column == 1)
				return appointment.getPatient().guiToString();
			else if (column == 2)
				return appointment.getRoom().guiToString();
			else if (column == 3)
				return (appointment.isUrgent()) ? "YES" : "";
			throw new IllegalStateException();
		}
	}

	@SuppressWarnings("unchecked")
	private void enableButtons() {
		btnEdit.setEnabled(false);
		btnProcess.setEnabled(false);
		Appointment appointment;
		if (tblAppointments.getSelectedRow() != -1) {
			appointment = ((AdvancedTableModel<Appointment>) tblAppointments.getModel())
					.getElementAt(tblAppointments.getSelectedRow());
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

	// LANZAMIENTO DE LA EDICION DE LA CITA
	// ----------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public void launchEditAppointment() {
		if (tblAppointments.getSelectedRow() != -1)
			adminDialog.launchEditAppointment(((AdvancedTableModel<Appointment>) tblAppointments.getModel())
					.getElementAt(tblAppointments.getSelectedRow()));
	}

	@SuppressWarnings("unchecked")
	public void launchProcessAppointment() {
		if (tblAppointments.getSelectedRow() != -1)
			adminDialog.launchProcessAppointment(((AdvancedTableModel<Appointment>) tblAppointments.getModel())
					.getElementAt(tblAppointments.getSelectedRow()));
	}
	
	// LANZAMIENTO DEL PANEL ----------------------------------------------------------

	@Override
	public void setFocus() {
		btnEdit.requestFocus();
	}
	
	public void fillComboBoxes() {
        // Patient list
        patientList = new BasicEventList<Patient>();
        patientList.addAll(patientService.findAllPatient());
        if (autoCompletePatient == null)
            autoCompletePatient = AutoCompleteSupport.install(getCbPatient(), patientList, new PatientTextFilterator(), patientFormat);
        autoCompletePatient.setFilterMode(TextMatcherEditor.CONTAINS);
        // Doctor List
        doctorList = new BasicEventList<Staff>();
        doctorList.addAll(doctorService.findAllDoctors());
        if (autoCompleteDoctor == null)
            autoCompleteDoctor = AutoCompleteSupport.install(getCbDoctor(), doctorList, new StaffTextFilterator(), staffFormat);
        autoCompleteDoctor.setFilterMode(TextMatcherEditor.CONTAINS);
        // Nurse List
        nurseList = new BasicEventList<Staff>();
        nurseList.addAll(nurseService.findAllNurses());
        if (autoCompleteNurse == null)
            autoCompleteNurse = AutoCompleteSupport.install(getCbNurse(), nurseList, new StaffTextFilterator(), staffFormat);
        autoCompleteDoctor.setFilterMode(TextMatcherEditor.CONTAINS);
        // Room list
        roomList = new BasicEventList<Room>();
        roomList.addAll(roomService.findAllRooms());
        if (autoCompleteRoom == null)
            autoCompleteRoom = AutoCompleteSupport.install(getCbRoom(), roomList, new RoomTextFilterator(), roomFormat);
    }
	
	// FILTRADO
	
	private void filter() {
		
	}
	
	

}
