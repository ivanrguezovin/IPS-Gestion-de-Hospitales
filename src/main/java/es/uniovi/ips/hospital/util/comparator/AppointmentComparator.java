package es.uniovi.ips.hospital.util.comparator;

import java.util.Comparator;

import es.uniovi.ips.hospital.domain.Appointment;

/**
 * Comparador de citas en base al doctor a cargo de estas
 * @author Ricardo Soto, uo265710
 */
public class AppointmentComparator implements Comparator<Appointment> {

	@Override
	public int compare(Appointment arg0, Appointment arg1) {
		return arg0.getId().compareTo(arg1.getId());
	}

}
