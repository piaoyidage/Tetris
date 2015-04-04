package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ÓÎÏ·Êý¾Ý¿â
 * @author Ben
 *
 */
public class LayerDatabase extends Layer
{

	private static final Image IMG_DB = new ImageIcon("graphics/string/db.png").getImage();
	
	public LayerDatabase(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		g.drawImage(IMG_DB, this.x + PADDING, this.y + PADDING, null);
	}
	
}
