package es.uniovi.ips.hospital.domain;

import javax.persistence.*;

@Entity
@Table(name = "medicalRecords")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    private Patient patient;
    
    public MedicalRecord() {}

    public MedicalRecord(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patient=" + patient +
                '}';
    }
}
