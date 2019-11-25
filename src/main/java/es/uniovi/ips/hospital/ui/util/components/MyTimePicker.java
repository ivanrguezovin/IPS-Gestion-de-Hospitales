package es.uniovi.ips.hospital.ui.util.components;

import com.github.lgooddatepicker.components.TimePicker;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyTimePicker extends TimePicker {

	private static final long serialVersionUID = -6630434973407748767L;
	
	public MyTimePicker() {
        super(new MyTimePickerSettings());
        this.getComponentTimeTextField().setBackground(PaletteFactory.getBaseDark());
        this.getComponentTimeTextField().setForeground(PaletteFactory.getLighter());
        this.getComponentSpinnerPanel().setBackground(PaletteFactory.getHighlighter());
        this.getComponentToggleTimeMenuButton().setBackground(PaletteFactory.getHighlighter());
	}

}
