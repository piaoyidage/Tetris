package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerButton extends Layer
{
//	private static final Image IMG_START = new ImageIcon("graphics/picture/start.png").getImage();
//	private static final Image IMG_EXIT = new ImageIcon("graphics/picture/exit.png").getImage();

	public LayerButton(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		
		// TODO ≤‚ ‘
//		g.drawImage(IMG_START, this.x + PADDING, this.y + PADDING, null);
//		g.drawImage(IMG_EXIT, this.x + PADDING * 3 + IMG_START.getWidth(null), this.y + PADDING, null);
	}

}
