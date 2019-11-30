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
	private JComboBox<VaccineType> cbType;
	private JLabel label_2;
	private JScrollPane scrollPane;
	private java.awt.Component verticalStrut;
	private java.awt.Component verticalStrut_1;
	private java.awt.Component verticalStrut_2;
	private java.awt.Component verticalStrut_3;
	private JLabel label_3;
	private JTextField tFHealthCard;
	private JLabel label_4;
	private JPanel panel_5;
	private JSpinner spinnerDate;
	private JPanel panel_6;
	private JButton buttonCreate;
	private Doctor doctor;
	private JTextArea tADescription;
	@Autowired private VaccineService vs;
	@Autowired private PatientService ps;
	private JLabel lblListVaccines;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel label_5;
	private JTextField tfSearchHealthCard;
	private JScrollPane scrollPane_1;
	private JList<Vaccine> listVaccines;
	private JButton btnSearch;
	private JPanel panel_9;
	private JButton btnMarkAsApplied;
	private JButton btnEdit;
	@Autowired private EditVaccineDialog evd;
	private JButton btnRefresh;
	
	
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
			panel_4.add(getCbType());
			panel_4.add(getLabel_2());
			panel_4.add(getScrollPane());
			panel_4.add(getVerticalStrut());
			panel_4.add(getVerticalStrut_1());
			panel_4.add(getVerticalStrut_2());
			panel_4.add(getVerticalStrut_3());
			panel_4.add(getLabel_3());
			panel_4.add(getTFHealthCard());
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
			scrollPane.setViewportView(getTADescription());
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
	private JTextField getTFHealthCard() {
		if (tFHealthCard == null) {
			tFHealthCard = new JTextField();
			tFHealthCard.setColumns(10);
		}
		return tFHealthCard;
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
			panel_5.add(getSpinnerDate());
		}
		return panel_5;
	}
	private JSpinner getSpinnerDate() {
		if (spinnerDate == null) {
			spinnerDate = new JSpinner();
			spinnerDate.setModel(new SpinnerDateModel(now(), null, null, Calendar.DAY_OF_YEAR));
			spinnerDate.setEditor(new javax.swing.JSpinner.DateEditor(spinnerDate, "dd/MM/yyyy"));
		}
		return spinnerDate;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel_6.add(getButtonCreate());
		}
		return panel_6;
	}
	private JButton getButtonCreate() {
		if (buttonCreate == null) {
			buttonCreate = new JButton("Create");
			buttonCreate.setMnemonic('c');
			buttonCreate.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(null, "Date must not be before today. Today is " + now());
					}else {
						String desc = getTADescription().getText();
						VaccineType vt = (VaccineType) getCbType().getSelectedItem();
						LocalDateTime ldt = convertToLocalDateTimeViaInstant((Date)getSpinnerDate().getValue());
						String hcn = tFHealthCard.getText();
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
							tADescription.setText("");
							tFHealthCard.setText("");
							spinnerDate.setValue(now());
						}else {
							JOptionPane.showMessageDialog(null, "Patient does not exist");
						}
						
					}
				}
			});
		}
		return buttonCreate;
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
	
	private JTextArea getTADescription() {
		if (tADescription == null) {
			tADescription = new JTextArea();
			tADescription.setWrapStyleWord(true);
			tADescription.setLineWrap(true);
		}
		return tADescription;
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
			panel_8.add(getTfSearchHealthCard());
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
	private JTextField getTfSearchHealthCard() {
		if (tfSearchHealthCard == null) {
			tfSearchHealthCard = new JTextField();
			tfSearchHealthCard.setColumns(10);
		}
		return tfSearchHealthCard;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getListVaccines());
		}
		return scrollPane_1;
	}
	private JList<Vaccine> getListVaccines() {
		if (listVaccines == null) {
			listVaccines = new JList<Vaccine>();
			listVaccines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listVaccines;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfSearchHealthCard.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the field");
					}
					Patient patient = null;
					for(Patient p: ps.findAllPatient()) {
						if(p.getHealthCardNumber().equals(tfSearchHealthCard.getText())) {
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
					listVaccines.setModel(new AbstractListModel<Vaccine>() {
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
			panel_9.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel_9.add(getBtnEdit());
			panel_9.add(getBtnRefresh());
			panel_9.add(getBtnMarkAsApplied());
		}
		return panel_9;
	}
	private JButton getBtnMarkAsApplied() {
		if (btnMarkAsApplied == null) {
			btnMarkAsApplied = new JButton("Modify Applied");
			btnMarkAsApplied.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listVaccines.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Select an element from the list");
					}else {
						Vaccine va = listVaccines.getSelectedValue();
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
						listVaccines.setModel(new AbstractListModel<Vaccine>() {
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
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listVaccines.getSelectedIndex()==-1) {
						JOptionPane.showMessageDialog(null, "Select an element from the list");
					}else {
						evd.setVisible(true);
						evd.setVaccine(listVaccines.getSelectedValue());
					}
					}
				});
			}
			return btnEdit;
		}
	
	private JButton getBtnRefresh() {
		if (btnRefresh == null) {
			btnRefresh = new JButton("Refresh");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Vaccine[] v = new Vaccine[evd.getVacunas().size()];
					int i=0;
					for(Vaccine c:evd.getVacunas()) {
						v[i] = c;
						i++;
					}
					listVaccines.setModel(new AbstractListModel<Vaccine>() {
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
		return btnRefresh;
	}

}
