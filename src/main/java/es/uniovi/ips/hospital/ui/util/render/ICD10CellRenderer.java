package es.uniovi.ips.hospital.ui.util.render;

import es.uniovi.ips.hospital.domain.ICD10;

import javax.swing.*;
import java.awt.*;

public class ICD10CellRenderer extends JLabel implements ListCellRenderer<ICD10> {
    @Override
    public Component getListCellRendererComponent(JList<? extends ICD10> jList,
                                                  ICD10 code,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        this.setOpaque(true);

        this.setBackground(isSelected ? Color.blue : Color.white);
        this.setForeground(isSelected ? Color.white : Color.black);

        this.setText(code.getFullCode());
        return this;
    }
}
