package es.uniovi.ips.hospital.ui.util.render;

import es.uniovi.ips.hospital.domain.Appointment;

import javax.swing.*;
import java.awt.*;

public class AppointmentCellRenderer extends JLabel implements ListCellRenderer<Appointment> {

	private static final long serialVersionUID = 647004768154891428L;

	@Override
    public Component getListCellRendererComponent(JList<? extends Appointment> jList,
                                                  Appointment appointment,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        this.setText(
                "Appointment with " +
                        appointment.getPatient().getName() +
                        " " +
                        appointment.getPatient().getSurname() +
                        " at " +
                        appointment.getStartTime().getHour() + ":" + appointment.getStartTime().getMinute() +
                        " in the room " +
                        appointment.getRoom().getLocation() + "\n"
        );
        return this;
    }
}
