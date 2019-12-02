package es.uniovi.ips.hospital.ui.doctor;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.ui.common.MedicalRecordPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.ApplyForAppointmentPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.CreateDiagnosticPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.CreatePrescriptionPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.EditPrescriptionPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.FillAppointmentPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.ShowMyAppointmentsPanel;
import es.uniovi.ips.hospital.ui.doctor.vaccine.CreateVaccinePanel;
import es.uniovi.ips.hospital.ui.doctor.vaccine.EditVaccinePanel;
import es.uniovi.ips.hospital.ui.doctor.vaccine.ShowVaccinesPanel;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBanner;
import es.uniovi.ips.hospital.ui.util.components.MySouthPanel;

import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

@Component
public class DoctorDialog extends JDialog {

	private static final long serialVersionUID = 4742925315288866290L;

	private Doctor user;

	@Autowired
	private DoctorMainPanel mainPanel;
	@Autowired
	private FillAppointmentPanel fillAppointmentPanel;
	@Autowired
	private MedicalRecordPanel medicalRecordPanel;
	@Autowired
	private EditPrescriptionPanel editPrescriptionPanel;
	@Autowired
	private CreatePrescriptionPanel createPrescriptionPanel;
	@Autowired
	private ApplyForAppointmentPanel applyForAppointmentPanel;
	@Autowired
	private ShowMyAppointmentsPanel showMyAppointmentsPanel;
	@Autowired
	private CreateDiagnosticPanel createDiagnosticPanel;
	@Autowired
	private CreateVaccinePanel createVaccinePanel;
	@Autowired
	private ShowVaccinesPanel showVaccinesPanel;
	@Autowired
	private EditVaccinePanel editVaccinePanel;

	private Stack<JPanel> stack;
	private JLabel banner;
	private JLabel side;
	private JPanel pnSouth;
	private JButton btnBack;

	/**
	 * Create the dialog.
	 */
	public DoctorDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				while (stack.size() > 1)
					back();
			}
		});
		setBounds(100, 100, 800, 800);
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(PaletteFactory.getBaseDark());
		getContentPane().add(getBanner(), BorderLayout.NORTH);
		getContentPane().add(getSide(), BorderLayout.WEST);
		getContentPane().add(getPnSouth(), BorderLayout.SOUTH);
		stack = new Stack<JPanel>();
	}

	private JLabel getBanner() {
		if (banner == null) {
			banner = new MyBanner();
		}
		return banner;
	}

	private JLabel getSide() {
		if (side == null) {
			side = new JLabel();
			side.setPreferredSize(new Dimension(100, 10));
			side.setMinimumSize(new Dimension(100, 10));
			side.setIcon(new ImageIcon(getClass().getResource("/DoctorSide.png")));
		}
		return side;
	}

	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new MySouthPanel();
			pnSouth.setPreferredSize(new Dimension(10, 50));
			pnSouth.setMinimumSize(new Dimension(10, 50));
			pnSouth.add(getBtnBack());
		}
		return pnSouth;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.addActionListener(e -> back());
		}
		return btnBack;
	}

	// CAMBIO DE VENTANAS
	// -------------------------------------------------------------------------

	public void run(Doctor user) {
		this.user = user;
		setTitle(user.guiToString());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		stack.clear();
		stack.push(mainPanel);
		getContentPane().revalidate();
		setVisible(true);
	}

	private void launch(JPanel panel) {
		getContentPane().remove(stack.peek());
		getContentPane().add(panel, BorderLayout.CENTER);
		stack.push(panel);
		getContentPane().revalidate();
		getContentPane().repaint();
		((Shiftable) panel).setFocus();
		btnBack.setEnabled(true);
	}

	private void back() {
		getContentPane().remove(stack.pop());
		boolean isMainPanel = stack.peek() == mainPanel;
		getContentPane().add(stack.peek(), BorderLayout.CENTER);
		getContentPane().revalidate();
		getContentPane().repaint();
		((Shiftable) stack.peek()).setFocus();
		if (isMainPanel)
			btnBack.setEnabled(false);
	}

	void launchApplyForAppointment() {
		applyForAppointmentPanel.fillComboBoxes();
		applyForAppointmentPanel.setDoctor(user);
		launch(applyForAppointmentPanel);
	}

	void launchShowMyAppointments() {
		showMyAppointmentsPanel.showAppointments(user);
		showMyAppointmentsPanel.fillComboBoxes();
		launch(showMyAppointmentsPanel);
	}

	public void launchFillAppointment(Appointment appointment) {
		fillAppointmentPanel.run(appointment, user);
		launch(fillAppointmentPanel);
	}

	public void launchMedicalRecord(Patient patient) {
		medicalRecordPanel.showHistoryOf(patient);
		launch(medicalRecordPanel);
	}

	public void launchEditPrescription(Patient patient) {
		editPrescriptionPanel.run(patient);
		launch(editPrescriptionPanel);
	}

	public void launchCreatePrescription(Patient patient) {
		createPrescriptionPanel.run(patient);
		launch(createPrescriptionPanel);
	}

	public void launchCreateDiagnostic(Appointment appointment) {
		createDiagnosticPanel.run(appointment, user);
		launch(createDiagnosticPanel);
	}

	void launchCreateVaccine() {
		launch(createVaccinePanel);
	}

	void launchShowVaccines() {
		showVaccinesPanel.setDoctor(user);
		launch(showVaccinesPanel);
	}

	public void launchEditVaccine(Vaccine vaccine) {
		editVaccinePanel.setVaccine(vaccine);
		launch(editVaccinePanel);
	}

}