package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends Staff {

    @ManyToMany(mappedBy = "doctors")
    private Set<Appointment> appointments = new HashSet<>();

    public Doctor() { super(); }

    public Doctor(String dni, String name, String surname, String email, String password, String addressStreet, 
					String addressCity, String addressZIPCode) {
		super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
	}

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

	@Override
	public String toString() {
		return "Doctor [id=" + getId() + ", dni=" + getDni() + ", name=" + getName() + ", surname=" + getSurname() + ", email=" + getEmail()
				+ ", password=" + getPassword() + ", addressStreet=" + getAddressStreet() + ", addressCity=" + getAddressCity()
				+ ", addressZIPCode=" + getAddressZIPCode() + ", schedules=" + getSchedules() + "]";
	}
}
