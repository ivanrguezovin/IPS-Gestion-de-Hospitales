package es.uniovi.ips.hospital.ui.doctor;

import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.ui.util.render.AppointmentCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class DoctorDialog extends JDialog {


    private JTabbedPane mainPanel;
    private JPanel appointmentsPanel;
    private JPanel patientsPanel;
    private JMenuBar menuBar;
    private JList<Appointment> appointments;
    private JList<Patient> patients;

    private Doctor myself;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointmentDialog appointmentDialog;

    public DoctorDialog() {
        myself = new Doctor();
        this.setModal(true);
        this.setSize(500, 800);

        this.getContentPane().add(BorderLayout.NORTH, getMenuBar());
        this.getContentPane().add(BorderLayout.CENTER, getMainPanel());
    }

    private JMenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new JMenuBar();
            JMenu menuFile = new JMenu("File");
            JMenu menuHelp = new JMenu("Help");
            menuBar.add(menuFile);
            menuBar.add(menuHelp);

            JMenuItem menuItemExit = new JMenuItem("Exit");
            menuFile.add(menuItemExit);
            menuItemExit.addActionListener(actionEvent -> {
                this.dispose();
                System.exit(1);
            });
        }
        return menuBar;

    }

    private JTabbedPane getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JTabbedPane();
            mainPanel.addTab("Appointments", getAppointmentsPanel());
            mainPanel.addTab("Patients", getPatientsPanel());
        }
        return mainPanel;
    }


    private JPanel getAppointmentsPanel() {
        if (appointmentsPanel == null) {
            appointmentsPanel = new JPanel();
            JLabel label = new JLabel("Your appointments for today");
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(getAppointments());
            appointmentsPanel.add(BorderLayout.NORTH, label);
            appointmentsPanel.add(BorderLayout.SOUTH, scrollPane);
        }
        return appointmentsPanel;
    }

    private JList<Appointment> getAppointments() {
        if (appointments == null) {
            appointments = new JList<>();
            appointments.setCellRenderer(new AppointmentCellRenderer());
            appointments.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        appointmentDialog.run(appointments.getSelectedValue());
                    }
                }
            });
        }
        return appointments;
    }


    private JPanel getPatientsPanel() {
        if (patientsPanel == null) {
            patientsPanel = new JPanel();
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(getPatients());
            patientsPanel.add(BorderLayout.CENTER, scrollPane);
        }
        return patientsPanel;
    }

    private JList<Patient> getPatients() {
        if (patients == null) {
            patients = new JList<>();
        }
        return patients;
    }

    public void run(Doctor doctor) {
        this.setMyself(doctor);
        this.setTitle("Hello Dr." + myself.getSurname());
        this.setLocationRelativeTo(null);
        this.loadAppointments();
        this.loadPatients();
        this.setVisible(true);
    }

    private void setMyself(Doctor myself) {
        this.myself = myself;
    }

    private void loadAppointments() {
        DefaultListModel<Appointment> model = new DefaultListModel<>();
        for (Appointment appointment : appointmentService.findAllByDoctor(myself)) {
            model.addElement(appointment);
        }
        appointments.setModel(model);
    }

    private void loadPatients() {
    }
}
