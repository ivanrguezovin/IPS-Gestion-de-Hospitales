package es.uniovi.ips.hospital.domain;

import org.hibernate.validator.constraints.Length;

import es.uniovi.ips.hospital.ui.util.PrintableOnGui;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff implements PrintableOnGui {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;
    
    @NotNull
    @Column(name = "dni", unique=true)
    @Length(message = "Your DNI is not a valid one")
    private String dni;

    @NotEmpty(message = "Please, provide the name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Please, provide the surname")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Email(message = "Please, provide a valid email")
    @NotEmpty(message = "Please, provide the email")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty(message = "Please, provide a password")
    @Column(name = "password", nullable = false)
    @Length(min = 5, message = "Your password must have at least 5 characters")
    private String password;

    @NotNull
    @Embedded
    @Column(name = "address", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Schedule> schedules = new HashSet<>();

    public Staff() {}

    public Staff(@NotEmpty(message = "Please, provide the dni or equivalent") String dni,
    			@NotEmpty(message = "Please, provide the name") String name,
                @NotEmpty(message = "Please, provide the surname") String surname,
                @Email(message = "Please, provide a valid email") @NotEmpty(message = "Please, provide the email") String email,
                @NotEmpty(message = "Please, provide a password") @Length(min = 5, message = "Your password must have at least 5 characters") String password,
                @NotNull String addressStreet,
                @NotNull String addressCity,
                @NotNull String addressZIPCode) {
    	this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = new Address(addressStreet,addressCity,addressZIPCode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Staff other = (Staff) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }
	
	@Override
	public String guiToString() {
		return id + " - " + name + " " + surname;
	}
}
