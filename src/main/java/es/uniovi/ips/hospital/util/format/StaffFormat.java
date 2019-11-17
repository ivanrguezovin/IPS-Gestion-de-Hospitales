package es.uniovi.ips.hospital.util.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.ui.util.PrintableOnGui;

@Component
public class StaffFormat extends Format {

	private static final long serialVersionUID = -442500120666111657L;
	
	@Autowired	private DoctorService doctorService;
	@Autowired	private NurseService nurseService;

	@Override
	public StringBuffer format(Object arg0, StringBuffer arg1, FieldPosition arg2) {
		if(arg0 == null)
			return arg1;
		return arg1.append(((PrintableOnGui)arg0).guiToString());
	}

	@Override
	public Object parseObject(String arg0, ParsePosition arg1) {
		String id = arg0.split(" ")[0];
		if (id.equals(""))							return null;
		if (doctorService.findById(id).isPresent()) return doctorService.findById(id).get();
		if (nurseService.findById(id).isPresent())	return nurseService.findById(id).get();	
		return null;	
	}

}
