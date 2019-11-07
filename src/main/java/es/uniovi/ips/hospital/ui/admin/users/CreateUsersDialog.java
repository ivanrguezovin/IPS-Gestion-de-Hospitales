package es.uniovi.ips.hospital.ui.admin.users;

import javax.swing.JDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
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
import java.awt.FlowLayout;

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
	private JLabel lblDni;
	private JTextField txDni;
	private JLabel lblName;
	private JTextField txName;
	private JLabel lblSurname;
	private JTextField txSurname;
	private JLabel lblPassword;
	private JTextField txPass;
	private JLabel lblAddresStreet;
	private JTextField txStreet;
	private JLabel lblAddressCity;
	private JTextField txCity;
	private JLabel lblAddressZipCode;
	private JTextField txZip;
	private JButton btnAddAdmin;
	private java.awt.Component horizontalStrut;
	private java.awt.Component horizontalStrut_1;
	private java.awt.Component horizontalStrut_2;
	private java.awt.Component horizontalStrut_3;
	private java.awt.Component horizontalStrut_4;
	private java.awt.Component horizontalStrut_5;
	private JLabel lblEmail;
	private JTextField txEmail;
	private java.awt.Component horizontalStrut_6;
	private java.awt.Component horizontalStrut_7;
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

	/**
	 * Create the dialog.
	 */
	public CreateUsersDialog() {
		setTitle("Create Users");
		setBounds(100, 100, 1450, 500);
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
			panel_3.add(getBtnAddPatient());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(null, "Create Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_4.setLayout(new GridLayout(0, 16, 0, 0));
			panel_4.add(getLblDni());
			panel_4.add(getTxDni());
			panel_4.add(getLblName());
			panel_4.add(getTxName());
			panel_4.add(getLblSurname());
			panel_4.add(getTxSurname());
			panel_4.add(getLblEmail());
			panel_4.add(getTxEmail());
			panel_4.add(getLblPassword());
			panel_4.add(getTxPass());
			panel_4.add(getLblAddresStreet());
			panel_4.add(getTxStreet());
			panel_4.add(getLblAddressCity());
			panel_4.add(getTxCity());
			panel_4.add(getLblAddressZipCode());
			panel_4.add(getTxZip());
			panel_4.add(getHorizontalStrut());
			panel_4.add(getHorizontalStrut_1());
			panel_4.add(getHorizontalStrut_2());
			panel_4.add(getHorizontalStrut_3());
			panel_4.add(getHorizontalStrut_4());
			panel_4.add(getHorizontalStrut_5());
			panel_4.add(getHorizontalStrut_6());
			panel_4.add(getHorizontalStrut_7());
			panel_4.add(getBtnAddAdmin());
		}
		return panel_4;
	}
	private JButton getBtnAddNurse() {
		if (btnAddNurse == null) {
			btnAddNurse = new JButton("Add Nurse");
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
		return btnAddNurse;
	}
	private JButton getBtnAddPatient() {
		if (btnAddPatient == null) {
			btnAddPatient = new JButton("Add Patient");
		}
		return btnAddPatient;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("Dni");
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDni;
	}
	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setColumns(10);
		}
		return txDni;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblName;
	}
	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setColumns(10);
		}
		return txName;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("Surname");
			lblSurname.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSurname;
	}
	private JTextField getTxSurname() {
		if (txSurname == null) {
			txSurname = new JTextField();
			txSurname.setColumns(10);
		}
		return txSurname;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPassword;
	}
	private JTextField getTxPass() {
		if (txPass == null) {
			txPass = new JTextField();
			txPass.setColumns(10);
		}
		return txPass;
	}
	private JLabel getLblAddresStreet() {
		if (lblAddresStreet == null) {
			lblAddresStreet = new JLabel("Addres Street");
			lblAddresStreet.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAddresStreet;
	}
	private JTextField getTxStreet() {
		if (txStreet == null) {
			txStreet = new JTextField();
			txStreet.setColumns(10);
		}
		return txStreet;
	}
	private JLabel getLblAddressCity() {
		if (lblAddressCity == null) {
			lblAddressCity = new JLabel("Address City");
			lblAddressCity.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAddressCity;
	}
	private JTextField getTxCity() {
		if (txCity == null) {
			txCity = new JTextField();
			txCity.setColumns(10);
		}
		return txCity;
	}
	private JLabel getLblAddressZipCode() {
		if (lblAddressZipCode == null) {
			lblAddressZipCode = new JLabel("ZIP Code");
		}
		return lblAddressZipCode;
	}
	private JTextField getTxZip() {
		if (txZip == null) {
			txZip = new JTextField();
			txZip.setColumns(10);
		}
		return txZip;
	}
	private JButton getBtnAddAdmin() {
		if (btnAddAdmin == null) {
			btnAddAdmin = new JButton("Add Admin");
			btnAddAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Doctor> doctors = doctorService.findAllDoctors();
					List<AdminAssistant> assistants = adminService.findAllAdminAssistant();
					List<Nurse> nurses = nurseService.findAllNurses();
					boolean check = false;
					String a = String.valueOf(txPass.getText().length());
					if(txCity.getText().equals("") || txDni.getText().equals("") || txZip.getText().equals("") 
							|| txName.getText().equals("") || txPass.getText().equals("") || txSurname.getText().equals("") 
							|| txStreet.getText().equals("") || txEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the fields");
					}else if(!txEmail.getText().matches("[a-zA-Z1-9]+@[a-zA-Z1-9]+.[a-zA-Z1-9]+")){
						JOptionPane.showMessageDialog(null, "Wrong email format");
					}else if(a.equals("0") || a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4")){
						JOptionPane.showMessageDialog(null, "Wrong password lenght");
					}else if(!txDni.getText().matches("[0-9]+[A-Z]") || !String.valueOf(txDni.getText().length()).equals("9")){
						JOptionPane.showMessageDialog(null, "Wrong dni format");
					}else{
						for(AdminAssistant aa: assistants) {
							if(txDni.getText().equals(aa.getDni())) {
								JOptionPane.showMessageDialog(null, "Admin already exists");
								check = true;
							}
						}
						for(Nurse aa: nurses) {
							if(txDni.getText().equals(aa.getDni())) {
								JOptionPane.showMessageDialog(null, "Staff member already exists");
								check=true;
							}
						}
						for(Doctor aa: doctors) {
							if(txDni.getText().equals(aa.getDni())) {
								JOptionPane.showMessageDialog(null, "Staff member already exists");
								check=true;
							}
						}
						if(!check) {
							AdminAssistant as = new AdminAssistant(txDni.getText(),txName.getText(),txSurname.getText(),
									txEmail.getText(), txPass.getText(),txStreet.getText(),txCity.getText(),txZip.getText());
							adminService.createAdminAssistant(as);
							JOptionPane.showMessageDialog(null, "Admin created");
							txCity.setText("");
							txZip.setText("");
							txSurname.setText("");
							txStreet.setText("");
							txPass.setText("");
							txName.setText("");
							txEmail.setText("");
							txDni.setText("");
						}
						
					}
				}
			});
		}
		return btnAddAdmin;
	}
	private java.awt.Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}
	private java.awt.Component getHorizontalStrut_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}
	private java.awt.Component getHorizontalStrut_2() {
		if (horizontalStrut_2 == null) {
			horizontalStrut_2 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_2;
	}
	private java.awt.Component getHorizontalStrut_3() {
		if (horizontalStrut_3 == null) {
			horizontalStrut_3 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_3;
	}
	private java.awt.Component getHorizontalStrut_4() {
		if (horizontalStrut_4 == null) {
			horizontalStrut_4 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_4;
	}
	private java.awt.Component getHorizontalStrut_5() {
		if (horizontalStrut_5 == null) {
			horizontalStrut_5 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_5;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
			lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEmail;
	}
	private JTextField getTxEmail() {
		if (txEmail == null) {
			txEmail = new JTextField();
			txEmail.setColumns(10);
		}
		return txEmail;
	}
	private java.awt.Component getHorizontalStrut_6() {
		if (horizontalStrut_6 == null) {
			horizontalStrut_6 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_6;
	}
	private java.awt.Component getHorizontalStrut_7() {
		if (horizontalStrut_7 == null) {
			horizontalStrut_7 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_7;
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
			btnAddDoctor = new JButton("Add Doctor");
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
}
