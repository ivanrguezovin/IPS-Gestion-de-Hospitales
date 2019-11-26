package es.uniovi.ips.hospital.ui.util.components;

import javax.swing.JCheckBox;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyCheckBox extends JCheckBox {

	private static final long serialVersionUID = -6502221868882573805L;

	public MyCheckBox(String string) {
		super(string);
		this.setBackground(PaletteFactory.getMainDark());
		this.setForeground(PaletteFactory.getLighter());
	}

}
