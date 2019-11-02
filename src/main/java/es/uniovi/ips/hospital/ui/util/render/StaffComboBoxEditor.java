package es.uniovi.ips.hospital.ui.util.render;

import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalComboBoxEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.ui.util.PrintableOnGui;

@Component
public class StaffComboBoxEditor extends MetalComboBoxEditor {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private NurseService nurseService;
	
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
		String id = ((JTextField) this.getEditorComponent()).getText().split(" ")[0];
		return (doctorService.findById(id).isPresent()) ? doctorService.findById(id).get() : nurseService.findById(id).get();	
	}

}
