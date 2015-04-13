package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import entity.GameAct;

/**
 * ��Ϸ���н���
 * @author Ben
 *
 */
public class LayerGame extends Layer
{
	/**
	 * ����ͼƬ
	 */
	private static final Image ACT = new ImageIcon("graphics/game/rect.png").getImage();
	
	/**
	 * ��ӰͼƬ
	 */
	private static final Image IMG_SHADOW = new ImageIcon("graphics/game/shadow.png").getImage();
	
	private static final int LEFT_SIDE = 9;
	private static final int RIGHT_SIDE = 0;
	
	// ���� 5
	private static final int LEFT_SHIFT = 5;

	public LayerGame(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		GameAct gameAct = this.dto.getGameAct();
		if (gameAct != null)
		{
			Point[] actPoints = gameAct.getActPoints();
			// ������Ӱ
			this.drawShadow(IMG_SHADOW, actPoints, g);
			// ���Ʒ���
			this.drawAct(actPoints, g);
		}
		// ���Ƶ�ͼ
		this.drawMap(g);
	}
	
	private void drawMap(Graphics g)
	{
		// TODO ������� index == 8
		boolean[][] gameMap = this.dto.getGameMap();
		// ���ݲ�ͬ�ȼ�����ѻ�����ɫ��ͬ
		int level = this.dto.getCurLevel();
		int index = level == 0 ? 0 : (level - 1) % 7 + 1;
		if (!this.dto.isStart())
		{
			index = 8;
		}
		for (int y = 0; y < gameMap.length; y++)
		{
			for (int x = 0; x < gameMap[y].length; x++)
			{
				if (gameMap[y][x])
				{
					this.drawActByPoint(x, y, index, g);
					// g.drawImage(ACT, this.x + x*ACT_SIZE + 10,
					// this.y + y*ACT_SIZE + 10,
					// this.x + x*ACT_SIZE + ACT_SIZE + 10,
					// this.y + y*ACT_SIZE + ACT_SIZE + 10,
					// 0, 0, 32, 32, null);
				}
			}
		}

	}

	private void drawAct(Point[] actPoints, Graphics g)
	{
		// ���Ʒ���
		// ���ݲ�ͬ��actType��ӡ��ͬ��ɫ�ķ���
		int actType = this.dto.getGameAct().getActType();
		// TODO
		for (int i = 0; i < actPoints.length; i++)
		{
			this.drawActByPoint(actPoints[i].x, actPoints[i].y, actType + 1, g);
			// g.drawImage(ACT, this.x + (actPoints[i].x << LEFT_SHIFT) + 10,
			// this.y + (actPoints[i].y << LEFT_SHIFT) + 10,
			// this.x + (actPoints[i].x + 1 << LEFT_SHIFT) + 10,
			// this.y + (actPoints[i].y + 1 << LEFT_SHIFT) + 10,
			// (actType+1) << LEFT_SHIFT, 0, (actType+2) << LEFT_SHIFT, 1 <<
			// LEFT_SHIFT, null);
		}
	}

	/**
	 * ������Ӱ
	 * @param imgShadow
	 * @param isShadow
	 * @param actPoints
	 * @param g
	 */
	private void drawShadow(Image imgShadow, Point[] actPoints, Graphics g)
	{
		if (!this.dto.isShowShadow())
		{
			return;
		}
		// Ѱ����Ӱ���ұ߽�
		int leftX = LEFT_SIDE;
		int rightX = RIGHT_SIDE;
		for (Point point : actPoints)
		{
			leftX = point.x < leftX ? point.x : leftX;
			rightX = point.x > rightX ? point.x : rightX;
		}
		g.drawImage(IMG_SHADOW,
					this.x + BORDER + (leftX << LEFT_SHIFT), 
					this.y + BORDER, 
					rightX - leftX + 1 << LEFT_SHIFT,
					this.height - (BORDER << 1),
					null);
	}

	/**
	 * ���Ʒ���
	 * @param x
	 * @param y
	 * @param index ��ͬ��ɫ�ķ�������
	 * @param g
	 */
	public void drawActByPoint(int x, int y, int index, Graphics g)
	{
		// TODO 8
		index = this.dto.isStart() ? index : 8;
		// TODO 10
		g.drawImage(ACT, this.x + (x << LEFT_SHIFT)  + 10, 
				 	this.y + (y << LEFT_SHIFT) + 10, 
				 	this.x + (x + 1 << LEFT_SHIFT) + 10,
				 	this.y + (y + 1 << LEFT_SHIFT) + 10,
				 	index << LEFT_SHIFT,
				 	0,
				 	(index+1) << LEFT_SHIFT,
				 	1 << LEFT_SHIFT,
				 	null);
	}

}
