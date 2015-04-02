package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ��Ϸ������ ��Ӧ��cfg.xml
 * @author Ben
 *
 */
public class GameConfig
{
	/**
	 * ��Ϸ������
	 */
	private int width;
	/**
	 * �߶�
	 */
	private int height;
	/**
	 * �ڱ߾�
	 */
	private int padding;
	/**
	 * �߿��С
	 */
	private int windowSize;
	/**
	 * ��
	 */
	private List<LayerConfig> layersConfig;
	
	/**
	 * ���캯��
	 * ��ȡXML�ļ�����ȡ��Ϸ��������
	 * @throws Exception
	 */
	public GameConfig() throws Exception
	{
		// ����XML��ȡ��
		SAXReader reader = new SAXReader();
		// ��ȡXML�ļ�
		Document doc = reader.read("config/cfg.xml");
		// ��Ԫ��
		Element game = doc.getRootElement();
		// game���ӽڵ�frame
		Element frame = game.element("frame");
		// ��ȡframe������
		this.setUiConfig(frame);
		// ��ȡϵͳ����
		Element system = game.element("system");
		this.setSystemConfig(system);
		// ��ȡ��������
		Element data = game.element("data");
		this.setDataConfig(data);
	}

	/**
	 * ��Ϸ��������
	 * @param frame
	 */
	private void setUiConfig(Element frame)
	{
		width = Integer.parseInt(frame.attributeValue("width"));
		height = Integer.parseInt(frame.attributeValue("height"));
		padding = Integer.parseInt(frame.attributeValue("padding"));
		windowSize = Integer.parseInt(frame.attributeValue("windowSize"));
		
		layersConfig = new ArrayList<LayerConfig>();
		
		// frame�ӽڵ�layer
		List<Element> layers = frame.elements("layer");
		for (Element layer : layers)
		{
			String className = layer.attributeValue("className");
			int x = Integer.parseInt(layer.attributeValue("x"));
			int y = Integer.parseInt(layer.attributeValue("y"));
			int w = Integer.parseInt(layer.attributeValue("w"));
			int h = Integer.parseInt(layer.attributeValue("h"));
			LayerConfig cl = new LayerConfig(className, x, y, w, h);
			layersConfig.add(cl);
		}
	}
	
	/**
	 * ϵͳ����
	 * @param system
	 */
	private void setSystemConfig(Element system)
	{
		// TODO
	}
	
	/**
	 * ��������
	 * @param data
	 */
	private void setDataConfig(Element data)
	{
		// TODO
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getPadding()
	{
		return padding;
	}

	public int getWindowSize()
	{
		return windowSize;
	}

	public List<LayerConfig> getLayersConfig()
	{
		return layersConfig;
	}
}
