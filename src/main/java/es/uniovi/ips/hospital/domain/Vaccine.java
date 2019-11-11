package es.uniovi.ips.hospital.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vaccines")
public class Vaccine {
	public enum VaccineType{VIVAS_ATENUADAS, INACTIVADAS, SUBUNIDADES_RECOMBINANTES_POLISACÁRIDAS_Y_COMBINADAS, CON_TOXOIDES}
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name="vaccineType")
    private VaccineType vaccineType;
    
    @NotNull
    @Column(name="description")
    private String description;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    @NotNull
    @Column(name="applied")
    private boolean applied=false;
    
    @ManyToOne
    private Patient patient;

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}

	public Vaccine() {}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public boolean isApplied() {
		return applied;
	}

	public VaccineType getVaccineType() {
		return vaccineType;
	}

	public void setVaccineType(VaccineType vaccineType) {
		this.vaccineType = vaccineType;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Vaccine " + getId() + " of type " + getVaccineType() + ": - Description: " + getDescription() + " - Date: " + 
				getDate() + " - Patient: " + getPatient().getName() + " " + getPatient().getSurname() + " con dni " 
				+ getPatient().getDni() + " - Applied: " + isApplied() + ".";
	}

	public Vaccine(@NotNull VaccineType vaccineType, @NotNull String description, LocalDateTime date,
			@NotNull boolean applied, Patient patient) {
		super();
		this.vaccineType = vaccineType;
		this.description = description;
		this.date = date;
		this.applied = applied;
		this.patient = patient;
	}
	
}
