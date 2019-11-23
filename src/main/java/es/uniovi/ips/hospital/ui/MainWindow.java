package es.uniovi.ips.hospital.ui;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.LoginService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class MainWindow {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminDialog adminDialog;

    @Autowired
    private DoctorDialog doctorDialog;
    
    private MainFrame frame;

    public MainWindow() {
        frame = new MainFrame();
        frame.setVisible(true);
    }

    private void login(String email, char[] password) {
        Staff user = loginService.login(email, password);

        // TODO Remove me
        //user = doctorService.findByEmail("doctor@ips.test");
        if (user instanceof Doctor) {
            //frame.setVisible(false);
            doctorDialog.run((Doctor) user);

        } else if (user instanceof AdminAssistant) {
            adminDialog.setVisible(true);
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
    		setTitle("Hospital");
    		//WebLookAndFeel.install();
    		setMinimumSize(new Dimension(600, 600));
    		setPreferredSize(new Dimension(600, 600));
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setLocationRelativeTo(null);
    		setBounds(100, 100, 600, 600);
    		contentPane = new JPanel();
    		contentPane.setLayout(new BorderLayout(0, 0));
    		setContentPane(contentPane);
    		contentPane.add(getBanner(), BorderLayout.NORTH);
    		contentPane.add(getPnCenter(), BorderLayout.CENTER);
    		contentPane.add(getPnSouth(), BorderLayout.SOUTH);
        	addFocusListener(new FocusAdapter() {
        		@Override
        		public void focusGained(FocusEvent e) {
        			setVisible(true);
        		}
        	});
    	}

    	private JLabel getBanner() {
    		if (banner == null) {
    			banner = new JLabel("");
    			banner.setHorizontalAlignment(SwingConstants.LEFT);
    			banner.setBorder(new MatteBorder(0, 0, 1, 0, PaletteFactory.getHighlighter()));
    			ImageIcon image = new ImageIcon();
                try {
                    File file = ResourceUtils.getFile("classpath:estonoesunbanner.png");
                    BufferedImage img = ImageIO.read(file);
                    Image i = img.getScaledInstance(600, 100, java.awt.Image.SCALE_SMOOTH);
                    image = new ImageIcon(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    			banner.setIcon(image);
    		}
    		return banner;
    	}
    	private JPanel getPnCenter() {
    		if (pnCenter == null) {
    			pnCenter = new JPanel();
    			pnCenter.setBackground(PaletteFactory.getBaseDark());
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
    			pnLogIn = new JPanel();
    			pnLogIn.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, PaletteFactory.getHighlighter(), null, null));
    			pnLogIn.setBackground(PaletteFactory.getMainDark());
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
    			lblLogIn.setForeground(PaletteFactory.getLighter());
    			lblLogIn.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
    			lblLogIn.setFont(new Font("Dialog", Font.BOLD, 16));
    		}
    		return lblLogIn;
    	}
    	private JLabel getLblUsername() {
    		if (lblUsername == null) {
    			lblUsername = new JLabel("Email:");
    			lblUsername.setForeground(PaletteFactory.getLighter());
    			lblUsername.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
    		}
    		return lblUsername;
    	}
    	private JTextField getTxtUsername() {
    		if (emailField == null) {
    			emailField = new JTextField();
    			emailField.setFont(new Font("Tahoma", Font.PLAIN, 13));
    			emailField.setBorder(new EmptyBorder(5, 5, 5, 5));
    			emailField.setBackground(PaletteFactory.getBaseDark());
    			emailField.setForeground(PaletteFactory.getLighter());
    			emailField.setCaretColor(PaletteFactory.getLighter());
    			emailField.setHorizontalAlignment(SwingConstants.CENTER);
    			emailField.setColumns(25);
    			emailField.setMaximumSize(emailField.getPreferredSize());
    		}
    		return emailField;
    	}
    	private JLabel getLblPassword() {
    		if (lblPassword == null) {
    			lblPassword = new JLabel("Password:");
    			lblPassword.setForeground(PaletteFactory.getLighter());
    			lblPassword.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
    		}
    		return lblPassword;
    	}
    	private JPasswordField getPFPass() {
    		if (passwordField == null) {
    			passwordField = new JPasswordField();
    			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
    			passwordField.setBorder(new EmptyBorder(5, 5, 5, 5));
    			passwordField.setBackground(PaletteFactory.getBaseDark());
    			passwordField.setForeground(PaletteFactory.getLighter());
    			passwordField.setCaretColor(PaletteFactory.getLighter());
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
    			btnLogIn.setBackground(PaletteFactory.getBaseDark());
    			btnLogIn.setForeground(PaletteFactory.getLighter());
    			btnLogIn.addActionListener(actionEvent -> login(emailField.getText(), passwordField.getPassword()));
    		}
    		return btnLogIn;
    	}
    	private JPanel getPnSouth() {
    		if (pnSouth == null) {
    			pnSouth = new JPanel();
    			pnSouth.setBorder(new MatteBorder(1, 0, 0, 0, PaletteFactory.getHighlighter()));
    			pnSouth.setBackground(PaletteFactory.getMainDark());
    			FlowLayout flowLayout = (FlowLayout) pnSouth.getLayout();
    			flowLayout.setAlignment(FlowLayout.RIGHT);
    			pnSouth.add(getBtnClose());
    		}
    		return pnSouth;
    	}
    	private JButton getBtnClose() {
    		if (btnClose == null) {
    			btnClose = new JButton("Close");
    			btnClose.setBackground(PaletteFactory.getBaseDark());
    			btnClose.setForeground(PaletteFactory.getLighter());
    			btnClose.addActionListener(actionEvent -> {
    	            dispose();
    	            System.exit(0);
    	        });
    		}
    		return btnClose;
    	}
    }
}
