package es.uniovi.ips.hospital.ui.util.render;

import es.uniovi.ips.hospital.domain.Diagnostic;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DiagnosticCellRenderer extends JLabel implements ListCellRenderer<Diagnostic> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -785229885701080336L;
	private String doctor = "";
	public void setDoctor(String d) {
		this.doctor=d;
	}
	
    @Override
    public Component getListCellRendererComponent(JList<? extends Diagnostic> jList,
                                                  Diagnostic diagnostic,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        this.setOpaque(true);

        this.setBackground(isSelected ? PaletteFactory.getHighlighter() : PaletteFactory.getBaseDark());
        this.setForeground(PaletteFactory.getLighter());

        if(!diagnostic.isActive()) {
        	this.setBackground(PaletteFactory.getMainDark());
            this.setForeground(!isSelected ? PaletteFactory.getBaseDark() : PaletteFactory.getHighlighter());
            this.setText("--ELIMINATED on " + new Date() + " by Dr." + doctor + "-- || Created by Dr." + diagnostic.getDoctor().getSurname() + " at " + diagnostic.getCreated().toString()
            		+ ". - " + diagnostic.getComment());
        }else {
        	this.setText("Created by Dr." + diagnostic.getDoctor().getSurname() + " at " + diagnostic.getCreated().toString()
            		+ ". - " + diagnostic.getComment());
        }
        
        
        return this;
    }
}
