package es.uniovi.ips.hospital.ui.doctor;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.ICD10;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.ICD10Service;
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
    private JScrollPane icd10Panel;
    private JPanel medicalHistoryPanel;

    private JLabel nameField;
    private JLabel surnameField;

    @Autowired
    private ICD10Service icd10Service;

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
            TitledBorder titledBorder = new TitledBorder("Diagnostic");
            diagnosticPanel.setBorder(titledBorder);

            JTextArea diagnosticField = new JTextArea(20, 40);

            diagnosticPanel.add(BorderLayout.WEST, diagnosticField);
            diagnosticPanel.add(BorderLayout.EAST, getICD10Panel());
        }
        return diagnosticPanel;
    }

    private JScrollPane getICD10Panel() {
        if (icd10Panel == null) {
            JTree icd10 = new JTree();
            icd10.setPreferredSize(new Dimension(300, 40));
            // TODO Populate the JTree
            icd10Panel = new JScrollPane(icd10,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
