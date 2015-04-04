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
	// TODO 配置
	private static final int ACT_SIZE = 32;
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
		
		// 打印方块
		Point[] actPoints = this.dto.getGameAct().getActPoints();
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
		
		// 打印地图
		// TODO 
		boolean[][] gameMap = this.dto.getGameMap();
		for (int y = 0; y < gameMap.length; y++)
		{
			for (int x = 0; x < gameMap[y].length; x++)
			{
				if (gameMap[y][x])
				{
					this.drawActByPoint(x, y, 0, g);
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
	 * 绘制方块
	 * @param x
	 * @param y
	 * @param index
	 * @param g
	 */
	public void drawActByPoint(int x, int y, int index, Graphics g)
	{
		g.drawImage(ACT, this.x + (x << LEFT_SHIFT)  + 10, 
				 	this.y + (y << LEFT_SHIFT) + 10, 
				 	this.x + (x + 1 << LEFT_SHIFT) + 10,
				 	this.y + (y + 1 << LEFT_SHIFT) + 10,
				 	index << LEFT_SHIFT, 0, (index+1) << LEFT_SHIFT, 1 << LEFT_SHIFT, null);
	}

}
