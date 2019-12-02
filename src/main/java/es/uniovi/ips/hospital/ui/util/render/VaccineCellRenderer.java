package es.uniovi.ips.hospital.ui.util.render;

import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

import javax.swing.*;
import java.awt.*;

public class VaccineCellRenderer extends JLabel implements ListCellRenderer<Vaccine> {
	
	private static final long serialVersionUID = 4852728371429306877L;
	
    @Override
    public Component getListCellRendererComponent(JList<? extends Vaccine> jList,
                                                  Vaccine vaccine,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        this.setOpaque(true);

        this.setBackground(isSelected ? PaletteFactory.getHighlighter() : PaletteFactory.getBaseDark());
        this.setForeground(PaletteFactory.getLighter());

        String applied = (vaccine.isApplied()) ? "APPLIED" : "NO APPLIED";
        this.setText(vaccine.getDate() + " - " + vaccine.getVaccineType() + " - " + applied);
        
        return this;
    }
}
