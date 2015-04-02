package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ”Œœ∑µ»º∂
 * @author Ben
 *
 */
public class LayerLevel extends Layer
{
	private static final Image IMG_LEVEL = new ImageIcon("graphics/string/level.png").getImage();

	public LayerLevel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		g.drawImage(IMG_LEVEL, this.x + PADDING, this.y + PADDING, null);
	}

}
