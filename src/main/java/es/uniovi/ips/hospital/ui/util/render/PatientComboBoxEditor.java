package es.uniovi.ips.hospital.ui.util.render;

import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalComboBoxEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.ui.util.PrintableOnGui;

@Component
public class PatientComboBoxEditor extends MetalComboBoxEditor {

	@Autowired
	private PatientService patientService;
	
	@Override
	public void setItem(Object o) {
		if ( o != null ) {
            ((JTextField) this.getEditorComponent()).setText(((PrintableOnGui) o).guiToString() );
        }
        else {
        	((JTextField) this.getEditorComponent()).setText( "" );
        }
	}
	
	@Override
	public Object getItem() {
		String[] info = ((JTextField) this.getEditorComponent()).getText().split(" ");
		return patientService.findPatientByDni(info[info.length-1]);	
	}
}
