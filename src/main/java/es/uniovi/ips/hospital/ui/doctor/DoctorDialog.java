package es.uniovi.ips.hospital.ui.doctor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DoctorDialog dialog = new DoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DoctorDialog() {
		setResizable(false);
		setTitle("Doctor Menu");
		setBounds(100, 100, 660, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(2, 3, 0, 0));
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panel.add(horizontalStrut);
			}
			{
				JLabel lblAppointments = new JLabel("Appointments");
				lblAppointments.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblAppointments);
			}
			{
				JButton btnSeeAppointments = new JButton("Appointments");
				btnSeeAppointments.setMnemonic('a');
				panel.add(btnSeeAppointments);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panel.add(horizontalStrut);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panel.add(horizontalStrut);
			}
			{
				JLabel lblNewLabel = new JLabel("Vaccines");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				JButton btnSeeVaccines = new JButton("Vaccines");
				btnSeeVaccines.setMnemonic('v');
				panel.add(btnSeeVaccines);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panel.add(horizontalStrut);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Back");
				okButton.setMnemonic('b');
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Exit");
				cancelButton.setMnemonic('s');
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(-1);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
