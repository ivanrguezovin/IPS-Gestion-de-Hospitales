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
import es.uniovi.ips.hospital.ui.admin.appointment.ShowAppointmentsDialog;
import es.uniovi.ips.hospital.ui.admin.schedule.ManageBreakScheduleDialog;
import es.uniovi.ips.hospital.ui.admin.schedule.ManageWorkScheduleDialog;
import es.uniovi.ips.hospital.ui.admin.users.CreateUsersDialog;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class AdminDialog extends JDialog {

	private static final long serialVersionUID = -1238718624919092329L;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel pnSide;
	private JPanel pnButtons;
	private JPanel pnAppointments;
	private JPanel pnSchedules;
	private JButton btnCreateAppointment;
	private JButton btnEditAppointment;
	private JButton btnManageSchedules;
	private JButton btnManageBreaks;

	@Autowired	private CreateAppointmentDialog createAppointmentDialog;
	@Autowired	private ShowAppointmentsDialog showAppointmentsDialog;
	@Autowired	private ManageWorkScheduleDialog manageWorkScheduleDialog;
	@Autowired	private ManageBreakScheduleDialog manageBreakScheduleDialog;
	@Autowired
	private CreateAppointmentDialog createAppointmentDialog;
	@Autowired
	private ManageWorkScheduleDialog manageWorkScheduleDialog;
	@Autowired
	private CreateUsersDialog createUsersDialog;

	private JPanel pnCreateUsers;
	private JButton btnCreateUsers;

	/**
	 * Create the dialog.
	 */
	public AdminDialog() {
		setTitle("Administrator");
		setModal(true);
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
			pnButtons.setLayout(new GridLayout(0, 1, 0, 0));
			pnButtons.add(getPnCreateUsers());
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
			pnAppointments.add(getBtnEditAppointment());
		}
		return pnAppointments;
	}
	private JPanel getPnSchedules() {
		if (pnSchedules == null) {
			pnSchedules = new JPanel();
			pnSchedules.setBorder(new TitledBorder(null, "Schedules", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSchedules.add(getBtnManageSchedules());
			pnSchedules.add(getBtnManageBreaks());
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
	private JButton getBtnEditAppointment() {
		if (btnEditAppointment == null) {
			btnEditAppointment = new JButton("Edit appointment");
			btnEditAppointment.addActionListener(actionEvent -> launchShowAppointmentsDialog());
		}
		return btnEditAppointment;
	}
	private JButton getBtnManageSchedules() {
		if (btnManageSchedules == null) {
			btnManageSchedules = new JButton("Manage schedules");
			btnManageSchedules.addActionListener(actionEvent -> launchScheduleDialog() );
		}
		return btnManageSchedules;
	}
	private JButton getBtnManageBreaks() {
		if (btnManageBreaks == null) {
			btnManageBreaks = new JButton("Manage breaks");
			btnManageBreaks.addActionListener(actionEvent -> launchBreakDialog() );
		}
		return btnManageBreaks;
	}

	// METODOS DE LANZAMIENTO DE VENTANAS

	private void launchCreateAppointmentDialog() {
		createAppointmentDialog.fillComboBoxes();
		createAppointmentDialog.setLocationRelativeTo(this);
		createAppointmentDialog.setVisible(true);
	}
	private void launchShowAppointmentsDialog() {
		showAppointmentsDialog.showAppointments();
		showAppointmentsDialog.setLocationRelativeTo(this);
		showAppointmentsDialog.setVisible(true);
	}
	private void launchScheduleDialog() {
		manageWorkScheduleDialog.fillLists();
		manageWorkScheduleDialog.setLocationRelativeTo(this);
		manageWorkScheduleDialog.setVisible(true);
	}
	private void launchBreakDialog() {
		manageBreakScheduleDialog.fillLists();
		manageBreakScheduleDialog.setLocationRelativeTo(this);
		manageBreakScheduleDialog.setVisible(true);
	}


	private void launchCreateUsers() {
		createUsersDialog.setLocationRelativeTo(null);
		createUsersDialog.setVisible(true);
	}
	private JPanel getPnCreateUsers() {
		if (pnCreateUsers == null) {
			pnCreateUsers = new JPanel();
			pnCreateUsers.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Create Users", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnCreateUsers.add(getBtnCreateUsers());
		}
		return pnCreateUsers;
	}
	private JButton getBtnCreateUsers() {
		if (btnCreateUsers == null) {
			btnCreateUsers = new JButton("Create Users");
			btnCreateUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchCreateUsers();
				}
			});
			btnCreateUsers.setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		return btnCreateUsers;
	}
}
