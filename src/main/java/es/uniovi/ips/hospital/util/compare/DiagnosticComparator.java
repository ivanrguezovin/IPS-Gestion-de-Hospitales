package es.uniovi.ips.hospital.util.compare;

import java.util.Comparator;

import es.uniovi.ips.hospital.domain.Diagnostic;

public class DiagnosticComparator implements Comparator<Diagnostic> {

	@Override
	public int compare(Diagnostic o1, Diagnostic o2) {
		return o1.getAppointment().getStartTime().compareTo(o2.getAppointment().getStartTime());
	}

}
