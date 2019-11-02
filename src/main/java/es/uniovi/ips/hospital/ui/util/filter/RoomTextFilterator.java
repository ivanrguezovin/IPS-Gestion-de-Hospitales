package es.uniovi.ips.hospital.ui.util.filter;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;
import es.uniovi.ips.hospital.domain.Room;

/**
 * Gestiona el filtro de texto de las listas de personal
 * @author Ricardo Soto (uo265710)
 */
public class RoomTextFilterator implements TextFilterator<Room> {
	
    public void getFilterStrings(List<String> baseList, Room room) {
        baseList.add("" + room.getId());
        baseList.add(room.getLocation());
    }
}