package ui;

import java.awt.Graphics;

/**
 * 游戏运行界面
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
