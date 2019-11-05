package es.uniovi.ips.hospital.ui.doctor.vaccine;


import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JComboBox;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.VaccineService;

import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@Component
public class VaccineDoctorDialog extends JDialog {
	
	private static final long serialVersionUID = 8070245607906932070L;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnBack;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel label;
	private JPanel panel_4;
	private JLabel label_1;
	private JComboBox<VaccineType> comboBox;
	private JLabel label_2;
	private JScrollPane scrollPane;
	private java.awt.Component verticalStrut;
	private java.awt.Component verticalStrut_1;
	private java.awt.Component verticalStrut_2;
	private java.awt.Component verticalStrut_3;
	private JLabel label_3;
	private JTextField textField;
	private JLabel label_4;
	private JPanel panel_5;
	private JSpinner spinner;
	private JPanel panel_6;
	private JButton button;
	private Doctor doctor;
	private JTextArea textArea;
	@Autowired private VaccineService vs;
	@Autowired private PatientService ps;
	
	/**
	 * Create the dialog.
	 */
	public VaccineDoctorDialog() {
		setTitle("Vaccines");
		setBounds(100, 100, 771, 445);
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.add(getBtnBack());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 1, 0, 0));
			panel_1.add(getPanel_2());
			panel_1.add(getPanel_3());
		}
		return panel_1;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
		}
		return btnBack;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getLabel(), BorderLayout.NORTH);
			panel_2.add(getPanel_4(), BorderLayout.CENTER);
			panel_2.add(getPanel_6(), BorderLayout.SOUTH);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Create Vaccines");
			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return label;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(0, 4, 0, 0));
			panel_4.add(getLabel_1());
			panel_4.add(getComboBox());
			panel_4.add(getLabel_2());
			panel_4.add(getScrollPane());
			panel_4.add(getVerticalStrut());
			panel_4.add(getVerticalStrut_1());
			panel_4.add(getVerticalStrut_2());
			panel_4.add(getVerticalStrut_3());
			panel_4.add(getLabel_3());
			panel_4.add(getTextField());
			panel_4.add(getLabel_4());
			panel_4.add(getPanel_5());
		}
		return panel_4;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Type");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_1;
	}
	private JComboBox<VaccineType> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<VaccineType>();
			comboBox.addItem(VaccineType.VIVAS_ATENUADAS);
			comboBox.addItem(VaccineType.INACTIVADAS);
			comboBox.addItem(VaccineType.CON_TOXOIDES);
			comboBox.addItem(VaccineType.SUBUNIDADES_RECOMBINANTES_POLISACÁRIDAS_Y_COMBINADAS);
		}
		return comboBox;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Description");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_2;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private java.awt.Component getVerticalStrut() {
		if (verticalStrut == null) {
			verticalStrut = Box.createVerticalStrut(20);
		}
		return verticalStrut;
	}
	private java.awt.Component getVerticalStrut_1() {
		if (verticalStrut_1 == null) {
			verticalStrut_1 = Box.createVerticalStrut(20);
		}
		return verticalStrut_1;
	}
	private java.awt.Component getVerticalStrut_2() {
		if (verticalStrut_2 == null) {
			verticalStrut_2 = Box.createVerticalStrut(20);
		}
		return verticalStrut_2;
	}
	private java.awt.Component getVerticalStrut_3() {
		if (verticalStrut_3 == null) {
			verticalStrut_3 = Box.createVerticalStrut(20);
		}
		return verticalStrut_3;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Patient´s health card number");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_3;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Date");
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new GridLayout(0, 1, 0, 0));
			panel_5.add(getSpinner());
		}
		return panel_5;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(now(), now(), null, Calendar.DAY_OF_YEAR));
			spinner.setEditor(new javax.swing.JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
		}
		return spinner;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel_6.add(getButton());
		}
		return panel_6;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Create");
			button.setMnemonic('c');
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getComboBox().getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Select vaccine type");
					}else if(getTextField().getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Type a health card number");
					}else if(!getTextField().getText().matches("[0-9]+") && getTextField().getText().length() != 10) {
						JOptionPane.showMessageDialog(null, "Type a correct health card number");
					}else if(getTextArea().getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Type a description");
					}else if(isBefore((Date)getSpinner().getValue(),today())) {
						JOptionPane.showMessageDialog(null, "Date must not be before today");
					}else {
						String desc = getTextArea().getText();
						VaccineType vt = (VaccineType) getComboBox().getSelectedItem();
						LocalDateTime ldt = convertToLocalDateTimeViaInstant((Date)getSpinner().getValue());
						String hcn = textField.getText();
						List<Patient> patients = ps.findAllPatient();
						Patient patient = null;
						for(Patient p: patients) {
							if(p.getHealthCardNumber().equals(hcn)) {
								patient = p;
							}
						}
						if(patient != null) {
							Vaccine v = new Vaccine(vt,desc,ldt,false,patient);
							vs.createVaccine(v);
							JOptionPane.showMessageDialog(null, "Vaccine added to " + patient.getDni());
						}else {
							JOptionPane.showMessageDialog(null, "Patient does not exist");
						}
						
					}
				}
			});
		}
		return button;
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public static boolean isAfter(Date date, Date reference) {
		return date.compareTo(reference) > 0;
	}

	public static boolean isBefore(Date date, Date reference) {
		return date.compareTo(reference) < 0;
	}

	/**
	 * @return Today at 00:00:00.000
	 */
	public static Date today() {
		return trunc( new Date() );
	}

	/**
	 * @return Today at hh:mm:ss.mmm, for example 12/12/2012 12:10:23.021
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * Truncates a date by setting hh:mm:ss to 00:00:00. 
	 * For example for date "12/02/2012 13:24:34" returns "12/02/2012 00:00:00.000"
	 * It is useful for comparing dates in the same day. 
	 * @param date
	 * @return 
	 */
	public static Date trunc(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setWrapStyleWord(true);
			textArea.setLineWrap(true);
		}
		return textArea;
	}
}
