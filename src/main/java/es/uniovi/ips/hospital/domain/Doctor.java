package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends Staff {

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();
    
    @Column(name="speciality")
    @NotEmpty
    private String speciality;
    
    @Column(unique = true, name="collegeNumber")
    @NotNull
    private Long collegeNumber;
    
    public Doctor() { super(); }

    public Doctor(String dni, String name, String surname, String email, String password, String addressStreet, 
					String addressCity, String addressZIPCode, String speciality, Long collegeNumber) {
		super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
		this.speciality=speciality;
		this.collegeNumber=collegeNumber;
	}

    public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Long getCollegeNumber() {
		return collegeNumber;
	}

	public void setCollegeNumber(Long collegeNumber) {
		this.collegeNumber = collegeNumber;
	}

	public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    @Override
    public String guiToString() {
    	return getId() + " - Dr. " + getName() + " " + getSurname();
    }
}
