package es.uniovi.ips.hospital.ui.util.render;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import ca.odell.glazedlists.swing.AdvancedTableModel;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

import java.awt.*;
import java.time.LocalDateTime;

public class AppointmentTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -1968605500684561168L;

	@SuppressWarnings("unchecked")
	@Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        Appointment appointment = ((AdvancedTableModel<Appointment>) table.getModel()).getElementAt(row);
        if (!appointment.isConfirmed()) {
            setForeground(PaletteFactory.getHighlighter());
        } else if (appointment.getStartTime().isBefore(LocalDateTime.now())) {
        	setForeground(Color.GRAY);
        } else {
            setForeground(table.getForeground());
        }       
        return this;
    }  
}
