package es.uniovi.ips.hospital.domain;

import javax.persistence.*;

@Entity
@Table(name = "nurses")
public class Nurse extends Staff {

    public Nurse() { super(); }

    public Nurse(String dni, String name, String surname, String email, String password, String addressStreet, 
    			String addressCity, String addressZIPCode) {
		super(dni, name, surname, email, password, addressStreet, addressCity, addressZIPCode);
	}
    
}
