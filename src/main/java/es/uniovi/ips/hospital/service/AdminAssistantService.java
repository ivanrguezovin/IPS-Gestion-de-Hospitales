package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.repository.AdminAssitantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminAssistantService")
public class AdminAssistantService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminAssitantRepository adminAssitantRepository;

    public List<AdminAssistant> findAllAdminAssistant() {
        return adminAssitantRepository.findAll();
    }

    public AdminAssistant createAdminAssistant(AdminAssistant adminAssistant) {
        log.info("the assistant with the email " + adminAssistant.getEmail() + " has been created");
        adminAssitantRepository.save(adminAssistant);
        return adminAssitantRepository.findByEmail(adminAssistant.getEmail());
    }
}
