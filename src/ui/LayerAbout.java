package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ”Œœ∑∞Ê»®
 * @author Ben
 *
 */
public class LayerAbout extends Layer
{

	private static final Image IMG_ABOUT = new ImageIcon("graphics/string/about.png").getImage();
	
	public LayerAbout(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		
		this.drawImageAtCenter(IMG_ABOUT, g);
		//g.drawImage(IMG_ABOUT, this.x + (PADDING << 1), this.y + PADDING, null);
	}

}
