package es.uniovi.ips.hospital.ui.admin.users;

import javax.swing.JDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.AdminAssistantService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.service.PatientService;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@Component
public class CreateUsersDialog extends JDialog {
	
	private static final long serialVersionUID = 1958924359775244981L;
	

	@Autowired private AdminAssistantService adminService;
	@Autowired private DoctorService doctorService;
	@Autowired private PatientService patientService;
	@Autowired private NurseService nurseService;
	
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnAddNurse;
	private JButton btnAddPatient;
	private JButton btnAddAdmin;
	private JLabel label;
	private JTextField txDni2;
	private JLabel label_1;
	private JTextField txName2;
	private JLabel label_2;
	private JTextField txSurname2;
	private JLabel label_3;
	private JTextField txEmail2;
	private JLabel label_4;
	private JTextField txPass2;
	private JLabel label_5;
	private JTextField txStreet2;
	private JLabel label_6;
	private JTextField txCity2;
	private JLabel label_7;
	private JTextField txZip2;
	private JLabel lbSpeciality;
	private JTextField txSpeciality;
	private JLabel lblCollegeN;
	private JTextField txCollege;
	private java.awt.Component horizontalStrut_8;
	private java.awt.Component horizontalStrut_9;
	private java.awt.Component horizontalStrut_10;
	private java.awt.Component horizontalStrut_11;
	private JButton btnAddDoctor;
	private JLabel label_8;
	private JTextField txDni3;
	private JLabel label_9;
	private JTextField txName3;
	private JLabel label_10;
	private JTextField txSurname3;
	private JLabel label_11;
	private JTextField txEmail3;
	private JLabel label_12;
	private JTextField txPass3;
	private JLabel label_13;
	private JTextField txStreet3;
	private JLabel label_14;
	private JTextField txCity3;
	private JLabel label_15;
	private JTextField txZip3;
	private JLabel label_16;
	private JTextField txSpeciality2;
	private JLabel label_17;
	private JTextField txCollege2;
	private java.awt.Component horizontalStrut_12;
	private java.awt.Component horizontalStrut_13;
	private java.awt.Component horizontalStrut_14;
	private java.awt.Component horizontalStrut_15;
	private JLabel label_18;
	private JTextField txDni4;
	private JLabel label_19;
	private JTextField txName4;
	private JLabel label_20;
	private JTextField txSurname4;
	private JLabel label_21;
	private JTextField txEmail4;
	private JLabel label_22;
	private JTextField txPass4;
	private JLabel label_23;
	private JTextField txStreet4;
	private JLabel label_24;
	private JTextField txCity4;
	private JLabel label_25;
	private JTextField txZip4;
	private JLabel lblHistorynumber;
	private JTextField txHistory;
	private JLabel lblHealthCard;
	private JTextField txCard;
	private java.awt.Component horizontalStrut_16;
	private java.awt.Component horizontalStrut_17;
	private java.awt.Component horizontalStrut_18;
	private java.awt.Component horizontalStrut_19;
	private java.awt.Component horizontalStrut;
	private java.awt.Component horizontalStrut_1;
	
	@Autowired public CreateAdminsDialog cad;

