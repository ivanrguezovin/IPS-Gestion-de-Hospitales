package es.uniovi.ips.hospital.ui.util.components;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MySouthPanel extends JPanel {
	
	private static final long serialVersionUID = 8965439521795706261L;

	public MySouthPanel() {
		this.setBorder(new MatteBorder(1, 0, 0, 0, PaletteFactory.getHighlighter()));
		this.setBackground(PaletteFactory.getMainDark());
		FlowLayout flowLayout = (FlowLayout) this.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
	}

}
