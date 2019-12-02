package es.uniovi.ips.hospital.ui.doctor.appointment;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.MedicalRecordService;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.render.MedicalRecordCellRender;

import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class EditPrescriptionPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = -4416001749661039073L;
	private JPanel contentPanel = new JPanel();

	@Autowired private MedicalRecordService medicalRecordService;
	@Autowired private DoctorDialog doctorDialog;
	
	private JButton btnEdit;
	private JPanel panelScrollPanels;
	private JScrollPane scrollPaneList;
	private JPanel panelText;
	private JScrollPane scrollPaneText;
	private JLabel lblPrescription;
	private JTextArea textAreaPrescription;
	private JList<MedicalRecord> listPrescriptions;
	private JPanel panelBotones;
	private JButton buttonCreateNew;
	
	private Patient patient;

	/**
	 * Create the dialog.
	 */
	public EditPrescriptionPanel() {
		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(10, 10));
		contentPanel.add(getPanelScrollPanels(), BorderLayout.CENTER);
	}
	
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new MyButton("Edit");
			btnEdit.setEnabled(false);
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MedicalRecord m = listPrescriptions.getSelectedValue();
					m.setPrescription(textAreaPrescription.getText());
					m.setDate(convertToLocalDateTimeViaInstant(new Date()));
					medicalRecordService.createMedicalRecord(m);
					JOptionPane.showMessageDialog(contentPanel, "Prescription edited");
				}
			});
		}
		return btnEdit;
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	private JPanel getPanelScrollPanels() {
		if (panelScrollPanels == null) {
			panelScrollPanels = new MyBackPanel();
			panelScrollPanels.setLayout(new GridLayout(0, 2, 10, 0));
			panelScrollPanels.add(getScrollPaneList());
			panelScrollPanels.add(getPanelText());
		}
		return panelScrollPanels;
	}
	private JScrollPane getScrollPaneList() {
		if (scrollPaneList == null) {
			scrollPaneList = new JScrollPane();
			scrollPaneList.getViewport().setBackground(PaletteFactory.getBaseDark());
			scrollPaneList.setBorder(Designer.getBorder());
			scrollPaneList.setViewportView(getListPrescriptions());
		}
		return scrollPaneList;
	}
	private JPanel getPanelText() {
		if (panelText == null) {
			panelText = new MyBackPanel();
			panelText.setLayout(new BorderLayout(0, 10));
			panelText.add(getScrollPaneText(), BorderLayout.CENTER);
			panelText.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return panelText;
	}
	private JScrollPane getScrollPaneText() {
		if (scrollPaneText == null) {
			scrollPaneText = new JScrollPane();
			scrollPaneText.setColumnHeaderView(getLblPrescription());
			scrollPaneText.setBorder(Designer.getBorder());;
			scrollPaneText.getViewport().setBackground(PaletteFactory.getMainDark());
			scrollPaneText.getColumnHeader().setBackground(PaletteFactory.getMainDark());
			scrollPaneText.getColumnHeader().setForeground(PaletteFactory.getLighter());
			scrollPaneText.setViewportView(getTextAreaPrescription());
		}
		return scrollPaneText;
	}
	private JLabel getLblPrescription() {
		if (lblPrescription == null) {
			lblPrescription = new JLabel("Prescription");
			lblPrescription.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
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
					if (m != null) {
						textAreaPrescription.setText(m.getPrescription());
						btnEdit.setEnabled(true);
					}
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
			panelBotones = new MyBackPanel();
			panelBotones.setLayout(new GridLayout(2, 1, 0, 5));
			panelBotones.add(getBtnEdit());
			panelBotones.add(getButtonCreateNew());
		}
		return panelBotones;
	}
	private JButton getButtonCreateNew() {
		if (buttonCreateNew == null) {
			buttonCreateNew = new MyButton("Create new");
			buttonCreateNew.addActionListener(e -> doctorDialog.launchCreatePrescription(patient));
		}
		return buttonCreateNew;
	}
	
	// -----------------------------------------------------------------------------------------
	
	public void run(Patient patient) {
		this.setPatient(patient);
		loadPrescriptions();
	}
	
	public void setPatient(Patient patient) {
		this.patient=patient;
	}

	@Override
	public void setFocus() {
		listPrescriptions.clearSelection();
		btnEdit.setEnabled(false);
		textAreaPrescription.requestFocus();
	}
}
