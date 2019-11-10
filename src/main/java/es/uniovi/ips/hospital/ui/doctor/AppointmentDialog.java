package es.uniovi.ips.hospital.ui.doctor;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.domain.ICD10;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.DiagnosticService;
import es.uniovi.ips.hospital.service.ICD10Service;
import es.uniovi.ips.hospital.ui.util.render.DiagnosticCellRenderer;
import es.uniovi.ips.hospital.ui.util.render.ICD10CellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class AppointmentDialog extends JDialog {
    private Appointment appointment;
    private Patient patient;

    private JPanel patientInfoPanel;
    private JPanel diagnosticPanel;
    private JPanel createDiagnosticPanel;
    private JPanel icd10Panel;
    private JPanel medicalHistoryPanel;

    private JLabel nameField;
    private JLabel surnameField;

    private JList<ICD10> icd10List;
    private JList<Diagnostic> diagnosticList;
    private JTextArea diagnosticArea;

    @Autowired
    private ICD10Service icd10Service;

    @Autowired
    private DiagnosticService diagnosticService;

    public AppointmentDialog() {
        appointment = new Appointment();
        patient = new Patient();
        this.setModal(true);
        this.setSize(1000, 800);

        this.getContentPane().add(BorderLayout.NORTH, getPatientInfoPanel());
        this.getContentPane().add(BorderLayout.CENTER, getDiagnosticPanel());
        this.getContentPane().add(BorderLayout.SOUTH, getMedicalHistoryPanel());
    }

    private JPanel getPatientInfoPanel() {
        if (patientInfoPanel == null) {
            patientInfoPanel = new JPanel();
            TitledBorder titledBorder = new TitledBorder("Patient");
            patientInfoPanel.setBorder(titledBorder);


            ImageIcon image = new ImageIcon();
            try {
                File file = ResourceUtils.getFile("classpath:profile.png");
                BufferedImage img = ImageIO.read(file);
                Image i = img.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
                image = new ImageIcon(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JLabel iconLabel = new JLabel(image);

            JLabel nameLabel = new JLabel("Name");
            JLabel surnameLabel = new JLabel("Surname");

            nameField = new JLabel();
            surnameField = new JLabel();

            JPanel infoPanel = new JPanel();

            infoPanel.setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(8, 8, 8, 8);
            gridBagConstraints.anchor = GridBagConstraints.EAST;

            infoPanel.add(nameLabel, gridBagConstraints);
            gridBagConstraints.gridy++;
            infoPanel.add(surnameLabel, gridBagConstraints);

            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx++;
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            infoPanel.add(nameField, gridBagConstraints);
            gridBagConstraints.gridy++;

            infoPanel.add(surnameField, gridBagConstraints);
            patientInfoPanel.add(BorderLayout.WEST, iconLabel);
            patientInfoPanel.add(BorderLayout.EAST, infoPanel);

        }
        return patientInfoPanel;
    }

    private JPanel getDiagnosticPanel() {
        if (diagnosticPanel == null) {
            diagnosticPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(diagnosticPanel, BoxLayout.Y_AXIS);
            diagnosticPanel.setLayout(boxLayout);
            TitledBorder titledBorder = new TitledBorder("Diagnostic");
            diagnosticPanel.setBorder(titledBorder);

            JScrollPane jScrollPane = new JScrollPane(getDiagnosticList(),
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JPanel buttonPanel = new JPanel();
            JButton createButton = new JButton("Create diagnostic");
            JButton deleteButton = new JButton("Delete diagnostic");

            createButton.addActionListener(actionEvent -> {
//                JOptionPane.showConfirmDialog(
//                        this,
//                        getCreateDiagnosticPanel(),
//                        "Create diagnostic",
//                        JOptionPane.OK_CANCEL_OPTION,
//                        JOptionPane.PLAIN_MESSAGE);
//                System.out.println(diagnosticArea.getText());
            });
            deleteButton.addActionListener(actionEvent -> System.out.println("Delete"));

            buttonPanel.add(BorderLayout.WEST, createButton);
            buttonPanel.add(BorderLayout.EAST, deleteButton);

            diagnosticPanel.add(BorderLayout.NORTH, jScrollPane);
            diagnosticPanel.add(BorderLayout.SOUTH, buttonPanel);
        }
        return diagnosticPanel;
    }

    private JList<Diagnostic> getDiagnosticList() {
        if (diagnosticList == null) {
            diagnosticList = new JList<>();
            diagnosticList.setCellRenderer(new DiagnosticCellRenderer());
        }
        return diagnosticList;
    }

    private JPanel getCreateDiagnosticPanel() {
        if (createDiagnosticPanel == null) {
            createDiagnosticPanel = new JPanel();
            diagnosticArea = new JTextArea();
            createDiagnosticPanel.add(BorderLayout.SOUTH, getICD10Panel());
            createDiagnosticPanel.add(BorderLayout.NORTH, diagnosticArea);
        }
        return createDiagnosticPanel;
    }

    private JPanel getICD10Panel() {
        if (icd10Panel == null) {
            icd10Panel = new JPanel();
            JTextField icd10TextField = new JTextField();
            DefaultListModel<ICD10> icd10Model = new DefaultListModel<>();
            icd10List = new JList<>(icd10Model);

            DefaultListModel<ICD10> model = new DefaultListModel<>();
            for (ICD10 code : icd10Service.findAll()) {
                model.addElement(code);
            }
            icd10List.setModel(model);

            icd10List.setCellRenderer(new ICD10CellRenderer());
            icd10List.setLayoutOrientation(JList.VERTICAL);

            JScrollPane jScrollPane = new JScrollPane(icd10List,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            icd10Panel.setLayout(new BorderLayout(0, 0));
            icd10Panel.add(BorderLayout.NORTH, icd10TextField);
            icd10Panel.add(BorderLayout.CENTER, jScrollPane);
            icd10Panel.add(BorderLayout.SOUTH, new JButton("Add"));
        }
        return icd10Panel;
    }

    private JPanel getMedicalHistoryPanel() {
        if (medicalHistoryPanel == null) {
            medicalHistoryPanel = new JPanel();
            TitledBorder titledBorder = new TitledBorder("Medical history");
            medicalHistoryPanel.setBorder(titledBorder);
        }
        return medicalHistoryPanel;
    }

    void run(Appointment appointment) {
        this.setAppointment(appointment);
        this.setPatient(appointment.getPatient());
        this.setTitle("Your appointment with " + patient.getName());
        this.nameField.setText(patient.getName());
        this.surnameField.setText(patient.getSurname());
        this.loadDiagnostics();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private void loadDiagnostics() {
        DefaultListModel<Diagnostic> model = new DefaultListModel<>();
        for (Diagnostic diagnostic : diagnosticService.findAllByAppointment(this.getAppointment())) {
            model.addElement(diagnostic);
        }
        diagnosticList.setModel(model);
    }
}
