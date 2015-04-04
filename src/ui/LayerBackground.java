package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer
{
	// TODO ¡Ÿ ±±≥æ∞
	private static final Image IMG_BG_TEMP = new ImageIcon("graphics/background/bg.png").getImage();

	public LayerBackground(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(IMG_BG_TEMP, 0, 0, null);
	}

}
