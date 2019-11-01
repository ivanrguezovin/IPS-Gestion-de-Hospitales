package es.uniovi.ips.hospital.ui.util.render;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import es.uniovi.ips.hospital.domain.Patient;

public class PatientCellRenderer extends JLabel implements ListCellRenderer<Patient> {

	private static final long serialVersionUID = -3962355677320930911L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Patient> list, Patient value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(value.guiToString());
		return this;
	}

}
