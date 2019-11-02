package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.uniovi.ips.hospital.ui.util.PrintableOnGui;

import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room implements PrintableOnGui {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", location=" + location + ", appointments=" + appointments + "]";
	}

	@Override
	public String guiToString() {
		return location;
	}

}
