package es.uniovi.ips.hospital;

import com.github.javafaker.Faker;
import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.AdminAssistantService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.MedicalRecordService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.service.PatientService;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    @Autowired	private AdminAssistantService adminAssistantService;

    @Autowired	private DoctorService doctorService;

    @Autowired	private NurseService nurseService;

    @Autowired	private PatientService patientService;
    
    @Autowired	private MedicalRecordService medicalRecordService;

    private Faker faker;

    public static void main(String[] args) {
        new SpringApplicationBuilder(HospitalApplication.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        faker = new Faker();
        generateFakeAdminAssistants(10);
        generateFakeDoctors(10);
        generateFakeNurses(10);
        generateFakePatients(100);
    }

    private void generateFakeAdminAssistants(int n) {
        for (int i = 0; i < n; i++) {
            AdminAssistant adminAssistant = new AdminAssistant(
            		faker.bothify("########?"),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(5, 10),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode());
            adminAssistantService.createAdminAssistant(adminAssistant);
        }
    }

    private void generateFakeDoctors(int n) {
        for (int i = 0; i < n; i++) {
            Doctor doctor = new Doctor(
            		faker.bothify("########?"),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(5, 10),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode());
            doctorService.createDoctor(doctor);
        }
    }

    private void generateFakeNurses(int n) {
        for (int i = 0; i < n; i++) {
            Nurse nurse = new Nurse(
            		faker.bothify("########?"),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(5, 10),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode());
            nurseService.createNurse(nurse);
        }
    }

    private void generateFakePatients(int n) {
        for (int i = 0; i < n; i++) {
            Patient patient = new Patient(
            		faker.bothify("########?"),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(5, 10),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode()
            );
            patientService.createPatient(patient);
            generateMedicalRecord(patient);
        }
    }

	private void generateMedicalRecord(Patient patient) {
		for (int i = 0; i < 3; i++) {
			MedicalRecord mr = new MedicalRecord(patient);
					mr.setDate(LocalDateTime.of(2018, faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 31), 
							faker.number().numberBetween(0, 23), faker.number().numberBetween(1, 59)));
					mr.setDescription(faker.medical().diseaseName());
					mr.setPrescription(faker.medical().medicineName());
			medicalRecordService.createMedicalRecord(mr);
		}
	}
}
