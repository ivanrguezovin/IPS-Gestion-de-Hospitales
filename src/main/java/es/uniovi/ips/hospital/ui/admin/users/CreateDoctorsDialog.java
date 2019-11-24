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
import es.uniovi.ips.hospital.ui.admin.AdminDialog;

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
public class CreateDoctorsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3229507852145590251L;
	
	@Autowired private AdminAssistantService adminService;
	@Autowired private DoctorService doctorService;
	@Autowired private NurseService nurseService;
	
	@Autowired private AdminDialog cud;
	
	private JPanel panel;
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
	private JLabel label_8;
	private JTextField txSpeciality;
	private JLabel label_9;
	private JTextField txCollege;
	private java.awt.Component horizontalStrut;
	private java.awt.Component horizontalStrut_1;
	private JButton button;
	private JButton btnBack;

	/**
	 * Create the dialog.
	 */
	public CreateDoctorsDialog() {
		setTitle("Create Doctors");
		setBounds(100, 100, 450, 300);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Create Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			panel.add(getLabel());
			panel.add(getTxDni2());
			panel.add(getLabel_1());
			panel.add(getTxName2());
			panel.add(getLabel_2());
			panel.add(getTxSurname2());
			panel.add(getLabel_3());
			panel.add(getTxEmail2());
			panel.add(getLabel_4());
			panel.add(getTxPass2());
			panel.add(getLabel_5());
			panel.add(getTxStreet2());
			panel.add(getLabel_6());
			panel.add(getTxCity2());
			panel.add(getLabel_7());
			panel.add(getTxZip2());
			panel.add(getLabel_8());
			panel.add(getTxSpeciality());
			panel.add(getLabel_9());
			panel.add(getTxCollege());
			panel.add(getHorizontalStrut());
			panel.add(getHorizontalStrut_1());
			panel.add(getBtnBack());
			panel.add(getButton());
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
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("Speciality");
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_8;
	}
	private JTextField getTxSpeciality() {
		if (txSpeciality == null) {
			txSpeciality = new JTextField();
			txSpeciality.setText("");
			txSpeciality.setColumns(10);
		}
		return txSpeciality;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("College Nº");
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_9;
	}
	private JTextField getTxCollege() {
		if (txCollege == null) {
			txCollege = new JTextField();
			txCollege.setColumns(10);
		}
		return txCollege;
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
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Add");
			button.addActionListener(new ActionListener() {
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
		return button;
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
