package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StaffRepository staffRepository;

    public Staff login(String email, char[] password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Staff user = staffRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(new String(password), user.getPassword())) {
            log.info("session opened for user " + user.getEmail());
            return user;
        }
        log.error("Wrong user/credentials");
        return null;
    }
}
