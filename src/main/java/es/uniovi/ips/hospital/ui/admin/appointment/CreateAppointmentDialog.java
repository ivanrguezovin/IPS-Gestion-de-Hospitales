package es.uniovi.ips.hospital.ui.admin.appointment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JCalendar;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.exception.InputException;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.RoomService;
import es.uniovi.ips.hospital.ui.common.MedicalRecordDialog;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.RoomTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.StaffTextFilterator;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.JComboBox;

@Component
public class CreateAppointmentDialog extends JDialog {

	private static final long serialVersionUID = 8434535298528019736L;
	
	@Autowired 	private MedicalRecordDialog medicalRecordDialog;
	@Autowired	private PatientService patientService;
	@Autowired	private DoctorService doctorService;
	@Autowired	private RoomService roomService;
	@Autowired	private PatientFormat patientFormat;
	@Autowired	private StaffFormat staffFormat;
	@Autowired	private RoomFormat roomFormat;
	
	private Set<Staff> selectedDoctors;
	private LocalDateTime appointmentDateTime;
	
	private final JPanel contentPanel = new JPanel();
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
	private JPanel pnShowDoctors;
	private JLabel lblDoctors;
	private JPanel pnSouth;
	private JButton btnCreate;
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
    private ActionListener setDateAction;
    private ActionListener modifyDateAction;

