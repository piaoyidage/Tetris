package ui;

import java.awt.Graphics;

/**
 * ��һ������˹����
 * @author Ben
 *
 */
public class LayerNext extends Layer
{

	public LayerNext(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
	}

}
