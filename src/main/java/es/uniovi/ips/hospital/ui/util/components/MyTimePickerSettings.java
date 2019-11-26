package es.uniovi.ips.hospital.ui.util.components;

import com.github.lgooddatepicker.components.TimePickerSettings;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyTimePickerSettings extends TimePickerSettings {
	
	public MyTimePickerSettings() {
		super();
        this.setDisplaySpinnerButtons(true);
        this.setColor(TimeArea.TimePickerTextValidTime, PaletteFactory.getLighter());
        this.setColor(TimeArea.TextFieldBackgroundValidTime, PaletteFactory.getBaseDark());
	}

}
