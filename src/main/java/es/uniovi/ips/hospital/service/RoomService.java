package es.uniovi.ips.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.repository.RoomRepository;

@Service("RoomService")
public class RoomService {
	
	@Autowired	private RoomRepository roomRepository;
	
	public List<Room> findAllRooms() {
		return roomRepository.findAll();
	}

	public Room findByLocation(String location) {
		return roomRepository.findByLocation(location);
	}

	public Room createRoom(Room room) {
		roomRepository.save(room);
		return roomRepository.findByLocation(room.getLocation());
	}

}
