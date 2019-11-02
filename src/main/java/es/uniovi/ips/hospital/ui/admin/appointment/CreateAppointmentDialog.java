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
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.ui.util.filter.StaffTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.PatientCellRenderer;
import es.uniovi.ips.hospital.ui.util.render.StaffCellRenderer;
import es.uniovi.ips.hospital.ui.util.render.StaffComboBoxEditor;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Panel;
import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingConstants;
import java.awt.Label;
import javax.swing.JComboBox;

@Component
public class CreateAppointmentDialog extends JDialog {

	private static final long serialVersionUID = 8434535298528019736L;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private StaffComboBoxEditor staffComboBoxEditor;
	
	private Set<Staff> selectedDoctors;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel pnAppointment;
	private JPanel pnPatient;
	private JPanel pnDoctor;
	private JPanel pnInfo;
	private JButton btnShowMedicalRecord;
	private JPanel pnSelectPatient;
	private JComboBox<Patient> cbPatient;
	private EventList<Patient> patientList;
	private JPanel pnContactInfo;
	private JLabel lblContactInfo;
	private JTextField txtContactInfo;
	private JButton btnAdd;
	private JPanel pnSelectDoctor;
	private JComboBox<Staff> cbDoctor;
	private EventList<Staff> doctorList;
	private JPanel pnShowDoctors;
	private JLabel lblDoctors;
	private JPanel pnSouth;
	private JButton btnCreate;
	private JPanel pnDateTime;
	private JPanel pnDetails;
	private JCheckBox chckbxUrgent;
	private JLabel lblRoom;
	private JTextField txtRoom;
    private JCalendar calendar;
    private TimePicker timePicker;
	private Panel pnRoom;
	private Panel pnTimePicker;
	private Label lblTime;

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
			GridBagConstraints gbc_pnDoctor = new GridBagConstraints();
			gbc_pnDoctor.insets = new Insets(0, 0, 5, 0);
			gbc_pnDoctor.fill = GridBagConstraints.BOTH;
			gbc_pnDoctor.gridx = 0;
			gbc_pnDoctor.gridy = 1;
			pnAppointment.add(getPnDoctor(), gbc_pnDoctor);
			GridBagConstraints gbc_pnInfo = new GridBagConstraints();
			gbc_pnInfo.fill = GridBagConstraints.BOTH;
			gbc_pnInfo.gridx = 0;
			gbc_pnInfo.gridy = 2;
			pnAppointment.add(getPnInfo(), gbc_pnInfo);
		}
		return pnAppointment;
	}
	private JPanel getPnPatient() {
		if (pnPatient == null) {
			pnPatient = new JPanel();
			pnPatient.setBorder(new TitledBorder(null, "Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPatient.setLayout(new GridLayout(0, 2, 0, 0));
			pnPatient.add(getPnSelectPatient());
			pnPatient.add(getPnContactInfo());
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
	private JButton getBtnShowMedicalRecord() {
		if (btnShowMedicalRecord == null) {
			btnShowMedicalRecord = new JButton("Show medical record");
			btnShowMedicalRecord.setEnabled(false);
		}
		return btnShowMedicalRecord;
	}
	private JPanel getPnSelectPatient() {
		if (pnSelectPatient == null) {
			pnSelectPatient = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnSelectPatient.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnSelectPatient.add(getCbPatient());
			pnSelectPatient.add(getBtnShowMedicalRecord());
		}
		return pnSelectPatient;
	}
	private JComboBox<Patient> getCbPatient() {
		if (cbPatient == null) {
			cbPatient = new JComboBox<Patient>();
			cbPatient.setRenderer(new PatientCellRenderer());
		}
		return cbPatient;
	}
	private JPanel getPnContactInfo() {
		if (pnContactInfo == null) {
			pnContactInfo = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnContactInfo.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnContactInfo.add(getLblContactInfo());
			pnContactInfo.add(getTxtContactInfo());
		}
		return pnContactInfo;
	}
	private JLabel getLblContactInfo() {
		if (lblContactInfo == null) {
			lblContactInfo = new JLabel("Contact info:");
		}
		return lblContactInfo;
	}
	private JTextField getTxtContactInfo() {
		if (txtContactInfo == null) {
			txtContactInfo = new JTextField();
			txtContactInfo.setEnabled(false);
			txtContactInfo.setColumns(10);
		}
		return txtContactInfo;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setEnabled(false);
			btnAdd.addActionListener(actionEvent -> addDoctor());
		}
		return btnAdd;
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
		}
		return cbDoctor;
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
			pnDetails.setLayout(new GridLayout(3, 1, 0, 0));
			pnDetails.add(getChckbxUrgent());
			pnDetails.add(getPnRoom());
			pnDetails.add(getPnTimePicker());
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
	private JLabel getLblRoom() {
		if (lblRoom == null) {
			lblRoom = new JLabel("Room:");
		}
		return lblRoom;
	}
	private JTextField getTxtRoom() {
		if (txtRoom == null) {
			txtRoom = new JTextField();
			txtRoom.setColumns(10);
		}
		return txtRoom;
	}
    private JCalendar getCalendar() {
        if (calendar == null) {
            calendar = new JCalendar();
        }
        return calendar;
    }
    private TimePicker getTimePicker() {
        if (timePicker == null) {
            timePicker = new TimePicker();
        }
        return timePicker;
    }
	private Panel getPnRoom() {
		if (pnRoom == null) {
			pnRoom = new Panel();
			pnRoom.add(getLblRoom());
			pnRoom.add(getTxtRoom());
		}
		return pnRoom;
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

    // METODOS Y CLASES DE INICIALIZACION ------------------------------------------------------------

    public void fillComboBoxes() {
        // Doctor List
        doctorList = new BasicEventList<Staff>();
        doctorList.addAll(doctorService.findAllDoctors());
        AutoCompleteSupport<Staff> autocompleteDoctor = AutoCompleteSupport.install(getCbDoctor(), doctorList, new StaffTextFilterator());
		autocompleteDoctor.setFilterMode(TextMatcherEditor.CONTAINS);
		cbDoctor.setEditor(staffComboBoxEditor);
		cbDoctor.setRenderer(new StaffCellRenderer());
        // Patient list
        patientList = new BasicEventList<Patient>();
        patientList.addAll(patientService.findAllPatient());
        AutoCompleteSupport<Patient> autoCompletePatient = AutoCompleteSupport.install(getCbPatient(), patientList, new PatientTextFilterator());
        autoCompletePatient.setFilterMode(TextMatcherEditor.CONTAINS);
    }
    
    // METODOS DE EJECUCION -------------------------------------------------------------------------
    
    private void addDoctor() {
    	Staff doctor = (Staff) getCbDoctor().getSelectedItem();
    	selectedDoctors.add(doctor);
    	doctorList.remove(doctor);
    	cbDoctor.setSelectedItem(null);
    	
    }
}
