package es.uniovi.ips.hospital.domain;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.uniovi.ips.hospital.util.comunication.SendEmail;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;
    
    @NotNull
    @Column(name = "urgent", nullable = false)
    private boolean urgent;

    @ManyToMany
    private Set<Doctor> doctors;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Room room;
    
    public Appointment() {}

    public Appointment(@NotNull LocalDateTime startTime,
                       @NotNull LocalDateTime endTime,
                       Set<Doctor> doctors,
                       Patient patient,
                       Room room) {
        this.startTime = startTime;
        this.urgent = false;
        this.doctors = doctors;
        this.patient = patient;
        this.room = room;
    }
    
    public Appointment(@NotNull LocalDateTime startTime,
			            @NotNull LocalDateTime endTime,
			            boolean urgent,
			            Set<Doctor> doctors,
			            Patient patient,
			            Room room) {
    	this(startTime, endTime, doctors, patient, room);
    	this.urgent = urgent;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", startTime=" + startTime + ", urgent=" + urgent
				+ ", doctors=" + doctors + ", patient=" + patient + ", room=" + room + "]";
	}

	public String guiToString() {
		return patient.guiToString() + " - " + startTime + " - " + room + " " + urgentString();
	}

	private String urgentString() {
		return (urgent) ? "(URGENT)" : "";
	}
	
	// BUSINESS METHODS

    public void sendEmail() throws AddressException, MessagingException {
    	new SendEmail(this).execute();
    }

    
}