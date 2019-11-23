package es.uniovi.ips.hospital.ui.util.components;

import javax.swing.JButton;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyButton extends JButton {

	private static final long serialVersionUID = -618744396341420636L;

	public MyButton(String string) {
		super(string);
		this.setBackground(PaletteFactory.getBaseDark());
		this.setForeground(PaletteFactory.getLighter());
	}

}
