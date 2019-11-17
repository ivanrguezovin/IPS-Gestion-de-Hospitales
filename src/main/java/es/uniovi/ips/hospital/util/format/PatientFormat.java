package es.uniovi.ips.hospital.util.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.ui.util.PrintableOnGui;

@Component
public class PatientFormat extends Format {
	
	private static final long serialVersionUID = -4642962227150235528L;
	
	@Autowired	private PatientService patientService;

	@Override
	public StringBuffer format(Object arg0, StringBuffer arg1, FieldPosition arg2) {
		if(arg0 == null)
			return arg1;
		return arg1.append(((PrintableOnGui)arg0).guiToString());
	}

	@Override
	public Object parseObject(String arg0, ParsePosition arg1) {
		String[] info = arg0.split(" ");
		return patientService.findPatientByDni(info[info.length-1]);	
	}

}
