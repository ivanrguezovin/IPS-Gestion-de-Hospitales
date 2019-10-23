package es.uniovi.ips.hospital.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nurses")
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

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
    @Column(name = "address_street", nullable = false)
    private String addressStreet;

    @NotNull
    @Column(name = "address_city", nullable = false)
    private String addressCity;

    @NotNull
    @Column(name = "address_zip_code", nullable = false)
    private String addressZIPCode;

    public Nurse() {

    }

    public Nurse(@NotEmpty(message = "Please, provide the name") String name,
                 @NotEmpty(message = "Please, provide the surname") String surname,
                 @Email(message = "Please, provide a valid email") @NotEmpty(message = "Please, provide the email") String email,
                 @NotEmpty(message = "Please, provide a password") @Length(min = 5, message = "Your password must have at least 5 characters") String password,
                 @NotNull String addressStreet,
                 @NotNull String addressCity,
                 @NotNull String addressZIPCode) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressZIPCode = addressZIPCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZIPCode() {
        return addressZIPCode;
    }

    public void setAddressZIPCode(String addressZIPCode) {
        this.addressZIPCode = addressZIPCode;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressZIPCode='" + addressZIPCode + '\'' +
                '}';
    }
}
