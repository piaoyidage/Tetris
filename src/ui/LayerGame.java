package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

/**
 * 游戏运行界面
 * @author Ben
 *
 */
public class LayerGame extends Layer
{
	/**
	 * 方块图片
	 */
	private static final Image ACT = new ImageIcon("graphics/game/rect.png").getImage();
	
	/**
	 * 阴影图片
	 */
	private static final Image IMG_SHADOW = new ImageIcon("graphics/game/shadow.png").getImage();
	
	private static final int LEFT_SIDE = 9;
	private static final int RIGHT_SIDE = 0;
	
	// 左移 5
	private static final int LEFT_SHIFT = 5;

	public LayerGame(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		
		// 绘制阴影
		this.drawShadow(IMG_SHADOW, true, actPoints, g);
		
		// 绘制方块
		// 根据不同的actType打印不同颜色的方块
		int actType = this.dto.getGameAct().getActType();
		// TODO
		for (int i = 0; i < actPoints.length; i++)
		{
			this.drawActByPoint(actPoints[i].x, actPoints[i].y, actType+1, g);
//			g.drawImage(ACT, this.x + (actPoints[i].x << LEFT_SHIFT)  + 10, 
//							 this.y + (actPoints[i].y << LEFT_SHIFT) + 10, 
//							 this.x + (actPoints[i].x + 1 << LEFT_SHIFT) + 10,
//							 this.y + (actPoints[i].y + 1 << LEFT_SHIFT) + 10,
//							 (actType+1) << LEFT_SHIFT, 0, (actType+2) << LEFT_SHIFT, 1 << LEFT_SHIFT, null);
		}
		
		// 绘制地图
		// TODO 如果输了 index == 8 
		boolean[][] gameMap = this.dto.getGameMap();
		// 根据不同等级方块堆积的颜色不同
		int level = this.dto.getCurLevel();
		int index = level == 0 ? 0 : (level - 1) % 7 + 1;
		for (int y = 0; y < gameMap.length; y++)
		{
			for (int x = 0; x < gameMap[y].length; x++)
			{
				if (gameMap[y][x])
				{
					this.drawActByPoint(x, y, index, g);
//					g.drawImage(ACT, this.x + x*ACT_SIZE + 10, 
//						 		this.y + y*ACT_SIZE + 10, 
//						 		this.x + x*ACT_SIZE + ACT_SIZE + 10,
//						 		this.y + y*ACT_SIZE + ACT_SIZE + 10,
//						 		0, 0, 32, 32, null);
				}
			}
		}
	}
	
	/**
	 * 绘制阴影
	 * @param imgShadow
	 * @param isShadow
	 * @param actPoints
	 * @param g
	 */
	private void drawShadow(Image imgShadow, boolean isShadow, Point[] actPoints, Graphics g)
	{
		if (!isShadow)
		{
			return;
		}
		// 寻找阴影左右边界
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
	 * 绘制方块
	 * @param x
	 * @param y
	 * @param index 不同颜色的方块索引
	 * @param g
	 */
	public void drawActByPoint(int x, int y, int index, Graphics g)
	{
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
