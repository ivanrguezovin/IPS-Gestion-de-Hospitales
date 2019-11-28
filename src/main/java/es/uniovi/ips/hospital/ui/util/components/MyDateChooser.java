package es.uniovi.ips.hospital.ui.util.components;

import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;

import com.toedter.calendar.JDateChooser;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

/**
 * Clase que extiene el JDateChooser de toedter
 *
 */
public class MyDateChooser extends JDateChooser {

	// Serial
	private static final long serialVersionUID = -1984787623138569090L;

	/**
	 * Crea el DateChooser modificando fecha mínima, color y comportamiento
	 */
	public MyDateChooser() {
		((JComponent) this.dateEditor).setBackground(PaletteFactory.getBaseDark());
		((JComponent) this.dateEditor).setForeground(PaletteFactory.getLighter());
		this.jcalendar.setBackground(PaletteFactory.getBaseDark());
		this.jcalendar.setForeground(PaletteFactory.getLighter());
		setForeground(PaletteFactory.getLighter());
        ((JSpinner) this.jcalendar.getYearChooser().getSpinner()).getEditor().setForeground(PaletteFactory.getLighter());
		this.popup = new JPopupMenu();
		this.popup.setLightWeightPopupEnabled(true);
		this.popup.add(this.jcalendar);
		setMinSelectableDate(new Date());
	}
	
}