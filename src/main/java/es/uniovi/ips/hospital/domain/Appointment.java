package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(name = "endTime", nullable = false)
    private LocalDateTime endTime;

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
        this.endTime = endTime;
        this.doctors = doctors;
        this.patient = patient;
        this.room = room;
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", doctors=" + doctors +
                ", patient=" + patient +
                ", room=" + room +
                '}';
    }
}