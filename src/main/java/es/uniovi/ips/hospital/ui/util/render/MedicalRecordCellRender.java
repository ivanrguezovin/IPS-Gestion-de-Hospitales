package es.uniovi.ips.hospital.ui.util.render;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MedicalRecordCellRender extends JLabel implements ListCellRenderer<MedicalRecord>{

	private static final long serialVersionUID = -6110870772917704063L;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends MedicalRecord> list, MedicalRecord value, int index,
			boolean isSelected, boolean cellHasFocus) {
		this.setOpaque(true);
		
        this.setBackground(isSelected ? PaletteFactory.getHighlighter() : PaletteFactory.getBaseDark());
        this.setForeground(PaletteFactory.getLighter());
        this.setText(value.getPrescription() + " - " + value.getDate());
		return this;
	}

}
