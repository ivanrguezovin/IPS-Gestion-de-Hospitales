package es.uniovi.ips.hospital.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    
    @Column(name = "prescription")
    private String prescription;
    
    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    private Patient patient;
    
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Diagnostic> diagnostics = new HashSet<>();
    
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

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescrition) {
		this.prescription = prescrition;
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
		return "MedicalRecord [id=" + id + ", description=" + description + ", date=" + date + "]";
	}
    
	public Set<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(Set<Diagnostic> diagnostics) {
        this.diagnostics = diagnostics;
    }
    
}
