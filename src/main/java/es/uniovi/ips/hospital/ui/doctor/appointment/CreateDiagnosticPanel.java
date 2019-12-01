package es.uniovi.ips.hospital.ui.doctor.appointment;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.render.ICD10CellRenderer;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

@Component
public class CreateDiagnosticPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 8671347993932448763L;
	private JPanel contentPanel = new JPanel();

	@Autowired
	private DiagnosticService diagnosticService;
	@Autowired
	private ICD10Service icd10Service;

	private JButton btnOk;
	private JScrollPane scrollPane;
	private JLabel lblComment;
	private JTextArea commentArea;
	private JScrollPane scrollPane_1;
	private JLabel lblCodes;
	private JList<ICD10> icd10List;
	private DefaultListModel<ICD10> icd10Model;

	private Diagnostic diagnostic;
	private Appointment appointment;
	private Doctor doctor;

	/**
	 * Create the dialog.
	 */
	public CreateDiagnosticPanel() {
		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(10, 10));
		contentPanel.add(getScrollPane(), BorderLayout.CENTER);
		contentPanel.add(getScrollPane_1(), BorderLayout.EAST);
		contentPanel.add(getBtnOk(), BorderLayout.SOUTH);
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new MyButton("Create");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onOK();
				}
			});
		}
		return btnOk;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(Designer.getBorder());
			scrollPane.setColumnHeaderView(getLblComment());
			scrollPane.setViewportView(getCommentArea());
			scrollPane.getColumnHeader().setBackground(PaletteFactory.getMainDark());
			scrollPane.getColumnHeader().setForeground(PaletteFactory.getLighter());
			scrollPane.getViewport().setBackground(PaletteFactory.getMainDark());
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

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBorder(Designer.getBorder());
			scrollPane_1.setColumnHeaderView(getLblCodes());
			scrollPane_1.getColumnHeader().setBackground(PaletteFactory.getMainDark());
			scrollPane_1.getColumnHeader().setForeground(PaletteFactory.getLighter());
			scrollPane_1.setViewportView(getIcd10List());
			scrollPane_1.getViewport().setBackground(PaletteFactory.getBaseDark());
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
	
	//-------------------------------------------------------------------------------

	public void run(Appointment appointment, Doctor doctor) {
		this.appointment = appointment;
		this.doctor = doctor;
		this.loadICD10List();
	}

	@Override
	public void setFocus() {
		commentArea.requestFocus();
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
	}

	private void loadICD10List() {
		for (ICD10 code : icd10Service.findAll()) {
			icd10Model.addElement(code);
		}
		icd10List.setModel(icd10Model);
	}
}
