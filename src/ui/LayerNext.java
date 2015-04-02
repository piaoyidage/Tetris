package ui;

import java.awt.Graphics;

/**
 * 下一个俄罗斯方块
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
