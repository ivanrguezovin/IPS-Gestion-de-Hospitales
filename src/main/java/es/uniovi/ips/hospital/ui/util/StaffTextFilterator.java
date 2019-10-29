package es.uniovi.ips.hospital.ui.util;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;
import es.uniovi.ips.hospital.domain.Staff;

/**
 * Gestiona el filtro de texto de las listas de personal
 * @author Ricardo Soto (uo265710)
 */
public class StaffTextFilterator implements TextFilterator<Staff> {
	
    public void getFilterStrings(List<String> baseList, Staff staff) {
        baseList.add(staff.getDni());
        baseList.add(staff.getName());
        baseList.add(staff.getSurname());
        baseList.add(staff.getEmail());
        baseList.add("" + staff.getId());
    }
}