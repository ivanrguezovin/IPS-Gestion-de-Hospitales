package es.uniovi.ips.hospital.ui.admin.users;

import javax.swing.JDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.service.AdminAssistantService;

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

@Component
public class CreateUsersDialog extends JDialog {
	
	private static final long serialVersionUID = 1958924359775244981L;
	

	@Autowired private AdminAssistantService adminService;
	
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnNewDoctor;
	private JButton btnNewNurse;
	private JButton btnNewPatient;
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

	/**
	 * Create the dialog.
	 */
	public CreateUsersDialog() {
		setTitle("Create Users");
		setBounds(100, 100, 1250, 300);
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
			panel_1.add(getBtnNewDoctor());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Create Nurse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.add(getBtnNewNurse());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Create Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.add(getBtnNewPatient());
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
	private JButton getBtnNewDoctor() {
		if (btnNewDoctor == null) {
			btnNewDoctor = new JButton("New Doctor");
		}
		return btnNewDoctor;
	}
	private JButton getBtnNewNurse() {
		if (btnNewNurse == null) {
			btnNewNurse = new JButton("New Nurse");
		}
		return btnNewNurse;
	}
	private JButton getBtnNewPatient() {
		if (btnNewPatient == null) {
			btnNewPatient = new JButton("New Patient");
		}
		return btnNewPatient;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("Dni");
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
					List<AdminAssistant> assistants = adminService.findAllAdminAssistant();
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
}
