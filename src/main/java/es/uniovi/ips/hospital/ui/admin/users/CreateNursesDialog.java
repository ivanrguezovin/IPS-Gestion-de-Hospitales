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
public class CreateNursesDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8408845391529085978L;
	private JPanel panel;
	private JLabel label;
	private JTextField txDni3;
	private JLabel label_1;
	private JTextField txName3;
	private JLabel label_2;
	private JTextField txSurname3;
	private JLabel label_3;
	private JTextField txEmail3;
	private JLabel label_4;
	private JTextField txPass3;
	private JLabel label_5;
	private JTextField txStreet3;
	private JLabel label_6;
	private JTextField txCity3;
	private JLabel label_7;
	private JTextField txZip3;
	private JLabel label_8;
	private JTextField txSpeciality2;
	private JLabel label_9;
	private JTextField txCollege2;
	private java.awt.Component horizontalStrut;
	private java.awt.Component horizontalStrut_1;
	private JButton button;
	private JButton btnBack;
	
	@Autowired private AdminAssistantService adminService;
	@Autowired private DoctorService doctorService;
	@Autowired private NurseService nurseService;

	
	@Autowired private AdminDialog cud;

	/**
	 * Create the dialog.
	 */
	public CreateNursesDialog() {
		setTitle("Create Nurses");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Create Nurse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			panel.add(getLabel());
			panel.add(getTxDni3());
			panel.add(getLabel_1());
			panel.add(getTxName3());
			panel.add(getLabel_2());
			panel.add(getTxSurname3());
			panel.add(getLabel_3());
			panel.add(getTxEmail3());
			panel.add(getLabel_4());
			panel.add(getTxPass3());
			panel.add(getLabel_5());
			panel.add(getTxStreet3());
			panel.add(getLabel_6());
			panel.add(getTxCity3());
			panel.add(getLabel_7());
			panel.add(getTxZip3());
			panel.add(getLabel_8());
			panel.add(getTxSpeciality2());
			panel.add(getLabel_9());
			panel.add(getTxCollege2());
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
	private JTextField getTxDni3() {
		if (txDni3 == null) {
			txDni3 = new JTextField();
			txDni3.setColumns(10);
		}
		return txDni3;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Name");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_1;
	}
	private JTextField getTxName3() {
		if (txName3 == null) {
			txName3 = new JTextField();
			txName3.setColumns(10);
		}
		return txName3;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Surname");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_2;
	}
	private JTextField getTxSurname3() {
		if (txSurname3 == null) {
			txSurname3 = new JTextField();
			txSurname3.setColumns(10);
		}
		return txSurname3;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Email");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_3;
	}
	private JTextField getTxEmail3() {
		if (txEmail3 == null) {
			txEmail3 = new JTextField();
			txEmail3.setColumns(10);
		}
		return txEmail3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Password");
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_4;
	}
	private JTextField getTxPass3() {
		if (txPass3 == null) {
			txPass3 = new JTextField();
			txPass3.setColumns(10);
		}
		return txPass3;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Addres Street");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_5;
	}
	private JTextField getTxStreet3() {
		if (txStreet3 == null) {
			txStreet3 = new JTextField();
			txStreet3.setColumns(10);
		}
		return txStreet3;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Address City");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_6;
	}
	private JTextField getTxCity3() {
		if (txCity3 == null) {
			txCity3 = new JTextField();
			txCity3.setColumns(10);
		}
		return txCity3;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("ZIP Code");
			label_7.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_7;
	}
	private JTextField getTxZip3() {
		if (txZip3 == null) {
			txZip3 = new JTextField();
			txZip3.setColumns(10);
		}
		return txZip3;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("Speciality");
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_8;
	}
	private JTextField getTxSpeciality2() {
		if (txSpeciality2 == null) {
			txSpeciality2 = new JTextField();
			txSpeciality2.setText("");
			txSpeciality2.setColumns(10);
		}
		return txSpeciality2;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("College Nº");
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_9;
	}
	private JTextField getTxCollege2() {
		if (txCollege2 == null) {
			txCollege2 = new JTextField();
			txCollege2.setColumns(10);
		}
		return txCollege2;
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
