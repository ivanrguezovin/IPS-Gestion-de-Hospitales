package es.uniovi.ips.hospital.ui.util.filter;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;
import es.uniovi.ips.hospital.domain.Appointment;

/**
 * Gestiona el filtro de texto de las listas de personal
 * @author Ricardo Soto (uo265710)
 */
public class AppointmentTextFilterator implements TextFilterator<Appointment> {
	
    public void getFilterStrings(List<String> baseList, Appointment appointment) {
        baseList.add(appointment.getId().toString());
        baseList.add(appointment.getStartTime().toString());
        baseList.add(appointment.getPatient().getDni());
        baseList.add(appointment.getPatient().getName());
        baseList.add(appointment.getPatient().getSurname());
        baseList.add(appointment.getRoom().getLocation());
        String urgent = (appointment.isUrgent()) ? "URGENT" : "";
        baseList.add(urgent);
    }
}