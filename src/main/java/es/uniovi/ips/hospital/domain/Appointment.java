package es.uniovi.ips.hospital.domain;

import es.uniovi.ips.hospital.util.comunication.SendEmail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @NotNull
    @Column(name = "urgent", nullable = false)
    private boolean urgent;

    @NotNull
    @Column(name = "contactInfo", nullable = false)
    private String contactInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Doctor> doctors;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Diagnostic> diagnostics;

    public Appointment() {
        doctors = new HashSet<>();
        diagnostics = new HashSet<>();
        this.urgent = false;
    }

    public Appointment(@NotNull LocalDateTime startTime,
                       Set<Doctor> doctors,
                       Patient patient,
                       Room room) {
        super();
        this.startTime = startTime;
        this.urgent = false;
        this.contactInfo = patient.getEmail();
        this.doctors = doctors;
        this.patient = patient;
        this.room = room;
    }

    public Appointment(@NotNull LocalDateTime startTime,
                       boolean urgent,
                       Set<Doctor> doctors,
                       Patient patient,
                       Room room) {
        this(startTime, doctors, patient, room);
        this.urgent = urgent;
    }

    public Appointment(@NotNull LocalDateTime startTime,
                       boolean urgent,
                       String contactInfo,
                       Set<Doctor> doctors,
                       Patient patient,
                       Room room) {
        this(startTime, doctors, patient, room);
        this.contactInfo = contactInfo;
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

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String prettifyDoctors() {
        StringBuilder aux = new StringBuilder();
        for (Doctor doctor : this.getDoctors()) {
            aux.append(doctor.getName()).append(", ");
        }
        return aux.toString();
    }

    public void addDoctor(Doctor doctor) {
        this.getDoctors().add(doctor);
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

    public Set<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(Set<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
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