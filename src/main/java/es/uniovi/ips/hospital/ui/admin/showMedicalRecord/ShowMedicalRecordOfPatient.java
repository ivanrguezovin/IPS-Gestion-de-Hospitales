package es.uniovi.ips.hospital.ui.admin.showMedicalRecord;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.RoomService;
import es.uniovi.ips.hospital.ui.common.MedicalRecordDialogWithoutPrescription;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.util.format.PatientFormat;
import es.uniovi.ips.hospital.util.format.RoomFormat;
import es.uniovi.ips.hospital.util.format.StaffFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ShowMedicalRecordOfPatient extends JDialog {

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
    private Set<Doctor> selectedDoctors;
    private List<Doctor> availableDoctors;
    private LocalDateTime appointmentDateTime;
    private JPanel pnAppointment;
    private JPanel pnPatient;
    private JButton btnShowMedicalRecord;
    private JComboBox<Patient> cbPatient;
    private EventList<Patient> patientList;
    private AutoCompleteSupport<Patient> autoCompletePatient;
    private JLabel lblContactInfo;
    private JTextField txtContactInfo;
    private EventList<Staff> doctorList;
    private AutoCompleteSupport<Staff> autoCompleteDoctor;
    private JPanel pnSouth;
    private JButton btnCreate;
    private EventList<Room> roomList;
    private AutoCompleteSupport<Room> autoCompleteRoom;
    private ActionListener setDateAction;
    private ActionListener modifyDateAction;

    /**
     * Create the dialog.
     */
    public ShowMedicalRecordOfPatient() {
        setModal(true);
        setPreferredSize(new Dimension(700, 500));
        setBounds(100, 100, 700, 292);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getPnAppointment(), BorderLayout.CENTER);
        selectedDoctors = new HashSet<Doctor>();
    }

    private JPanel getPnAppointment() {
        if (pnAppointment == null) {
            pnAppointment = new JPanel();
            GridBagLayout gbl_pnAppointment = new GridBagLayout();
            gbl_pnAppointment.columnWidths = new int[]{0, 0};
            gbl_pnAppointment.columnWeights = new double[]{1.0, Double.MIN_VALUE};
            gbl_pnAppointment.rowWeights = new double[]{0.0, 1.0};
            pnAppointment.setLayout(gbl_pnAppointment);
            GridBagConstraints gbc_pnPatient = new GridBagConstraints();
            gbc_pnPatient.gridheight = 2;
            gbc_pnPatient.insets = new Insets(0, 0, 5, 0);
            gbc_pnPatient.fill = GridBagConstraints.BOTH;
            gbc_pnPatient.gridx = 0;
            gbc_pnPatient.gridy = 0;
            pnAppointment.add(getPnPatient(), gbc_pnPatient);
        }
        return pnAppointment;
    }

    private JPanel getPnPatient() {
        if (pnPatient == null) {
            pnPatient = new JPanel();
            pnPatient.setBorder(new TitledBorder(null, "Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnPatient.setLayout(null);
            pnPatient.add(getCbPatient());
            pnPatient.add(getBtnShowMedicalRecord());
            pnPatient.add(getLblContactInfo());
            pnPatient.add(getTxtContactInfo());
        }
        return pnPatient;
    }

    private JComboBox<Patient> getCbPatient() {
        if (cbPatient == null) {
            cbPatient = new JComboBox<>();
            cbPatient.setBounds(11, 22, 278, 20);
            cbPatient.addItemListener(itemEvent -> selectPatient());
            autoCompletePatient = null;
        }
        return cbPatient;
    }

    private JButton getBtnShowMedicalRecord() {
        if (btnShowMedicalRecord == null) {
            btnShowMedicalRecord = new JButton("Show medical record");
            btnShowMedicalRecord.setBounds(405, 21, 246, 23);
            btnShowMedicalRecord.setEnabled(false);

            btnShowMedicalRecord.addActionListener(actionEvent -> showMedicalRecord());
        }
        return btnShowMedicalRecord;
    }


    private void showMedicalRecord() {
        Patient patient = (Patient) cbPatient.getSelectedItem();
        System.out.println(patient);
        medicalRecordDialogWithoutPrescription.showHistoryOf(patient);
    }

    private JLabel getLblContactInfo() {
        if (lblContactInfo == null) {
            lblContactInfo = new JLabel("Contact info:");
            lblContactInfo.setBounds(301, 117, 94, 14);
        }
        return lblContactInfo;
    }

    private JTextField getTxtContactInfo() {
        if (txtContactInfo == null) {
            txtContactInfo = new JTextField(15);
            txtContactInfo.setHorizontalAlignment(SwingConstants.CENTER);
            txtContactInfo.setBounds(405, 114, 246, 20);
            txtContactInfo.setEnabled(false);
        }
        return txtContactInfo;
    }

    // METODOS Y CLASES DE INICIALIZACION ------------------------------------------------------------

    public void fillComboBoxes() {
        // Patient list
        patientList = new BasicEventList<>();
        patientList.addAll(patientService.findAllPatient());
        if (autoCompletePatient == null)
            autoCompletePatient = AutoCompleteSupport.install(getCbPatient(), patientList, new PatientTextFilterator(), patientFormat);
        autoCompletePatient.setFilterMode(TextMatcherEditor.CONTAINS);
    }

    // METODOS DE EJECUCION -------------------------------------------------------------------------

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
}
