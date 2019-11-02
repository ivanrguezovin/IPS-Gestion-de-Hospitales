package es.uniovi.ips.hospital.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.ui.admin.AdminDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainWindow {

    private JList<String> listWorkers;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AdminDialog adminDialog;

    public MainWindow() {
        JFrame frame = new JFrame("List of patients");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

//        try {
//            UIManager.setLookAndFeel(new FlatDarculaLaf());
//        } catch (Exception ex) {
//            System.err.println("Failed to initialize the look and feel");
//        }

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        JMenuItem menuItemOpen = new JMenuItem("Open");
        menuFile.add(menuItemOpen);

        JPanel panel = new JPanel();
        JButton buttonSearch = new JButton("List patients");

        buttonSearch.addActionListener(actionEvent -> loadPatients());

        panel.add(buttonSearch);

        listWorkers = new JList<>();
        listWorkers.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(listWorkers);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(actionEvent -> adminDialog.setVisible(true));
        panel.add(btnAdmin);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadPatients() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Patient patient : patientService.findAllPatient()) {
            model.addElement(patient.getName() + " " + patient.getSurname());
        }
        listWorkers.setModel(model);
    }
}
