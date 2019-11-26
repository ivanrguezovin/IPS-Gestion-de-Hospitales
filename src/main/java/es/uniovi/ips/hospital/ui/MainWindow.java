package es.uniovi.ips.hospital.ui;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.LoginService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import es.uniovi.ips.hospital.ui.nurse.NurseDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyBanner;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.components.MySouthPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

import java.awt.*;

@Component
public class MainWindow {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminDialog adminDialog;

    @Autowired
    private DoctorDialog doctorDialog;
    
    @Autowired
    private NurseDialog nurseDialog;
    
    private MainFrame frame;

    public MainWindow() {
        frame = new MainFrame();
        frame.setVisible( true );
    }

	private void login(String email, char[] password) {
        Staff user = loginService.login(email, password);

        if (user instanceof Doctor) {
            doctorDialog.run((Doctor) user);
        } else if (user instanceof AdminAssistant) {
        	adminDialog.setFrame();
            adminDialog.setVisible(true);
        } else if (user instanceof Nurse) {
        	nurseDialog.run((Nurse) user);
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong user/credentials");
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    
    class MainFrame extends JFrame {

    	private static final long serialVersionUID = 6099612979061213276L;
    	private JPanel contentPane;
    	private JLabel banner;
    	private JPanel pnCenter;
    	private JPanel pnLogIn;
    	private JLabel lblLogIn;
    	private JLabel lblUsername;
    	private JTextField emailField;
    	private JLabel lblPassword;
    	private JPasswordField passwordField;
    	private JButton btnLogIn;
    	private JPanel pnSouth;
    	private JButton btnClose;

    	/**
    	 * Create the frame.
    	 */
    	public MainFrame() {
        	Designer.setDesign();
    		setTitle("Hospital");
    		setMinimumSize(new Dimension(700, 700));
    		setPreferredSize(new Dimension(700, 700));
    		setMaximumSize(getPreferredSize());
    		setResizable(false);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setLocationRelativeTo(null);
    		setBounds(100, 100, 700, 700);
    		contentPane = new JPanel();
    		contentPane.setLayout(new BorderLayout(0, 0));
    		setContentPane(contentPane);
    		contentPane.add(getBanner(), BorderLayout.NORTH);
    		contentPane.add(getPnCenter(), BorderLayout.CENTER);
    		contentPane.add(getPnSouth(), BorderLayout.SOUTH);
    	}

    	private JLabel getBanner() {
    		if (banner == null) {
    			banner = new MyBanner();
    			banner.setHorizontalAlignment(SwingConstants.LEFT);
    		}
    		return banner;
    	}
    	private JPanel getPnCenter() {
    		if (pnCenter == null) {
    			pnCenter = new MyBackPanel();
    			GridBagLayout gbl_pnLogIn = new GridBagLayout();
    			gbl_pnLogIn.columnWidths = new int[]{150,300,150};
    			gbl_pnLogIn.rowHeights = new int[]{0};
    			gbl_pnLogIn.columnWeights = new double[]{0.5,0.0,0.5};
    			gbl_pnLogIn.rowWeights = new double[]{Double.MIN_VALUE};
    			pnCenter.setLayout(gbl_pnLogIn);
    			GridBagConstraints gbc_pnLogIn = new GridBagConstraints();
    			gbc_pnLogIn.fill = GridBagConstraints.HORIZONTAL;
    			gbc_pnLogIn.insets = new Insets(0, 0, 0, 0);
    			gbc_pnLogIn.gridx = 1;
    			gbc_pnLogIn.gridy = 0;
    			pnCenter.add(getPnLogIn(), gbc_pnLogIn);
    		}
    		return pnCenter;
    	}
    	private JPanel getPnLogIn() {
    		if (pnLogIn == null) {
    			pnLogIn = new MyFrontPanel();
    			pnLogIn.setBorder(Designer.getBorder());
    			pnLogIn.setLayout(new BoxLayout(pnLogIn, BoxLayout.Y_AXIS));
    			pnLogIn.add(Box.createRigidArea(new Dimension(0,20)));
    			pnLogIn.add(getLblLogIn());
    			pnLogIn.add(Box.createRigidArea(new Dimension(0,10)));
    			pnLogIn.add(getLblUsername());
    			pnLogIn.add(getTxtUsername());
    			pnLogIn.add(Box.createRigidArea(new Dimension(0,10)));
    			pnLogIn.add(getLblPassword());
    			pnLogIn.add(getPFPass());
    			pnLogIn.add(Box.createRigidArea(new Dimension(0,10)));
    			pnLogIn.add(getBtnLogIn());
    			pnLogIn.add(Box.createRigidArea(new Dimension(0,20)));
    		}
    		return pnLogIn;
    	}
    	private JLabel getLblLogIn() {
    		if (lblLogIn == null) {
    			lblLogIn = new JLabel("Log-in");
    			lblLogIn.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
    			lblLogIn.setFont(new Font("Dialog", Font.BOLD, 16));
    		}
    		return lblLogIn;
    	}
    	private JLabel getLblUsername() {
    		if (lblUsername == null) {
    			lblUsername = new JLabel("Email:");
    			lblUsername.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
    		}
    		return lblUsername;
    	}
    	private JTextField getTxtUsername() {
    		if (emailField == null) {
    			emailField = new JTextField();
    			emailField.setFont(new Font("Tahoma", Font.PLAIN, 13));
    			emailField.setHorizontalAlignment(SwingConstants.CENTER);
    			emailField.setColumns(25);
    			emailField.setMaximumSize(emailField.getPreferredSize());
    		}
    		return emailField;
    	}
    	private JLabel getLblPassword() {
    		if (lblPassword == null) {
    			lblPassword = new JLabel("Password:");
    			lblPassword.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
    		}
    		return lblPassword;
    	}
    	private JPasswordField getPFPass() {
    		if (passwordField == null) {
    			passwordField = new JPasswordField();
    			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
    			passwordField.setHorizontalAlignment(SwingConstants.CENTER);
    			passwordField.setColumns(25);
    			passwordField.setMaximumSize(emailField.getPreferredSize());
    		}
    		return passwordField;
    	}
    	private JButton getBtnLogIn() {
    		if (btnLogIn == null) {
    			btnLogIn = new JButton("Log in");
    			btnLogIn.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			btnLogIn.addActionListener(actionEvent -> login(emailField.getText(), passwordField.getPassword()));
    		}
    		return btnLogIn;
    	}
    	private JPanel getPnSouth() {
    		if (pnSouth == null) {
    			pnSouth = new MySouthPanel();
    			pnSouth.add(getBtnClose());
    		}
    		return pnSouth;
    	}
    	private JButton getBtnClose() {
    		if (btnClose == null) {
    			btnClose = new JButton("Close");
    			btnClose.addActionListener(actionEvent -> {
    	            dispose();
    	            System.exit(0);
    	        });
    		}
    		return btnClose;
    	}
    }
}