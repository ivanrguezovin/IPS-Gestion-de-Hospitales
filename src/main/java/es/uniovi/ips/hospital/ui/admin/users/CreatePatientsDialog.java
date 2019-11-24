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
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.AdminAssistantService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.service.PatientService;
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
public class CreatePatientsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 221110413043116148L;
	private JPanel panel;
	private JLabel label;
	private JTextField txDni4;
	private JLabel label_1;
	private JTextField txName4;
	private JLabel label_2;
	private JTextField txSurname4;
	private JLabel label_3;
	private JTextField txEmail4;
	private JLabel label_4;
	private JTextField txPass4;
	private JLabel label_5;
	private JTextField txStreet4;
	private JLabel label_6;
	private JTextField txCity4;
	private JLabel label_7;
	private JTextField txZip4;
	private JLabel label_8;
	private JTextField txHistory;
	private JLabel label_9;
	private JTextField txCard;
	private java.awt.Component horizontalStrut;
	private java.awt.Component horizontalStrut_1;
	private JButton button;
	private JButton btnBack;

	@Autowired private AdminAssistantService adminService;
	@Autowired private DoctorService doctorService;
	@Autowired private NurseService nurseService;
	@Autowired private PatientService patientService;
	
	@Autowired private AdminDialog cud;
	
	/**
	 * Create the dialog.
	 */
	public CreatePatientsDialog() {
		setTitle("Create Patients");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Create Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			panel.add(getLabel());
			panel.add(getTxDni4());
			panel.add(getLabel_1());
			panel.add(getTxName4());
			panel.add(getLabel_2());
			panel.add(getTxSurname4());
			panel.add(getLabel_3());
			panel.add(getTxEmail4());
			panel.add(getLabel_4());
			panel.add(getTxPass4());
			panel.add(getLabel_5());
			panel.add(getTxStreet4());
			panel.add(getLabel_6());
			panel.add(getTxCity4());
			panel.add(getLabel_7());
			panel.add(getTxZip4());
			panel.add(getLabel_8());
			panel.add(getTxHistory());
			panel.add(getLabel_9());
			panel.add(getTxCard());
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
	private JTextField getTxDni4() {
		if (txDni4 == null) {
			txDni4 = new JTextField();
			txDni4.setColumns(10);
		}
		return txDni4;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Name");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_1;
	}
	private JTextField getTxName4() {
		if (txName4 == null) {
			txName4 = new JTextField();
			txName4.setColumns(10);
		}
		return txName4;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Surname");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_2;
	}
	private JTextField getTxSurname4() {
		if (txSurname4 == null) {
			txSurname4 = new JTextField();
			txSurname4.setColumns(10);
		}
		return txSurname4;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Email");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_3;
	}
	private JTextField getTxEmail4() {
		if (txEmail4 == null) {
			txEmail4 = new JTextField();
			txEmail4.setColumns(10);
		}
		return txEmail4;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Password");
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_4;
	}
	private JTextField getTxPass4() {
		if (txPass4 == null) {
			txPass4 = new JTextField();
			txPass4.setColumns(10);
		}
		return txPass4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Addres Street");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_5;
	}
	private JTextField getTxStreet4() {
		if (txStreet4 == null) {
			txStreet4 = new JTextField();
			txStreet4.setColumns(10);
		}
		return txStreet4;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("Address City");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_6;
	}
	private JTextField getTxCity4() {
		if (txCity4 == null) {
			txCity4 = new JTextField();
			txCity4.setColumns(10);
		}
		return txCity4;
	}
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("ZIP Code");
			label_7.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_7;
	}
	private JTextField getTxZip4() {
		if (txZip4 == null) {
			txZip4 = new JTextField();
			txZip4.setColumns(10);
		}
		return txZip4;
	}
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("History Nº");
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_8;
	}
	private JTextField getTxHistory() {
		if (txHistory == null) {
			txHistory = new JTextField();
			txHistory.setText("");
			txHistory.setColumns(10);
		}
		return txHistory;
	}
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("Health Card");
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return label_9;
	}
	private JTextField getTxCard() {
		if (txCard == null) {
			txCard = new JTextField();
			txCard.setColumns(10);
		}
		return txCard;
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
