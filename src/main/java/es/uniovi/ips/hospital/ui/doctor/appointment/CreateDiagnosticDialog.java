package es.uniovi.ips.hospital.ui.doctor.appointment;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.ICD10;
import es.uniovi.ips.hospital.service.DiagnosticService;
import es.uniovi.ips.hospital.service.ICD10Service;
import es.uniovi.ips.hospital.ui.util.render.ICD10CellRenderer;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

@Component
public class CreateDiagnosticDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8671347993932448763L;
	private JPanel panel;
	private JButton btnOk;
	private JButton btnCancel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel lblComment;
	private JTextArea commentArea;
	private JScrollPane scrollPane_1;
	private JLabel lblCodes;
	private JList<ICD10> icd10List;
	private DefaultListModel<ICD10> icd10Model;
	@Autowired
    private DiagnosticService diagnosticService;
    @Autowired
    private ICD10Service icd10Service;

    private Diagnostic diagnostic;
    private Appointment appointment;
    private Doctor doctor;

	/**
	 * Create the dialog.
	 */
	public CreateDiagnosticDialog() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
	}
	
	

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.add(getBtnOk());
			panel.add(getBtnCancel());
		}
		return panel;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onOK();
				}
			});
		}
		return btnOk;
	}
	
	private void onOK() {
        diagnostic = new Diagnostic();
        diagnostic.setComment(commentArea.getText());
        diagnostic.setDoctor(this.doctor);
        diagnostic.setAppointment(this.appointment);
        diagnostic.setCreated(LocalDateTime.now());
        diagnosticService.createDiagnostic(diagnostic);
        List<ICD10> selectedCodes = icd10List.getSelectedValuesList();
        diagnostic.setCodes(selectedCodes);
        for (ICD10 code : selectedCodes) {
            code.addDiagnostic(diagnostic);
        }
        diagnosticService.createDiagnostic(diagnostic);
        dispose();
    }
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 2, 0, 0));
			panel_1.add(getScrollPane());
			panel_1.add(getScrollPane_1());
		}
		return panel_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setColumnHeaderView(getLblComment());
			scrollPane.setViewportView(getCommentArea());
		}
		return scrollPane;
	}
	private JLabel getLblComment() {
		if (lblComment == null) {
			lblComment = new JLabel("Comment");
			lblComment.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblComment.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblComment;
	}
	private JTextArea getCommentArea() {
		if (commentArea == null) {
			commentArea = new JTextArea();
			commentArea.setWrapStyleWord(true);
			commentArea.setLineWrap(true);
		}
		return commentArea;
	}
	public void run(Appointment appointment, Doctor doctor) {
        this.appointment = appointment;
        this.doctor = doctor;
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.loadICD10List();
        this.setVisible(true);
    }
	private void loadICD10List() {
        for (ICD10 code : icd10Service.findAll()) {
            icd10Model.addElement(code);
        }
        icd10List.setModel(icd10Model);
    }
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setColumnHeaderView(getLblCodes());
			scrollPane_1.setViewportView(getIcd10List());
		}
		return scrollPane_1;
	}
	private JLabel getLblCodes() {
		if (lblCodes == null) {
			lblCodes = new JLabel("Codes");
			lblCodes.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblCodes.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCodes;
	}
	private JList<ICD10> getIcd10List() {
		if (icd10List == null) {
			icd10Model = new DefaultListModel<>();
	        icd10List = new JList<>(icd10Model);
	        icd10List.setCellRenderer(new ICD10CellRenderer());
		}
		return icd10List;
	}
}
