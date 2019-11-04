package es.uniovi.ips.hospital;

import com.github.javafaker.Faker;
import es.uniovi.ips.hospital.domain.*;
import es.uniovi.ips.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.time.LocalDateTime;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    @Autowired
    private AdminAssistantService adminAssistantService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private NurseService nurseService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private ScheduleService scheduleService;

    private Faker faker;

    public static void main(String[] args) {
        new SpringApplicationBuilder(HospitalApplication.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        faker = new Faker();
        generateFakeAdminAssistants(10);
        generateTestAssistant();
        generateFakeDoctors(10);
        generateTestDoctor();
        generateFakeNurses(10);
        generateFakePatients(100);
        generateFakeRooms(10);
    }

    private void generateFakeAdminAssistants(int n) {
        for (int i = 0; i < n; i++) {
            AdminAssistant adminAssistant = new AdminAssistant(
                    faker.bothify("########?").toUpperCase(),
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

    private void generateTestAssistant() {
        AdminAssistant adminAssistant = new AdminAssistant(
                faker.bothify("########?").toUpperCase(),
                faker.name().firstName(),
                faker.name().lastName(),
                "admin@ips.test",
                "password",
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode());
        adminAssistantService.createAdminAssistant(adminAssistant);
    }

    private void generateFakeDoctors(int n) {
        for (int i = 0; i < n; i++) {
            Doctor doctor = new Doctor(
                    faker.bothify("########?").toUpperCase(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(5, 10),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode());
            doctorService.createDoctor(doctor);
            generateSchedule(doctor);
        }
    }

    private void generateTestDoctor() {
        Doctor doctor = new Doctor(
                faker.bothify("########?").toUpperCase(),
                faker.name().firstName(),
                faker.name().lastName(),
                "doctor@ips.test",
                "password",
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode());
        doctorService.createDoctor(doctor);
        generateSchedule(doctor);
    }

    private void generateFakeNurses(int n) {
        for (int i = 0; i < n; i++) {
            Nurse nurse = new Nurse(
                    faker.bothify("########?").toUpperCase(),
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
                    faker.bothify("########?").toUpperCase(),
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

    private void generateFakeRooms(int n) {
        for (int i = 0; i < n; i++) {
            Room room = new Room();
            room.setLocation(faker.numerify("###"));
            roomService.createRoom(room);
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

    private void generateSchedule(Staff employee) {
        for (int i = 0; i < 31; i++) {
            Schedule schedule = new Schedule();
            schedule.setEmployee(employee);
            schedule.setStartTime(LocalDateTime.of(2019, 12, i + 1, faker.number().numberBetween(0, 11), 0));
            schedule.setEndTime(LocalDateTime.of(2019, 12, i + 1, faker.number().numberBetween(12, 23), 0));
            scheduleService.createSchedule(schedule);
        }
    }
}
