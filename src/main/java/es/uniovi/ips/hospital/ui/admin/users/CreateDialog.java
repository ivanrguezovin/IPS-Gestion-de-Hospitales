package es.uniovi.ips.hospital.ui.admin.users;

import java.awt.EventQueue;

import javax.swing.JDialog;

public class CreateDialog extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDialog dialog = new CreateDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public CreateDialog() {
		setBounds(100, 100, 450, 300);

	}

}
