package es.uniovi.ips.hospital;

import com.github.javafaker.Faker;
import es.uniovi.ips.hospital.domain.*;
import es.uniovi.ips.hospital.exception.BusinessException;
import es.uniovi.ips.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
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
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ICD10Service icd10Service;

    private Faker faker;
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        new SpringApplicationBuilder(HospitalApplication.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        faker = new Faker();
        passwordEncoder = new BCryptPasswordEncoder();
        generateFakeAdminAssistants(10);
        generateTestAssistant();
        generateFakeNurses(10);
        generateFakePatients(10);
        generateFakeRooms(10);
        generateFakeDoctors(10);
        generateTestDoctor();
        generateTestNurse();
        icd10Service.load();
    }

    private void generateFakeAdminAssistants(int n) {
        for (int i = 0; i < n; i++) {
            AdminAssistant adminAssistant = new AdminAssistant(
                    faker.bothify("########?").toUpperCase(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    passwordEncoder.encode(faker.internet().password(5, 10)),
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
                passwordEncoder.encode("password"),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode());
        adminAssistantService.createAdminAssistant(adminAssistant);
    }

    private void generateFakeDoctors(int n) {
        Long l = 1L;
        for (int i = 0; i < n; i++) {
            Doctor doctor = new Doctor(
                    faker.bothify("########?").toUpperCase(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    passwordEncoder.encode(faker.internet().password(5, 10)),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode(), "Especialidad de prueba", l);
            l++;
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
                passwordEncoder.encode("password"),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(), "Especialidad de prueba", 11L);
        doctor = doctorService.createDoctor(doctor);
        generateSchedule(doctor);
        generateAppointments(doctor);
    }

    private void generateFakeNurses(int n) {
        Long l = 12L;
        for (int i = 0; i < n; i++) {
            Nurse nurse = new Nurse(
                    faker.bothify("########?").toUpperCase(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    passwordEncoder.encode(faker.internet().password(5, 10)),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode(), "Especialidad de prueba", l);
            l++;
            nurseService.createNurse(nurse);
        }
    }

    private void generateTestNurse() {
        Nurse nurse = new Nurse(
                faker.bothify("########?").toUpperCase(),
                faker.name().firstName(),
                faker.name().lastName(),
                "nurse@ips.test",
                passwordEncoder.encode("password"),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode(), "Especialidad de prueba", 11L);
        nurse = nurseService.createNurse(nurse);
        generateSchedule(nurse);
        generateAppointmentsNurse(nurse);
    }

    private void generateFakePatients(int n) {
        for (int i = 0; i < n; i++) {
            Patient patient = new Patient(
                    faker.bothify("########?").toUpperCase(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().safeEmailAddress(),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().zipCode(),
                    faker.bothify("##########").toUpperCase(),
                    faker.random().nextInt(1, 999999)
            );
            patientService.createPatient(patient);
            generateMedicalRecord(patient);
        }
    }

    private void generateFakeRooms(int n) {
        for (int i = 0; i < n; i++) {
            Room room = new Room();
            room.setLocation(faker.numerify("###"));
            try {
                roomService.createRoom(room);
            } catch (Exception e) {
                System.out.println("Duplicated room");
            }
            ;
        }
    }

    private void generateMedicalRecord(Patient patient) {
        for (int i = 0; i < 3; i++) {
            MedicalRecord mr = new MedicalRecord(patient);
            LocalDateTime h = LocalDateTime.of(2018, faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 31),
                    faker.number().numberBetween(0, 23), faker.number().numberBetween(1, 59));
            mr.setDate(h);
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


    private void generateAppointments(Doctor doctor) {
        List<Patient> patients = patientService.findAllPatient();
        List<Room> rooms = roomService.findAllRooms();
        for (int i = 10; i < 31; i++) {
            Appointment appointment = new Appointment();
            LocalDateTime start = LocalDateTime.of(2019, 12, i + 1, faker.number().numberBetween(0, 11), 0);
            appointment.setStartTime(start);
            appointment.setEndTime(start.plusMinutes(45));
            appointment.setPatient(patients.get(i % patients.size()));
            appointment.addDoctor(doctor);
            appointment.setRoom(rooms.get(i % rooms.size()));
            appointment.setContactInfo(appointment.getPatient().getEmail());
            try {
                appointmentService.createAppointment(appointment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void generateAppointmentsNurse(Nurse nurse) {
        List<Patient> patients = patientService.findAllPatient();
        List<Room> rooms = roomService.findAllRooms();
        for (int i = 0; i < 10; i++) {
            Appointment appointment = new Appointment();
            LocalDateTime start = LocalDateTime.of(2019, 12, i + 1, faker.number().numberBetween(0, 11), 0);
            appointment.setStartTime(start);
            appointment.setEndTime(start.plusMinutes(45));
            appointment.setPatient(patients.get(i % patients.size()));
            appointment.addNurse(nurse);
            appointment.setRoom(rooms.get(i % rooms.size()));
            appointment.setContactInfo(appointment.getPatient().getEmail());
            try {
                appointmentService.createAppointment(appointment);
            } catch (BusinessException e) {
                System.out.println("Duplicated appointment");
            }
        }
    }
}
