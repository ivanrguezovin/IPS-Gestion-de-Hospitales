package es.uniovi.ips.hospital.ui.nurse;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.components.MyBanner;
import es.uniovi.ips.hospital.ui.util.components.MySouthPanel;

import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.ImageIcon;

@Component
public class NurseDialog extends JDialog {

	private static final long serialVersionUID = 259159641592524677L;
	
	@Autowired private NurseMainPanel nurseMainPanel;

	private JLabel banner;
	private JLabel side;
	private JPanel pnSouth;

	/**
	 * Create the dialog.
	 */
	public NurseDialog() {
		setBounds(100, 100, 800, 800);
		setModal(true);
		setResizable(false);
		setTitle("My appointments");
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
			side.setIcon(new ImageIcon(getClass().getResource("/NurseSide.png")));
		}
		return side;
	}

	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new MySouthPanel();
			pnSouth.setPreferredSize(new Dimension(10, 50));
			pnSouth.setMinimumSize(new Dimension(10, 50));
		}
		return pnSouth;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	public void run(Nurse myself) {
		nurseMainPanel.showAppointments(myself);
		nurseMainPanel.fillComboBoxes();
		getContentPane().add(nurseMainPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
