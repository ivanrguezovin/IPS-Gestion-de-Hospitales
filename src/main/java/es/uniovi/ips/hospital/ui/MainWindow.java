package es.uniovi.ips.hospital.ui;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.LoginService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainWindow {

    @Autowired
    private LoginService loginService;


    @Autowired
    private AdminDialog adminDialog;

    @Autowired
    private DoctorDialog doctorDialog;

    // TODO Remove me
    @Autowired
    private DoctorService doctorService;

    private JFrame frame;

    public MainWindow() {
        frame = new JFrame("Hospital");

        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton closeButton = new JButton("Close");

        loginButton.addActionListener(actionEvent -> login(emailField.getText(), passwordField.getPassword()));
        closeButton.addActionListener(actionEvent -> {
            frame.dispose();
            System.exit(0);
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(8, 8, 8, 8);
        gridBagConstraints.anchor = GridBagConstraints.EAST;

        frame.add(emailLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        frame.add(passwordLabel, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx++;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        frame.add(emailField, gridBagConstraints);
        gridBagConstraints.gridy++;
        frame.add(passwordField, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx++;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1;
        frame.add(loginButton, gridBagConstraints);
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridx++;
        frame.add(closeButton, gridBagConstraints);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login(String email, char[] password) {
        Staff user = loginService.login(email, password);

        // TODO Remove me
        user = doctorService.findByEmail("doctor@ips.test");
        if (user instanceof Doctor) {
            //frame.setVisible(false);
            doctorDialog.run((Doctor) user);

        } else if (user instanceof AdminAssistant) {
            System.out.println("AdminAssistant");
            adminDialog.setVisible(true);
        } else {
            // TODO Show incorrect user login
            System.out.println("TODO Show incorrect user login");
        }
    }
}
