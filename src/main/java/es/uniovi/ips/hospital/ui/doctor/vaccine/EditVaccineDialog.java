package es.uniovi.ips.hospital.ui.doctor.vaccine;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JComboBox;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.VaccineService;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

@Component
public class EditVaccineDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5459106730873677461L;
	private JPanel panel;
	private JLabel label;
	private JComboBox<VaccineType> cbType;
	private JLabel label_1;
	private JTextArea tADescription;
	private JLabel label_2;
	private JTextField tFHealthCard;
	private JLabel label_3;
	private JSpinner spinnerDate;
	private JLabel label_7;
	private JPanel panel_1;
	private JButton btnEdit;
	private JButton btnBack;
	private Vaccine vaccine;
	@Autowired private VaccineService vs;
	@Autowired private PatientService ps;
	private List<Vaccine> vacunas = new ArrayList<>();

	/**
	 * Create the dialog.
	 */
	public EditVaccineDialog() {
		this.vaccine=new Vaccine();
		setResizable(false);
		setBounds(100, 100, 566, 253);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void setVaccine(Vaccine vaccine) {
		this.vaccine=vaccine;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getLabel());
			panel.add(getCbType());
			panel.add(getLabel_1());
			panel.add(getTADescription());
			panel.add(getLabel_2());
			panel.add(getTFHealthCard());
			panel.add(getLabel_3());
			panel.add(getSpinnerDate());
			panel.add(getLabel_7());
			panel.add(getPanel_1());
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Type");
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label;
	}
	private JComboBox<VaccineType> getCbType() {
		if (cbType == null) {
			cbType = new JComboBox<VaccineType>();
			cbType.addItem(VaccineType.VIVAS_ATENUADAS);
			cbType.addItem(VaccineType.INACTIVADAS);
			cbType.addItem(VaccineType.CON_TOXOIDES);
			cbType.addItem(VaccineType.SUBUNIDADES_RECOMBINANTES_POLISACÁRIDAS_Y_COMBINADAS);
		}
		return cbType;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Description");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_1;
	}
	private JTextArea getTADescription() {
		if (tADescription == null) {
			tADescription = new JTextArea();
			tADescription.setWrapStyleWord(true);
			tADescription.setLineWrap(true);
		}
		return tADescription;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Patient´s health card");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_2;
	}
	private JTextField getTFHealthCard() {
		if (tFHealthCard == null) {
			tFHealthCard = new JTextField();
			tFHealthCard.setColumns(10);
		}
		return tFHealthCard;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Date");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_3;
	}
	private JSpinner getSpinnerDate() {
		if (spinnerDate == null) {
			spinnerDate = new JSpinner();
			spinnerDate.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			spinnerDate.setEditor(new javax.swing.JSpinner.DateEditor(spinnerDate, "dd/MM/yyyy"));
		}
		return spinnerDate;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("");
		}
		return label_7;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(1, 0, 0, 0));
			panel_1.add(getBtnEdit());
			panel_1.add(getBtnBack());
		}
		return panel_1;
	}
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getCbType().getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Select vaccine type");
					}else if(getTFHealthCard().getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Type a health card number");
					}else if(!getTFHealthCard().getText().matches("[0-9]+") && getTFHealthCard().getText().length() != 10) {
						JOptionPane.showMessageDialog(null, "Type a correct health card number");
					}else if(getTADescription().getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Type a description");
					}else if(isBefore((Date)getSpinnerDate().getValue(),today())) {
						JOptionPane.showMessageDialog(null, "Date must not be before today. Today is "+new Date());
					}else {
						Vaccine va = vaccine;
						List<Patient> patients = ps.findAllPatient();
						Patient patient = null;
						for(Patient p: patients) {
							if(p.getHealthCardNumber().equals(tFHealthCard.getText())) {
								patient = p;
							}
						}
						if(patient==null) {
							JOptionPane.showMessageDialog(null, "Patient does not exist");
						}else {
							if(va.getPatient().getHealthCardNumber().equals(patient.getHealthCardNumber())) {
								va.setPatient(patient);
								LocalDateTime d=convertToLocalDateTimeViaInstant((Date)spinnerDate.getValue());
								va.setDate(d);
								va.setDescription(tADescription.getText());
								va.setVaccineType((VaccineType)cbType.getSelectedItem());
								vs.createVaccine(va);
								Patient pat = va.getPatient();
								vacunas = vs.findByPatient(pat);
							}else {
								ps.findPatientByDni(va.getPatient().getDni()).getVaccines().remove(va);
								va.setPatient(patient);
								va.setDate(convertToLocalDateTimeViaInstant((Date)spinnerDate.getValue()));
								va.setDescription(tADescription.getText());
								va.setVaccineType((VaccineType)cbType.getSelectedItem());
								vs.createVaccine(va);
								Patient pat = va.getPatient();
								vacunas = vs.findByPatient(pat);
							}
							tFHealthCard.setText(va.getPatient().getHealthCardNumber());
							tADescription.setText("");
							tFHealthCard.setText("");
							spinnerDate.setValue(new Date());
							JOptionPane.showMessageDialog(null, "Vaccine modified");
						}
						
					}
					
				}
			});
		}
		return btnEdit;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return btnBack;
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	/**
	 * @return Today at 00:00:00.000
	 */
	public static Date today() {
		return trunc( new Date() );
	}
	
	public static boolean isBefore(Date date, Date reference) {
		return date.compareTo(reference) < 0;
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
	
	public List<Vaccine> getVacunas(){
		return vacunas;
	}
}
