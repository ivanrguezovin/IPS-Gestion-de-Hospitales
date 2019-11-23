package es.uniovi.ips.hospital.ui.util.components;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import org.springframework.util.ResourceUtils;

import es.uniovi.ips.hospital.ui.util.PaletteFactory;

public class MyBanner extends JLabel {

	private static final long serialVersionUID = 6041564729540726840L;
	
	public MyBanner() {
		this.setBorder(new MatteBorder(0, 0, 1, 0, PaletteFactory.getHighlighter()));
		ImageIcon image = new ImageIcon();
        try {
            File file = ResourceUtils.getFile("classpath:estonoesunbanner.png");
            BufferedImage img = ImageIO.read(file);
            Image i = img.getScaledInstance(600, 100, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
		this.setIcon(image);
	}

}
