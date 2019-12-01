package es.uniovi.ips.hospital.ui.util.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.JComboBox;
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
	@SuppressWarnings("rawtypes")
	public MyDateChooser() {
		this.setDateFormatString("dd/MM/yy");
		((JComponent) this.dateEditor).setBackground(PaletteFactory.getBaseDark());
		((JComponent) this.dateEditor).setForeground(PaletteFactory.getLighter());
		this.dateEditor.getUiComponent().setBackground(PaletteFactory.getBaseDark());
		this.dateEditor.getUiComponent().setForeground(PaletteFactory.getLighter());
		jcalendar.setForeground(PaletteFactory.getLighter());
		((JComboBox) jcalendar.getMonthChooser().getComboBox()).setUI(new MyComboBox().getUI());
		((JSpinner) jcalendar.getYearChooser().getSpinner()).getEditor().setForeground(PaletteFactory.getLighter());
		this.popup = new JPopupMenu();
		this.popup.setLightWeightPopupEnabled(true);
		this.popup.add(this.jcalendar);
		setMinSelectableDate(new Date());
		addPropertyChangeListener(e -> correctForegrounds());
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				correctForegrounds();
			}

			public void focusLost(FocusEvent e) {
				correctForegrounds();
			}
		});
	}

	private void correctForegrounds() {
		((JComponent) this.dateEditor).setForeground(PaletteFactory.getLighter());
		((JSpinner) jcalendar.getYearChooser().getSpinner()).getEditor().setForeground(PaletteFactory.getLighter());
	}

}