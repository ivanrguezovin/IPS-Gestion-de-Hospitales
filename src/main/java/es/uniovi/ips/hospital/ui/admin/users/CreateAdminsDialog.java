package es.uniovi.ips.hospital.ui.admin.users;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.service.AdminAssistantService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@Component
public class CreateAdminsDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 649590320703859836L;
	private JPanel panel;
	private JLabel label;
	private JTextField txDni;
	private JLabel label_1;
	private JTextField txName;
	private JLabel label_2;
	private JTextField txSurname;
	private JLabel label_3;
	private JTextField txEmail;
	private JLabel label_4;
	private JTextField txPass;
	private JLabel label_5;
	private JTextField txStreet;
	private JLabel label_6;
	private JTextField txCity;
	private JLabel label_7;
	private JTextField txZip;
	private java.awt.Component horizontalStrut;
	private java.awt.Component horizontalStrut_1;
	private java.awt.Component horizontalStrut_2;
	private java.awt.Component horizontalStrut_3;
	private java.awt.Component horizontalStrut_4;
	private java.awt.Component horizontalStrut_5;
	private JButton btAdd;
	private JButton btnBack;
	
	@Autowired private AdminAssistantService adminService;
	@Autowired private DoctorService doctorService;
	@Autowired private NurseService nurseService;

	
	@Autowired private CreateUsersDialog cud;

	/**
	 * Create the dialog.
	 */
	public CreateAdminsDialog() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Create Admin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			panel.add(getLabel());
			panel.add(getTxDni());
			panel.add(getLabel_1());
			panel.add(getTxName());
			panel.add(getLabel_2());
			panel.add(getTxSurname());
			panel.add(getLabel_3());
			panel.add(getTxEmail());
			panel.add(getLabel_4());
			panel.add(getTxPass());
			panel.add(getLabel_5());
			panel.add(getTxStreet());
			panel.add(getLabel_6());
			panel.add(getTxCity());
			panel.add(getLabel_7());
			panel.add(getTxZip());
			panel.add(getHorizontalStrut());
			panel.add(getHorizontalStrut_1());
			panel.add(getHorizontalStrut_2());
			panel.add(getHorizontalStrut_3());
			panel.add(getHorizontalStrut_4());
			panel.add(getHorizontalStrut_5());
			panel.add(getBtnBack());
			panel.add(getBtAdd());
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Dni");
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label;
	}
	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setColumns(10);
		}
		return txDni;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Name");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_1;
	}
	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setColumns(10);
		}
		return txName;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Surname");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_2;
	}
	private JTextField getTxSurname() {
		if (txSurname == null) {
			txSurname = new JTextField();
			txSurname.setColumns(10);
		}
		return txSurname;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Email");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_3;
	}
	private JTextField getTxEmail() {
		if (txEmail == null) {
			txEmail = new JTextField();
			txEmail.setColumns(10);
		}
		return txEmail;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Password");
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_4;
	}
	private JTextField getTxPass() {
		if (txPass == null) {
			txPass = new JTextField();
			txPass.setColumns(10);
		}
		return txPass;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Addres Street");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_5;
	}
	private JTextField getTxStreet() {
		if (txStreet == null) {
			txStreet = new JTextField();
			txStreet.setColumns(10);
		}
		return txStreet;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Address City");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_6;
	}
	private JTextField getTxCity() {
		if (txCity == null) {
			txCity = new JTextField();
			txCity.setColumns(10);
		}
		return txCity;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("ZIP Code");
		}
		return label_7;
	}
	private JTextField getTxZip() {
		if (txZip == null) {
			txZip = new JTextField();
			txZip.setColumns(10);
		}
		return txZip;
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
	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("Add");
			btAdd.addActionListener(new ActionListener() {
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
		return btAdd;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					cud.setVisible(true);
				}
			});
		}
		return btnBack;
	}
}
