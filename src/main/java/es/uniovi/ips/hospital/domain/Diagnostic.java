package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "diagnostics")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "comment")
    private String comment;

    @NotNull
    @ManyToOne
    private Doctor doctor;

    @ManyToMany(mappedBy = "diagnostics", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ICD10> codes;

    @NotNull
    @ManyToOne
    private Appointment appointment;

    @NotNull
    @Column(name = "when")
    private LocalDateTime created;
    
    @NotNull
    @Column(name = "active")
    private boolean active = true;

    public Diagnostic() {}

    public Diagnostic(@NotNull String comment,
                      @NotNull Doctor doctor,
                      @NotNull List<ICD10> codes,
                      @NotNull Appointment appointment,
                      @NotNull LocalDateTime created) {
        super();
        this.comment = comment;
        this.doctor = doctor;
        this.codes = codes;
        this.appointment = appointment;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<ICD10> getCodes() {
        return codes;
    }

    public void setCodes(List<ICD10> codes) {
        this.codes = codes;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
    public String toString() {
        return "Diagnostic{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", doctor=" + doctor +
                ", codes=" + codes +
                ", appointment=" + appointment +
                ", created=" + created +
                '}';
    }
}
