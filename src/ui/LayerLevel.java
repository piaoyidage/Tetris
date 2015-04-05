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
	private static final int IMG_LEVEL_WIDTH = IMG_LEVEL.getWidth(null);
	

	public LayerLevel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		// 窗口中心绘制“等级”
		int centerX = this.width - IMG_LEVEL_WIDTH >> 1;
		g.drawImage(IMG_LEVEL, this.x + centerX, this.y + PADDING, null);
		// 绘制等级数字
		drawNumberAlignRight(centerX, 64, this.dto.getCurLevel(), 2, g);
	}

	
}
