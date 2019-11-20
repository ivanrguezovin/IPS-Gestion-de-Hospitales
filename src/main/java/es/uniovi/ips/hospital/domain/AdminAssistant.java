package es.uniovi.ips.hospital.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adminAssistants")
public class AdminAssistant extends Staff {

    public AdminAssistant() {
        super();
    }

    public AdminAssistant(String dni, String name, String surname, String email, String password, String addressStreet,
                          String addressCity, String addressZIPCode) {
        super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
    }
}
