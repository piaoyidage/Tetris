package config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig implements Serializable
{
	/**
	 * 游戏名字
	 */
	private String title;
	/**
	 * 游戏界面位置调整上移
	 */
	private int windowUp;
	/**
	 * 游戏界面宽度
	 */
	private int width;
	/**
	 * 高度
	 */
	private int height;
	/**
	 * 内边距
	 */
	private int padding;
	/**
	 * 边框大小
	 */
	private int border;
	/**
	 * 层属性
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
		
		// frame子节点layer
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
		
		// frame子节点button
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
