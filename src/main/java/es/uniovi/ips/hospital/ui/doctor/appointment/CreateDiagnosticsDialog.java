//package es.uniovi.ips.hospital.ui.doctor.appointment;
//
//import javax.swing.JDialog;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import es.uniovi.ips.hospital.domain.MedicalRecord;
//import es.uniovi.ips.hospital.domain.Patient;
//import es.uniovi.ips.hospital.service.DiagnosticService;
//import es.uniovi.ips.hospital.service.MedicalRecordService;
//import javax.swing.JPanel;
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import javax.swing.JButton;
//import java.awt.GridLayout;
//import javax.swing.JScrollPane;
//import javax.swing.JLabel;
//import javax.swing.JTextArea;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//@Component
//public class CreateDiagnosticsDialog extends JDialog {
//
//	private static final long serialVersionUID = 6133213670893004792L;
//	@Autowired private DiagnosticService ds = new DiagnosticService();
//	private JPanel panel;
//	private JButton btnBack;
//	private JPanel panel_1;
//	private JScrollPane scrollPane;
//	private JScrollPane scrollPane_1;
//	private JLabel lblDescription;
//	private JLabel lblPrescription;
//	private JTextArea textAreaDesc;
//	private JTextArea textAreaPresc;
//	private JButton btnEdit;
//	/**
//	 * Create the dialog.
//	 */
//	public CreateDiagnosticsDialog() {
//		setBounds(100, 100, 450, 300);
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		getContentPane().add(getPanel(), BorderLayout.SOUTH);
//		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
//	}
//
//	private JPanel getPanel() {
//		if (panel == null) {
//			panel = new JPanel();
//			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
//			flowLayout.setAlignment(FlowLayout.RIGHT);
//			panel.add(getBtnEdit());
//			panel.add(getBtnBack());
//		}
//		return panel;
//	}
//	private JButton getBtnBack() {
//		if (btnBack == null) {
//			btnBack = new JButton("Back");
//		}
//		return btnBack;
//	}
//	private JPanel getPanel_1() {
//		if (panel_1 == null) {
//			panel_1 = new JPanel();
//			panel_1.setLayout(new GridLayout(1, 0, 0, 0));
//			panel_1.add(getScrollPane());
//			panel_1.add(getScrollPane_1());
//		}
//		return panel_1;
//	}
//	private JScrollPane getScrollPane() {
//		if (scrollPane == null) {
//			scrollPane = new JScrollPane();
//			scrollPane.setColumnHeaderView(getLblDescription());
//			scrollPane.setViewportView(getTextAreaDesc());
//		}
//		return scrollPane;
//	}
//	private JScrollPane getScrollPane_1() {
//		if (scrollPane_1 == null) {
//			scrollPane_1 = new JScrollPane();
//			scrollPane_1.setColumnHeaderView(getLblPrescription());
//			scrollPane_1.setViewportView(getTextAreaPresc());
//		}
//		return scrollPane_1;
//	}
//	private JLabel getLblDescription() {
//		if (lblDescription == null) {
//			lblDescription = new JLabel("Description:");
//		}
//		return lblDescription;
//	}
//	private JLabel getLblPrescription() {
//		if (lblPrescription == null) {
//			lblPrescription = new JLabel("Prescription:");
//		}
//		return lblPrescription;
//	}
//	private JTextArea getTextAreaDesc() {
//		if (textAreaDesc == null) {
//			textAreaDesc = new JTextArea();
//			textAreaDesc.setLineWrap(true);
//			textAreaDesc.setWrapStyleWord(true);
//		}
//		return textAreaDesc;
//	}
//	private JTextArea getTextAreaPresc() {
//		if (textAreaPresc == null) {
//			textAreaPresc = new JTextArea();
//			textAreaPresc.setWrapStyleWord(true);
//			textAreaPresc.setLineWrap(true);
//		}
//		return textAreaPresc;
//	}
//	private JButton getBtnEdit() {
//		if (btnEdit == null) {
//			btnEdit = new JButton("Edit");
//			btnEdit.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					String des = textAreaDesc.getText();
//					String presc = textAreaPresc.getText();
//					MedicalRecord mr = new MedicalRecord(patient);
//					mr.setDescription(des);
//					mr.setPrescription(presc);
//					ds.createDiagnostic(diagnostic)
//				}
//			});
//		}
//		return btnEdit;
//	}
//}
