package es.uniovi.ips.hospital.ui.util.render;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class StaffCellRenderer extends JLabel implements ListCellRenderer<Staff> {

	private static final long serialVersionUID = -3962355677320930911L;
	private List<Doctor> availableDoctors;
	
	public StaffCellRenderer() {
		this.availableDoctors = null;
	}
	
	public StaffCellRenderer(List<Doctor> availableDoctors) {
		this.availableDoctors = availableDoctors;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Staff> list, Staff value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(value.guiToString());
		setBackground(PaletteFactory.getHighlighter());
		setOpaque(isSelected);
		if (availableDoctors != null) {
			setForeground(Color.GRAY);
			for (Staff doc : availableDoctors)
				if (value.equals(doc)) {
					setForeground(PaletteFactory.getLighter());
					return this;
				}
		}
		return this;
	}

}
