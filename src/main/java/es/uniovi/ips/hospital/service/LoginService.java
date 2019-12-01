package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff login(String email, char[] password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Staff user = staffRepository.findByEmail(email);
        if (passwordEncoder.matches(new String(password), user.getPassword())) {
            return user;
        }
        return null;
    }
}
