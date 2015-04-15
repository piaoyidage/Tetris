package config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig implements Serializable
{
	/**
	 * ��Ϸ����
	 */
	private String title;
	/**
	 * ��Ϸ����λ�õ�������
	 */
	private int windowUp;
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
	private int border;
	/**
	 * ������
	 */
	private List<LayerConfig> layersConfig;
	
	private ButtonConfig buttonConfig;
	
	public ButtonConfig getButtonConfig()
	{
		return buttonConfig;
	}

	public FrameConfig(Element frame)
	{
		width = Integer.parseInt(frame.attributeValue("width"));
		height = Integer.parseInt(frame.attributeValue("height"));
		padding = Integer.parseInt(frame.attributeValue("padding"));
		border = Integer.parseInt(frame.attributeValue("border"));
		
		layersConfig = new ArrayList<LayerConfig>();
		
		// frame�ӽڵ�layer
		@SuppressWarnings("unchecked")
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
		
		// frame�ӽڵ�button
		buttonConfig = new ButtonConfig(frame.element("button"));
	}
	
	public String getTitle()
	{
		return title;
	}
	public int getWindowUp()
	{
		return windowUp;
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
	public int getBorder()
	{
		return border;
	}
	public List<LayerConfig> getLayersConfig()
	{
		return layersConfig;
	}
	
}
