package config;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig implements Serializable
{
	private int maxRow;
	private int maxCol;
	private int levelUp;
	
	private final List<Point[]> pointConfig;
	
	private final HashMap<Integer, Integer> addPoint;
	
	public List<Point[]> getPointConfig()
	{
		return pointConfig;
	}

	public HashMap<Integer, Integer> getAddPoint()
	{
		return addPoint;
	}

	@SuppressWarnings("unchecked")
	public SystemConfig(Element system)
	{
		maxRow = Integer.parseInt(system.attributeValue("maxRow"));
		maxCol = Integer.parseInt(system.attributeValue("maxCol"));
		levelUp = Integer.parseInt(system.attributeValue("levelUp"));
		
		List<Element> rmPoint = system.elements("addPoint");
		addPoint = new HashMap<Integer, Integer>();
		for (Element e : rmPoint)
		{
			int rm = Integer.parseInt(e.attributeValue("rm"));
			int point = Integer.parseInt(e.attributeValue("point"));
			addPoint.put(rm, point);
		}
		
		// 方块组
		List<Element> rects = system.elements("rect");
		pointConfig = new ArrayList(rects.size());
		for (Element rect : rects)
		{
			// 每个方块组的各个方块坐标
			List<Element> points = rect.elements("point");
			Point[] p = new Point[points.size()];
			for (int i = 0; i < points.size(); i++)
			{
				int x = Integer.parseInt(points.get(i).attributeValue("x"));
				int y = Integer.parseInt(points.get(i).attributeValue("y"));
				p[i] = new Point(x, y);
			}
			pointConfig.add(p);
		}
	}

	public int getMaxRow()
	{
		return maxRow;
	}

	public int getMaxCol()
	{
		return maxCol;
	}

	public int getLevelUp()
	{
		return levelUp;
	}

}
