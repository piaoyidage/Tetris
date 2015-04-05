package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.ConfigFactory;
import config.GameConfig;
import dto.GameDto;

/**
 * 游戏布局边框
 * @author Ben
 *
 */
public abstract class Layer
{
	
	/**
	 * 数字图片
	 */
	protected static final Image IMG_NUM = new ImageIcon("graphics/string/number.png").getImage();
	protected static final int NUM_WIDTH = IMG_NUM.getWidth(null) / 10;
	protected static final int NUM_HEIGHT = IMG_NUM.getHeight(null);
	
	
	// 图片、图片宽度、图片高度
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
	
	/**
	 * 数据源
	 */
	protected GameDto dto;
	
	public void setDto(GameDto dto)
	{
		this.dto = dto;
	}

	// 图片边框宽度
	private static final int SIZE;
	// 内边距
	protected static final int PADDING;
	// 从配置中读取来初始化
	static
	{
		GameConfig gameConfig = ConfigFactory.getGameConfig();
		SIZE = gameConfig.getWindowSize();
		PADDING = gameConfig.getPadding();
	}
	
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
	
	/**
	 * 绘制数字，并且右对齐
	 * @param x 
	 * @param y
	 * @param num 数字
	 * @param maxBit 最大位数
	 * @param g 画笔
	 */
	protected void drawNumberAlignRight(int x, int y, int num, int maxBit, Graphics g)
	{
		// 数字转成字符串
		String numStr = Integer.toString(num);
		// 打印数字右对齐
		for (int i = 0; i < maxBit; i++)
		{
			if (maxBit - i <= numStr.length())
			{
				int index = i + numStr.length() - maxBit;
				int bit = numStr.charAt(index) - '0';
				g.drawImage(IMG_NUM, this.x + x + i * NUM_WIDTH, 
						 	this.y + y, this.x + x + NUM_WIDTH + i * NUM_WIDTH, 
						 	this.y + y + NUM_HEIGHT, 
						 	bit * NUM_WIDTH, 
						 	0, 
						 	(bit+1) * NUM_WIDTH, NUM_HEIGHT, null);
			}
		}

	}
	
	/**
	 * 图片居中显示
	 * @param img
	 * @param g
	 */
	protected void drawImageAtCenter(Image img, Graphics g)
	{
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		g.drawImage(img, this.x + (this.width-imgW>>1), this.y + (this.height-imgH>>1), null);
	}
}
