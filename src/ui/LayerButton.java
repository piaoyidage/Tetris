package ui;

import java.awt.Graphics;

public class LayerButton extends Layer
{

	public LayerButton(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
	}

}
