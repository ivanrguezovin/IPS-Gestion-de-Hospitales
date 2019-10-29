package es.uniovi.ips.hospital.util.comparator;

import java.util.Comparator;

import es.uniovi.ips.hospital.domain.MedicalRecord;

/**
 * Comparador de historiales m�dicos en base a la fecha de cada entrada particular
 * @author Ricardo Soto, uo265710
 */
public class MedicalHistoryComparator implements Comparator<MedicalRecord> {

	@Override
	public int compare(MedicalRecord arg0, MedicalRecord arg1) {
		return arg0.getDate().compareTo(arg1.getDate());
	}

}
