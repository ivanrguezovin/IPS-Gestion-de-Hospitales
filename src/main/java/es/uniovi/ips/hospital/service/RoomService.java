package es.uniovi.ips.hospital.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.ips.hospital.domain.Room;
import es.uniovi.ips.hospital.repository.RoomRepository;

@Service("RoomService")
public class RoomService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	private RoomRepository roomRepository;
	
	public List<Room> findAllRooms() {
		return roomRepository.findAll();
	}

	public Room findByLocation(String location) {
		return roomRepository.findByLocation(location);
	}

	public Room createRoom(Room room) {
		roomRepository.save(room);
		log.info("the room at " + room.getLocation() + " has been created");
		return roomRepository.findByLocation(room.getLocation());
	}

}
