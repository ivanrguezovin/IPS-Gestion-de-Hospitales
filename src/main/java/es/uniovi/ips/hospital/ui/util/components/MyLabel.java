package es.uniovi.ips.hospital.ui.util.components;

import javax.swing.JLabel;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyLabel extends JLabel {

	private static final long serialVersionUID = -4114983874121730863L;
	
	public MyLabel(String text) {
		super(text);
		this.setForeground(PaletteFactory.getLighter());
	}

}
