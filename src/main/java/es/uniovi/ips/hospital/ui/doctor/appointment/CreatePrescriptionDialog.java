package es.uniovi.ips.hospital.ui.doctor.appointment;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.MedicalRecordService;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

@Component
public class CreatePrescriptionDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156510247619276196L;
	private JPanel panelOk;
	private JButton btnCreate;
	private JPanel panelEdition;
	private JScrollPane scrollPanePrescription;
	private JScrollPane scrollPaneDescription;
	private JLabel lblPrescription;
	private JLabel lblDescription;
	private JTextArea textAreaPrescription;
	private JTextArea textAreaDescription;
	@Autowired private MedicalRecordService medicalRecordService;
	private Patient patient;

	/**
	 * Create the dialog.
	 */
	public CreatePrescriptionDialog() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(getPanelOk(), BorderLayout.SOUTH);
		getContentPane().add(getPanelEdition(), BorderLayout.CENTER);
	}
	
	public void run(Patient patient) {
		this.setVisible(true);
		this.setPatient(patient);
	}
	
	public void setPatient(Patient patient) {
		this.patient=patient;
	}
	
	private JPanel getPanelOk() {
		if (panelOk == null) {
			panelOk = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelOk.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelOk.add(getBtnCreate());
		}
		return panelOk;
	}
	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String prescription = textAreaPrescription.getText();
					String description = textAreaDescription.getText();
					LocalDateTime date = convertToLocalDateTimeViaInstant(new Date());
					MedicalRecord mR = new MedicalRecord();
					mR.setDate(date);
					mR.setDescription(description);
					mR.setPrescription(prescription);
					mR.setPatient(patient);
					medicalRecordService.createMedicalRecord(mR);
					JOptionPane.showMessageDialog(null, "Prescription added");
				}
			});
		}
		return btnCreate;
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private JPanel getPanelEdition() {
		if (panelEdition == null) {
			panelEdition = new JPanel();
			panelEdition.setLayout(new GridLayout(0, 2, 0, 0));
			panelEdition.add(getScrollPanePrescription());
			panelEdition.add(getScrollPaneDescription());
		}
		return panelEdition;
	}
	private JScrollPane getScrollPanePrescription() {
		if (scrollPanePrescription == null) {
			scrollPanePrescription = new JScrollPane();
			scrollPanePrescription.setColumnHeaderView(getLblPrescription());
			scrollPanePrescription.setViewportView(getTextAreaPrescription());
		}
		return scrollPanePrescription;
	}
	private JScrollPane getScrollPaneDescription() {
		if (scrollPaneDescription == null) {
			scrollPaneDescription = new JScrollPane();
			scrollPaneDescription.setColumnHeaderView(getLblDescription());
			scrollPaneDescription.setViewportView(getTextAreaDescription());
		}
		return scrollPaneDescription;
	}
	private JLabel getLblPrescription() {
		if (lblPrescription == null) {
			lblPrescription = new JLabel("Prescription");
			lblPrescription.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPrescription.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrescription;
	}
	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Description");
			lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDescription;
	}
	private JTextArea getTextAreaPrescription() {
		if (textAreaPrescription == null) {
			textAreaPrescription = new JTextArea();
			textAreaPrescription.setLineWrap(true);
			textAreaPrescription.setWrapStyleWord(true);
		}
		return textAreaPrescription;
	}
	private JTextArea getTextAreaDescription() {
		if (textAreaDescription == null) {
			textAreaDescription = new JTextArea();
			textAreaDescription.setWrapStyleWord(true);
			textAreaDescription.setLineWrap(true);
		}
		return textAreaDescription;
	}
}
