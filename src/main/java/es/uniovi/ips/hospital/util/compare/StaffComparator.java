package es.uniovi.ips.hospital.util.compare;

import java.util.Comparator;

import es.uniovi.ips.hospital.domain.Staff;

/**
 * Comparador de miembros del personal en base a su nombre completo
 * @author Ricardo Soto, uo265710
 */
public class StaffComparator implements Comparator<Staff> {

	@Override
	public int compare(Staff arg0, Staff arg1) {
		String s0 = arg0.getName() + " " + arg0.getSurname();
		String s1 = arg1.getName() + " " + arg1.getSurname();
		return s0.compareTo(s1);
	}

}
