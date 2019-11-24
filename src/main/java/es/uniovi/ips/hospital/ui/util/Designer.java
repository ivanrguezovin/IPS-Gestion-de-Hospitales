package es.uniovi.ips.hospital.ui.util;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Designer {

	public static void setDesign() {
		// Paneles
		UIManager.put("Panel.background", PaletteFactory.getMainDark());
		
		// Botones
        UIManager.put("Button.background", PaletteFactory.getBaseDark());
        UIManager.put("Button.foreground", PaletteFactory.getLighter());
        UIManager.put("Button.highlight", PaletteFactory.getHighlighter());
        UIManager.put("Button.select", PaletteFactory.getHighlighter());
        UIManager.put("Button.focus", PaletteFactory.getHighlighter());
        
        // CheckBoxes
        UIManager.put("CheckBox.background", PaletteFactory.getMainDark());
        UIManager.put("CheckBox.foreground", PaletteFactory.getLighter());
        UIManager.put("CheckBox.highlight", PaletteFactory.getHighlighter());
        UIManager.put("CheckBox.select", PaletteFactory.getHighlighter());
        UIManager.put("CheckBox.focus", PaletteFactory.getHighlighter());
		
		// ComboBoxes
        UIManager.put("ComboBox.background", PaletteFactory.getAltDark());
        UIManager.put("ComboBox.foreground", PaletteFactory.getLighter());
        UIManager.put("ComboBox.highlight", PaletteFactory.getHighlighter());
        UIManager.put("ComboBox.select", PaletteFactory.getHighlighter());
        UIManager.put("ComboBox.focus", PaletteFactory.getHighlighter());
        UIManager.put("ComboBox.selectionBackground", PaletteFactory.getHighlighter());
        UIManager.put("ComboBox.selectionForeground", PaletteFactory.getLighter());
        
        // Labels
        UIManager.put("Label.foreground", PaletteFactory.getLighter());
        
        
        
        
        
        
        
        
        

        // PasswordFields
        UIManager.put("PasswordField.border", new EmptyBorder(5, 5, 5, 5));
        UIManager.put("PasswordField.background", PaletteFactory.getBaseDark());
        UIManager.put("PasswordField.foreground", PaletteFactory.getLighter());
        UIManager.put("PasswordField.caretForeground", PaletteFactory.getLighter());
        
        // TextFields
        UIManager.put("TextField.border", new EmptyBorder(5, 5, 5, 5));
        UIManager.put("TextField.background", PaletteFactory.getBaseDark());
        UIManager.put("TextField.foreground", PaletteFactory.getLighter());
        UIManager.put("TextField.caretForeground", PaletteFactory.getLighter());
		
	}

}