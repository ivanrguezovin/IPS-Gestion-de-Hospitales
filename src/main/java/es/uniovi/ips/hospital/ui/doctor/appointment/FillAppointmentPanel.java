package es.uniovi.ips.hospital.ui.doctor.appointment;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.DiagnosticService;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.render.DiagnosticCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Component
public class FillAppointmentPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = -2608429797740890553L;
	private JPanel contentPanel = new JPanel();

	@Autowired
	private DiagnosticService diagnosticService;
	@Autowired
	private DoctorDialog doctorDialog;

	private Appointment appointment;
	private Patient patient;
	private Doctor doctor;
	private JList<Diagnostic> diagnosticList;

	private JPanel patientInfoPanel;
	private JPanel infoPanel;
	private JPanel diagnosticPanel;
	private JPanel buttonsPanel;

	private JLabel nameField;
	private JLabel surnameField;
	private JLabel healthcardField;

	public FillAppointmentPanel() {
		appointment = new Appointment();
		patient = new Patient();
		doctor = new Doctor();

		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(10, 10));
		contentPanel.add(getPatientInfoPanel(), BorderLayout.NORTH);
		contentPanel.add(getDiagnosticPanel(), BorderLayout.CENTER);
		contentPanel.add(getButtonsPanel(), BorderLayout.EAST);
	}

	private JPanel getPatientInfoPanel() {
		if (patientInfoPanel == null) {
			patientInfoPanel = new MyFrontPanel();
			patientInfoPanel.setBorder(Designer.getBorder());

			ImageIcon image = new ImageIcon();
			try {
				File file = ResourceUtils.getFile("classpath:profile.png");
				BufferedImage img = ImageIO.read(file);
				Image i = img.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
				image = new ImageIcon(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
			JLabel iconLabel = new JLabel(image);

			patientInfoPanel.add(BorderLayout.WEST, iconLabel);
			patientInfoPanel.add(BorderLayout.EAST, getInfoPanel());

		}
		return patientInfoPanel;
	}

	private JPanel getInfoPanel() {
		if (infoPanel == null) {
			infoPanel = new MyFrontPanel();

			JLabel nameLabel = new JLabel("Name:");
			JLabel surnameLabel = new JLabel("Surname:");
			JLabel healthcardLabel = new JLabel("Healthcard:");
			nameField = new JLabel();
			surnameField = new JLabel();
			healthcardField = new JLabel();
			infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

			infoPanel.add(nameLabel);
			infoPanel.add(nameField);
			infoPanel.add(Box.createRigidArea(new Dimension(5, 5)));
			infoPanel.add(surnameLabel);
			infoPanel.add(surnameField);
			infoPanel.add(Box.createRigidArea(new Dimension(5, 5)));
			infoPanel.add(healthcardLabel);
			infoPanel.add(healthcardField);
			infoPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		}
		return infoPanel;
	}

	private JPanel getDiagnosticPanel() {
		if (diagnosticPanel == null) {
			diagnosticPanel = new MyFrontPanel();
			diagnosticPanel.setBorder(Designer.getBorder());
			BoxLayout boxLayout = new BoxLayout(diagnosticPanel, BoxLayout.Y_AXIS);
			diagnosticPanel.setLayout(boxLayout);
			JLabel lblDiagnostics = new JLabel("Diagnostic:");
			lblDiagnostics.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			diagnosticPanel.add(lblDiagnostics);
			JScrollPane jScrollPane = new JScrollPane(getDiagnosticList(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			diagnosticPanel.add(BorderLayout.NORTH, jScrollPane);
		}
		return diagnosticPanel;
	}

	private JList<Diagnostic> getDiagnosticList() {
		if (diagnosticList == null) {
			diagnosticList = new JList<Diagnostic>();
			diagnosticList.setCellRenderer(new DiagnosticCellRenderer());
		}
		return diagnosticList;
	}

	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {

			buttonsPanel = new MyBackPanel();
			JButton btnShowMedicalRecord = new MyButton("Show Medical Record");
			JButton editButton = new MyButton("Edit prescription");
			JButton createButton = new MyButton("Create diagnostic");
			JButton deleteButton = new MyButton("Delete diagnostic");
			JButton refreshButton = new MyButton("Refresh");
			JButton allButton = new MyButton("All diagnostics");

			btnShowMedicalRecord.addActionListener(actionEvent -> showMedicalRecord());
			createButton.addActionListener(actionEvent -> launchCreateDiagnostic());
			deleteButton.addActionListener(actionEvent -> this.deleteDiagnostic());
			refreshButton.addActionListener(actionEvent -> this.loadDiagnostics());
			allButton.addActionListener(actionEvent -> this.allDiagnostics());

			GridBagLayout gbl_pnButtons = new GridBagLayout();
			gbl_pnButtons.columnWidths = new int[] { 0 };
			gbl_pnButtons.rowHeights = new int[] { 10, 20, 10, 20, 10, 20, 10, 20, 10, 20, 10, 20, 10 };
			gbl_pnButtons.columnWeights = new double[] { 1.0 };
			gbl_pnButtons.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
			buttonsPanel.setLayout(gbl_pnButtons);
			GridBagConstraints gbc_pnShowMedicalRecord = new GridBagConstraints();
			gbc_pnShowMedicalRecord.insets = new Insets(5, 5, 5, 5);
			gbc_pnShowMedicalRecord.fill = GridBagConstraints.BOTH;
			gbc_pnShowMedicalRecord.gridx = 0;
			gbc_pnShowMedicalRecord.gridy = 1;
			buttonsPanel.add(btnShowMedicalRecord, gbc_pnShowMedicalRecord);
			GridBagConstraints gbc_pnEditPrescription = new GridBagConstraints();
			gbc_pnEditPrescription.insets = new Insets(5, 5, 5, 5);
			gbc_pnEditPrescription.fill = GridBagConstraints.BOTH;
			gbc_pnEditPrescription.gridx = 0;
			gbc_pnEditPrescription.gridy = 3;
			buttonsPanel.add(editButton, gbc_pnEditPrescription);
			GridBagConstraints gbc_pnCreateDiagnostic = new GridBagConstraints();
			gbc_pnCreateDiagnostic.insets = new Insets(5, 5, 5, 5);
			gbc_pnCreateDiagnostic.fill = GridBagConstraints.BOTH;
			gbc_pnCreateDiagnostic.gridx = 0;
			gbc_pnCreateDiagnostic.gridy = 5;
			buttonsPanel.add(createButton, gbc_pnCreateDiagnostic);
			GridBagConstraints gbc_pnDeleteDiagnostic = new GridBagConstraints();
			gbc_pnDeleteDiagnostic.insets = new Insets(5, 5, 5, 5);
			gbc_pnDeleteDiagnostic.fill = GridBagConstraints.BOTH;
			gbc_pnDeleteDiagnostic.gridx = 0;
			gbc_pnDeleteDiagnostic.gridy = 7;
			buttonsPanel.add(deleteButton, gbc_pnDeleteDiagnostic);
			GridBagConstraints gbc_pnRefresh = new GridBagConstraints();
			gbc_pnRefresh.insets = new Insets(5, 5, 5, 5);
			gbc_pnRefresh.fill = GridBagConstraints.BOTH;
			gbc_pnRefresh.gridx = 0;
			gbc_pnRefresh.gridy = 9;
			buttonsPanel.add(refreshButton, gbc_pnRefresh);
			GridBagConstraints gbc_pnAll = new GridBagConstraints();
			gbc_pnAll.insets = new Insets(5, 5, 5, 5);
			gbc_pnAll.fill = GridBagConstraints.BOTH;
			gbc_pnAll.gridx = 0;
			gbc_pnAll.gridy = 11;
			buttonsPanel.add(allButton, gbc_pnAll);
		}
		return buttonsPanel;
	}

	// ---------------------------------------------------------------------------------------

	public void run(Appointment appointment, Doctor doctor) {
		this.setAppointment(appointment);
		this.setDoctor(doctor);
		this.setPatient(appointment.getPatient());
		this.nameField.setText(patient.getName());
		this.surnameField.setText(patient.getSurname());
		this.healthcardField.setText(patient.getHealthCardNumber());
		this.loadDiagnostics();
	}

	@Override
	public void setFocus() {
		diagnosticList.requestFocus();
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	// ----------------------------------------------------------------------------------------
	
	private void showMedicalRecord() {
		System.out.println(patient);
		doctorDialog.launchMedicalRecord(patient);
	}
	
	private void launchCreateDiagnostic() {
		doctorDialog.launchCreateDiagnostic(this.appointment);
	}

	private void deleteDiagnostic() {
		if (diagnosticList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Select an item from the list");
		} else {
			Diagnostic d = diagnosticList.getSelectedValue();
			d.setActive(false);
			diagnosticService.createDiagnostic(d);
		}
	}

	private void loadDiagnostics() {
		DefaultListModel<Diagnostic> model = new DefaultListModel<>();
		for (Diagnostic diagnostic : diagnosticService.findAllByAppointment(this.getAppointment())) {
			model.addElement(diagnostic);
		}
		diagnosticList.setModel(model);
		System.out.println("Diagnostics from this appointment");
	}

	private void allDiagnostics() {
		DefaultListModel<Diagnostic> model = new DefaultListModel<>();
		Set<Appointment> aps = doctor.getAppointments();
		java.util.List<Appointment> appointments = new ArrayList<>();
		for (Appointment a : aps) {
			if (a.getPatient().getHealthCardNumber().equals(patient.getHealthCardNumber())) {
				appointments.add(a);
			}
		}
		for (Appointment a : appointments) {
			for (Diagnostic diagnostic : diagnosticService.findAllByAppointment(a)) {
				model.addElement(diagnostic);
			}
		}
		diagnosticList.setModel(model);
		System.out.println("All diagnostics");
	}
}