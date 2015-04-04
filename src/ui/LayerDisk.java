package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 游戏本地记录
 * @author Ben
 *
 */
public class LayerDisk extends Layer
{

	private static final Image IMG_DISK = new ImageIcon("graphics/string/disk.png").getImage();
	
	public LayerDisk(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		g.drawImage(IMG_DISK, this.x + PADDING, this.y + PADDING, null);
	}

}
