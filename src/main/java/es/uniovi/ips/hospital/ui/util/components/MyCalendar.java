package es.uniovi.ips.hospital.ui.util.components;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

import com.toedter.calendar.JCalendar;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyCalendar extends JCalendar {

	private static final long serialVersionUID = 8838849962794698596L;

	@SuppressWarnings("rawtypes")
	public MyCalendar() {
        this.setMinSelectableDate(new Date());
        this.setForeground(PaletteFactory.getLighter());
        ((JComboBox) this.getMonthChooser().getComboBox()).setUI(new MyComboBox().getUI());
        ((JSpinner) this.getYearChooser().getSpinner()).getEditor().setForeground(PaletteFactory.getLighter());
	}

}