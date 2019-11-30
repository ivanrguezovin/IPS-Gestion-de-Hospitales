package es.uniovi.ips.hospital.ui.doctor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;

import java.awt.*;

import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

@Component
public class DoctorMainPanel extends JPanel implements Shiftable {

    private static final long serialVersionUID = -1238718624919092329L;
    
    @Autowired	private DoctorDialog2 doctorDialog;

    private final JPanel contentPanel = new MyBackPanel();
    private JPanel pnAppointments;
    private JPanel pnSchedules;
    private JPanel pnPatients;
    private JPanel pnCreateUsers;
	private JLabel lblAppointments;
	private JLabel lblSchedules;
	private JLabel lblPatients;
	private JLabel lblDatabase;
    private JButton btnCreateAppointment;
    private JButton btnEditAppointment;
    private JButton btnManageSchedules;
    private JButton btnManageBreaks;
    private JButton btnShowMedicalRecords;
    private JButton btnCreateAdmins;
    private JButton btnCreateDoctors;
    private JButton btnCreateNurses;
    private JButton btnCreatePatients;


    /**
     * Create the dialog.
     */
    public DoctorMainPanel() {
        setBounds(100, 100, 650, 700);
        this.setLayout(new BorderLayout());
        this.add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_pnMain = new GridBagLayout();
        gbl_pnMain.columnWidths = new int[]{55, 290, 10, 290, 55};
        gbl_pnMain.rowHeights = new int[]{30, 290, 10, 290, 30};
        gbl_pnMain.columnWeights = new double[]{0.0,0.0,0.0,0.0,0.0};
        gbl_pnMain.rowWeights = new double[]{0.0,0.0,0.0,0.0,0.0};
		contentPanel.setLayout(gbl_pnMain);
		GridBagConstraints gbc_pnBlank = new GridBagConstraints();
        gbc_pnBlank.insets = new Insets(5, 5, 5, 5);
		gbc_pnBlank.fill = GridBagConstraints.BOTH;
		gbc_pnBlank.gridx = 0;
		gbc_pnBlank.gridy = 0;
		contentPanel.add(new MyBackPanel(), gbc_pnBlank);
		GridBagConstraints gbc_pnAppointment = new GridBagConstraints();
        gbc_pnAppointment.insets = new Insets(5, 5, 5, 5);
		gbc_pnAppointment.fill = GridBagConstraints.BOTH;
		gbc_pnAppointment.gridx = 1;
		gbc_pnAppointment.gridy = 1;
		contentPanel.add(getPnAppointments(), gbc_pnAppointment);
		GridBagConstraints gbc_pnSchedules = new GridBagConstraints();
		gbc_pnSchedules.insets = new Insets(5, 5, 5, 5);
		gbc_pnSchedules.fill = GridBagConstraints.BOTH;
		gbc_pnSchedules.gridx = 1;
		gbc_pnSchedules.gridy = 3;
		contentPanel.add(getPnSchedules(), gbc_pnSchedules);
		GridBagConstraints gbc_pnPatients = new GridBagConstraints();
		gbc_pnPatients.insets = new Insets(5, 5, 5, 5);
		gbc_pnPatients.fill = GridBagConstraints.BOTH;
		gbc_pnPatients.gridx = 3;
		gbc_pnPatients.gridy = 1;
		contentPanel.add(getPnPatients(), gbc_pnPatients);
		GridBagConstraints gbc_pnDatabase = new GridBagConstraints();
		gbc_pnDatabase.insets = new Insets(5, 5, 5, 5);
		gbc_pnDatabase.fill = GridBagConstraints.BOTH;
		gbc_pnDatabase.gridx = 3;
		gbc_pnDatabase.gridy = 3;
		contentPanel.add(getPnCreateUsers(), gbc_pnDatabase);
		GridBagConstraints gbc_pnBlank2 = new GridBagConstraints();
        gbc_pnBlank2.insets = new Insets(5, 5, 5, 5);
		gbc_pnBlank2.fill = GridBagConstraints.BOTH;
		gbc_pnBlank2.gridx = 4;
		gbc_pnBlank2.gridy = 4;
		contentPanel.add(new MyBackPanel(), gbc_pnBlank2);
    }

    private JPanel getPnAppointments() {
        if (pnAppointments == null) {
            pnAppointments = new MyFrontPanel();
            pnAppointments.setBorder(new CompoundBorder(Designer.getBorder(), new EmptyBorder(5, 5, 5, 5)));
            pnAppointments.setLayout(new GridLayout(9, 1, 10, 0));
            pnAppointments.add(getLblAppointments());
            pnAppointments.add(Box.createRigidArea(new Dimension(0,0)));
            pnAppointments.add(getBtnCreateAppointment());
            pnAppointments.add(Box.createRigidArea(new Dimension(0,0)));
            pnAppointments.add(getBtnEditAppointment());
        }
        return pnAppointments;
    }

    private JPanel getPnSchedules() {
        if (pnSchedules == null) {
            pnSchedules = new MyFrontPanel();
            pnSchedules.setBorder(new CompoundBorder(Designer.getBorder(), new EmptyBorder(5, 5, 5, 5)));
            pnSchedules.setLayout(new GridLayout(9, 1, 10, 0));
            pnSchedules.add(getLblSchedules());
            pnSchedules.add(Box.createRigidArea(new Dimension(0,0)));
            pnSchedules.add(getBtnManageSchedules());
            pnSchedules.add(Box.createRigidArea(new Dimension(0,0)));
            pnSchedules.add(getBtnManageBreaks());
        }
        return pnSchedules;
    }

