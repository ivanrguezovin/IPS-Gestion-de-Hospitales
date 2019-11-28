package es.uniovi.ips.hospital.ui.admin.appointment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JCalendar;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.exception.InputException;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.RoomService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyCalendar;
import es.uniovi.ips.hospital.ui.util.components.MyComboBox;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.components.MyTimePicker;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.RoomTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.StaffTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.StaffCellRenderer;
import es.uniovi.ips.hospital.util.format.PatientFormat;
import es.uniovi.ips.hospital.util.format.RoomFormat;
import es.uniovi.ips.hospital.util.format.StaffFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.JComboBox;

@Component
public class EditAppointmentPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 3489197994027411322L;
	private final JPanel contentPanel = new JPanel();
	
	@Autowired 	private AdminDialog adminDialog;
	@Autowired	private PatientService patientService;
	@Autowired	private DoctorService doctorService;
	@Autowired	private NurseService nurseService;
	@Autowired	private RoomService roomService;
	@Autowired	private AppointmentService appointmentService;
	@Autowired	private PatientFormat patientFormat;
	@Autowired	private StaffFormat staffFormat;
	@Autowired	private RoomFormat roomFormat;
	
	private List<Doctor> selectedDoctors;
	private List<Doctor> availableDoctors;
    private List<Nurse> selectedNurses;
	private LocalDateTime appointmentDateTime;
    private LocalDateTime appointmentEndTime;
	private Appointment appointment;
	private JPanel pnAppointment;
	private JPanel pnPatient;
	private JPanel pnDoctor;
	private JPanel pnInfo;
	private JButton btnShowMedicalRecord;
	private JComboBox<Patient> cbPatient;
	private EventList<Patient> patientList;
	private AutoCompleteSupport<Patient> autoCompletePatient;
	private JLabel lblContactInfo;
	private JTextField txtContactInfo;
	private JButton btnAdd;
	private JPanel pnSelectDoctor;
	private JComboBox<Staff> cbDoctor;
	private EventList<Staff> doctorList;
	private AutoCompleteSupport<Staff> autoCompleteDoctor;
    private AutoCompleteSupport<Staff> autoCompleteNurse;
	private JPanel pnShowDoctors;
    private JPanel pnRemoveDoctor;
    private JComboBox<Doctor> cbSelectedDoctors;
    private EventList<Doctor> selectedDoctorsList;
    private AutoCompleteSupport<Doctor> autoCompleteSelectedDoctor;
    private AutoCompleteSupport<Nurse> autoCompleteSelectedNurse;
    private JButton btnRemove;
	private JLabel lblDoctors;
	private JButton btnUpdate;
	private JPanel pnDateTime;
	private JPanel pnDetails;
	private JCheckBox chckbxUrgent;
	private Panel pnRoom;
	private JLabel lblRoom;
	private JComboBox<Room> cbRoom;
	private EventList<Room> roomList;
	private AutoCompleteSupport<Room> autoCompleteRoom;
    private JCalendar calendar;
	private Panel pnTimePicker;
	private Label lblTime;
    private TimePicker timePicker;
    private JButton btnSetDate;
    private Label lblEnd;
    private TimePicker timePickerEnd;
    private ActionListener setDateAction;
    private ActionListener modifyDateAction;
	private JPanel pnPatientSelect;
	private JPanel pnPatientInfo;
	private JPanel pnManageDoctor;
	private JLabel lblPatient;
	private JPanel pnNurse;
	private JPanel pnManageNurse;
	private JPanel pnShowNurses;
	private JPanel pnSelectNurse;
	private JPanel pnRemoveNurse;
	private JComboBox<Staff> cbNurse;
    private EventList<Staff> nurseList;
	private JLabel lblNurses;
	private JComboBox<Nurse> cbSelectedNurses;
    private EventList<Nurse> selectedNursesList;
	private JButton btnRemoveNurse;
	private JButton btnAddNurse;
	private JLabel lblDoctors_1;
	private JLabel lblNurses_1;

	/**
	 * Create the dpanel.
	 */
	public EditAppointmentPanel() {
        setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(700, 500));
		setMinimumSize(new Dimension(700, 500));
		this.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.add(getPnAppointment(), BorderLayout.CENTER);
		contentPanel.add(getBtnUpdate(), BorderLayout.SOUTH);
		selectedDoctors = new ArrayList<Doctor>();
        selectedNurses = new ArrayList<Nurse>();
	}

	private JPanel getPnAppointment() {
        if (pnAppointment == null) {
            pnAppointment = new MyBackPanel();
            GridBagLayout gbl_pnAppointment = new GridBagLayout();
            gbl_pnAppointment.columnWidths = new int[]{0};
            gbl_pnAppointment.rowHeights = new int[]{10, 100, 15, 250, 15, 70, 10, 70, 10};
            gbl_pnAppointment.columnWeights = new double[]{1.0};
            gbl_pnAppointment.rowWeights = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0};
            pnAppointment.setLayout(gbl_pnAppointment);
            GridBagConstraints gbc_pnPatient = new GridBagConstraints();
            gbc_pnPatient.insets = new Insets(5, 5, 5, 5);
            gbc_pnPatient.fill = GridBagConstraints.BOTH;
            gbc_pnPatient.gridx = 0;
            gbc_pnPatient.gridy = 1;
            pnAppointment.add(getPnPatient(), gbc_pnPatient);
            GridBagConstraints gbc_pnInfo = new GridBagConstraints();
            gbc_pnInfo.fill = GridBagConstraints.BOTH;
            gbc_pnInfo.gridx = 0;
            gbc_pnInfo.gridy = 3;
            pnAppointment.add(getPnInfo(), gbc_pnInfo);
            GridBagConstraints gbc_pnDoctor = new GridBagConstraints();
            gbc_pnDoctor.insets = new Insets(0, 0, 5, 0);
            gbc_pnDoctor.fill = GridBagConstraints.BOTH;
            gbc_pnDoctor.gridx = 0;
            gbc_pnDoctor.gridy = 5;
            pnAppointment.add(getPnDoctor(), gbc_pnDoctor);
            GridBagConstraints gbc_pnNurse = new GridBagConstraints();
            gbc_pnNurse.insets = new Insets(0, 0, 5, 0);
            gbc_pnNurse.fill = GridBagConstraints.BOTH;
            gbc_pnNurse.gridx = 0;
            gbc_pnNurse.gridy = 7;
            pnAppointment.add(getPnNurse(), gbc_pnNurse);
        }
        return pnAppointment;
	}
	private JPanel getPnPatient() {
        if (pnPatient == null) {
            pnPatient = new MyFrontPanel();
			pnPatient.setBorder(Designer.getBorder());
            FlowLayout flowLayout = (FlowLayout) pnPatient.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnPatient.add(getPnPatientSelect());
            pnPatient.add(getPnPatientInfo());
        }
        return pnPatient;
    }
    private JPanel getPnPatientSelect() {
    	if (pnPatientSelect == null) {
            pnPatientSelect = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnPatientSelect.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnPatientSelect.add(getLblPatient());
            pnPatientSelect.add(getCbPatient());
        }
        return pnPatientSelect;
    }
    private JPanel getPnPatientInfo() {
    	if (pnPatientInfo == null) {
    		pnPatientInfo = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnPatientInfo.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnPatientInfo.add(getBtnShowMedicalRecord());
            pnPatientInfo.add(getLblContactInfo());
            pnPatientInfo.add(getTxtContactInfo());
        }
        return pnPatientInfo;
    }
    private JPanel getPnInfo() {
        if (pnInfo == null) {
            pnInfo = new MyBackPanel();
            pnInfo.setLayout(new GridLayout(0, 2, 0, 0));
            GridBagLayout gbl_pnInfo = new GridBagLayout();
            gbl_pnInfo.columnWidths = new int[]{400, 300};
            gbl_pnInfo.rowHeights = new int[]{0};
            gbl_pnInfo.columnWeights = new double[]{1.0, Double.MIN_VALUE};
            gbl_pnInfo.rowWeights = new double[]{0.0};
            pnInfo.setLayout(gbl_pnInfo);
            GridBagConstraints gbc_pnDateTime = new GridBagConstraints();
            gbc_pnDateTime.insets = new Insets(0, 5, 5, 5);
            gbc_pnDateTime.fill = GridBagConstraints.BOTH;
            gbc_pnDateTime.gridx = 0;
            gbc_pnDateTime.gridy = 0;
            pnInfo.add(getPnDateTime(), gbc_pnDateTime);
            GridBagConstraints gbc_pnDetails = new GridBagConstraints();
            gbc_pnDetails.insets = new Insets(0, 5, 5, 5);
            gbc_pnDetails.fill = GridBagConstraints.BOTH;
            gbc_pnDetails.gridx = 1;
            gbc_pnDetails.gridy = 0;
            pnInfo.add(getPnDetails(), gbc_pnDetails);
        }
        return pnInfo;
    }
    private JPanel getPnDoctor() {
        if (pnDoctor == null) {
            pnDoctor = new MyFrontPanel();
            pnDoctor.setBorder(Designer.getBorder());
            pnDoctor.setLayout(new GridLayout(2, 1));
            pnDoctor.add(getPnManageDoctor());
            pnDoctor.add(getPnShowDoctors());
        }
        return pnDoctor;
    }    

    private JPanel getPnNurse() {
        if (pnNurse == null) {
        	pnNurse = new MyFrontPanel();
            pnNurse.setBorder(Designer.getBorder());
            pnNurse.setLayout(new GridLayout(2, 1));
            pnNurse.add(getPnManageNurse());
            pnNurse.add(getPnShowNurses());
        }
        return pnNurse;
    }
    private JPanel getPnManageDoctor() {
        if (pnManageDoctor == null) {
        	pnManageDoctor = new MyFrontPanel();
        	pnManageDoctor.setLayout(new GridLayout(1,2));
        	pnManageDoctor.add(getPnSelectDoctor());
        	pnManageDoctor.add(getPnRemoveDoctor());
        }
        return pnManageDoctor;
    }
    private JPanel getPnShowDoctors() {
        if (pnShowDoctors == null) {
            pnShowDoctors = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnShowDoctors.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnShowDoctors.add(getLblDoctors());
        }
        return pnShowDoctors;
    }
    
    private JPanel getPnManageNurse() {
        if (pnManageNurse == null) {
        	pnManageNurse = new MyFrontPanel();
        	pnManageNurse.setLayout(new GridLayout(1,2));
        	pnManageNurse.add(getPnSelectNurse());
        	pnManageNurse.add(getPnRemoveNurse());
        }
        return pnManageNurse;
    }

    private JPanel getPnShowNurses() {
        if (pnShowNurses == null) {
        	pnShowNurses = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnShowNurses.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnShowNurses.add(getLblNurses());
        }
        return pnShowNurses;
    }
    private JPanel getPnSelectDoctor() {
        if (pnSelectDoctor == null) {
            pnSelectDoctor = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnSelectDoctor.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnSelectDoctor.add(getLblDoctors_1());
            pnSelectDoctor.add(getCbDoctor());
            pnSelectDoctor.add(getBtnAdd());
        }
        return pnSelectDoctor;
    }
    private JPanel getPnRemoveDoctor() {
        if (pnRemoveDoctor == null) {
            pnRemoveDoctor = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnRemoveDoctor.getLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            pnRemoveDoctor.add(getCbSelectedDoctors());
            pnRemoveDoctor.add(getBtnRemove());
        }
        return pnRemoveDoctor;
    }

    private JPanel getPnSelectNurse() {
        if (pnSelectNurse == null) {
        	pnSelectNurse = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnSelectNurse.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnSelectNurse.add(getLblNurses_1());
            pnSelectNurse.add(getCbNurse());
            pnSelectNurse.add(getBtnAddNurse());
        }
        return pnSelectNurse;
    }

    private JPanel getPnRemoveNurse() {
        if (pnRemoveNurse == null) {
        	pnRemoveNurse = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnRemoveNurse.getLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            pnRemoveNurse.add(getCbSelectedNurses());
            pnRemoveNurse.add(getBtnRemoveNurse());
        }
        return pnRemoveNurse;
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
			cbPatient.addItemListener(itemEvent -> selectPatient());
			autoCompletePatient = null;
		}
		return cbPatient;
	}
	private JButton getBtnShowMedicalRecord() {
		if (btnShowMedicalRecord == null) {
			btnShowMedicalRecord = new JButton("Show medical record");
			btnShowMedicalRecord.setEnabled(false);
			btnShowMedicalRecord.addActionListener(actionEvent -> showMedicalRecord());
		}
		return btnShowMedicalRecord;
	}
	private JLabel getLblContactInfo() {
		if (lblContactInfo == null) {
			lblContactInfo = new JLabel("Contact info:");
		}
		return lblContactInfo;
	}
	private JTextField getTxtContactInfo() {
		if (txtContactInfo == null) {
			txtContactInfo = new JTextField(15);
			txtContactInfo.setEnabled(false);
		}
		return txtContactInfo;
	}
	private JComboBox<Staff> getCbDoctor() {
		if (cbDoctor == null) {
			cbDoctor = new MyComboBox<Staff>();
			cbDoctor.addItemListener(itemEvent -> btnAdd.setEnabled(true));
			autoCompleteDoctor = null;
			cbDoctor.setEnabled(false);
		}
		return cbDoctor;
	}

    private JComboBox<Staff> getCbNurse() {
        if (cbNurse == null) {
        	cbNurse = new MyComboBox<Staff>();
            cbNurse.addItemListener(itemEvent -> btnAddNurse.setEnabled(true));
            autoCompleteNurse = null;
            cbNurse.setEnabled(false);
        }
        return cbNurse;
    }
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(actionEvent -> addDoctor());
			btnAdd.setEnabled(false);
		}
		return btnAdd;
	}

    private JButton getBtnAddNurse() {
        if (btnAddNurse == null) {
        	btnAddNurse = new JButton("Add");
            btnAddNurse.addActionListener(actionEvent -> addNurse());
            btnAddNurse.setEnabled(false);
        }
        return btnAddNurse;
    }
	private JLabel getLblDoctors() {
		if (lblDoctors == null) {
			lblDoctors = new JLabel("");
		}
		return lblDoctors;
	}

    private JLabel getLblNurses() {
        if (lblNurses == null) {
        	lblNurses = new JLabel("");
        }
        return lblNurses;
    }
	private JComboBox<Doctor> getCbSelectedDoctors() {
		if (cbSelectedDoctors == null) {
			cbSelectedDoctors = new MyComboBox<Doctor>();
			cbSelectedDoctors.addItemListener(itemEvent -> getBtnRemove().setEnabled(true));
			autoCompleteSelectedDoctor = null;
			cbSelectedDoctors.setEnabled(false);
		}
		return cbSelectedDoctors;
	}

    private JComboBox<Nurse> getCbSelectedNurses() {
        if (cbSelectedNurses == null) {
        	cbSelectedNurses = new MyComboBox<Nurse>();
        	cbSelectedNurses.addItemListener(itemEvent -> getBtnRemoveNurse().setEnabled(true));
            autoCompleteSelectedNurse = null;
            cbSelectedNurses.setEnabled(false);
        }
        return cbSelectedNurses;
    }
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.addActionListener(actionEvent -> removeDoctor());
			btnRemove.setEnabled(false);
		}
		return btnRemove;
	}

    private JButton getBtnRemoveNurse() {
        if (btnRemoveNurse == null) {
        	btnRemoveNurse = new JButton("Remove");
            btnRemoveNurse.addActionListener(actionEvent -> removeNurse());
            btnRemoveNurse.setEnabled(false);
        }
        return btnRemoveNurse;
    }
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new MyButton("Confirm");
			btnUpdate.addActionListener(action -> modifyAppointment());
		}
		return btnUpdate;
	}
	private JPanel getPnDateTime() {
		if (pnDateTime == null) {
			pnDateTime = new JPanel();
            pnDateTime.setBorder(Designer.getBorder());
            pnDateTime.setLayout(new BorderLayout(0, 0));
			pnDateTime.add(getCalendar(), BorderLayout.CENTER);
		}
		return pnDateTime;
	}
	private JPanel getPnDetails() {
		if (pnDetails == null) {
			pnDetails = new JPanel();
            pnDetails.setBorder(Designer.getBorder());
			pnDetails.setLayout(new GridLayout(4, 1, 0, 0));
			pnDetails.add(getChckbxUrgent());
			pnDetails.add(getPnRoom());
			pnDetails.add(getPnTimePicker());
			pnDetails.add(getBtnSetDate());
		}
		return pnDetails;
	}
	private JCheckBox getChckbxUrgent() {
		if (chckbxUrgent == null) {
			chckbxUrgent = new JCheckBox("URGENT");
			chckbxUrgent.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxUrgent;
	}
	private Panel getPnRoom() {
		if (pnRoom == null) {
			pnRoom = new Panel();
			pnRoom.add(getLblRoom());
			pnRoom.add(getCbRoom());
		}
		return pnRoom;
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
	private JCalendar getCalendar() {
        if (calendar == null) {
            calendar = new MyCalendar();
        }
        return calendar;
    }
	private Panel getPnTimePicker() {
		if (pnTimePicker == null) {
			pnTimePicker = new Panel();
			pnTimePicker.add(getLblTime());
			pnTimePicker.add(getTimePicker());
            pnTimePicker.add(getLblEnd());
            pnTimePicker.add(getTimePickerEnd());
		}
		return pnTimePicker;
	}
	private Label getLblTime() {
		if (lblTime == null) {
			lblTime = new Label("Start:");
		}
		return lblTime;
	}
    private TimePicker getTimePicker() {
        if (timePicker == null) {
            timePicker = new MyTimePicker();
        }
        return timePicker;
    }
    
	private Label getLblEnd() {
		if (lblEnd == null) {
			lblEnd = new Label("End:");
		}
		return lblEnd;
	}
	
	private TimePicker getTimePickerEnd() {
		if (timePickerEnd == null) {
			timePickerEnd = new MyTimePicker();
		}
		return timePickerEnd;
	}
	private JButton getBtnSetDate() {
		if (btnSetDate == null) {
			btnSetDate = new JButton("Set date");
			setDateAction = new SetDateAction();
			modifyDateAction = new ModifyDateAction();
			btnSetDate.addActionListener(setDateAction);
		}
		return btnSetDate;
	}

    // METODOS Y CLASES DE INICIALIZACION ------------------------------------------------------------

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
		// Selected Doctors List
		selectedDoctorsList = new BasicEventList<Doctor>();
		if (autoCompleteSelectedDoctor == null)
			autoCompleteSelectedDoctor = AutoCompleteSupport.install(getCbSelectedDoctors(), selectedDoctorsList, new StaffTextFilterator(), staffFormat);
        autoCompleteSelectedDoctor.setFilterMode(TextMatcherEditor.CONTAINS);
        // Nurse List
        nurseList = new BasicEventList<Staff>();
        nurseList.addAll(nurseService.findAllNurses());
        if (autoCompleteNurse == null)
            autoCompleteNurse = AutoCompleteSupport.install(getCbNurse(), nurseList, new StaffTextFilterator(), staffFormat);
        autoCompleteDoctor.setFilterMode(TextMatcherEditor.CONTAINS);
        // Selected Nurses List
        selectedNursesList = new BasicEventList<Nurse>();
        if (autoCompleteSelectedNurse == null)
            autoCompleteSelectedNurse = AutoCompleteSupport.install(getCbSelectedNurses(), selectedNursesList, new StaffTextFilterator(), staffFormat);
        autoCompleteSelectedNurse.setFilterMode(TextMatcherEditor.CONTAINS);
		// Room list
        roomList = new BasicEventList<Room>();
        roomList.addAll(roomService.findAllRooms());
        if (autoCompleteRoom == null)
        	autoCompleteRoom = AutoCompleteSupport.install(getCbRoom(), roomList, new RoomTextFilterator(), roomFormat);
    }
    
    public void setFocus() {
        cbPatient.requestFocus();
    }
    
    private void showMedicalRecord() {
        Patient patient = (Patient) cbPatient.getSelectedItem();
        adminDialog.launchMedicalRecord(patient);
    }
    
    
    public void setAppointment(Appointment appointment) {
    	this.appointment = appointment;
    	cbPatient.setSelectedItem(appointment.getPatient());
    	txtContactInfo.setText(appointment.getContactInfo());
    	calendar.setDate(Date.from(appointment.getStartTime().toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    	chckbxUrgent.setSelected(appointment.isUrgent());
    	cbRoom.setSelectedItem(appointment.getRoom());
    	timePicker.setTime(appointment.getStartTime().toLocalTime());
    	timePickerEnd.setTime(appointment.getEndTime().toLocalTime());
    	selectedDoctors = new ArrayList<Doctor>(appointment.getDoctors());
    	selectedDoctorsList.addAll(selectedDoctors);
    	doctorList.removeAll(selectedDoctors);
		lblDoctors.setText("<html><p style=\"width:500px\">"
				+ selectedDoctors.stream().map(d -> d.guiToString()).collect(Collectors.joining(", "))
				+ "<p></html>");
    	selectedNurses = new ArrayList<Nurse>(appointment.getNurses());
    	selectedNursesList.addAll(selectedNurses);
    	nurseList.removeAll(selectedNurses);
		lblNurses.setText("<html><p style=\"width:500px\">"
				+ selectedNurses.stream().map(n -> n.guiToString()).collect(Collectors.joining(", "))
				+ "<p></html>");
		setDate();
    }
    
    private class SetDateAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setDate();
		}  	
    }
    
    private class ModifyDateAction implements ActionListener {
    	@Override
		public void actionPerformed(ActionEvent e) {
			modifyDate();
		} 	
    }
    
    // METODOS DE EJECUCION -------------------------------------------------------------------------
    
    // Carga todos los datos introducidos y crea la cita
    private void modifyAppointment() {
    	try {
    		// Comprobación de que tenemos todos los campos necesarios
    		checkPatient();
    		checkContactInfo();
    		checkDate();
    		checkTime();
    		checkRoom();
    		// Comprobación de que los doctores están disponibles, aviso en caso contrario
    		if (JOptionPane.showConfirmDialog(this, "This will modify the selected appointment, are you sure?") != JOptionPane.YES_OPTION)
    			return;
    		if (isThereUnavailableDoctor())
	    		if (JOptionPane.showConfirmDialog(this, "One of the selected doctors doesn't work on the specified time, are you sure?") != JOptionPane.YES_OPTION)
	    			return;
    		// Creación de la cita
    		Set<Doctor> doctorsSet = new HashSet<Doctor>(selectedDoctors);
    		Set<Nurse> nursesSet = new HashSet<Nurse>(selectedNurses);
    		appointment.setPatient((Patient) cbPatient.getSelectedItem());
    		appointment.setStartTime(appointmentDateTime);
    		appointment.setEndTime(appointmentEndTime);
    		appointment.setUrgent(chckbxUrgent.isSelected());
    		appointment.setContactInfo(txtContactInfo.getText());
    		appointment.setRoom((Room) cbRoom.getSelectedItem());
    		appointment.setDoctors(doctorsSet);
    		appointment.setNurses(nursesSet);
    		appointmentService.updateAppointment(appointment);
    		// Envío del email si es urgente y se ha seleccionado algún doctor
    		if (chckbxUrgent.isSelected() && !selectedDoctors.isEmpty())
    			appointment.sendEmail();
    		// Notificación de creación satisfactoria y restauración de la ventana
    		modifyDate();
    		JOptionPane.showMessageDialog(this, "The appointment was edited succesfully");
    	} catch(Exception ie) {
    		JOptionPane.showMessageDialog(this, ie.getMessage());
    	}
    }

	// Añade el doctor a la lista de doctores de la cita y lo sustrae de los posibles doctores
    private void addDoctor() {
    	if (getCbDoctor().getSelectedItem() != null) {
			Staff doctor = (Staff) getCbDoctor().getSelectedItem();
			selectedDoctors.add((Doctor) doctor);
			selectedDoctorsList.add((Doctor) doctor);
			doctorList.remove(doctor);
			cbDoctor.setSelectedItem(null);
			lblDoctors.setText("<html><p style=\"width:500px\">"
					+ selectedDoctors.stream().map(d -> d.guiToString()).collect(Collectors.joining(", "))
					+ "<p></html>");
			cbSelectedDoctors.setEnabled(true);
		}
    }

    // Añade el enfermero a la lista de enfermeros de la cita y lo sustrae de los posibles
    private void addNurse() {
        if (getCbNurse().getSelectedItem() != null) {
            Staff nurse = (Staff) getCbNurse().getSelectedItem();
            selectedNurses.add((Nurse) nurse);
            selectedNursesList.add((Nurse) nurse);
            nurseList.remove(nurse);
            cbNurse.setSelectedItem(null);
            lblNurses.setText("<html><p style=\"width:500px\">"
                    + selectedNurses.stream().map(n -> n.guiToString()).collect(Collectors.joining(", "))
                    + "<p></html>");
            cbSelectedNurses.setEnabled(true);
        }
    }

	// Elimina el doctor de la lista de doctores de la cita y lo añade a la de los posibles doctores
    private void removeDoctor() {
    	if (getCbSelectedDoctors().getSelectedItem() != null) {
			Doctor doctor = (Doctor) getCbSelectedDoctors().getSelectedItem();
			selectedDoctors.remove(doctor);
			selectedDoctorsList.remove(doctor);
			doctorList.add(doctor);
			cbSelectedDoctors.setSelectedItem(null);
			lblDoctors.setText("<html><p style=\"width:500px\">"
					+ selectedDoctors.stream().map(d -> d.guiToString()).collect(Collectors.joining(", "))
					+ "<p></html>");
			if (selectedDoctors.isEmpty()) {
				cbSelectedDoctors.setEnabled(false);
				btnRemove.setEnabled(false);
			}
		}
    }
    
    private void removeNurse() {
        if (getCbSelectedNurses().getSelectedItem() != null) {
            Nurse nurse = (Nurse) getCbSelectedNurses().getSelectedItem();
            selectedNurses.remove(nurse);
            selectedNursesList.remove((Nurse) nurse);
            nurseList.add(nurse);
            cbSelectedNurses.setSelectedItem(null);
            lblNurses.setText("<html><p style=\"width:500px\">"
                    + selectedNurses.stream().map(n -> n.guiToString()).collect(Collectors.joining(", "))
                    + "<p></html>");
            if (selectedNurses.isEmpty()) {
                cbSelectedNurses.setEnabled(false);
                btnRemoveNurse.setEnabled(false);
            }
        }
    }
    
    // Selecciona el paciente y carga sus datos
	private void selectPatient() {
		if (getCbPatient().getSelectedItem() != null) {
			txtContactInfo.setText(((Patient) cbPatient.getSelectedItem()).getEmail());
			txtContactInfo.setEnabled(true);
			btnShowMedicalRecord.setEnabled(true);
		} else {
			txtContactInfo.setText("");
			txtContactInfo.setEnabled(false);
			btnShowMedicalRecord.setEnabled(false);
		}
	}
	
	// Establece el horario de la cita y carga la lista de doctores
	private void setDate() {
		try { 
			// Generamos la fecha de la cita si es posible
			checkDate();
			checkTime();
            checkRoom();
			LocalDate date = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			appointmentDateTime = LocalDateTime.of(date, timePicker.getTime());
            if (timePicker.getTime().isBefore(timePickerEnd.getTime()))
            	appointmentEndTime = LocalDateTime.of(date, timePickerEnd.getTime());
            else
            	appointmentEndTime = LocalDateTime.of(date.plusDays(1), timePickerEnd.getTime());
			// Desactivamos los elementos de selección de fecha y cambiamos el botón
			setAppointmentComponentsEnabled(false);
			swapButton();
			// Obtenemos la lista de doctores disponibles para esa cita y activamos su panel
            availableDoctors = doctorService.findAvailableDoctors(appointmentDateTime);
            List<Doctor> allDoctors = doctorService.findAllDoctors();
            List<Nurse>	availableNurses = nurseService.findAvailableNurses(appointmentDateTime);
            doctorList.clear();
            nurseList.clear();
            doctorList.addAll(allDoctors);
            nurseList.addAll(availableNurses);
            cbDoctor.setRenderer(new StaffCellRenderer(availableDoctors));
            cbNurse.setRenderer(new StaffCellRenderer());
            setStaffComponentsEnabled(true);
		} catch (InputException ie) {
			JOptionPane.showMessageDialog(this, ie.getMessage());
		}		
	}

	private void modifyDate() {
		appointmentDateTime = null;
        appointmentEndTime = null;
		// Reiniciamos los doctores seleccionados, en otra hora pueden no trabajar
		selectedDoctors.clear();
		lblDoctors.setText("");
        selectedNurses.clear();
        lblNurses.setText("");
		// Reactivamos los elementos de selección de cita y cambiamos el botón
		setAppointmentComponentsEnabled(true);
		swapButton();
		// Desactivamos el panel de doctores
		setStaffComponentsEnabled(false);
	} 
	
	private void setAppointmentComponentsEnabled(boolean b) {
		calendar.setEnabled(b);
		timePicker.setEnabled(b);
		timePickerEnd.setEnabled(b);
		chckbxUrgent.setEnabled(b);
		lblRoom.setEnabled(b);
		cbRoom.setEnabled(b);
	}
	
	private void setStaffComponentsEnabled(boolean b) {
        cbDoctor.setEnabled(b);
        cbNurse.setEnabled(b);
        btnAdd.setEnabled(b);
        btnAddNurse.setEnabled(b);
        if (!selectedDoctors.isEmpty()) {
            cbSelectedDoctors.setEnabled(b);
            btnRemove.setEnabled(b);
        }
        if (!selectedNurses.isEmpty()) {
            cbSelectedNurses.setEnabled(b);
            btnRemoveNurse.setEnabled(b);
        }
    }
	
	private void swapButton() {
		if (btnSetDate.getActionListeners()[0] == setDateAction) {
			btnSetDate.removeActionListener(setDateAction);
			btnSetDate.addActionListener(modifyDateAction);
			btnSetDate.setText("Modify date");
		} else {
			btnSetDate.removeActionListener(modifyDateAction);
			btnSetDate.addActionListener(setDateAction);
			btnSetDate.setText("Set date");
		}
	}
	
	// METODOS DE COMPROBACION DE LA ENTRADA DE DATOS ----------------------------------
	
	private void checkPatient() throws InputException {
		if (cbPatient.getSelectedItem() == null)
			throw new InputException("You must select a patient for the appointment");
	}
	
	private void checkContactInfo() throws InputException {
		if (txtContactInfo.getText().trim().isEmpty())
			throw new InputException("The appointment needs some contact information");
	}
	
	private void checkDate() throws InputException {
		if (calendar.getDate() == null)
			throw new InputException("You must select a date for the appointment");
	}

	private void checkTime() throws InputException {
        if (timePicker.getTime() == null)
            throw new InputException("You must select a start time for the appointment");
        if (timePicker.getTime() == null)
            throw new InputException("You must select an end time for the appointment");
	}
	
	private void checkRoom() throws InputException {
		if (cbRoom.getSelectedItem() == null) {
			throw new InputException("You must select a room for the appointment");
		}
	}
    
    private boolean isThereUnavailableDoctor() {
		for (Staff doc: selectedDoctors)
			if (!availableDoctors.contains(doc))
				return true;
		return false;
	}
	private JLabel getLblDoctors_1() {
		if (lblDoctors_1 == null) {
			lblDoctors_1 = new JLabel("Doctors:");
		}
		return lblDoctors_1;
	}
	private JLabel getLblNurses_1() {
		if (lblNurses_1 == null) {
			lblNurses_1 = new JLabel("Nurses:");
		}
		return lblNurses_1;
	}
}
