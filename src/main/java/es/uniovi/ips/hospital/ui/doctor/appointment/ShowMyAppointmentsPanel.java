package es.uniovi.ips.hospital.ui.doctor.appointment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

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
import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.service.RoomService;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyComboBox;
import es.uniovi.ips.hospital.ui.util.components.MyDateChooser;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.filter.AppointmentTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.RoomTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.AppointmentTableCellRenderer;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;
import es.uniovi.ips.hospital.util.format.RoomFormat;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

@Component
public class ShowMyAppointmentsPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 5888443942953898676L;
	private final JPanel contentPanel = new JPanel();

	@Autowired
	private DoctorDialog doctorDialog;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomFormat roomFormat;

	private JPanel pnFilters;
	private JPanel pnTable;
	private JTextField txtSearch;
	private TextMatcherEditor<Appointment> textMatcherEditor;
	private FilterList<Appointment> filterList;
	private JScrollPane spTable;
	private JTable tblAppointments;
	private JLabel lblFilter;
	private JPanel pnFrom;
	private JPanel pnTo;
	private JPanel pnRoom;
	private JPanel pnCheckList;
	private JSeparator separatorNurseRoom;
	private JSeparator separatorRoomCheckList;
	private JLabel lblFrom;
	private JDateChooser fromChooser;
	private JLabel lblTo;
	private JDateChooser toChooser;
	private JLabel lblRoom;
	private JComboBox<Room> cbRoom;
	private JCheckBox chckbxJustUrgents;
	private JCheckBox chckbxHidePast;
	private AutoCompleteSupport<Room> autoCompleteRoom;
	private EventList<Appointment> appointmentList;
	private EventList<Appointment> allAppointmentList;
	private EventList<Room> roomList;
	private JPanel pnBottom;
	private JButton btnProcess;

	/**
	 * Create the dialog.
	 */
	public ShowMyAppointmentsPanel() {
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
			pnFilters.add(Box.createRigidArea(new Dimension(10, 10)));
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
			fromChooser = new MyDateChooser();
			fromChooser.addPropertyChangeListener(e -> filter());
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
			toChooser = new MyDateChooser();
			toChooser.addPropertyChangeListener(e -> filter());
		}
		return toChooser;
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
			pnRoom.add(Box.createRigidArea(new Dimension(10, 10)));
			pnRoom.add(getLblRoom());
			pnRoom.add(getCbRoom());
			pnRoom.add(Box.createRigidArea(new Dimension(10, 10)));
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
			pnCheckList.add(Box.createRigidArea(new Dimension(10, 10)));
			pnCheckList.add(getChckbxJustUrgents());
			pnCheckList.add(getChckbxHidePast());
		}
		return pnCheckList;
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
			cbRoom.addItemListener(itemEvent -> filter());
			autoCompleteRoom = null;
		}
		return cbRoom;
	}

	private JCheckBox getChckbxJustUrgents() {
		if (chckbxJustUrgents == null) {
			chckbxJustUrgents = new JCheckBox("Only urgents");
			chckbxJustUrgents.addItemListener(e -> filter());
		}
		return chckbxJustUrgents;
	}

	private JCheckBox getChckbxHidePast() {
		if (chckbxHidePast == null) {
			chckbxHidePast = new JCheckBox("Hide past");
			chckbxHidePast.addItemListener(e -> filter());
		}
		return chckbxHidePast;
	}

	private JPanel getPnTable() {
		if (pnTable == null) {
			pnTable = new MyFrontPanel();
			pnTable.setLayout(new BorderLayout(0, 0));
			pnTable.setBorder(Designer.getBorder());
			pnTable.add(getTxtSearch(), BorderLayout.NORTH);
			pnTable.add(getSpTable(), BorderLayout.CENTER);
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
			pnBottom.setLayout(new BorderLayout(0, 0));
			pnBottom.add(getBtnProcess());
		}
		return pnBottom;
	}

	private JButton getBtnProcess() {
		if (btnProcess == null) {
			btnProcess = new MyButton("Process");
			btnProcess.setEnabled(false);
			btnProcess.addActionListener(actionEvent -> launchProcessAppointment());
		}
		return btnProcess;
	}

	// LANZAMIENTO DE LA EDICION DE LA CITA
	// ----------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public void launchProcessAppointment() {
		if (tblAppointments.getSelectedRow() != -1)
			doctorDialog.launchFillAppointment(((AdvancedTableModel<Appointment>) tblAppointments.getModel())
					.getElementAt(tblAppointments.getSelectedRow()));
	}
	
	private void enableButtons() {
		btnProcess.setEnabled(false);
		if (tblAppointments.getSelectedRow() != -1) {
			btnProcess.setEnabled(true);
		}

	}

	// FORMAT Y DATOS DE LA TABLA
	// ---------------------------------------------------------------------------------

	public void showAppointments(Doctor myself) {
		allAppointmentList = new BasicEventList<Appointment>();
		allAppointmentList.addAll(appointmentService.findAllByDoctor(myself));
		appointmentList = new BasicEventList<Appointment>();
		appointmentList.addAll(allAppointmentList);
		SortedList<Appointment> sortedList = new SortedList<Appointment>(appointmentList, new AppointmentComparator());
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

	private class ListReloader extends FocusAdapter {

		public void focusGained(FocusEvent arg0) {
			filterList.clear();
			filterList.addAll(appointmentService.findAllAppointments());
		}
	}

	// LANZAMIENTO DEL PANEL
	// ----------------------------------------------------------

	@Override
	public void setFocus() {
		fromChooser.requestFocus();
	}

	public void fillComboBoxes() {
		// Room list
		roomList = new BasicEventList<Room>();
		roomList.addAll(roomService.findAllRooms());
		if (autoCompleteRoom == null)
			autoCompleteRoom = AutoCompleteSupport.install(getCbRoom(), roomList, new RoomTextFilterator(), roomFormat);
	}

	// FILTRADO

	private void filter() {
		tblAppointments.clearSelection();
		appointmentList.clear();
		appointmentList.addAll(allAppointmentList);
		if (fromChooser.getDate() != null)
			filterFrom();
		if (toChooser.getDate() != null)
			filterTo();
		if (cbRoom.getSelectedItem() != null)
			filterRoom();
		if (chckbxJustUrgents.isSelected())
			filterNotUrgent();
		if (chckbxHidePast.isSelected())
			filterPast();
	}

	private void filterFrom() {
		EventList<Appointment> filteredAppointmentList = new BasicEventList<Appointment>();
		filteredAppointmentList.addAll(appointmentList);
		appointmentList.clear();
		appointmentList.addAll(filteredAppointmentList.stream().filter(x -> checkFrom(x)).collect(Collectors.toList()));
	}

	private void filterTo() {
		EventList<Appointment> filteredAppointmentList = new BasicEventList<Appointment>();
		filteredAppointmentList.addAll(appointmentList);
		appointmentList.clear();
		appointmentList.addAll(filteredAppointmentList.stream().filter(x -> checkTo(x)).collect(Collectors.toList()));
	}

	private void filterRoom() {
		EventList<Appointment> filteredAppointmentList = new BasicEventList<Appointment>();
		filteredAppointmentList.addAll(appointmentList);
		appointmentList.clear();
		appointmentList.addAll(filteredAppointmentList.stream().filter(x -> checkRoom(x)).collect(Collectors.toList()));
	}

	private void filterNotUrgent() {
		EventList<Appointment> filteredAppointmentList = new BasicEventList<Appointment>();
		filteredAppointmentList.addAll(appointmentList);
		appointmentList.clear();
		appointmentList.addAll(filteredAppointmentList.stream().filter(x -> x.isUrgent()).collect(Collectors.toList()));
	}

	private void filterPast() {
		EventList<Appointment> filteredAppointmentList = new BasicEventList<Appointment>();
		filteredAppointmentList.addAll(appointmentList);
		appointmentList.clear();
		appointmentList.addAll(filteredAppointmentList.stream()
				.filter(x -> x.getStartTime().isAfter(LocalDateTime.now())).collect(Collectors.toList()));
	}

	private boolean checkFrom(Appointment x) {
		LocalDate from = fromChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date = x.getStartTime().toLocalDate();
		return from.isBefore(date);
	}

	private boolean checkTo(Appointment x) {
		LocalDate to = toChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date = x.getStartTime().toLocalDate();
		return to.isAfter(date);
	}

	private boolean checkRoom(Appointment x) {
		return x.getRoom().equals(cbRoom.getSelectedItem());
	}

}
