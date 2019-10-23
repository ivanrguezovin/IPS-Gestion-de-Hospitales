package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.repository.AdminAssitantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminAssistantService")
public class AdminAssistantService {
    @Autowired
    private AdminAssitantRepository adminAssitantRepository;

    public List<AdminAssistant> findAllAdminAssistant() {
        return adminAssitantRepository.findAll();
    }

    public AdminAssistant createAdminAssistant(AdminAssistant adminAssistant) {
        adminAssitantRepository.save(adminAssistant);
        return adminAssitantRepository.findByEmail(adminAssistant.getEmail());
    }
}
