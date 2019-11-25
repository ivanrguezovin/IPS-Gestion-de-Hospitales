package es.uniovi.ips.hospital.ui.admin.appointment;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JCalendar;
import es.uniovi.ips.hospital.domain.*;
import es.uniovi.ips.hospital.exception.InputException;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.RoomService;
import es.uniovi.ips.hospital.ui.common.MedicalRecordDialogWithoutPrescription;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyCheckBox;
import es.uniovi.ips.hospital.ui.util.components.MyComboBox;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.RoomTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.StaffTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.StaffCellRenderer;
import es.uniovi.ips.hospital.util.format.PatientFormat;
import es.uniovi.ips.hospital.util.format.RoomFormat;
import es.uniovi.ips.hospital.util.format.StaffFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CreateAppointmentPanel extends JPanel implements Shiftable {

    private static final long serialVersionUID = 8434535298528019736L;
    private final JPanel contentPanel = new JPanel();
    
    @Autowired
    private MedicalRecordDialogWithoutPrescription medicalRecordDialogWithoutPrescription;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientFormat patientFormat;
    @Autowired
    private StaffFormat staffFormat;
    @Autowired
    private RoomFormat roomFormat;
    
    private List<Doctor> selectedDoctors;
    private List<Doctor> availableDoctors;
    private LocalDateTime appointmentDateTime;
    private LocalDateTime appointmentEndTime;
    private JPanel pnAppointment;
    private JPanel pnPatient;
    private JPanel pnDoctor;
    private JPanel pnInfo;
    private JPanel pnPatientSelect;
    private JPanel pnPatientInfo;
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
    private JPanel pnRemoveDoctor;
    private JComboBox<Doctor> cbSelectedDoctors;
    private EventList<Doctor> selectedDoctorsList;
    private AutoCompleteSupport<Doctor> autoCompleteSelectedDoctor;
    private JButton btnRemove;
    private JLabel lblDoctors;
    private JButton btnCreate;
    private JPanel pnDateTime;
    private JPanel pnDetails;
    private JCheckBox chckbxUrgent;
    private JPanel pnRoom;
    private JLabel lblRoom;
    private JComboBox<Room> cbRoom;
    private EventList<Room> roomList;
    private AutoCompleteSupport<Room> autoCompleteRoom;
    private JCalendar calendar;
    private JPanel pnTimePicker;
    private JLabel lblTime;
    private TimePicker timePicker;
    private JLabel lblEnd;
    private TimePicker timePickerEnd;
    private JButton btnSetDate;
    private ActionListener setDateAction;
    private ActionListener modifyDateAction;
    private JLabel lblPatient;
	private JPanel pnManageDoctor;

    /**
     * Create the dialog.
     */
    public CreateAppointmentPanel() {
        setBounds(100, 100, 650, 700);
        setPreferredSize(new Dimension(650, 700));
        setMinimumSize(new Dimension(650, 700));
        this.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getPnAppointment(), BorderLayout.CENTER);
        contentPanel.add(getBtnCreate(), BorderLayout.SOUTH);
        selectedDoctors = new ArrayList<Doctor>();
    }

    private JPanel getPnAppointment() {
        if (pnAppointment == null) {
            pnAppointment = new JPanel();
            GridBagLayout gbl_pnAppointment = new GridBagLayout();
            gbl_pnAppointment.columnWidths = new int[]{0};
            gbl_pnAppointment.rowHeights = new int[]{10, 100, 15, 250, 15, 150, 10};
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
            pnInfo = new JPanel();
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

    private JPanel getPnSelectDoctor() {
        if (pnSelectDoctor == null) {
            pnSelectDoctor = new MyFrontPanel();
            FlowLayout flowLayout = (FlowLayout) pnSelectDoctor.getLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
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

    private JButton getBtnAdd() {
        if (btnAdd == null) {
            btnAdd = new JButton("Add");
            btnAdd.addActionListener(actionEvent -> addDoctor());
            btnAdd.setEnabled(false);
        }
        return btnAdd;
    }

    private JLabel getLblDoctors() {
        if (lblDoctors == null) {
            lblDoctors = new JLabel("");
        }
        return lblDoctors;
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

    private JButton getBtnRemove() {
        if (btnRemove == null) {
            btnRemove = new JButton("Remove");
            btnRemove.addActionListener(actionEvent -> removeDoctor());
            btnRemove.setEnabled(false);
        }
        return btnRemove;
    }

    private JButton getBtnCreate() {
        if (btnCreate == null) {
            btnCreate = new JButton("Create");
            btnCreate.setBackground(PaletteFactory.getHighlighter());
            btnCreate.setFont(new Font("Tahoma", Font.BOLD, 20));
            btnCreate.addActionListener(action -> createAppointment());
        }
        return btnCreate;
    }

    private JPanel getPnDateTime() {
        if (pnDateTime == null) {
            pnDateTime = new MyFrontPanel();
            pnDateTime.setBorder(Designer.getBorder());
            pnDateTime.setLayout(new BorderLayout(0, 0));
            pnDateTime.add(getCalendar(), BorderLayout.CENTER);
        }
        return pnDateTime;
    }

    private JPanel getPnDetails() {
        if (pnDetails == null) {
            pnDetails = new MyFrontPanel();
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
            chckbxUrgent = new MyCheckBox("URGENT");
            chckbxUrgent.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return chckbxUrgent;
    }

    private JPanel getPnRoom() {
        if (pnRoom == null) {
            pnRoom = new MyFrontPanel();
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

    @SuppressWarnings("rawtypes")
	private JCalendar getCalendar() {
        if (calendar == null) {
            calendar = new JCalendar();
            calendar.setMinSelectableDate(new Date());
            calendar.setForeground(PaletteFactory.getLighter());
            ((JComboBox) calendar.getMonthChooser().getComboBox()).setUI(new MyComboBox().getUI());
        }
        return calendar;
    }

    private JPanel getPnTimePicker() {
        if (pnTimePicker == null) {
            pnTimePicker = new MyFrontPanel();
            pnTimePicker.add(getLblTime());
            pnTimePicker.add(getTimePicker());
            pnTimePicker.add(getLblEnd());
            pnTimePicker.add(getTimePickerEnd());
        }
        return pnTimePicker;
    }

    private JLabel getLblTime() {
        if (lblTime == null) {
            lblTime = new JLabel("Start:");
        }
        return lblTime;
    }

    private TimePicker getTimePicker() {
        if (timePicker == null) {
            timePicker = new TimePicker();
        }
        return timePicker;
    }
    
	private JLabel getLblEnd() {
		if (lblEnd == null) {
			lblEnd = new JLabel("End:");
		}
		return lblEnd;
	}
	
	private TimePicker getTimePickerEnd() {
		if (timePickerEnd == null) {
			timePickerEnd = new TimePicker();
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
        System.out.println(patient);
        medicalRecordDialogWithoutPrescription.showHistoryOf(patient);
    }

    // Carga todos los datos introducidos y crea la cita
    private void createAppointment() {
        try {
            // Comprobación de que tenemos todos los campos necesarios
            checkPatient();
            checkContactInfo();
            checkDate();
            checkTime();
            checkRoom();
            // Comprobación de que los doctores están disponibles, aviso en caso contrario
            if (isThereUnavailableDoctor())
                if (JOptionPane.showConfirmDialog(this, "One of the selected doctors doesn't work on the specified time, are you sure?") != JOptionPane.YES_OPTION)
                    return;
            // Creación de la cita
            Set<Doctor> doctorsSet = new HashSet<Doctor>(selectedDoctors);
            Appointment appointment = new Appointment();
            appointment.setPatient((Patient) cbPatient.getSelectedItem());
            appointment.setStartTime(appointmentDateTime);
            appointment.setEndTime(appointmentEndTime);
            appointment.setUrgent(chckbxUrgent.isSelected());
            appointment.setContactInfo(txtContactInfo.getText());
            appointment.setRoom((Room) cbRoom.getSelectedItem());
            appointment.setDoctors(doctorsSet);
            appointmentService.createAppointment(appointment);
            // Envío del email si es urgente y se ha seleccionado algún doctor
            if (chckbxUrgent.isSelected() && !selectedDoctors.isEmpty())
                appointment.sendEmail();
            // Notificación de creación satisfactoria y restauración de la ventana
            modifyDate();
            JOptionPane.showMessageDialog(this, "The appointment was created succesfully");
        } catch (Exception ie) {
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

    // METODOS DE EJECUCION -------------------------------------------------------------------------

    // Elimina el doctor de la lista de doctores de la cita y lo añade a la de los posibles doctores
    private void removeDoctor() {
        if (getCbSelectedDoctors().getSelectedItem() != null) {
            Doctor doctor = (Doctor) getCbSelectedDoctors().getSelectedItem();
            selectedDoctors.remove(doctor);
            selectedDoctorsList.remove((Doctor) doctor);
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
            doctorList.clear();
            doctorList.addAll(allDoctors);
            cbDoctor.setRenderer(new StaffCellRenderer(availableDoctors));
            setDoctorComponentsEnabled(true);
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
        if (!selectedDoctors.isEmpty()) {
            cbSelectedDoctors.setEnabled(b);
            btnRemove.setEnabled(b);
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

    private void checkPatient() throws InputException {
        if (cbPatient.getSelectedItem() == null)
            throw new InputException("You must select a patient for the appointment");
    }

    private void checkContactInfo() throws InputException {
        if (txtContactInfo.getText().trim().isEmpty())
            throw new InputException("The appointment needs some contact information");
    }

    // METODOS DE COMPROBACION DE LA ENTRADA DE DATOS ----------------------------------

    private void checkDate() throws InputException {
        if (calendar.getDate() == null)
            throw new InputException("You must select a date for the appointment");
    }

    private void checkTime() throws InputException {
        if (timePicker.getTime() == null)
            throw new InputException("You must select a start time for the appointment");
        if (timePickerEnd.getTime() == null)
            throw new InputException("You must select an end time for the appointment");
    }

    private void checkRoom() throws InputException {
        if (cbRoom.getSelectedItem() == null) {
            throw new InputException("You must select a room for the appointment");
        }
    }

    private boolean isThereUnavailableDoctor() {
        for (Staff doc : selectedDoctors)
            if (!availableDoctors.contains(doc))
                return true;
        return false;
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
}
