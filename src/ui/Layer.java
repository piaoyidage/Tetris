package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 游戏布局边框
 * @author Ben
 *
 */
public abstract class Layer
{
	// 图片、图片宽度、图片高度、图片边框宽度
	private static final int SIZE = 10;
	private static final Image IMG = new ImageIcon("graphics/window/window.png").getImage();
	private static final int IMG_WIDTH = IMG.getWidth(null);
	private	static final int IMG_HEIGHT = IMG.getHeight(null);
	// x坐标
	protected int x;
	// y坐标
	protected int y;
	// 宽度
	protected int width;
	// 高度
	protected int height;
	// 内边距
	protected static final int PADDING = 16; 
	
	protected Layer(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	// 绘制布局边框
	protected void createWindow(Graphics g)
	{
		// 1.左上
		g.drawImage(IMG, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
		// 2.上
		g.drawImage(IMG, x + SIZE, y, x - SIZE + width, y + SIZE, SIZE, 0, IMG_WIDTH
				- SIZE, SIZE, null);
		// 3.右上
		g.drawImage(IMG, x - SIZE + width, y, x + width, y + SIZE, IMG_WIDTH - SIZE,
				0, IMG_WIDTH, SIZE, null);
		// 4.中左
		g.drawImage(IMG, x, y + SIZE, x + SIZE, y - SIZE + height, 0, SIZE,
				SIZE, IMG_HEIGHT - SIZE, null);
		// 5.中
		g.drawImage(IMG, x + SIZE, y + SIZE, x - SIZE + width, y - SIZE
				+ height, SIZE, SIZE, IMG_WIDTH - SIZE, IMG_HEIGHT - SIZE, null);
		// 6.中右
		g.drawImage(IMG, x - SIZE + width, y + SIZE, x + width, y - SIZE
				+ height, IMG_WIDTH - SIZE, SIZE, IMG_WIDTH, IMG_HEIGHT - SIZE, null);
		// 7.左下
		g.drawImage(IMG, x, y - SIZE + height, x + SIZE, y + height, 0, IMG_HEIGHT
				- SIZE, SIZE, IMG_HEIGHT, null);
		// 8.下
		g.drawImage(IMG, x + SIZE, y - SIZE + height, x - SIZE + width, y
				+ height, SIZE, IMG_HEIGHT - SIZE, IMG_WIDTH - SIZE, IMG_HEIGHT, null);
		// 9.下右
		g.drawImage(IMG, x - SIZE + width, y - SIZE + height, x + width, y
				+ height, IMG_WIDTH - SIZE, IMG_HEIGHT - SIZE, IMG_WIDTH, IMG_HEIGHT, null);
	}
	
	// 让子类重写
	public abstract void paint(Graphics g);
}