    private JPanel getPnPatients() {
        if (pnPatients == null) {
            pnPatients = new MyFrontPanel();
            pnPatients.setBorder(new CompoundBorder(Designer.getBorder(), new EmptyBorder(5, 5, 5, 5)));
            pnPatients.setLayout(new GridLayout(9, 1, 10, 0));
            pnPatients.add(getLblPatients());
            pnPatients.add(Box.createRigidArea(new Dimension(0,0)));
            pnPatients.add(getBtnShowMedicalRecords());
        }
        return pnPatients;
    }

    private JPanel getPnCreateUsers() {
        if (pnCreateUsers == null) {
            pnCreateUsers = new MyFrontPanel();
            pnCreateUsers.setBorder(new CompoundBorder(Designer.getBorder(), new EmptyBorder(5, 5, 5, 5)));
            pnCreateUsers.setLayout(new GridLayout(9, 1, 10, 0));
            pnCreateUsers.add(getLblDatabase());
            pnCreateUsers.add(Box.createRigidArea(new Dimension(0,0)));
            pnCreateUsers.add(getBtnCreateAdmins());
            pnCreateUsers.add(Box.createRigidArea(new Dimension(0,0)));
            pnCreateUsers.add(getBtnCreateDoctors());
            pnCreateUsers.add(Box.createRigidArea(new Dimension(0,0)));
            pnCreateUsers.add(getBtnCreateNurses());
            pnCreateUsers.add(Box.createRigidArea(new Dimension(0,0)));
            pnCreateUsers.add(getBtnCreatePatientss());
        }
        return pnCreateUsers;
    }
    
    private JLabel getLblAppointments() {
    	if (lblAppointments == null) {
    		lblAppointments = new JLabel("Appointments");
    		lblAppointments.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	return lblAppointments;
    }
    
    private JLabel getLblSchedules() {
    	if (lblSchedules == null) {
    		lblSchedules = new JLabel("Schedules");
    		lblSchedules.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	return lblSchedules;
    }
    
    private JLabel getLblPatients() {
    	if (lblPatients == null) {
    		lblPatients = new JLabel("Patients");
    		lblPatients.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	return lblPatients;
    }
    
    private JLabel getLblDatabase() {
    	if (lblDatabase == null) {
    		lblDatabase = new JLabel("Database");
    		lblDatabase.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	return lblDatabase;
    }

    private JButton getBtnCreateAppointment() {
        if (btnCreateAppointment == null) {
            btnCreateAppointment = new JButton("Create appointment");
            btnCreateAppointment.addActionListener(e -> doctorDialog.launchApplyForAppointment());
        }
        return btnCreateAppointment;
    }

    private JButton getBtnEditAppointment() {
        if (btnEditAppointment == null) {
            btnEditAppointment = new JButton("Manage appointments");
            btnEditAppointment.addActionListener(e -> doctorDialog.launchShowMyAppointments());
        }
        return btnEditAppointment;
    }

    private JButton getBtnManageSchedules() {
        if (btnManageSchedules == null) {
            btnManageSchedules = new JButton("Manage schedules");
//            btnManageSchedules.addActionListener(e -> doctorDialog.launchManageWorkSchedule());
        }
        return btnManageSchedules;
    }

    private JButton getBtnManageBreaks() {
        if (btnManageBreaks == null) {
            btnManageBreaks = new JButton("Manage breaks");
//            btnManageBreaks.addActionListener(e -> doctorDialog.launchManageBreaks());
        }
        return btnManageBreaks;
    }

    private JButton getBtnShowMedicalRecords() {
        if (btnShowMedicalRecords == null) {
            btnShowMedicalRecords = new JButton("Show patient info");
//            btnShowMedicalRecords.addActionListener(e -> doctorDialog.launchPatientInfo());
        }
        return btnShowMedicalRecords;
    }

    private JButton getBtnCreateAdmins() {
        if (btnCreateAdmins == null) {
        	btnCreateAdmins = new JButton("Add administrator");
//        	btnCreateAdmins.addActionListener(e -> doctorDialog.launchCreateAdmins());
        }
        return btnCreateAdmins;
    }

    private JButton getBtnCreateDoctors() {
        if (btnCreateDoctors == null) {
        	btnCreateDoctors = new JButton("Add doctor");
//        	btnCreateDoctors.addActionListener(e -> doctorDialog.launchCreateDoctors());
        }
        return btnCreateDoctors;
    }

    private JButton getBtnCreateNurses() {
        if (btnCreateNurses == null) {
        	btnCreateNurses = new JButton("Add nurse");
//        	btnCreateNurses.addActionListener(e -> doctorDialog.launchCreateNurses());
        }
        return btnCreateNurses;
    }

    private JButton getBtnCreatePatientss() {
        if (btnCreatePatients == null) {
        	btnCreatePatients = new JButton("Add patient");
//        	btnCreatePatients.addActionListener(e -> doctorDialog.launchCreatePatients());
        }
        return btnCreatePatients;
    }
    
    ////////////////////////////////////////////////////////////////////////////////

	@Override
	public void setFocus() {
		btnCreateAppointment.requestFocus();
	}

}
