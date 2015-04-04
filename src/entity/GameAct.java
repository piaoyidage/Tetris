package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Ben
 * 下落的方块
 */
public class GameAct
{
	/**
	 * 七种不同下落方块
	 */
	private static List<Point[]> POINT_CONFIG;
	
	static
	{
		POINT_CONFIG = new ArrayList<Point[]>(7);
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(6, 0)});
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(4, 1)});
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(3, 1)});
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(5, 0), new Point(3, 1), new Point(4, 1)});
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(5, 1)});
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(5, 1)});
		POINT_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(4, 1), new Point(5, 1)});
	}
	
	private int actType;
	
	public int getActType()
	{
		return actType;
	}

	/**
	 * 方块的右上角坐标点
	 */
	private Point[] actPoints = null;

	public GameAct()
	{
		
		init();
//		// TODO 配置
//		actPoints = new Point[]
//		{
//				new Point(3, 0),
//				new Point(4, 0),
//				new Point(5, 0),
//				new Point(5, 1)
//		};
	}

	public Point[] getActPoints()
	{
		return actPoints;
	}
	
	/**
	 * 下落方块的移动
	 * @param x
	 * @param y
	 */
	public boolean move(int x, int y, boolean[][] gameMap)
	{

		// 判断是否可以移动
		for (int i = 0; i < actPoints.length; i++)
		{
			int newX = actPoints[i].x + x;
			int newY = actPoints[i].y + y;
			if (isOverZone(newX, newY, gameMap))
			{
				return false;
			}
		}

		// 移动
		for (int i = 0; i < actPoints.length; i++)
		{
			actPoints[i].x += x;
			actPoints[i].y += y;
		}
		return true;
	}
	
	/**
	 * 方块翻转
	 * @param x
	 * @param y
	 */
	public void round(boolean[][] gameMap)
	{
		// 四个方块组成的正方形不需要翻转
		if (this.actType == 4)
		{
			return;
		}
		/*
		 * 顺时针旋转
		 * A(Xa, Ya)绕C(Xc, Yc)旋转90度到B(Xb, Yb)
		 * Xb = Ya - Yc + Xc
		 * Yb = Xc - Xa + Yc
		 * 
		 */
		// 判断是否可以翻转
		for (int i = 0; i < actPoints.length; i++)
		{
			int newX = actPoints[i].y - actPoints[0].y + actPoints[0].x;
			int newY = actPoints[0].x - actPoints[i].x + actPoints[0].y;
			if (isOverZone(newX, newY, gameMap))
			{
				return;
			}
		}

		// 进行翻转
		for (int i = 0; i < actPoints.length; i++)
		{
			int newX = actPoints[i].y - actPoints[0].y + actPoints[0].x;
			int newY = actPoints[0].x - actPoints[i].x + actPoints[0].y;
			actPoints[i].x = newX;
			actPoints[i].y = newY;
		}

	}
	
	/**
	 * 判断是否可以移动
	 * @return
	 */
//	public boolean moveAble(int x, int y)
//	{
//		// TODO
//		for (int i = 0; i < actPoints.length; i++)
//		{
//			int xChanged = actPoints[i].x + x;
//			int yChanged = actPoints[i].y + y;
//			if (xChanged < 0 || xChanged > 9 || yChanged < 0 || yChanged > 17)
//			{
//				return false;
//			}
//		}
//		return true;
//	}
	
	/**
	 * 判断当前坐标是否在游戏运行界面
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOverZone(int x, int y, boolean[][] gameMap)
	{
		return x < 0 || x > 9 || y < 0 || y > 17 || gameMap[y][x];
	}

	/**
	 * 每次刷新一个随机的下落方块
	 */
	public void init()
	{
		// 随机0-6
		int actType = new Random().nextInt(7);
		this.actType = actType;
		Point[] points = POINT_CONFIG.get(actType);
		actPoints = new Point[points.length];
		for (int i = 0; i < actPoints.length; i++)
		{
			actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}
}
