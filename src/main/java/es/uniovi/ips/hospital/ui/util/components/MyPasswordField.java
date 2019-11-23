package es.uniovi.ips.hospital.ui.util.components;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyPasswordField extends JPasswordField {

	private static final long serialVersionUID = -7755051943543519520L;
	
	public MyPasswordField() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(PaletteFactory.getBaseDark());
		this.setForeground(PaletteFactory.getLighter());
		this.setCaretColor(PaletteFactory.getLighter());
	}

}
