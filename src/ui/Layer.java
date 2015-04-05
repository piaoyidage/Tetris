package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.ConfigFactory;
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
	private static final int SIZE;
	// �ڱ߾�
	protected static final int PADDING;
	// �������ж�ȡ����ʼ��
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
	
	// ���Ʋ��ֱ߿�
	protected void createWindow(Graphics g)
	{
		// 1.����
		g.drawImage(IMG, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
		// 2.��
		g.drawImage(IMG, x + SIZE, y, x - SIZE + width, y + SIZE, SIZE, 0, IMG_WIDTH
				- SIZE, SIZE, null);
		// 3.����
		g.drawImage(IMG, x - SIZE + width, y, x + width, y + SIZE, IMG_WIDTH - SIZE,
				0, IMG_WIDTH, SIZE, null);
		// 4.����
		g.drawImage(IMG, x, y + SIZE, x + SIZE, y - SIZE + height, 0, SIZE,
				SIZE, IMG_HEIGHT - SIZE, null);
		// 5.��
		g.drawImage(IMG, x + SIZE, y + SIZE, x - SIZE + width, y - SIZE
				+ height, SIZE, SIZE, IMG_WIDTH - SIZE, IMG_HEIGHT - SIZE, null);
		// 6.����
		g.drawImage(IMG, x - SIZE + width, y + SIZE, x + width, y - SIZE
				+ height, IMG_WIDTH - SIZE, SIZE, IMG_WIDTH, IMG_HEIGHT - SIZE, null);
		// 7.����
		g.drawImage(IMG, x, y - SIZE + height, x + SIZE, y + height, 0, IMG_HEIGHT
				- SIZE, SIZE, IMG_HEIGHT, null);
		// 8.��
		g.drawImage(IMG, x + SIZE, y - SIZE + height, x - SIZE + width, y
				+ height, SIZE, IMG_HEIGHT - SIZE, IMG_WIDTH - SIZE, IMG_HEIGHT, null);
		// 9.����
		g.drawImage(IMG, x - SIZE + width, y - SIZE + height, x + width, y
				+ height, IMG_WIDTH - SIZE, IMG_HEIGHT - SIZE, IMG_WIDTH, IMG_HEIGHT, null);
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
}
