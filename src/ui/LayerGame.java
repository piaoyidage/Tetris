package ui;

import java.awt.Graphics;

/**
 * ��Ϸ���н���
 * @author Ben
 *
 */
public class LayerGame extends Layer
{

	public LayerGame(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
	}

}
