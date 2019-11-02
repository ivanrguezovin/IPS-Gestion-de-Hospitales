package es.uniovi.ips.hospital.ui.util.render;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;

public class StaffFormat extends Format {

	private static final long serialVersionUID = -4578272668214434800L;
	
	@Autowired
	DoctorService doctorService;
	@Autowired
	NurseService nurseService;
	
	@Override
	public StringBuffer format(Object arg0, StringBuffer arg1, FieldPosition arg2) {
		return new StringBuffer().append(((Staff) arg0).guiToString());
	}

	@Override
	public Object parseObject(String arg0, ParsePosition arg1) {
		return (doctorService.findById(arg0.split(" ")[0]).isPresent()) 
				? doctorService.findById(arg0.split(" ")[0]).get() 
				: nurseService.findById(arg0.split(" ")[0]).get();	
	}

}
