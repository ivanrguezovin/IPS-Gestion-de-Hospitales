package es.uniovi.ips.hospital.ui.doctor.diagnostic;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.ICD10;
import es.uniovi.ips.hospital.service.DiagnosticService;
import es.uniovi.ips.hospital.service.ICD10Service;
import es.uniovi.ips.hospital.ui.util.render.ICD10CellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CreateDiagnosticDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea commentArea;
    private JList<ICD10> icd10List;
    private JScrollPane scrollPanel;
    private DefaultListModel<ICD10> icd10Model;

    @Autowired
    private DiagnosticService diagnosticService;
    @Autowired
    private ICD10Service icd10Service;

    private Diagnostic diagnostic;
    private Appointment appointment;
    private Doctor doctor;

    public CreateDiagnosticDialog() {
        this.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

    private void onCancel() {
        dispose();
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

    private void createUIComponents() {
        icd10Model = new DefaultListModel<>();
        icd10List = new JList<>(icd10Model);
        icd10List.setCellRenderer(new ICD10CellRenderer());
    }
}
