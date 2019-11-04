package es.uniovi.ips.hospital.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @NotNull
    @Column(name="name")
    private String name;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    @NotNull
    @Column(name="applied")
    private boolean applied;
    
    @ManyToMany(mappedBy = "vaccines", fetch = FetchType.EAGER)
    private Set<Patient> patients = new HashSet<>();

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Vaccine [id=" + id + ", date=" + date + ", applied=" + applied + ", patients=" + patients + "]";
	}

	public Vaccine() {}
	
	
	public Vaccine(Long id, @NotNull String name, LocalDateTime date, @NotNull boolean applied, Set<Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.applied = applied;
		this.patients = patients;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public boolean isApplied() {
		return applied;
	}

	public Set<Patient> getPatients() {
		return patients;
	}
	
}
