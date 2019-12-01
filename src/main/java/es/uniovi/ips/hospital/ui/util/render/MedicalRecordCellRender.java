package es.uniovi.ips.hospital.ui.util.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import es.uniovi.ips.hospital.domain.MedicalRecord;

public class MedicalRecordCellRender extends JLabel implements ListCellRenderer<MedicalRecord>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110870772917704063L;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends MedicalRecord> list, MedicalRecord value, int index,
			boolean isSelected, boolean cellHasFocus) {
		this.setOpaque(true);

        this.setBackground(isSelected ? Color.blue : Color.white);
        this.setForeground(isSelected ? Color.white : Color.black);
        this.setText(value.getPrescription() + " - " + value.getDate());
		return this;
	}

}
