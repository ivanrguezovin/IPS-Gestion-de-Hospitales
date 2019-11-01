package es.uniovi.ips.hospital.ui.util.filter;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;
import es.uniovi.ips.hospital.domain.Patient;

/**
 * Gestiona el filtro de texto de las listas de personal
 * @author Ricardo Soto (uo265710)
 */
public class PatientTextFilterator implements TextFilterator<Patient> {
	
    public void getFilterStrings(List<String> baseList, Patient patient) {
        baseList.add(patient.getDni());
        baseList.add(patient.getName());
        baseList.add(patient.getSurname());
        baseList.add(patient.getEmail());
        baseList.add("" + patient.getId());
    }
}