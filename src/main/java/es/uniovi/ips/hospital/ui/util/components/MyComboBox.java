package es.uniovi.ips.hospital.ui.util.components;

import javax.swing.JComboBox;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyComboBox<E> extends JComboBox<E> {

	private static final long serialVersionUID = -8070713189821473204L;
	
	public MyComboBox() {
		this.getEditor().getEditorComponent().setBackground(PaletteFactory.getBaseDark());
		this.getEditor().getEditorComponent().setForeground(PaletteFactory.getLighter());
	}

}
