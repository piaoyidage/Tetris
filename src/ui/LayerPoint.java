package ui;

import java.awt.Graphics;

/**
 * ”Œœ∑µ√∑÷
 * @author Ben
 *
 */
public class LayerPoint extends Layer
{

	public LayerPoint(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
	}

}
