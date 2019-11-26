package es.uniovi.ips.hospital.ui.util.components;

import java.awt.Font;

import javax.swing.JButton;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyButton extends JButton {

	private static final long serialVersionUID = -618744396341420636L;
	
	public MyButton(String string) {
		super(string);
        setBackground(PaletteFactory.getHighlighter());
        setFont(new Font("Tahoma", Font.BOLD, 20));
	}

}
