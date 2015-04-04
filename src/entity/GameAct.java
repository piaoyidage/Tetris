package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Ben
 * ����ķ���
 */
public class GameAct
{
	/**
	 * ���ֲ�ͬ���䷽��
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
	 * ��������Ͻ������
	 */
	private Point[] actPoints = null;

	public GameAct()
	{
		
		init();
//		// TODO ����
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
	 * ���䷽����ƶ�
	 * @param x
	 * @param y
	 */
	public boolean move(int x, int y, boolean[][] gameMap)
	{

		// �ж��Ƿ�����ƶ�
		for (int i = 0; i < actPoints.length; i++)
		{
			int newX = actPoints[i].x + x;
			int newY = actPoints[i].y + y;
			if (isOverZone(newX, newY, gameMap))
			{
				return false;
			}
		}

		// �ƶ�
		for (int i = 0; i < actPoints.length; i++)
		{
			actPoints[i].x += x;
			actPoints[i].y += y;
		}
		return true;
	}
	
	/**
	 * ���鷭ת
	 * @param x
	 * @param y
	 */
	public void round(boolean[][] gameMap)
	{
		// �ĸ�������ɵ������β���Ҫ��ת
		if (this.actType == 4)
		{
			return;
		}
		/*
		 * ˳ʱ����ת
		 * A(Xa, Ya)��C(Xc, Yc)��ת90�ȵ�B(Xb, Yb)
		 * Xb = Ya - Yc + Xc
		 * Yb = Xc - Xa + Yc
		 * 
		 */
		// �ж��Ƿ���Է�ת
		for (int i = 0; i < actPoints.length; i++)
		{
			int newX = actPoints[i].y - actPoints[0].y + actPoints[0].x;
			int newY = actPoints[0].x - actPoints[i].x + actPoints[0].y;
			if (isOverZone(newX, newY, gameMap))
			{
				return;
			}
		}

		// ���з�ת
		for (int i = 0; i < actPoints.length; i++)
		{
			int newX = actPoints[i].y - actPoints[0].y + actPoints[0].x;
			int newY = actPoints[0].x - actPoints[i].x + actPoints[0].y;
			actPoints[i].x = newX;
			actPoints[i].y = newY;
		}

	}
	
	/**
	 * �ж��Ƿ�����ƶ�
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
	 * �жϵ�ǰ�����Ƿ�����Ϸ���н���
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOverZone(int x, int y, boolean[][] gameMap)
	{
		return x < 0 || x > 9 || y < 0 || y > 17 || gameMap[y][x];
	}

	/**
	 * ÿ��ˢ��һ����������䷽��
	 */
	public void init()
	{
		// ���0-6
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
