package es.uniovi.ips.hospital.ui.doctor;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.ui.doctor.appointment.ApplyForAppointmentPanel;
import es.uniovi.ips.hospital.ui.doctor.appointment.ShowMyAppointmentsPanel;
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

@Component
public class DoctorDialog2 extends JDialog {

	private static final long serialVersionUID = 4742925315288866290L;

    private Doctor user;

    @Autowired	private DoctorMainPanel mainPanel;
    @Autowired	private ApplyForAppointmentPanel applyForAppointmentPanel;
    @Autowired	private ShowMyAppointmentsPanel showMyAppointmentsPanel;

	private JPanel current;
	private JPanel previous;
	private JLabel banner;
	private JLabel side;
	private JPanel pnSouth;
	private JButton btnBack;

	/**
	 * Create the dialog.
	 */
	public DoctorDialog2() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { previous = mainPanel; back();}
		});
		setBounds(100, 100, 800, 800);
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(PaletteFactory.getBaseDark());
		getContentPane().add(getBanner(), BorderLayout.NORTH);
		getContentPane().add(getSide(), BorderLayout.WEST);
		getContentPane().add(getPnSouth(), BorderLayout.SOUTH);
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
	
	// CAMBIO DE VENTANAS -------------------------------------------------------------------------
	
	public void run(Doctor user) {
		this.user = user;
		setTitle(user.guiToString());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		current = mainPanel;
		getContentPane().revalidate();
		setVisible(true);
	}
	
	private void launch(JPanel panel) {
		getContentPane().remove(current);
		getContentPane().add(panel, BorderLayout.CENTER);
		previous = current;
		current = panel;
		getContentPane().revalidate();
		getContentPane().repaint();
		((Shiftable) panel).setFocus();
		btnBack.setEnabled(true);
	}
	
	private void back() {
		boolean isMainPanel = previous == mainPanel;
		launch(previous);
		if (isMainPanel)
			btnBack.setEnabled(false);
		else
			previous = mainPanel;
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
	
	public void launchProcessAppointment(Appointment appointment) {
//		processAppointmentPanel.fillComboBoxes();
//		processAppointmentPanel.setAppointment(appointment);
//		launch(processAppointmentPanel);
//		
	}
//	
//	public void launchEditAppointment(Appointment appointment) {
//		editAppointmentPanel.fillComboBoxes();
//		editAppointmentPanel.setAppointment(appointment);
//		launch(editAppointmentPanel);
//	}
//	
//
//    void launchManageWorkSchedule() {
//        manageWorkSchedulePanel.fillLists();
//        launch(manageWorkSchedulePanel);
//    }
//
//    void launchManageBreaks() {
//        manageBreakSchedulePanel.fillLists();
//        launch(manageBreakSchedulePanel);
//    }
//    
//    void launchPatientInfo() {
//    	patientInfoPanel.fillList();
//    	launch(patientInfoPanel);
//    }
//    
//    public void launchMedicalRecord(Patient patient) {
//        medicalRecordWithoutPrescriptionPanel.showHistoryOf(patient);
//        launch(medicalRecordWithoutPrescriptionPanel);
//    }
//    
//	public void launchCreateAdmins() {
//		launch(createAdminsPanel);
//	}
//    
//	public void launchCreateDoctors() {
//		launch(createDoctorsPanel);
//	}
//    
//	public void launchCreateNurses() {
//		launch(createNursesPanel);
//	}
//    
//	public void launchCreatePatients() {
//		launch(createPatientsPanel);
//	}
}
