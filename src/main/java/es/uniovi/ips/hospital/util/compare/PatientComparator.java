package es.uniovi.ips.hospital.util.compare;

import java.util.Comparator;

import es.uniovi.ips.hospital.domain.Patient;

/**
 * Comparador de miembros del personal en base a su nombre completo
 * @author Ricardo Soto, uo265710
 */
public class PatientComparator implements Comparator<Patient> {

	@Override
	public int compare(Patient arg0, Patient arg1) {
		String s0 = arg0.getName() + " " + arg0.getSurname();
		String s1 = arg1.getName() + " " + arg1.getSurname();
		return s0.compareTo(s1);
	}

}
