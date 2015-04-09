package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.FrameConfig;
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
	protected static final int BORDER;
	// 内边距
	protected static final int PADDING;
	// 从配置中读取来初始化
	static
	{
		FrameConfig frameConfig = GameConfig.getFrameConfig();
		BORDER = frameConfig.getBorder();
		PADDING = frameConfig.getPadding();
	}
	
	/**
	 * 进度条值槽
	 */
	protected static final Image IMG_PROGRESS_BAR = new ImageIcon("graphics/string/progressBar.png").getImage();
	protected static final int EXP_WIDTH = IMG_PROGRESS_BAR.getWidth(null);
	protected static final int EXP_HEIGHT = IMG_PROGRESS_BAR.getHeight(null);
	protected static final int EXP_X_START = PADDING;
	
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
		g.drawImage(IMG, x, y, x + BORDER, y + BORDER, 0, 0, BORDER, BORDER, null);
		// 2.上
		g.drawImage(IMG, x + BORDER, y, x - BORDER + width, y + BORDER, BORDER, 0, IMG_WIDTH
				- BORDER, BORDER, null);
		// 3.右上
		g.drawImage(IMG, x - BORDER + width, y, x + width, y + BORDER, IMG_WIDTH - BORDER,
				0, IMG_WIDTH, BORDER, null);
		// 4.中左
		g.drawImage(IMG, x, y + BORDER, x + BORDER, y - BORDER + height, 0, BORDER,
				BORDER, IMG_HEIGHT - BORDER, null);
		// 5.中
		g.drawImage(IMG, x + BORDER, y + BORDER, x - BORDER + width, y - BORDER
				+ height, BORDER, BORDER, IMG_WIDTH - BORDER, IMG_HEIGHT - BORDER, null);
		// 6.中右
		g.drawImage(IMG, x - BORDER + width, y + BORDER, x + width, y - BORDER
				+ height, IMG_WIDTH - BORDER, BORDER, IMG_WIDTH, IMG_HEIGHT - BORDER, null);
		// 7.左下
		g.drawImage(IMG, x, y - BORDER + height, x + BORDER, y + height, 0, IMG_HEIGHT
				- BORDER, BORDER, IMG_HEIGHT, null);
		// 8.下
		g.drawImage(IMG, x + BORDER, y - BORDER + height, x - BORDER + width, y
				+ height, BORDER, IMG_HEIGHT - BORDER, IMG_WIDTH - BORDER, IMG_HEIGHT, null);
		// 9.下右
		g.drawImage(IMG, x - BORDER + width, y - BORDER + height, x + width, y
				+ height, IMG_WIDTH - BORDER, IMG_HEIGHT - BORDER, IMG_WIDTH, IMG_HEIGHT, null);
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
	
	/**
	 * 绘制值槽
	 * @param expYStart 开始y坐标
	 * @param curValue 
	 * @param maxValue
	 * @param g
	 */
	protected void drawProgressBar(int expYStart, int curValue, int maxValue, Graphics g)
	{
		// 绘制黑色矩形框
		g.setColor(Color.BLACK);
		g.fillRect(this.x + EXP_X_START, expYStart, EXP_WIDTH + 4, EXP_HEIGHT + 4);
		// 绘制白色矩形框
		g.setColor(Color.WHITE);
		g.fillRect(this.x + EXP_X_START + 1, expYStart + 1, EXP_WIDTH + 2, EXP_HEIGHT + 2);
		// 绘制黑色矩形框
		g.setColor(Color.BLACK);
		g.fillRect(this.x + EXP_X_START + 2, expYStart + 2, EXP_WIDTH, EXP_HEIGHT);
		// 绘制进度条
		g.drawImage(IMG_PROGRESS_BAR, 
					this.x + EXP_X_START + 2,
					expYStart + 2, 
					(int)(this.x + EXP_X_START + 2 + (double)curValue / maxValue * EXP_WIDTH), 
					expYStart + 2 + EXP_HEIGHT, 0, 0, 
					(int)((double)curValue / maxValue * EXP_WIDTH), EXP_HEIGHT, null);
	}
}
