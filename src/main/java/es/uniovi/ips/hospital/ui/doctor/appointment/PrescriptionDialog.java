package es.uniovi.ips.hospital.ui.doctor.appointment;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.MedicalRecordService;
import es.uniovi.ips.hospital.ui.util.render.MedicalRecordCellRender;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class PrescriptionDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4416001749661039073L;
	private JButton btnEdit;
	private JPanel panelScrollPanels;
	private JScrollPane scrollPaneList;
	private JPanel panelText;
	private JScrollPane scrollPaneText;
	private JLabel lblPrescription;
	private JTextArea textAreaPrescription;
	private JList<MedicalRecord> listPrescriptions;
	private JPanel panelBotones;
	private JPanel panelEdit;
	private JPanel panelCreateNew;
	private JButton buttonCreateNew;
	@Autowired private MedicalRecordService medicalRecordService;
	private Patient patient;
	@Autowired private CreatePrescriptionDialog createPrescriptionDialog;

	/**
	 * Create the dialog.
	 */
	public PrescriptionDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().add(getPanelScrollPanels(), BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void run(Patient patient) {
		this.setVisible(true);
		this.setPatient(patient);
		loadPrescriptions();
	}
	
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MedicalRecord m = listPrescriptions.getSelectedValue();
					m.setPrescription(textAreaPrescription.getText());
					m.setDate(convertToLocalDateTimeViaInstant(new Date()));
					medicalRecordService.createMedicalRecord(m);
					JOptionPane.showMessageDialog(null, "Prescription edited");
				}
			});
		}
		return btnEdit;
	}
	
	public void setPatient(Patient patient) {
		this.patient=patient;
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private JPanel getPanelScrollPanels() {
		if (panelScrollPanels == null) {
			panelScrollPanels = new JPanel();
			panelScrollPanels.setLayout(new GridLayout(0, 2, 0, 0));
			panelScrollPanels.add(getScrollPaneList());
			panelScrollPanels.add(getPanelText());
		}
		return panelScrollPanels;
	}
	private JScrollPane getScrollPaneList() {
		if (scrollPaneList == null) {
			scrollPaneList = new JScrollPane();
			scrollPaneList.setViewportView(getListPrescriptions());
		}
		return scrollPaneList;
	}
	private JPanel getPanelText() {
		if (panelText == null) {
			panelText = new JPanel();
			panelText.setLayout(new GridLayout(2, 0, 0, 0));
			panelText.add(getScrollPaneText());
			panelText.add(getPanelBotones());
		}
		return panelText;
	}
	private JScrollPane getScrollPaneText() {
		if (scrollPaneText == null) {
			scrollPaneText = new JScrollPane();
			scrollPaneText.setColumnHeaderView(getLblPrescription());
			scrollPaneText.setViewportView(getTextAreaPrescription());
		}
		return scrollPaneText;
	}
	private JLabel getLblPrescription() {
		if (lblPrescription == null) {
			lblPrescription = new JLabel("Prescription");
		}
		return lblPrescription;
	}
	private JTextArea getTextAreaPrescription() {
		if (textAreaPrescription == null) {
			textAreaPrescription = new JTextArea();
			textAreaPrescription.setLineWrap(true);
			textAreaPrescription.setWrapStyleWord(true);
		}
		return textAreaPrescription;
	}
	private JList<MedicalRecord> getListPrescriptions() {
		if (listPrescriptions == null) {
			listPrescriptions = new JList<MedicalRecord>();
			listPrescriptions.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					MedicalRecord m = listPrescriptions.getSelectedValue();
					textAreaPrescription.setText(m.getPrescription());
				}
			});
			listPrescriptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPrescriptions.setCellRenderer(new MedicalRecordCellRender());
		}
		return listPrescriptions;
	}
	
	public void loadPrescriptions() {
		DefaultListModel<MedicalRecord> model = new DefaultListModel<>();
		List<MedicalRecord> mrs = medicalRecordService.findAllByPatient(patient);
        for (MedicalRecord m: mrs) {
            model.addElement(m);
        }
        listPrescriptions.setModel(model);
	}
	
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new GridLayout(2, 1, 0, 0));
			panelBotones.add(getPanelEdit());
			panelBotones.add(getPanelCreateNew());
		}
		return panelBotones;
	}
	private JPanel getPanelEdit() {
		if (panelEdit == null) {
			panelEdit = new JPanel();
			panelEdit.setBorder(new TitledBorder(null, "Edit prescription selected", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEdit.add(getBtnEdit());
		}
		return panelEdit;
	}
	private JPanel getPanelCreateNew() {
		if (panelCreateNew == null) {
			panelCreateNew = new JPanel();
			panelCreateNew.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Create new prescription", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelCreateNew.add(getButtonCreateNew());
		}
		return panelCreateNew;
	}
	private JButton getButtonCreateNew() {
		if (buttonCreateNew == null) {
			buttonCreateNew = new JButton("Create new");
			buttonCreateNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createPrescriptionDialog.run(patient);
				}
			});
		}
		return buttonCreateNew;
	}
}
