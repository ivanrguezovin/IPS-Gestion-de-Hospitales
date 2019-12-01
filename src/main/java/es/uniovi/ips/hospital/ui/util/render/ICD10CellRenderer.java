package es.uniovi.ips.hospital.ui.util.render;

import es.uniovi.ips.hospital.domain.ICD10;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;

import javax.swing.*;
import java.awt.*;

public class ICD10CellRenderer extends JLabel implements ListCellRenderer<ICD10> {

	private static final long serialVersionUID = 1531705867237089726L;

	@Override
    public Component getListCellRendererComponent(JList<? extends ICD10> jList,
                                                  ICD10 code,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        this.setOpaque(true);

        this.setBackground(isSelected ? PaletteFactory.getHighlighter() : PaletteFactory.getBaseDark());
        this.setForeground(PaletteFactory.getLighter());

        this.setText(code.getFullCode());
        return this;
    }
}
