package es.uniovi.ips.hospital.ui.nurse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Nurse;

@Component
public class NurseDialog extends JDialog {

	private static final long serialVersionUID = 224987595461753740L;

	private final JPanel contentPanel = new JPanel();

    private Nurse nurse;

    @Autowired
    private MyAppointmentsDialogNurse myAppointmentsDialog;


    public NurseDialog() {
        setResizable(false);
        setTitle("Nurse Menu");
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
                JButton btnSeeAppointments = new JButton("Show Appointments");
                btnSeeAppointments.addActionListener(actionEvent -> myAppointmentsDialog.run(nurse));
                btnSeeAppointments.setMnemonic('a');
                panel.add(btnSeeAppointments);
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
                okButton.addActionListener(e -> dispose());
                okButton.setMnemonic('b');
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Exit");
                cancelButton.setMnemonic('s');
                cancelButton.addActionListener(e -> System.exit(-1));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public void run(Nurse nurse) {
        this.setNurse(nurse);
        this.setVisible(true);
    }

}
