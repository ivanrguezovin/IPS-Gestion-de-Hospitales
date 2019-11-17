package es.uniovi.ips.hospital.ui.util.render;

import es.uniovi.ips.hospital.domain.Diagnostic;

import javax.swing.*;
import java.awt.*;

public class DiagnosticCellRenderer extends JLabel implements ListCellRenderer<Diagnostic> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Diagnostic> jList,
                                                  Diagnostic diagnostic,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        this.setOpaque(true);

        this.setBackground(isSelected ? Color.blue : Color.white);
        this.setForeground(isSelected ? Color.white : Color.black);

        this.setText("Created by Dr." + diagnostic.getDoctor().getSurname() + " at " + diagnostic.getCreated().toString());
        return this;
    }
}
