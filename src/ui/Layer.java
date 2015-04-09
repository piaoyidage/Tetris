package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

/**
 * ��Ϸ���ֱ߿�
 * @author Ben
 *
 */
public abstract class Layer
{
	
	/**
	 * ����ͼƬ
	 */
	protected static final Image IMG_NUM = new ImageIcon("graphics/string/number.png").getImage();
	protected static final int NUM_WIDTH = IMG_NUM.getWidth(null) / 10;
	protected static final int NUM_HEIGHT = IMG_NUM.getHeight(null);
	
	
	// ͼƬ��ͼƬ��ȡ�ͼƬ�߶�
	private static final Image IMG = new ImageIcon("graphics/window/window.png").getImage();
	private static final int IMG_WIDTH = IMG.getWidth(null);
	private	static final int IMG_HEIGHT = IMG.getHeight(null);
	// x����
	protected int x;
	// y����
	protected int y;
	// ���
	protected int width;
	// �߶�
	protected int height;
	
	/**
	 * ����Դ
	 */
	protected GameDto dto;
	
	public void setDto(GameDto dto)
	{
		this.dto = dto;
	}

	// ͼƬ�߿���
	protected static final int BORDER;
	// �ڱ߾�
	protected static final int PADDING;
	// �������ж�ȡ����ʼ��
	static
	{
		FrameConfig frameConfig = GameConfig.getFrameConfig();
		BORDER = frameConfig.getBorder();
		PADDING = frameConfig.getPadding();
	}
	
	/**
	 * ������ֵ��
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
	
	// ���Ʋ��ֱ߿�
	protected void createWindow(Graphics g)
	{
		// 1.����
		g.drawImage(IMG, x, y, x + BORDER, y + BORDER, 0, 0, BORDER, BORDER, null);
		// 2.��
		g.drawImage(IMG, x + BORDER, y, x - BORDER + width, y + BORDER, BORDER, 0, IMG_WIDTH
				- BORDER, BORDER, null);
		// 3.����
		g.drawImage(IMG, x - BORDER + width, y, x + width, y + BORDER, IMG_WIDTH - BORDER,
				0, IMG_WIDTH, BORDER, null);
		// 4.����
		g.drawImage(IMG, x, y + BORDER, x + BORDER, y - BORDER + height, 0, BORDER,
				BORDER, IMG_HEIGHT - BORDER, null);
		// 5.��
		g.drawImage(IMG, x + BORDER, y + BORDER, x - BORDER + width, y - BORDER
				+ height, BORDER, BORDER, IMG_WIDTH - BORDER, IMG_HEIGHT - BORDER, null);
		// 6.����
		g.drawImage(IMG, x - BORDER + width, y + BORDER, x + width, y - BORDER
				+ height, IMG_WIDTH - BORDER, BORDER, IMG_WIDTH, IMG_HEIGHT - BORDER, null);
		// 7.����
		g.drawImage(IMG, x, y - BORDER + height, x + BORDER, y + height, 0, IMG_HEIGHT
				- BORDER, BORDER, IMG_HEIGHT, null);
		// 8.��
		g.drawImage(IMG, x + BORDER, y - BORDER + height, x - BORDER + width, y
				+ height, BORDER, IMG_HEIGHT - BORDER, IMG_WIDTH - BORDER, IMG_HEIGHT, null);
		// 9.����
		g.drawImage(IMG, x - BORDER + width, y - BORDER + height, x + width, y
				+ height, IMG_WIDTH - BORDER, IMG_HEIGHT - BORDER, IMG_WIDTH, IMG_HEIGHT, null);
	}
	
	// ��������д
	public abstract void paint(Graphics g);
	
	/**
	 * �������֣������Ҷ���
	 * @param x 
	 * @param y
	 * @param num ����
	 * @param maxBit ���λ��
	 * @param g ����
	 */
	protected void drawNumberAlignRight(int x, int y, int num, int maxBit, Graphics g)
	{
		// ����ת���ַ���
		String numStr = Integer.toString(num);
		// ��ӡ�����Ҷ���
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
	 * ͼƬ������ʾ
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
	 * ����ֵ��
	 * @param expYStart ��ʼy����
	 * @param curValue 
	 * @param maxValue
	 * @param g
	 */
	protected void drawProgressBar(int expYStart, int curValue, int maxValue, Graphics g)
	{
		// ���ƺ�ɫ���ο�
		g.setColor(Color.BLACK);
		g.fillRect(this.x + EXP_X_START, expYStart, EXP_WIDTH + 4, EXP_HEIGHT + 4);
		// ���ư�ɫ���ο�
		g.setColor(Color.WHITE);
		g.fillRect(this.x + EXP_X_START + 1, expYStart + 1, EXP_WIDTH + 2, EXP_HEIGHT + 2);
		// ���ƺ�ɫ���ο�
		g.setColor(Color.BLACK);
		g.fillRect(this.x + EXP_X_START + 2, expYStart + 2, EXP_WIDTH, EXP_HEIGHT);
		// ���ƽ�����
		g.drawImage(IMG_PROGRESS_BAR, 
					this.x + EXP_X_START + 2,
					expYStart + 2, 
					(int)(this.x + EXP_X_START + 2 + (double)curValue / maxValue * EXP_WIDTH), 
					expYStart + 2 + EXP_HEIGHT, 0, 0, 
					(int)((double)curValue / maxValue * EXP_WIDTH), EXP_HEIGHT, null);
	}
}
