package es.uniovi.ips.hospital.domain;

import es.uniovi.ips.hospital.ui.util.PrintableOnGui;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "patients")
@Transactional
public class Patient implements PrintableOnGui {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "dni")
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

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<MedicalRecord> medicalRecords;

    public Patient() {
    }

    public Patient(@NotEmpty(message = "Please, provide the dni or equivalent") String dni,
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
        this.address = new Address(addressStreet, addressCity, addressZIPCode);
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

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email
                + ", password=" + password + ", address=" + address + ", medicalRecords=" + medicalRecords + "]";
    }

    public String guiToString() {
        return name + " " + surname + " - " + dni;
    }
}
