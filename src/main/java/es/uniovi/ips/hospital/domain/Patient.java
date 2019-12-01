package es.uniovi.ips.hospital.domain;

import es.uniovi.ips.hospital.ui.util.PrintableOnGui;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient implements PrintableOnGui {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "historyNumber", unique = true)
    private Integer historyNumber;

    @NotNull
    @Column(name = "dni", unique = true)
    @Length(min = 9, max = 9, message = "Your DNI is not a valid one")
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

    @NotNull
    @Embedded
    @Column(name = "address", nullable = false)
    private Address address;

    @NotNull
    @Length(min = 10, max = 10, message = "Your health card number is not a valid one")
    @Column(name = "healthCardNumber", unique = true)
    private String healthCardNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Vaccine> vaccines;

    public Patient() {
        // Nothing here.
    }

    public Patient(@NotEmpty(message = "Please, provide the dni or equivalent") String dni,
                   @NotEmpty(message = "Please, provide the name") String name,
                   @NotEmpty(message = "Please, provide the surname") String surname,
                   @Email(message = "Please, provide a valid email")
                   @NotEmpty(message = "Please, provide the email") String email,
                   @NotNull String addressStreet,
                   @NotNull String addressCity,
                   @NotNull String addressZIPCode,
                   @NotEmpty String healthCardNumber,
                   Integer historyNumber) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = new Address(addressStreet, addressCity, addressZIPCode);
        this.healthCardNumber = healthCardNumber;
        this.historyNumber = historyNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHistoryNumber() {
        return historyNumber;
    }

    public void setHistoryNumber(Integer historyNumber) {
        this.historyNumber = historyNumber;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public Set<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(Set<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email
                + ", address=" + address + ", medicalRecords=" + medicalRecords + "]";
    }

    public String guiToString() {
        return name + " " + surname + " - " + dni;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Patient other = (Patient) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
}
