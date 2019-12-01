package es.uniovi.ips.hospital.ui.doctor.appointment;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.MedicalRecordService;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

@Component
public class CreatePrescriptionPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 9156510247619276196L;
	private JPanel contentPanel = new JPanel();
	
	@Autowired private MedicalRecordService medicalRecordService;
	
	private JButton btnCreate;
	private JPanel panelEdition;
	private JScrollPane scrollPanePrescription;
	private JScrollPane scrollPaneDescription;
	private JLabel lblPrescription;
	private JLabel lblDescription;
	private JTextArea textAreaPrescription;
	private JTextArea textAreaDescription;
	
	private Patient patient;

	/**
	 * Create the dialog.
	 */
	public CreatePrescriptionPanel() {
		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(10, 10));
		contentPanel.add(getPanelEdition(), BorderLayout.CENTER);
		contentPanel.add(getBtnCreate(), BorderLayout.SOUTH);
	}
	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new MyButton("Create");
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
	
	private JPanel getPanelEdition() {
		if (panelEdition == null) {
			panelEdition = new MyBackPanel();
			panelEdition.setLayout(new GridLayout(2, 1, 0, 10));
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
			scrollPanePrescription.setBorder(Designer.getBorder());
			scrollPanePrescription.getColumnHeader().setBackground(PaletteFactory.getMainDark());
			scrollPanePrescription.getColumnHeader().setForeground(PaletteFactory.getLighter());
			scrollPanePrescription.getViewport().setBackground(PaletteFactory.getBaseDark());
		}
		return scrollPanePrescription;
	}
	private JScrollPane getScrollPaneDescription() {
		if (scrollPaneDescription == null) {
			scrollPaneDescription = new JScrollPane();
			scrollPaneDescription.setColumnHeaderView(getLblDescription());
			scrollPaneDescription.setViewportView(getTextAreaDescription());
			scrollPaneDescription.setBorder(Designer.getBorder());
			scrollPaneDescription.getColumnHeader().setBackground(PaletteFactory.getMainDark());
			scrollPaneDescription.getColumnHeader().setForeground(PaletteFactory.getLighter());
			scrollPaneDescription.getViewport().setBackground(PaletteFactory.getBaseDark());
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
	
	//-------------------------------------------------------------------------------------------------------
	
	public void run(Patient patient) {
		this.setPatient(patient);
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public void setPatient(Patient patient) {
		this.patient=patient;
	}
	@Override
	public void setFocus() {
		textAreaDescription.requestFocus();
	}
}