	/**
	 * Create the dialog.
	 */
	public CreateUsersDialog() {
		setTitle("Create Users");
		setBounds(100, 100, 593, 373);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(4, 0, 0, 0));
			panel.add(getPanel_4());
			panel.add(getPanel_1());
			panel.add(getPanel_2());
			panel.add(getPanel_3());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Create Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setLayout(new GridLayout(0, 16, 0, 0));
			panel_1.add(getLabel());
			panel_1.add(getTxDni2());
			panel_1.add(getLabel_1());
			panel_1.add(getTxName2());
			panel_1.add(getLabel_2());
			panel_1.add(getTxSurname2());
			panel_1.add(getLabel_3());
			panel_1.add(getTxEmail2());
			panel_1.add(getLabel_4());
			panel_1.add(getTxPass2());
			panel_1.add(getLabel_5());
			panel_1.add(getTxStreet2());
			panel_1.add(getLabel_6());
			panel_1.add(getTxCity2());
			panel_1.add(getLabel_7());
			panel_1.add(getTxZip2());
			panel_1.add(getLbSpeciality());
			panel_1.add(getTxSpeciality());
			panel_1.add(getLblCollegeN());
			panel_1.add(getTxCollege());
			panel_1.add(getHorizontalStrut_8());
			panel_1.add(getHorizontalStrut_9());
			panel_1.add(getHorizontalStrut_10());
			panel_1.add(getHorizontalStrut_11());
			panel_1.add(getBtnAddDoctor());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Create Nurse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setLayout(new GridLayout(0, 16, 0, 0));
			panel_2.add(getLabel_8());
			panel_2.add(getTxDni3());
			panel_2.add(getLabel_9());
			panel_2.add(getTxName3());
			panel_2.add(getLabel_10());
			panel_2.add(getTxSurname3());
			panel_2.add(getLabel_11());
			panel_2.add(getTxEmail3());
			panel_2.add(getLabel_12());
			panel_2.add(getTxPass3());
			panel_2.add(getLabel_13());
			panel_2.add(getTxStreet3());
			panel_2.add(getLabel_14());
			panel_2.add(getTxCity3());
			panel_2.add(getLabel_15());
			panel_2.add(getTxZip3());
			panel_2.add(getLabel_16());
			panel_2.add(getTxSpeciality2());
			panel_2.add(getLabel_17());
			panel_2.add(getTxCollege2());
			panel_2.add(getHorizontalStrut_12());
			panel_2.add(getHorizontalStrut_13());
			panel_2.add(getHorizontalStrut_14());
			panel_2.add(getHorizontalStrut_15());
			panel_2.add(getBtnAddNurse());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Create Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setLayout(new GridLayout(0, 16, 0, 0));
			panel_3.add(getLabel_18());
			panel_3.add(getTxDni4());
			panel_3.add(getLabel_19());
			panel_3.add(getTxName4());
			panel_3.add(getLabel_20());
			panel_3.add(getTxSurname4());
			panel_3.add(getLabel_21());
			panel_3.add(getTxEmail4());
			panel_3.add(getLabel_22());
			panel_3.add(getTxPass4());
			panel_3.add(getLabel_23());
			panel_3.add(getTxStreet4());
			panel_3.add(getLabel_24());
			panel_3.add(getTxCity4());
			panel_3.add(getLabel_25());
			panel_3.add(getTxZip4());
			panel_3.add(getLblHistorynumber());
			panel_3.add(getTxHistory());
			panel_3.add(getLblHealthCard());
			panel_3.add(getTxCard());
			panel_3.add(getHorizontalStrut_16());
			panel_3.add(getHorizontalStrut_17());
			panel_3.add(getHorizontalStrut_18());
			panel_3.add(getHorizontalStrut_19());
			panel_3.add(getBtnAddPatient());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(null, "Create Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_4.setLayout(new GridLayout(0, 5, 0, 0));
			panel_4.add(getHorizontalStrut_20());
			panel_4.add(getHorizontalStrut_1_1());
			panel_4.add(getBtnAddAdmin());
		}
		return panel_4;
	}
	private JButton getBtnAddNurse() {
		if (btnAddNurse == null) {
			btnAddNurse = new JButton("Add");
			btnAddNurse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Doctor> doctors = doctorService.findAllDoctors();
					List<AdminAssistant> assistants = adminService.findAllAdminAssistant();
					List<Nurse> nurses = nurseService.findAllNurses();
					boolean check = false;
					String a = String.valueOf(txPass3.getText().length());
					if(txCity3.getText().equals("") || txDni3.getText().equals("") || txZip3.getText().equals("") 
							|| txName3.getText().equals("") || txPass3.getText().equals("") || txSurname3.getText().equals("") 
							|| txStreet3.getText().equals("") || txEmail3.getText().equals("") || txSpeciality2.getText().equals("")
							|| txCollege2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the fields");
					}else if(!txEmail3.getText().matches("[a-zA-Z1-9]+@[a-zA-Z1-9]+.[a-zA-Z1-9]+")){
						JOptionPane.showMessageDialog(null, "Wrong email format");
					}else if(a.equals("0") || a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4")){
						JOptionPane.showMessageDialog(null, "Wrong password lenght");
					}else if(!txDni3.getText().matches("[0-9]+[A-Z]") || !String.valueOf(txDni3.getText().length()).equals("9")){
						JOptionPane.showMessageDialog(null, "Wrong dni format");
					}else if(!isNumeric(txCollege2.getText())) {
						JOptionPane.showMessageDialog(null, "Wrong college number format");
					}else if(Long.parseLong(txCollege2.getText()) < 1) {
						JOptionPane.showMessageDialog(null, "College number must be a positive value (>0)");
					}else{
						for(Doctor aa: doctors) {
							if(txDni3.getText().equals(aa.getDni()) || txCollege2.getText().equals(Long.toString(aa.getCollegeNumber()))) {
								JOptionPane.showMessageDialog(null, "Staff member already exists");
								check=true;
							}
						}
						for(AdminAssistant aa: assistants) {
							if(txDni3.getText().equals(aa.getDni())) {
								JOptionPane.showMessageDialog(null, "Staff member already exists");
								check=true;
							}
						}
						for(Nurse aa: nurses) {
							if(txDni3.getText().equals(aa.getDni())|| txCollege2.getText().equals(Long.toString(aa.getCollegeNumber()))) {
								JOptionPane.showMessageDialog(null, "Nurse already exists");
								check=true;
							}
						}
						if(!check) {
							Nurse as = new Nurse(txDni3.getText(),txName3.getText(),txSurname3.getText(),
									txEmail3.getText(), txPass3.getText(),txStreet3.getText(),txCity3.getText(),txZip3.getText()
									, txSpeciality2.getText(), Long.parseLong(txCollege2.getText()));
							nurseService.createNurse(as);
							JOptionPane.showMessageDialog(null, "Nurse created");
							txCity3.setText("");
							txZip3.setText("");
							txSurname3.setText("");
							txStreet3.setText("");
							txPass3.setText("");
							txName3.setText("");
							txEmail3.setText("");
							txDni3.setText("");
							txSpeciality2.setText("");
							txCollege2.setText("");
						}
						
					}
				}
			});
		}
		return btnAddNurse;
	}
	private JButton getBtnAddPatient() {
		if (btnAddPatient == null) {
			btnAddPatient = new JButton("Add");
			btnAddPatient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Patient> patients = patientService.findAllPatient();
					List<Doctor> doctors = doctorService.findAllDoctors();
					List<AdminAssistant> assistants = adminService.findAllAdminAssistant();
					List<Nurse> nurses = nurseService.findAllNurses();
					boolean check = false;
					String a = String.valueOf(txPass4.getText().length());
					String b = String.valueOf(txCard.getText().length());
					if(txCity4.getText().equals("") || txDni4.getText().equals("") || txZip4.getText().equals("") 
							|| txName4.getText().equals("") || txPass4.getText().equals("") || txSurname4.getText().equals("") 
							|| txStreet4.getText().equals("") || txEmail4.getText().equals("") || txCard.getText().equals("")
							|| txHistory.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the fields");
					}else if(!txEmail4.getText().matches("[a-zA-Z1-9]+@[a-zA-Z1-9]+.[a-zA-Z1-9]+")){
						JOptionPane.showMessageDialog(null, "Wrong email format");
					}else if(a.equals("0") || a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4")){
						JOptionPane.showMessageDialog(null, "Wrong password lenght");
					}else if(!b.equals("10")){
						JOptionPane.showMessageDialog(null, "Wrong health card lenght");
					}else if(!txDni4.getText().matches("[0-9]+[A-Z]") || !String.valueOf(txDni4.getText().length()).equals("9")){
						JOptionPane.showMessageDialog(null, "Wrong dni format");
					}else if(!isNumeric(txHistory.getText())) {
						JOptionPane.showMessageDialog(null, "Wrong history number format");
					}else if(Integer.parseInt(txHistory.getText()) < 1) {
						JOptionPane.showMessageDialog(null, "College number must be a positive value (>0)");
					}else {
						for(Patient p: patients) {
							if(txDni4.getText().equals(p.getDni()) || Integer.parseInt(txHistory.getText())==p.getHistoryNumber()
									|| txCard.getText().equals(p.getHealthCardNumber())) {
								JOptionPane.showMessageDialog(null, "Patient already exists");
								check=true;
							}
						}
						for(Doctor aa: doctors) {
							if(txDni4.getText().equals(aa.getDni())) {
								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog(null, "Patient is a staff member that will be added with his data. Do you want do that?",
										"Warning",dialogButton);
								if(dialogResult == JOptionPane.YES_OPTION){
									txName4.setText(aa.getName());
									txSurname4.setText(aa.getSurname());
									txEmail4.setText(aa.getEmail());
									txStreet4.setText(aa.getAddress().getStreet());
									txCity4.setText(aa.getAddress().getCity());
									txZip4.setText(aa.getAddress().getZipCode());
									txPass4.setText(aa.getPassword());
								}else {
									check=true;
									JOptionPane.showMessageDialog(null,"Process canceled");
								}
							}
						}
						for(AdminAssistant aa: assistants) {
							if(txDni4.getText().equals(aa.getDni())) {
								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog(null, "Patient is a staff member that will be added with his data. Do you want do that?",
										"Warning",dialogButton);
								if(dialogResult == JOptionPane.YES_OPTION){
									txName4.setText(aa.getName());
									txSurname4.setText(aa.getSurname());
									txEmail4.setText(aa.getEmail());
									txStreet4.setText(aa.getAddress().getStreet());
									txCity4.setText(aa.getAddress().getCity());
									txZip4.setText(aa.getAddress().getZipCode());
									txPass4.setText(aa.getPassword());
								}else {
									check=true;
									JOptionPane.showMessageDialog(null,"Process canceled");
								}
							}
						}
						for(Nurse aa: nurses) {
							if(txDni4.getText().equals(aa.getDni())) {
								int dialogButton = JOptionPane.YES_NO_OPTION;
								int dialogResult = JOptionPane.showConfirmDialog(null, "Patient is a staff member that will be added with his data. Do you want do that?",
										"Warning",dialogButton);
								if(dialogResult == JOptionPane.YES_OPTION){
									txName4.setText(aa.getName());
									txSurname4.setText(aa.getSurname());
									txEmail4.setText(aa.getEmail());
									txStreet4.setText(aa.getAddress().getStreet());
									txCity4.setText(aa.getAddress().getCity());
									txZip4.setText(aa.getAddress().getZipCode());
									txPass4.setText(aa.getPassword());
								}else {
									check=true;
									JOptionPane.showMessageDialog(null,"Process canceled");
								}
							}
						}
						if(!check){
							Patient patient = new Patient(txDni4.getText(),txName4.getText(),txSurname4.getText(),
									txEmail4.getText(), txStreet4.getText(), txCity4.getText(), txZip4.getText(),
									txCard.getText(), Integer.parseInt(txHistory.getText()));
							patientService.createPatient(patient);
							JOptionPane.showMessageDialog(null, "Patient created");
							txCity4.setText("");
							txZip4.setText("");
							txSurname4.setText("");
							txStreet4.setText("");
							txPass4.setText("");
							txName4.setText("");
							txEmail4.setText("");
							txDni4.setText("");
							txHistory.setText("");
							txCard.setText("");
						}
					}
				}
			});
		}
		return btnAddPatient;
	}
	private JButton getBtnAddAdmin() {
		if (btnAddAdmin == null) {
			btnAddAdmin = new JButton("Create Admin");
			btnAddAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					cad.setVisible(true);
				}
			});
		}
		return btnAddAdmin;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Dni");
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label;
	}
	private JTextField getTxDni2() {
		if (txDni2 == null) {
			txDni2 = new JTextField();
			txDni2.setColumns(10);
		}
		return txDni2;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Name");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_1;
	}
	private JTextField getTxName2() {
		if (txName2 == null) {
			txName2 = new JTextField();
			txName2.setColumns(10);
		}
		return txName2;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Surname");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_2;
	}
	private JTextField getTxSurname2() {
		if (txSurname2 == null) {
			txSurname2 = new JTextField();
			txSurname2.setColumns(10);
		}
		return txSurname2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Email");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_3;
	}
	private JTextField getTxEmail2() {
		if (txEmail2 == null) {
			txEmail2 = new JTextField();
			txEmail2.setColumns(10);
		}
		return txEmail2;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Password");
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_4;
	}
	private JTextField getTxPass2() {
		if (txPass2 == null) {
			txPass2 = new JTextField();
			txPass2.setColumns(10);
		}
		return txPass2;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Addres Street");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_5;
	}
	private JTextField getTxStreet2() {
		if (txStreet2 == null) {
			txStreet2 = new JTextField();
			txStreet2.setColumns(10);
		}
		return txStreet2;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Address City");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_6;
	}
	private JTextField getTxCity2() {
		if (txCity2 == null) {
			txCity2 = new JTextField();
			txCity2.setColumns(10);
		}
		return txCity2;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("ZIP Code");
			label_7.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_7;
	}
	private JTextField getTxZip2() {
		if (txZip2 == null) {
			txZip2 = new JTextField();
			txZip2.setColumns(10);
		}
		return txZip2;
	}
	private JLabel getLbSpeciality() {
		if (lbSpeciality == null) {
			lbSpeciality = new JLabel("Speciality");
			lbSpeciality.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbSpeciality;
	}
	private JTextField getTxSpeciality() {
		if (txSpeciality == null) {
			txSpeciality = new JTextField();
			txSpeciality.setText("");
			txSpeciality.setColumns(10);
		}
		return txSpeciality;
	}
	private JLabel getLblCollegeN() {
		if (lblCollegeN == null) {
			lblCollegeN = new JLabel("College Nº");
			lblCollegeN.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCollegeN;
	}
	private JTextField getTxCollege() {
		if (txCollege == null) {
			txCollege = new JTextField();
			txCollege.setColumns(10);
		}
		return txCollege;
	}
	private java.awt.Component getHorizontalStrut_8() {
		if (horizontalStrut_8 == null) {
			horizontalStrut_8 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_8;
	}
	private java.awt.Component getHorizontalStrut_9() {
		if (horizontalStrut_9 == null) {
			horizontalStrut_9 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_9;
	}
	private java.awt.Component getHorizontalStrut_10() {
		if (horizontalStrut_10 == null) {
			horizontalStrut_10 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_10;
	}
	private java.awt.Component getHorizontalStrut_11() {
		if (horizontalStrut_11 == null) {
			horizontalStrut_11 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_11;
	}
	private JButton getBtnAddDoctor() {
		if (btnAddDoctor == null) {
			btnAddDoctor = new JButton("Add");
			btnAddDoctor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Doctor> doctors = doctorService.findAllDoctors();
					List<AdminAssistant> assistants = adminService.findAllAdminAssistant();
					List<Nurse> nurses = nurseService.findAllNurses();
					boolean check = false;
					String a = String.valueOf(txPass2.getText().length());
					if(txCity2.getText().equals("") || txDni2.getText().equals("") || txZip2.getText().equals("") 
							|| txName2.getText().equals("") || txPass2.getText().equals("") || txSurname2.getText().equals("") 
							|| txStreet2.getText().equals("") || txEmail2.getText().equals("") || txSpeciality.getText().equals("")
							|| txCollege.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the fields");
					}else if(!txEmail2.getText().matches("[a-zA-Z1-9]+@[a-zA-Z1-9]+.[a-zA-Z1-9]+")){
						JOptionPane.showMessageDialog(null, "Wrong email format");
					}else if(a.equals("0") || a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4")){
						JOptionPane.showMessageDialog(null, "Wrong password lenght");
					}else if(!txDni2.getText().matches("[0-9]+[A-Z]") || !String.valueOf(txDni2.getText().length()).equals("9")){
						JOptionPane.showMessageDialog(null, "Wrong dni format");
					}else if(!isNumeric(txCollege.getText())) {
						JOptionPane.showMessageDialog(null, "Wrong college number format");
					}else if(Long.parseLong(txCollege.getText()) < 1) {
						JOptionPane.showMessageDialog(null, "College number must be a positive value (>0)");
					}else{
						for(Doctor aa: doctors) {
							if(txDni2.getText().equals(aa.getDni()) || txCollege.getText().equals(Long.toString(aa.getCollegeNumber()))) {
								JOptionPane.showMessageDialog(null, "Doctor already exists");
								check=true;
							}
						}
						for(AdminAssistant aa: assistants) {
							if(txDni2.getText().equals(aa.getDni())) {
								JOptionPane.showMessageDialog(null, "Staff member already exists");
								check=true;
							}
						}
						for(Nurse aa: nurses) {
							if(txDni2.getText().equals(aa.getDni())) {
								JOptionPane.showMessageDialog(null, "Staff member already exists");
								check=true;
							}
						}
						if(!check) {
							Doctor as = new Doctor(txDni2.getText(),txName2.getText(),txSurname2.getText(),
									txEmail2.getText(), txPass2.getText(),txStreet2.getText(),txCity2.getText(),txZip2.getText()
									, txSpeciality.getText(), Long.parseLong(txCollege.getText()));
							doctorService.createDoctor(as);
							JOptionPane.showMessageDialog(null, "Doctor created");
							txCity2.setText("");
							txZip2.setText("");
							txSurname2.setText("");
							txStreet2.setText("");
							txPass2.setText("");
							txName2.setText("");
							txEmail2.setText("");
							txDni2.setText("");
							txSpeciality.setText("");
							txCollege.setText("");
						}
						
					}
				}
			});
		}
		return btnAddDoctor;
	}
	
	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("Dni");
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_8;
	}
	private JTextField getTxDni3() {
		if (txDni3 == null) {
			txDni3 = new JTextField();
			txDni3.setColumns(10);
		}
		return txDni3;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("Name");
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_9;
	}
	private JTextField getTxName3() {
		if (txName3 == null) {
			txName3 = new JTextField();
			txName3.setColumns(10);
		}
		return txName3;
	}
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("Surname");
			label_10.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_10;
	}
	private JTextField getTxSurname3() {
		if (txSurname3 == null) {
			txSurname3 = new JTextField();
			txSurname3.setColumns(10);
		}
		return txSurname3;
	}
	private JLabel getLabel_11() {
		if (label_11 == null) {
			label_11 = new JLabel("Email");
			label_11.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_11;
	}
	private JTextField getTxEmail3() {
		if (txEmail3 == null) {
			txEmail3 = new JTextField();
			txEmail3.setColumns(10);
		}
		return txEmail3;
	}
	private JLabel getLabel_12() {
		if (label_12 == null) {
			label_12 = new JLabel("Password");
			label_12.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_12;
	}
	private JTextField getTxPass3() {
		if (txPass3 == null) {
			txPass3 = new JTextField();
			txPass3.setColumns(10);
		}
		return txPass3;
	}
	private JLabel getLabel_13() {
		if (label_13 == null) {
			label_13 = new JLabel("Addres Street");
			label_13.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_13;
	}
	private JTextField getTxStreet3() {
		if (txStreet3 == null) {
			txStreet3 = new JTextField();
			txStreet3.setColumns(10);
		}
		return txStreet3;
	}
	private JLabel getLabel_14() {
		if (label_14 == null) {
			label_14 = new JLabel("Address City");
			label_14.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_14;
	}
	private JTextField getTxCity3() {
		if (txCity3 == null) {
			txCity3 = new JTextField();
			txCity3.setColumns(10);
		}
		return txCity3;
	}
	private JLabel getLabel_15() {
		if (label_15 == null) {
			label_15 = new JLabel("ZIP Code");
			label_15.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_15;
	}
	private JTextField getTxZip3() {
		if (txZip3 == null) {
			txZip3 = new JTextField();
			txZip3.setColumns(10);
		}
		return txZip3;
	}
	private JLabel getLabel_16() {
		if (label_16 == null) {
			label_16 = new JLabel("Speciality");
			label_16.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_16;
	}
	private JTextField getTxSpeciality2() {
		if (txSpeciality2 == null) {
			txSpeciality2 = new JTextField();
			txSpeciality2.setText("");
			txSpeciality2.setColumns(10);
		}
		return txSpeciality2;
	}
	private JLabel getLabel_17() {
		if (label_17 == null) {
			label_17 = new JLabel("College Nº");
			label_17.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_17;
	}
	private JTextField getTxCollege2() {
		if (txCollege2 == null) {
			txCollege2 = new JTextField();
			txCollege2.setColumns(10);
		}
		return txCollege2;
	}
	private java.awt.Component getHorizontalStrut_12() {
		if (horizontalStrut_12 == null) {
			horizontalStrut_12 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_12;
	}
	private java.awt.Component getHorizontalStrut_13() {
		if (horizontalStrut_13 == null) {
			horizontalStrut_13 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_13;
	}
	private java.awt.Component getHorizontalStrut_14() {
		if (horizontalStrut_14 == null) {
			horizontalStrut_14 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_14;
	}
	private java.awt.Component getHorizontalStrut_15() {
		if (horizontalStrut_15 == null) {
			horizontalStrut_15 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_15;
	}
	private JLabel getLabel_18() {
		if (label_18 == null) {
			label_18 = new JLabel("Dni");
			label_18.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_18;
	}
	private JTextField getTxDni4() {
		if (txDni4 == null) {
			txDni4 = new JTextField();
			txDni4.setColumns(10);
		}
		return txDni4;
	}
	private JLabel getLabel_19() {
		if (label_19 == null) {
			label_19 = new JLabel("Name");
			label_19.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_19;
	}
	private JTextField getTxName4() {
		if (txName4 == null) {
			txName4 = new JTextField();
			txName4.setColumns(10);
		}
		return txName4;
	}
	private JLabel getLabel_20() {
		if (label_20 == null) {
			label_20 = new JLabel("Surname");
			label_20.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_20;
	}
	private JTextField getTxSurname4() {
		if (txSurname4 == null) {
			txSurname4 = new JTextField();
			txSurname4.setColumns(10);
		}
		return txSurname4;
	}
	private JLabel getLabel_21() {
		if (label_21 == null) {
			label_21 = new JLabel("Email");
			label_21.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_21;
	}
	private JTextField getTxEmail4() {
		if (txEmail4 == null) {
			txEmail4 = new JTextField();
			txEmail4.setColumns(10);
		}
		return txEmail4;
	}
	private JLabel getLabel_22() {
		if (label_22 == null) {
			label_22 = new JLabel("Password");
			label_22.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_22;
	}
	private JTextField getTxPass4() {
		if (txPass4 == null) {
			txPass4 = new JTextField();
			txPass4.setColumns(10);
		}
		return txPass4;
	}
	private JLabel getLabel_23() {
		if (label_23 == null) {
			label_23 = new JLabel("Addres Street");
			label_23.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_23;
	}
	private JTextField getTxStreet4() {
		if (txStreet4 == null) {
			txStreet4 = new JTextField();
			txStreet4.setColumns(10);
		}
		return txStreet4;
	}
	private JLabel getLabel_24() {
		if (label_24 == null) {
			label_24 = new JLabel("Address City");
			label_24.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_24;
	}
	private JTextField getTxCity4() {
		if (txCity4 == null) {
			txCity4 = new JTextField();
			txCity4.setColumns(10);
		}
		return txCity4;
	}
	private JLabel getLabel_25() {
		if (label_25 == null) {
			label_25 = new JLabel("ZIP Code");
			label_25.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_25;
	}
	private JTextField getTxZip4() {
		if (txZip4 == null) {
			txZip4 = new JTextField();
			txZip4.setColumns(10);
		}
		return txZip4;
	}
	private JLabel getLblHistorynumber() {
		if (lblHistorynumber == null) {
			lblHistorynumber = new JLabel("History Nº");
			lblHistorynumber.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHistorynumber;
	}
	private JTextField getTxHistory() {
		if (txHistory == null) {
			txHistory = new JTextField();
			txHistory.setText("");
			txHistory.setColumns(10);
		}
		return txHistory;
	}
	private JLabel getLblHealthCard() {
		if (lblHealthCard == null) {
			lblHealthCard = new JLabel("Health Card");
			lblHealthCard.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHealthCard;
	}
	private JTextField getTxCard() {
		if (txCard == null) {
			txCard = new JTextField();
			txCard.setColumns(10);
		}
		return txCard;
	}
	private java.awt.Component getHorizontalStrut_16() {
		if (horizontalStrut_16 == null) {
			horizontalStrut_16 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_16;
	}
	private java.awt.Component getHorizontalStrut_17() {
		if (horizontalStrut_17 == null) {
			horizontalStrut_17 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_17;
	}
	private java.awt.Component getHorizontalStrut_18() {
		if (horizontalStrut_18 == null) {
			horizontalStrut_18 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_18;
	}
	private java.awt.Component getHorizontalStrut_19() {
		if (horizontalStrut_19 == null) {
			horizontalStrut_19 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_19;
	}
	private java.awt.Component getHorizontalStrut_20() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}
	private java.awt.Component getHorizontalStrut_1_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}
}
