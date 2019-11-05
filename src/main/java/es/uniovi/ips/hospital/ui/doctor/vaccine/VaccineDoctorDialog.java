package es.uniovi.ips.hospital.ui.doctor.vaccine;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Box;

@Component
public class VaccineDoctorDialog extends JDialog {

	private Doctor doctor;
	private final JPanel contentPanel = new JPanel();
	
	@Autowired
	private DoctorDialog dd;
	private JTextField textField;
	private JTextField textField_1;

	
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Create the dialog.
	 */
	public VaccineDoctorDialog() {
		setTitle("Vaccines");
		setBounds(100, 100, 751, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblCreateVaccines = new JLabel("Create Vaccines");
				lblCreateVaccines.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblCreateVaccines, BorderLayout.NORTH);
			}
			{
				JPanel panel_1 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				panel.add(panel_1, BorderLayout.SOUTH);
				{
					JButton btnCreate = new JButton("Create");
					panel_1.add(btnCreate);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new GridLayout(0, 4, 0, 0));
				{
					JLabel lblTipo = new JLabel("Type");
					panel_1.add(lblTipo);
				}
				{
					JComboBox<VaccineType> comboBox = new JComboBox<VaccineType>();
					comboBox.addItem(VaccineType.VIVAS_ATENUADAS);
					comboBox.addItem(VaccineType.INACTIVADAS);
					comboBox.addItem(VaccineType.CON_TOXOIDES);
					comboBox.addItem(VaccineType.SUBUNIDADES_RECOMBINANTES_POLISACÁRIDAS_Y_COMBINADAS);
					panel_1.add(comboBox);
				}
				{
					JLabel lblDescription = new JLabel("Description");
					panel_1.add(lblDescription);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel_1.add(scrollPane);
					{
						JTextArea textArea = new JTextArea();
						textArea.setWrapStyleWord(true);
						textArea.setLineWrap(true);
						scrollPane.setViewportView(textArea);
					}
				}
				{
					java.awt.Component verticalStrut = Box.createVerticalStrut(20);
					panel_1.add(verticalStrut);
				}
				{
					java.awt.Component verticalStrut = Box.createVerticalStrut(20);
					panel_1.add(verticalStrut);
				}
				{
					java.awt.Component verticalStrut = Box.createVerticalStrut(20);
					panel_1.add(verticalStrut);
				}
				{
					java.awt.Component verticalStrut = Box.createVerticalStrut(20);
					panel_1.add(verticalStrut);
				}
				{
					JLabel lblPatient = new JLabel("Patient´s health card number");
					panel_1.add(lblPatient);
				}
				{
					textField = new JTextField();
					panel_1.add(textField);
					textField.setColumns(10);
				}
				{
					JLabel lblDate = new JLabel("Date");
					panel_1.add(lblDate);
				}
				{
					textField_1 = new JTextField();
					panel_1.add(textField_1);
					textField_1.setColumns(10);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Back");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						dd.setVisible(true);
						dd.setDoctor(doctor);
					}
				});
				cancelButton.setMnemonic('b');
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("Exit");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(-1);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

}
