package es.uniovi.ips.hospital.ui.admin;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.ui.admin.appointment.CreateAppointmentPanel;
import es.uniovi.ips.hospital.ui.admin.appointment.EditAppointmentPanel;
import es.uniovi.ips.hospital.ui.admin.appointment.ProcessAppointmentPanel;
import es.uniovi.ips.hospital.ui.admin.appointment.ShowAppointmentsPanel;
import es.uniovi.ips.hospital.ui.admin.schedule.ManageBreakSchedulePanel;
import es.uniovi.ips.hospital.ui.admin.schedule.ManageWorkSchedulePanel;
import es.uniovi.ips.hospital.ui.admin.showMedicalRecord.PatientInfoPanel;
import es.uniovi.ips.hospital.ui.admin.users.CreateAdminsPanel;
import es.uniovi.ips.hospital.ui.admin.users.CreateDoctorsPanel;
import es.uniovi.ips.hospital.ui.admin.users.CreateNursesPanel;
import es.uniovi.ips.hospital.ui.admin.users.CreatePatientsPanel;
import es.uniovi.ips.hospital.ui.common.MedicalRecordWithoutPrescriptionPanel;
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
public class AdminDialog extends JDialog {

	private static final long serialVersionUID = 4742925315288866290L;

    @Autowired	private AdminMainPanel mainPanel;
    @Autowired	private CreateAppointmentPanel createAppointmentPanel;
    @Autowired	private ShowAppointmentsPanel showAppointmentsPanel;
    @Autowired	private EditAppointmentPanel editAppointmentPanel;
    @Autowired	private ProcessAppointmentPanel processAppointmentPanel;
    @Autowired	private ManageWorkSchedulePanel manageWorkSchedulePanel;
    @Autowired	private ManageBreakSchedulePanel manageBreakSchedulePanel;
    @Autowired	private PatientInfoPanel patientInfoPanel;
    @Autowired	private MedicalRecordWithoutPrescriptionPanel medicalRecordWithoutPrescriptionPanel;
    @Autowired	private CreateAdminsPanel createAdminsPanel;
    @Autowired	private CreateDoctorsPanel createDoctorsPanel;
    @Autowired	private CreateNursesPanel createNursesPanel;
    @Autowired	private CreatePatientsPanel createPatientsPanel;

	private Stack<JPanel> stack;
	private JLabel banner;
	private JLabel side;
	private JPanel pnSouth;
	private JButton btnBack;

	/**
	 * Create the dialog.
	 */
	public AdminDialog() {
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
		setTitle("Administration");
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
			side.setIcon(new ImageIcon(getClass().getResource("/AdminSide.png")));
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
	
	// CAMBIO DE VENTANAS -------------------------------------------------------------------------
	
	public void run() {
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
	
	void launchCreateAppointment() {
        createAppointmentPanel.fillComboBoxes();
        launch(createAppointmentPanel);
    }
	
	void launchShowAppointments() {
        showAppointmentsPanel.showAppointments();
        showAppointmentsPanel.fillComboBoxes();
        launch(showAppointmentsPanel);
	}
	
	public void launchEditAppointment(Appointment appointment) {
		editAppointmentPanel.fillComboBoxes();
		editAppointmentPanel.setAppointment(appointment);
		launch(editAppointmentPanel);
	}
	
	public void launchProcessAppointment(Appointment appointment) {
		processAppointmentPanel.fillComboBoxes();
		processAppointmentPanel.setAppointment(appointment);
		launch(processAppointmentPanel);
		
	}

    void launchManageWorkSchedule() {
        manageWorkSchedulePanel.fillLists();
        launch(manageWorkSchedulePanel);
    }

    void launchManageBreaks() {
        manageBreakSchedulePanel.fillLists();
        launch(manageBreakSchedulePanel);
    }
    
    void launchPatientInfo() {
    	patientInfoPanel.fillList();
    	launch(patientInfoPanel);
    }
    
    public void launchMedicalRecord(Patient patient) {
        medicalRecordWithoutPrescriptionPanel.showHistoryOf(patient);
        launch(medicalRecordWithoutPrescriptionPanel);
    }
    
	public void launchCreateAdmins() {
		launch(createAdminsPanel);
	}
    
	public void launchCreateDoctors() {
		launch(createDoctorsPanel);
	}
    
	public void launchCreateNurses() {
		launch(createNursesPanel);
	}
    
	public void launchCreatePatients() {
		launch(createPatientsPanel);
	}
}