	/**
	 * Create the dialog.
	 */
	public CreateAppointmentDialog() {
		setModal(true);
		setPreferredSize(new Dimension(700, 500));
		setMinimumSize(new Dimension(700, 500));
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnAppointment(), BorderLayout.CENTER);
		contentPanel.add(getPnSouth(), BorderLayout.SOUTH);
		selectedDoctors = new HashSet<Staff>();
	}

	private JPanel getPnAppointment() {
		if (pnAppointment == null) {
			pnAppointment = new JPanel();
			GridBagLayout gbl_pnAppointment = new GridBagLayout();
			gbl_pnAppointment.columnWidths = new int[]{0, 0};
			gbl_pnAppointment.rowHeights = new int[]{50,100,100};
			gbl_pnAppointment.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_pnAppointment.rowWeights = new double[]{0.0,1.0,1.0};
			pnAppointment.setLayout(gbl_pnAppointment);
			GridBagConstraints gbc_pnPatient = new GridBagConstraints();
			gbc_pnPatient.insets = new Insets(0, 0, 5, 0);
			gbc_pnPatient.fill = GridBagConstraints.BOTH;
			gbc_pnPatient.gridx = 0;
			gbc_pnPatient.gridy = 0;
			pnAppointment.add(getPnPatient(), gbc_pnPatient);
			GridBagConstraints gbc_pnInfo = new GridBagConstraints();
			gbc_pnInfo.fill = GridBagConstraints.BOTH;
			gbc_pnInfo.gridx = 0;
			gbc_pnInfo.gridy = 1;
			pnAppointment.add(getPnInfo(), gbc_pnInfo);
			GridBagConstraints gbc_pnDoctor = new GridBagConstraints();
			gbc_pnDoctor.insets = new Insets(0, 0, 5, 0);
			gbc_pnDoctor.fill = GridBagConstraints.BOTH;
			gbc_pnDoctor.gridx = 0;
			gbc_pnDoctor.gridy = 2;
			pnAppointment.add(getPnDoctor(), gbc_pnDoctor);
		}
		return pnAppointment;
	}
	private JPanel getPnPatient() {
		if (pnPatient == null) {
			pnPatient = new JPanel();
			pnPatient.setBorder(new TitledBorder(null, "Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			FlowLayout flowLayout = (FlowLayout) pnPatient.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnPatient.add(getCbPatient());
			pnPatient.add(getBtnShowMedicalRecord());
			pnPatient.add(getLblContactInfo());
			pnPatient.add(getTxtContactInfo());
		}
		return pnPatient;
	}
	private JPanel getPnDoctor() {
		if (pnDoctor == null) {
			pnDoctor = new JPanel();
			pnDoctor.setBorder(new TitledBorder(null, "Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnDoctor.setLayout(new BorderLayout(0, 0));
			pnDoctor.add(getPnSelectDoctor(), BorderLayout.NORTH);
			pnDoctor.add(getPnShowDoctors());
		}
		return pnDoctor;
	}
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBorder(new TitledBorder(null, "Appointment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnInfo.setLayout(new GridLayout(0, 2, 0, 0));
			pnInfo.add(getPnDateTime());
			pnInfo.add(getPnDetails());
		}
		return pnInfo;
	}
	private JComboBox<Patient> getCbPatient() {
		if (cbPatient == null) {
			cbPatient = new JComboBox<Patient>();
			cbPatient.addItemListener(itemEvent -> selectPatient());
			autoCompletePatient = null;
		}
		return cbPatient;
	}
	private JButton getBtnShowMedicalRecord() {
		if (btnShowMedicalRecord == null) {
			btnShowMedicalRecord = new JButton("Show medical record");
			btnShowMedicalRecord.setEnabled(false);
			btnShowMedicalRecord.addActionListener(actionEvent -> medicalRecordDialog.showHistoryOf((Patient) cbPatient.getSelectedItem()));
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
	private JPanel getPnSelectDoctor() {
		if (pnSelectDoctor == null) {
			pnSelectDoctor = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnSelectDoctor.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnSelectDoctor.add(getCbDoctor());
			pnSelectDoctor.add(getBtnAdd());
		}
		return pnSelectDoctor;
	}
	private JComboBox<Staff> getCbDoctor() {
		if (cbDoctor == null) {
			cbDoctor = new JComboBox<Staff>();
			cbDoctor.addItemListener(itemEvent -> btnAdd.setEnabled(true));
			autoCompleteDoctor = null;
			cbDoctor.setEnabled(false);
		}
		return cbDoctor;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setEnabled(false);
			btnAdd.addActionListener(actionEvent -> addDoctor());
			btnAdd.setEnabled(false);
		}
		return btnAdd;
	}
	private JPanel getPnShowDoctors() {
		if (pnShowDoctors == null) {
			pnShowDoctors = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnShowDoctors.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnShowDoctors.add(getLblDoctors());
		}
		return pnShowDoctors;
	}
	private JLabel getLblDoctors() {
		if (lblDoctors == null) {
			lblDoctors = new JLabel("");
		}
		return lblDoctors;
	}
	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnSouth.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnSouth.add(getBtnCreate());
		}
		return pnSouth;
	}
	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton("Create");
		}
		return btnCreate;
	}
	private JPanel getPnDateTime() {
		if (pnDateTime == null) {
			pnDateTime = new JPanel();
			pnDateTime.setLayout(new BorderLayout(0, 0));
			pnDateTime.add(getCalendar(), BorderLayout.CENTER);
		}
		return pnDateTime;
	}
	private JPanel getPnDetails() {
		if (pnDetails == null) {
			pnDetails = new JPanel();
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
			cbRoom = new JComboBox<Room>();
			autoCompleteRoom = null;
		}
		return cbRoom;
	}
    private JCalendar getCalendar() {
        if (calendar == null) {
            calendar = new JCalendar();
            calendar.setMinSelectableDate(new Date());
        }
        return calendar;
    }
	private Panel getPnTimePicker() {
		if (pnTimePicker == null) {
			pnTimePicker = new Panel();
			pnTimePicker.add(getLblTime());
			pnTimePicker.add(getTimePicker());
		}
		return pnTimePicker;
	}
	private Label getLblTime() {
		if (lblTime == null) {
			lblTime = new Label("Time:");
		}
		return lblTime;
	}
    private TimePicker getTimePicker() {
        if (timePicker == null) {
            timePicker = new TimePicker();
        }
        return timePicker;
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
        // Doctor List
        doctorList = new BasicEventList<Staff>();
        doctorList.addAll(doctorService.findAllDoctors());
        if (autoCompleteDoctor == null)
        	autoCompleteDoctor = AutoCompleteSupport.install(getCbDoctor(), doctorList, new StaffTextFilterator(), staffFormat);
		autoCompleteDoctor.setFilterMode(TextMatcherEditor.CONTAINS);
        // Patient list
        patientList = new BasicEventList<Patient>();
        patientList.addAll(patientService.findAllPatient());
        if (autoCompletePatient == null)
        	autoCompletePatient = AutoCompleteSupport.install(getCbPatient(), patientList, new PatientTextFilterator(), patientFormat);
        autoCompletePatient.setFilterMode(TextMatcherEditor.CONTAINS);
        // Room list
        roomList = new BasicEventList<Room>();
        roomList.addAll(roomService.findAllRooms());
        if (autoCompleteRoom == null)
        	autoCompleteRoom = AutoCompleteSupport.install(getCbRoom(), roomList, new RoomTextFilterator(), roomFormat);
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
    
    private void addDoctor() {
    	Staff doctor = (Staff) getCbDoctor().getSelectedItem();
    	selectedDoctors.add(doctor);
    	doctorList.remove(doctor);
    	cbDoctor.setSelectedItem(null);
    	lblDoctors.setText("<html><p style=\"width:500px\">" + selectedDoctors.stream().map(d -> d.guiToString()).collect(Collectors.joining(", ")) + "<p></html>");
    }
    
	private void selectPatient() {
		txtContactInfo.setText(((Patient) cbPatient.getSelectedItem()).getEmail());
		txtContactInfo.setEnabled(true);
		btnShowMedicalRecord.setEnabled(true);
	}
	
	private void setDate() {
		try { 
			// Generamos la fecha de la cita si es posible
			checkDate();
			checkTime();
			LocalDate date = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			appointmentDateTime = LocalDateTime.of(date, timePicker.getTime());
			// Desactivamos los elementos de selección de fecha y cambiamos el botón
			setAppointmentComponentsEnabled(false);
			swapButton();
			// Obtenemos la lista de doctores disponibles para esa cita y activamos su panel
			List<Doctor> availableDoctors = doctorService.findAvailableDoctors(appointmentDateTime);
			doctorList.clear();
			doctorList.addAll(availableDoctors);
			//doctorList.removeIf(doctor -> !availableDoctors.contains(doctor));
			setDoctorComponentsEnabled(true);
		} catch (InputException ie) {
			JOptionPane.showMessageDialog(this, ie.getMessage());
		}		
	}

	private void modifyDate() {
		appointmentDateTime = null;
		// Reiniciamos los doctores seleccionados, en otra hora pueden no trabajar
		selectedDoctors = new HashSet<Staff>();
		lblDoctors.setText("");
		// Reactivamos los elementos de selección de cita y cambiamos el botón
		setAppointmentComponentsEnabled(true);
		swapButton();
		// Desactivamos el panel de doctores
		setDoctorComponentsEnabled(false);
	} 
	
	private void setAppointmentComponentsEnabled(boolean b) {
		calendar.setEnabled(b);
		timePicker.setEnabled(b);
		chckbxUrgent.setEnabled(b);
		lblRoom.setEnabled(b);
		cbRoom.setEnabled(b);
	}
	
	private void setDoctorComponentsEnabled(boolean b) {
		cbDoctor.setEnabled(b);
		btnAdd.setEnabled(b);
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
	
	private void checkDate() throws InputException {
		if (calendar.getDate() == null)
			throw new InputException("You must select a date for the appointment");
	}

	private void checkTime() throws InputException {
		if (timePicker.getTime() == null)
			throw new InputException("You must select a time for the appointment");
	}
	
}
