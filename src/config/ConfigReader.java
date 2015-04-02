package config;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ����ʹ��dom4j��ȡxml
 * @author Ben
 *
 */
public class ConfigReader
{
	public static void readConfig() throws Exception
	{
		SAXReader reader = new SAXReader();
		Document doc = reader.read("config/cfg.xml");
		// ��Ԫ��
		Element game = doc.getRootElement();
		// game���ӽڵ�frame
		Element frame = game.element("frame");
		int width = Integer.parseInt(frame.attributeValue("width"));
		int heigth = Integer.parseInt(frame.attributeValue("height"));
		int padding = Integer.parseInt(frame.attributeValue("padding"));
		int windowSize = Integer.parseInt(frame.attributeValue("windowSize"));
		System.out.print(width + " ");
		System.out.print(heigth + " ");
		System.out.print(padding + " ");
		System.out.println(windowSize);
		// frame�ӽڵ�layer
		List<Element> layers = frame.elements("layer");
		for (Element layer : layers)
		{
			String className = layer.attributeValue("className");
			int x = Integer.parseInt(layer.attributeValue("x"));
			int y = Integer.parseInt(layer.attributeValue("y"));
			int w = Integer.parseInt(layer.attributeValue("w"));
			int h = Integer.parseInt(layer.attributeValue("h"));
			System.out.print(className + " ");
			System.out.print(x + " ");
			System.out.print(y + " ");
			System.out.print(w + " ");
			System.out.println(h);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		ConfigReader.readConfig();
	}
}