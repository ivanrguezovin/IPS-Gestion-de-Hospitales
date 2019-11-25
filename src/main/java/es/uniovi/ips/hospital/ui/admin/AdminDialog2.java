package es.uniovi.ips.hospital.ui.admin;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.ui.admin.appointment.CreateAppointmentPanel;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBanner;
import es.uniovi.ips.hospital.ui.util.components.MySouthPanel;

import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@Component
public class AdminDialog2 extends JDialog {

	private static final long serialVersionUID = 4742925315288866290L;

    @Autowired
    private AdminMainPanel mainPanel;
    @Autowired
    private CreateAppointmentPanel createAppointmentPanel;

	private JPanel current;
	private JLabel banner;
	private JLabel side;
	private JPanel pnSouth;
	private JButton btnBack;

	/**
	 * Create the dialog.
	 */
	public AdminDialog2() {
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
	
	public void setFrame() {
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		current = mainPanel;
		getContentPane().revalidate();
	}
	
	private void launch(JPanel panel) {
		getContentPane().remove(current);
		getContentPane().add(panel, BorderLayout.CENTER);
		current = panel;
		getContentPane().revalidate();
		getContentPane().repaint();
		((Shiftable) panel).setFocus();
		btnBack.setEnabled(true);
	}
	
	private void back() {
		launch(mainPanel);
		btnBack.setEnabled(false);
	}
	
	public void launchCreateAppointment() {
        createAppointmentPanel.fillComboBoxes();
        launch(createAppointmentPanel);
    }
}
