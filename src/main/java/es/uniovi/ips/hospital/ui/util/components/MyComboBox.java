package es.uniovi.ips.hospital.ui.util.components;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyComboBox<E> extends JComboBox<E> {

	private static final long serialVersionUID = -8070713189821473204L;
	
	public MyComboBox() {
		this.getEditor().getEditorComponent().setBackground(PaletteFactory.getBaseDark());
		this.getEditor().getEditorComponent().setForeground(PaletteFactory.getLighter());
		this.setUI(ColorArrowUI.createUI(this));
	}
	
	static class ColorArrowUI extends BasicComboBoxUI {

	    public static ComboBoxUI createUI(JComponent c) {
	        return new ColorArrowUI();
	    }

	    @Override protected JButton createArrowButton() {
	    	final JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, 
	    			PaletteFactory.getHighlighter(), PaletteFactory.getBaseDark(), 
	    			PaletteFactory.getBaseDark(), PaletteFactory.getLighter());
	        button.addChangeListener(new ChangeListener(){

	            @Override
	            public void stateChanged(ChangeEvent arg0) {
	                if(button.isEnabled())
	                	PaletteFactory.getHighlighter();
	                else {
	                    button.setBackground(Color.LIGHT_GRAY);
	                    button.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	                }
	            }
	        });
	        return button;
	    }
	}

}
