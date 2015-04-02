package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ��Ϸ���ֱ߿�
 * @author Ben
 *
 */
public abstract class Layer
{
	// ͼƬ��ͼƬ��ȡ�ͼƬ�߶ȡ�ͼƬ�߿���
	private static final int SIZE = 10;
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
	// �ڱ߾�
	protected static final int PADDING = 16; 
	
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
}
