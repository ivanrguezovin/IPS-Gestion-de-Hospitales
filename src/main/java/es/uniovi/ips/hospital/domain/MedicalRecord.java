package es.uniovi.ips.hospital.domain;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medicalRecords")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @Column(name = "description")
    private String description;
    
    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    private Patient patient;
    
    public MedicalRecord() {}

    public MedicalRecord(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

	@Override
	public String toString() {
		return "MedicalRecord [id=" + id + ", description=" + description + ", date=" + date + ", patient=" + patient
				+ "]";
	}
    
    
}
