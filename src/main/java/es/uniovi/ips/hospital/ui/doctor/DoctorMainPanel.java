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
    
    @Autowired	private DoctorDialog doctorDialog;

    private final JPanel contentPanel = new MyBackPanel();
    private JPanel pnAppointments;
    private JPanel pnVaccines;
	private JLabel lblAppointments;
	private JLabel lblVaccines;
    private JButton btnApplyForAppointment;
    private JButton btnManageAppointment;
    private JButton btnCreateVaccines;
    private JButton btnManageVaccines;


    /**
     * Create the dialog.
     */
    public DoctorMainPanel() {
        setBounds(100, 100, 650, 700);
        this.setLayout(new BorderLayout());
        this.add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_pnMain = new GridBagLayout();
        gbl_pnMain.columnWidths = new int[]{205, 290, 205};
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
		GridBagConstraints gbc_pnVaccines = new GridBagConstraints();
		gbc_pnVaccines.insets = new Insets(5, 5, 5, 5);
		gbc_pnVaccines.fill = GridBagConstraints.BOTH;
		gbc_pnVaccines.gridx = 1;
		gbc_pnVaccines.gridy = 3;
		contentPanel.add(getPnVaccines(), gbc_pnVaccines);
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
            pnAppointments.add(getBtnManageAppointment());
            pnAppointments.add(Box.createRigidArea(new Dimension(0,0)));
            pnAppointments.add(getBtnApplyForAppointment());
        }
        return pnAppointments;
    }

    private JPanel getPnVaccines() {
        if (pnVaccines == null) {
            pnVaccines = new MyFrontPanel();
            pnVaccines.setBorder(new CompoundBorder(Designer.getBorder(), new EmptyBorder(5, 5, 5, 5)));
            pnVaccines.setLayout(new GridLayout(9, 1, 10, 0));
            pnVaccines.add(getLblVaccines());
            pnVaccines.add(Box.createRigidArea(new Dimension(0,0)));
            pnVaccines.add(getBtnCreateVaccines());
            pnVaccines.add(Box.createRigidArea(new Dimension(0,0)));
            pnVaccines.add(getBtnManageVaccines());
        }
        return pnVaccines;
    }
    
    private JLabel getLblAppointments() {
    	if (lblAppointments == null) {
    		lblAppointments = new JLabel("Appointments");
    		lblAppointments.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	return lblAppointments;
    }
    
    private JLabel getLblVaccines() {
    	if (lblVaccines == null) {
    		lblVaccines = new JLabel("Vaccines");
    		lblVaccines.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	return lblVaccines;
    }

    private JButton getBtnApplyForAppointment() {
        if (btnApplyForAppointment == null) {
            btnApplyForAppointment = new JButton("Apply for appointment");
            btnApplyForAppointment.addActionListener(e -> doctorDialog.launchApplyForAppointment());
        }
        return btnApplyForAppointment;
    }

    private JButton getBtnManageAppointment() {
        if (btnManageAppointment == null) {
            btnManageAppointment = new JButton("Manage appointments");
            btnManageAppointment.addActionListener(e -> doctorDialog.launchShowMyAppointments());
        }
        return btnManageAppointment;
    }

    private JButton getBtnCreateVaccines() {
        if (btnCreateVaccines == null) {
            btnCreateVaccines = new JButton("Create vaccines");
            btnCreateVaccines.addActionListener(e -> doctorDialog.launchCreateVaccine());
        }
        return btnCreateVaccines;
    }

    private JButton getBtnManageVaccines() {
        if (btnManageVaccines == null) {
            btnManageVaccines = new JButton("Manage vaccines");
            btnManageVaccines.addActionListener(e -> doctorDialog.launchShowVaccines());
        }
        return btnManageVaccines;
    }
    
    ////////////////////////////////////////////////////////////////////////////////

	@Override
	public void setFocus() {
		btnApplyForAppointment.requestFocus();
	}

}
