package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 游戏等级
 * @author Ben
 *
 */
public class LayerLevel extends Layer
{
	/**
	 * 等级图片
	 */
	private static final Image IMG_LEVEL = new ImageIcon("graphics/string/level.png").getImage();
	
	/**
	 * 数字图片
	 */
	private static final Image IMG_LEVEL_NUM = new ImageIcon("graphics/string/number.png").getImage();
	private static final int NUM_WIDTH = 60;
	private static final int NUM_HEIGHT = 80;

	public LayerLevel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		g.drawImage(IMG_LEVEL, this.x + PADDING, this.y + PADDING, null);
		
		drawLevelNumber(IMG_LEVEL_NUM, 64, 32, 2, g);
	}

	/**
	 * 绘制数字
	 * @param img
	 * @param x
	 * @param y
	 * @param g
	 */
	public void drawLevelNumber(Image img, int x, int y, int num, Graphics g)
	{
		g.drawImage(img, this.x + x, 
						 this.y + y, this.x + x + NUM_WIDTH, 
						 this.y + y + NUM_HEIGHT, num * NUM_WIDTH, 
						 0, 
						 (num+1) * NUM_WIDTH, NUM_HEIGHT, null);
	}
}
