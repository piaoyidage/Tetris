package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 下一个俄罗斯方块组
 * @author Ben
 *
 */
public class LayerNext extends Layer
{
	// 7种不同的方块组
	private static Image[] NEXT_ACT;
	static
	{
		NEXT_ACT = new Image[7];
		for (int i = 0; i < NEXT_ACT.length; i++)
		{
			NEXT_ACT[i] = new ImageIcon("graphics/game/" + i + ".png").getImage();
		}
	}

	public LayerNext(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		//g.drawImage(NEXT_ACT[this.dto.getNext()], this.x + 32, this.y + 32, null);
		this.drawImageAtCenter(NEXT_ACT[this.dto.getNext()], g);
	}

	
}
