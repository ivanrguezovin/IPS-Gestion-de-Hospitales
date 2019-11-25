package es.uniovi.ips.hospital.ui.admin;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.components.MySouthPanel;

import java.awt.*;

@Component
public class AdminMainPanel extends JPanel implements Shiftable {

    private static final long serialVersionUID = -1238718624919092329L;
    
    @Autowired
    private AdminDialog2 adminDialog;

    private final JPanel contentPanel = new MyBackPanel();
    private JPanel pnSide;
    private JPanel pnButtons;
    private JPanel pnAppointments;
    private JPanel pnSchedules;
    private JButton btnCreateAppointment;
    private JButton btnEditAppointment;
    private JButton btnManageSchedules;
    private JButton btnManageBreaks;
    private JPanel pnPatients;
    private JButton btnShowMedicalRecords;
    private JPanel pnCreateUsers;
    private JButton btnCreateUsers;

    /**
     * Create the dialog.
     */
    public AdminMainPanel() {
        setBounds(100, 100, 650, 700);
        this.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
        contentPanel.add(getPnSide());
        contentPanel.add(getPnButtons());
    }

    private JPanel getPnSide() {
        if (pnSide == null) {
            pnSide = new JPanel();
            pnSide.setLayout(new GridLayout(2, 1, 0, 0));
            pnSide.add(getPnPatients());
            pnSide.add(getPnCreateUsers());
        }
        return pnSide;
    }

    private JPanel getPnButtons() {
        if (pnButtons == null) {
            pnButtons = new MySouthPanel();
            pnButtons.setLayout(new GridLayout(2, 0, 0, 0));
            pnButtons.add(getPnAppointments());
            pnButtons.add(getPnSchedules());
        }
        return pnButtons;
    }

    private JPanel getPnAppointments() {
        if (pnAppointments == null) {
            pnAppointments = new MyFrontPanel();
            pnAppointments.setBorder(new TitledBorder(null, "Appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnAppointments.add(getBtnCreateAppointment());
            pnAppointments.add(getBtnEditAppointment());
        }
        return pnAppointments;
    }

    private JPanel getPnSchedules() {
        if (pnSchedules == null) {
            pnSchedules = new MyFrontPanel();
            pnSchedules.setBorder(new TitledBorder(null, "Schedules", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnSchedules.add(getBtnManageSchedules());
            pnSchedules.add(getBtnManageBreaks());
        }
        return pnSchedules;
    }

    private JPanel getPnPatients() {
        if (pnPatients == null) {
            pnPatients = new MyFrontPanel();
            pnPatients.setBorder(new TitledBorder(null, "Patients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnPatients.add(getBtnShowMedicalRecords());
        }
        return pnPatients;
    }

    private JPanel getPnCreateUsers() {
        if (pnCreateUsers == null) {
            pnCreateUsers = new MyFrontPanel();
            pnCreateUsers.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Create Users", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
            pnCreateUsers.add(getBtnCreateUsers());
        }
        return pnCreateUsers;
    }

    private JButton getBtnCreateAppointment() {
        if (btnCreateAppointment == null) {
            btnCreateAppointment = new JButton("Create appointment");
            btnCreateAppointment.addActionListener(e -> adminDialog.launchCreateAppointment());
        }
        return btnCreateAppointment;
    }

    private JButton getBtnEditAppointment() {
        if (btnEditAppointment == null) {
            btnEditAppointment = new JButton("Edit appointment");
        }
        return btnEditAppointment;
    }

    private JButton getBtnManageSchedules() {
        if (btnManageSchedules == null) {
            btnManageSchedules = new JButton("Manage schedules");
        }
        return btnManageSchedules;
    }

    private JButton getBtnManageBreaks() {
        if (btnManageBreaks == null) {
            btnManageBreaks = new JButton("Manage breaks");
        }
        return btnManageBreaks;
    }

    private JButton getBtnShowMedicalRecords() {
        if (btnShowMedicalRecords == null) {
            btnShowMedicalRecords = new JButton("Show Medical Records");
        }
        return btnShowMedicalRecords;
    }

    private JButton getBtnCreateUsers() {
        if (btnCreateUsers == null) {
            btnCreateUsers = new JButton("Create Users");
            btnCreateUsers.setFont(new Font("Tahoma", Font.BOLD, 11));
        }
        return btnCreateUsers;
    }
    
    ////////////////////////////////////////////////////////////////////////////////

	@Override
	public void setFocus() {
		btnCreateAppointment.requestFocus();
	}

}
