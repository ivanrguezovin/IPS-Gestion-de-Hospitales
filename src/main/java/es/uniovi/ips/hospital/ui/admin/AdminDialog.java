package es.uniovi.ips.hospital.ui.admin;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.ui.admin.appointment.CreateAppointmentDialog;
import es.uniovi.ips.hospital.ui.admin.schedule.ManageWorkScheduleDialog;

@Component
public class AdminDialog extends JDialog {

	private static final long serialVersionUID = -1238718624919092329L;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel pnSide;
	private JPanel pnButtons;
	private JPanel pnAppointments;
	private JPanel pnSchedules;
	private JButton btnCreateAppointment;
	private JButton btnManageSchedules;

	@Autowired
	private CreateAppointmentDialog createAppointmentDialog;
	@Autowired
	private ManageWorkScheduleDialog manageWorkScheduleDialog;

	/**
	 * Create the dialog.
	 */
	public AdminDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.add(getPnSide());
		contentPanel.add(getPnButtons());
	}

	private JPanel getPnSide() {
		if (pnSide == null) {
			pnSide = new JPanel();
		}
		return pnSide;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setLayout(new GridLayout(2, 0, 0, 0));
			pnButtons.add(getPnAppointments());
			pnButtons.add(getPnSchedules());
		}
		return pnButtons;
	}
	private JPanel getPnAppointments() {
		if (pnAppointments == null) {
			pnAppointments = new JPanel();
			pnAppointments.setBorder(new TitledBorder(null, "Appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnAppointments.add(getBtnCreateAppointment());
		}
		return pnAppointments;
	}
	private JPanel getPnSchedules() {
		if (pnSchedules == null) {
			pnSchedules = new JPanel();
			pnSchedules.setBorder(new TitledBorder(null, "Schedules", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSchedules.add(getBtnManageSchedules());
		}
		return pnSchedules;
	}
	private JButton getBtnCreateAppointment() {
		if (btnCreateAppointment == null) {
			btnCreateAppointment = new JButton("Create appointment");
			btnCreateAppointment.addActionListener(actionEvent -> launchCreateAppointmentDialog());
		}
		return btnCreateAppointment;
	}
	private JButton getBtnManageSchedules() {
		if (btnManageSchedules == null) {
			btnManageSchedules = new JButton("Manage schedules");
			btnManageSchedules.addActionListener(actionEvent -> launchScheduleDialog() );
		}
		return btnManageSchedules;
	}
	
	// METODOS DE LANZAMIENTO DE VENTANAS

	private void launchScheduleDialog() {
		manageWorkScheduleDialog.fillLists();
		manageWorkScheduleDialog.setVisible(true);
	}
	
	private void launchCreateAppointmentDialog() {
		createAppointmentDialog.fillLists();
		createAppointmentDialog.setVisible(true);
	}
}
