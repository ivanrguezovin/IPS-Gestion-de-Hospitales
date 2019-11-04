package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginService {

    @Autowired
    private StaffRepository staffRepository;

    public Staff login(String email, char[] password) {
        // I know, this is the worst verification system ever
        Staff user = staffRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(String.valueOf(password))) {
            return user;
        }
        return null;
    }
}
