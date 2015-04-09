package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig
{
	private final List<Point[]> pointConfig;
	
	public List<Point[]> getPointConfig()
	{
		return pointConfig;
	}

	public SystemConfig(Element system)
	{
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

}
