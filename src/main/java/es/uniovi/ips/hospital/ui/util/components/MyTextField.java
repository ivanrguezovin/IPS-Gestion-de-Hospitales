package es.uniovi.ips.hospital.ui.util.components;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyTextField extends JTextField {

	private static final long serialVersionUID = 5399498168225600522L;

	public MyTextField() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(PaletteFactory.getBaseDark());
		this.setForeground(PaletteFactory.getLighter());
		this.setCaretColor(PaletteFactory.getLighter());
	}

}
