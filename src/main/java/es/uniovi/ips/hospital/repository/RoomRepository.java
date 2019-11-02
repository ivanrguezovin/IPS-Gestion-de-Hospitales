package es.uniovi.ips.hospital.repository;

import es.uniovi.ips.hospital.domain.Room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

	Room findByLocation(String location);
}
