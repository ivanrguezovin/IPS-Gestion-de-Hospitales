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
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

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
	private JLabel lblListVaccines;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel label_5;
	private JTextField textField_1;
	private JScrollPane scrollPane_1;
	private JList<Vaccine> list;
	private JButton btnSearch;
	private JPanel panel_9;
	private JButton btnMarkAsApplied;
	private JLabel label_6;
	private JComboBox<VaccineType> comboBox_1;
	private JLabel label_7;
	private JTextArea textArea_1;
	private JLabel lblPatientsHealthCard;
	private JTextField textField_2;
	private JLabel label_9;
	private JSpinner spinner_1;
	private JButton btnEdit;
	
	/**
	 * Create the dialog.
	 */
	public VaccineDoctorDialog() {
		setTitle("Vaccines");
		setBounds(100, 100, 1200, 600);
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
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
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getLblListVaccines(), BorderLayout.NORTH);
			panel_3.add(getPanel_7(), BorderLayout.CENTER);
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
			spinner.setModel(new SpinnerDateModel(now(), null, null, Calendar.DAY_OF_YEAR));
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
						JOptionPane.showMessageDialog(null, "Date must not be before today. Today is " + now());
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
							textArea.setText("");
							textField.setText("");
							spinner.setValue(now());
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
	
	public static Date fromDdMmYyyy(int dd, int mm, int yyyy) {
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.DAY_OF_MONTH, dd);
		c.set(Calendar.MONTH, mm - 1); // base 0
		c.set(Calendar.YEAR, yyyy);
		
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
	private JLabel getLblListVaccines() {
		if (lblListVaccines == null) {
			lblListVaccines = new JLabel("List Vaccines");
			lblListVaccines.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblListVaccines;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(new BorderLayout(0, 0));
			panel_7.add(getPanel_8(), BorderLayout.NORTH);
			panel_7.add(getScrollPane_1(), BorderLayout.CENTER);
			panel_7.add(getPanel_9(), BorderLayout.SOUTH);
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.add(getLabel_5());
			panel_8.add(getTextField_1());
			panel_8.add(getBtnSearch());
		}
		return panel_8;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Patient´s health card number");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_5;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getList());
		}
		return scrollPane_1;
	}
	private JList<Vaccine> getList() {
		if (list == null) {
			list = new JList<Vaccine>();
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return list;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textField_1.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the field");
					}
					Patient patient = null;
					for(Patient p: ps.findAllPatient()) {
						if(p.getHealthCardNumber().equals(textField_1.getText())) {
							patient=p;
						}
					}
					if(patient==null) {
						JOptionPane.showMessageDialog(null, "Patient does not exist");
					}
					List<Vaccine> vaccinesPatient = vs.findByPatient(patient);
					Vaccine[] v = new Vaccine[vaccinesPatient.size()];
					int i=0;
					for(Vaccine c:vaccinesPatient) {
						v[i] = c;
						i++;
					}
					list.setModel(new AbstractListModel<Vaccine>() {
						/**
						 * 
						 */
						private static final long serialVersionUID = -1222956312762187769L;
						Vaccine[] values = v;
						public int getSize() {
							return values.length;
						}
						public Vaccine getElementAt(int index) {
							return values[index];
						}
					});
				}
			});
		}
		return btnSearch;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setLayout(new GridLayout(0, 10, 0, 0));
			panel_9.add(getLabel_6());
			panel_9.add(getComboBox_1());
			panel_9.add(getLabel_7());
			panel_9.add(getTextArea_1());
			panel_9.add(getLblPatientsHealthCard());
			panel_9.add(getTextField_2());
			panel_9.add(getLabel_9());
			panel_9.add(getSpinner_1());
			panel_9.add(getBtnEdit());
			panel_9.add(getBtnMarkAsApplied());
		}
		return panel_9;
	}
	private JButton getBtnMarkAsApplied() {
		if (btnMarkAsApplied == null) {
			btnMarkAsApplied = new JButton("Modify Applied");
			btnMarkAsApplied.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(list.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Select an element from the list");
					}else {
						Vaccine va = list.getSelectedValue();
						if(va.isApplied()) {
							va.setApplied(false);
						}else {
							va.setApplied(true);
						}
						vs.createVaccine(va);
						Patient patient = va.getPatient();
						List<Vaccine> vaccinesPatient = vs.findByPatient(patient);
						Vaccine[] v = new Vaccine[vaccinesPatient.size()];
						int i=0;
						for(Vaccine c:vaccinesPatient) {
							v[i] = c;
							i++;
						}
						list.setModel(new AbstractListModel<Vaccine>() {
							/**
							 * 
							 */
							private static final long serialVersionUID = -1222956312762187769L;
							Vaccine[] values = v;
							public int getSize() {
								return values.length;
							}
							public Vaccine getElementAt(int index) {
								return values[index];
							}
						});
						if(va.isApplied()) {
							JOptionPane.showMessageDialog(null, "Vaccine applied to patient " + patient.getDni());
						}else {
							JOptionPane.showMessageDialog(null, "Vaccine not applied to patient " + patient.getDni());
						}
						
					}
					
				}
			});
		}
		return btnMarkAsApplied;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Type");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_6;
	}
	private JComboBox<VaccineType> getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox<VaccineType>();
			comboBox_1.addItem(VaccineType.VIVAS_ATENUADAS);
			comboBox_1.addItem(VaccineType.INACTIVADAS);
			comboBox_1.addItem(VaccineType.CON_TOXOIDES);
			comboBox_1.addItem(VaccineType.SUBUNIDADES_RECOMBINANTES_POLISACÁRIDAS_Y_COMBINADAS);
		}
		return comboBox_1;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("Description");
			label_7.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_7;
	}
	private JTextArea getTextArea_1() {
		if (textArea_1 == null) {
			textArea_1 = new JTextArea();
			textArea_1.setWrapStyleWord(true);
			textArea_1.setLineWrap(true);
		}
		return textArea_1;
	}
	private JLabel getLblPatientsHealthCard() {
		if (lblPatientsHealthCard == null) {
			lblPatientsHealthCard = new JLabel("Patient´s health card");
			lblPatientsHealthCard.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPatientsHealthCard;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
		}
		return textField_2;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("Date");
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_9;
	}
	private JSpinner getSpinner_1() {
		if (spinner_1 == null) {
			spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerDateModel(now(), null, null, Calendar.DAY_OF_YEAR));
			spinner_1.setEditor(new javax.swing.JSpinner.DateEditor(spinner_1, "dd/MM/yyyy"));
		}
		return spinner_1;
	}
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(list.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Select an element from the list");
					}else if(getComboBox_1().getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Select vaccine type");
					}else if(getTextField_2().getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Type a health card number");
					}else if(!getTextField_2().getText().matches("[0-9]+") && getTextField().getText().length() != 10) {
						JOptionPane.showMessageDialog(null, "Type a correct health card number");
					}else if(getTextArea_1().getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Type a description");
					}else if(isBefore((Date)getSpinner_1().getValue(),today())) {
						JOptionPane.showMessageDialog(null, "Date must not be before today. Today is "+now());
					}else {
						Vaccine va = list.getSelectedValue();
						List<Patient> patients = ps.findAllPatient();
						Patient patient = null;
						for(Patient p: patients) {
							if(p.getHealthCardNumber().equals(textField_2.getText())) {
								patient = p;
							}
						}
						if(patient==null) {
							JOptionPane.showMessageDialog(null, "Patient does not exist");
						}else {
							if(va.getPatient().equals(patient.getHealthCardNumber())) {
								va.setPatient(patient);
								va.setDate(convertToLocalDateTimeViaInstant((Date)spinner_1.getValue()));
								va.setDescription(textArea_1.getText());
								va.setVaccineType((VaccineType)comboBox_1.getSelectedItem());
								vs.createVaccine(va);
								Patient pat = va.getPatient();
								List<Vaccine> vaccinesPatient = vs.findByPatient(pat);
								Vaccine[] v = new Vaccine[vaccinesPatient.size()];
								int i=0;
								for(Vaccine c:vaccinesPatient) {
									v[i] = c;
									i++;
								}
								list.setModel(new AbstractListModel<Vaccine>() {
									/**
									 * 
									 */
									private static final long serialVersionUID = -1222956312762187769L;
									Vaccine[] values = v;
									public int getSize() {
										return values.length;
									}
									public Vaccine getElementAt(int index) {
										return values[index];
									}
								});
							}else {
								ps.findPatientByDni(va.getPatient().getDni()).getVaccines().remove(va);
								va.setPatient(patient);
								va.setDate(convertToLocalDateTimeViaInstant((Date)spinner_1.getValue()));
								va.setDescription(textArea_1.getText());
								va.setVaccineType((VaccineType)comboBox_1.getSelectedItem());
								vs.createVaccine(va);
								Patient pat = va.getPatient();
								List<Vaccine> vaccinesPatient = vs.findByPatient(pat);
								Vaccine[] v = new Vaccine[vaccinesPatient.size()];
								int i=0;
								for(Vaccine c:vaccinesPatient) {
									v[i] = c;
									i++;
								}
								list.setModel(new AbstractListModel<Vaccine>() {
									/**
									 * 
									 */
									private static final long serialVersionUID = -1222956312762187769L;
									Vaccine[] values = v;
									public int getSize() {
										return values.length;
									}
									public Vaccine getElementAt(int index) {
										return values[index];
									}
								});
							}
							textField_1.setText(va.getPatient().getHealthCardNumber());
							textArea_1.setText("");
							textField_2.setText("");
							spinner_1.setValue(now());
							JOptionPane.showMessageDialog(null, "Vaccine modified");
						}
						
					}
					
				}
			});
		}
		return btnEdit;
	}
}
