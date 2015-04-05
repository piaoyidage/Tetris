package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ��Ϸ�÷�
 * @author Ben
 *
 */
public class LayerPoint extends Layer
{
	/**
	 * ����ͼƬ
	 */
	private static final Image IMG_POINT = new ImageIcon("graphics/string/point.png").getImage();
	private static final int IMG_POINT_WIDTH = IMG_POINT.getWidth(null);
	private static final int IMG_POINT_HEIGHT = IMG_POINT.getHeight(null);
	private static final int MAX_BIT = 5;
	/**
	 * ���Ʒ�����ʼx���꣬�����this.x
	 */
	private final int pointX;
	private final int pointY;
	
	/**
	 * ����ͼƬ
	 */
	private static final Image IMG_RMLINE = new ImageIcon("graphics/string/rmline.png").getImage();
	private static final int IMG_RMLINE_WIDTH = IMG_RMLINE.getWidth(null);
	private static final int IMG_RMLINE_HEIGHT = IMG_RMLINE.getHeight(null);
	
	private final int rmLineX;
	private final int rmLineY;
	
	/**
	 * ����ֵ empirical value
	 */
	private final int expX;
	private final int expY;
	private final int expWidth;
	private final int expHeight;
	
	/**
	 * ����ΪLEVEL_UP��������
	 */
	private static final int LEVEL_UP = 20;
	
	private static final Image IMG_PROGRESS_BAR = new ImageIcon("graphics/string/progressBar.png").getImage();
	
//	private static final Font DEFAULT_FONT = new Font("����", Font.BOLD, 20);
	
	/**
	 * ��һ��ͼƬ
	 */
	private static final Image IMG_NEXT = new ImageIcon("graphics/string/next.png").getImage();
	
	public LayerPoint(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		pointY = PADDING;
		pointX = this.width - IMG_POINT_WIDTH - NUM_WIDTH * MAX_BIT + (PADDING << 1);
		
		rmLineY = (PADDING << 1) + IMG_POINT_HEIGHT;
		rmLineX = this.width - IMG_RMLINE_WIDTH - NUM_WIDTH * MAX_BIT + (PADDING << 1);
		
		expX = PADDING;
		expY = rmLineY + PADDING + IMG_RMLINE_HEIGHT;
		expWidth = this.width - (PADDING << 1);
		expHeight = PADDING << 1;
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		
		g.drawImage(IMG_POINT, this.x + PADDING, this.y + PADDING, null);
		// ��ʾ����
		this.drawNumberAlignRight(pointX , pointY, this.dto.getCurPoint(), MAX_BIT, g);
		
		g.drawImage(IMG_RMLINE, this.x + PADDING, this.y + (PADDING << 1) + IMG_POINT_HEIGHT, null);
		// ��ʾ������
		this.drawNumberAlignRight(rmLineX , rmLineY, this.dto.getCurRemoveRowLines(), MAX_BIT, g);
	
		// ��ʾֵ��
		int rmLines = this.dto.getCurRemoveRowLines();
		int progressBarWidth = (int)((double)(rmLines % LEVEL_UP) / (double)LEVEL_UP * (expWidth-4));
		// ����������ʱ�Ľ�������ʾ
		if (progressBarWidth == 0)
		{
			progressBarWidth = rmLines > 0  ? expWidth - 4 : 0;
		}
		this.drawProgressBar(this.x + expX + 2, this.y + expY + 2, progressBarWidth, expHeight - 4, g);
		
	}

	/**
	 * ���ƽ�����ֵ��
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param g
	 */
	private void drawProgressBar(int x, int y, int width, int height, Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(this.x + expX, this.y + expY, expWidth, expHeight);
		g.setColor(Color.WHITE);
		g.fillRect(this.x + expX + 1, this.y + expY + 1, expWidth - 2, expHeight - 2);
		g.setColor(Color.BLACK);
		g.fillRect(this.x + expX + 2, this.y + expY + 2, expWidth - 4, expHeight - 4);
//		g.setColor(Color.BLUE);
//		// ������
//		int progressBar = (int)((double)(this.dto.getCurRemoveRowLines() % LEVEL_UP) / (double)LEVEL_UP * (expWidth-4));
//		g.fillRect(this.x + expX + 2, this.y + expY + 2, progressBar, expHeight - 4);
		g.drawImage(IMG_PROGRESS_BAR, x, y, x + width, y + height, 0, 0, width, height, null);
//		g.setFont(DEFAULT_FONT);
//		g.setColor(Color.WHITE);
//		g.drawString("��һ��", x , y + 20);
		g.drawImage(IMG_NEXT, x + 2, y + 2, null);
	}
}
