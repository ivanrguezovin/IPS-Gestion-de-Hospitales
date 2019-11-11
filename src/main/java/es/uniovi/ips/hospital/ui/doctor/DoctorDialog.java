package es.uniovi.ips.hospital.ui.doctor;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.ui.doctor.appointment.MyAppointmentsDialog;
import es.uniovi.ips.hospital.ui.doctor.vaccine.VaccineDoctorDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class DoctorDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();

    private Doctor doctor;

    @Autowired
    private VaccineDoctorDialog vdd;

    @Autowired
    private MyAppointmentsDialog myAppointmentsDialog;


    public DoctorDialog() {
        setResizable(false);
        setTitle("Doctor Menu");
        setBounds(100, 100, 660, 246);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new GridLayout(2, 3, 0, 0));
            {
                java.awt.Component horizontalStrut = Box.createHorizontalStrut(20);
                panel.add(horizontalStrut);
            }
            {
                JLabel lblAppointments = new JLabel("Appointments");
                lblAppointments.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(lblAppointments);
            }
            {
                JButton btnSeeAppointments = new JButton("Appointments");
                btnSeeAppointments.addActionListener(actionEvent -> myAppointmentsDialog.run(doctor));
                btnSeeAppointments.setMnemonic('a');
                panel.add(btnSeeAppointments);
            }
            {
                java.awt.Component horizontalStrut = Box.createHorizontalStrut(20);
                panel.add(horizontalStrut);
            }
            {
                java.awt.Component horizontalStrut = Box.createHorizontalStrut(20);
                panel.add(horizontalStrut);
            }
            {
                JLabel lblNewLabel = new JLabel("Vaccines");
                lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(lblNewLabel);
            }
            {
                JButton btnSeeVaccines = new JButton("Vaccines");
                btnSeeVaccines.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        vdd.setVisible(true);
                        vdd.setDoctor(doctor);
                    }
                });
                btnSeeVaccines.setMnemonic('v');
                panel.add(btnSeeVaccines);
            }
            {
                java.awt.Component horizontalStrut = Box.createHorizontalStrut(20);
                panel.add(horizontalStrut);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Back");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                okButton.setMnemonic('b');
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Exit");
                cancelButton.setMnemonic('s');
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(-1);
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void run(Doctor doctor) {
        this.setDoctor(doctor);
        this.setVisible(true);
    }

}
