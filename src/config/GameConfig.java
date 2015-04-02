package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏的配置 对应于cfg.xml
 * @author Ben
 *
 */
public class GameConfig
{
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
	private int windowSize;
	/**
	 * 层
	 */
	private List<LayerConfig> layersConfig;
	
	/**
	 * 构造函数
	 * 读取XML文件，获取游戏所有配置
	 * @throws Exception
	 */
	public GameConfig() throws Exception
	{
		// 创建XML读取器
		SAXReader reader = new SAXReader();
		// 读取XML文件
		Document doc = reader.read("config/cfg.xml");
		// 根元素
		Element game = doc.getRootElement();
		// game的子节点frame
		Element frame = game.element("frame");
		// 读取frame的配置
		this.setUiConfig(frame);
		// 读取系统配置
		Element system = game.element("system");
		this.setSystemConfig(system);
		// 读取数据配置
		Element data = game.element("data");
		this.setDataConfig(data);
	}

	/**
	 * 游戏界面配置
	 * @param frame
	 */
	private void setUiConfig(Element frame)
	{
		width = Integer.parseInt(frame.attributeValue("width"));
		height = Integer.parseInt(frame.attributeValue("height"));
		padding = Integer.parseInt(frame.attributeValue("padding"));
		windowSize = Integer.parseInt(frame.attributeValue("windowSize"));
		
		layersConfig = new ArrayList<LayerConfig>();
		
		// frame子节点layer
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
	 * 系统配置
	 * @param system
	 */
	private void setSystemConfig(Element system)
	{
		// TODO
	}
	
	/**
	 * 数据配置
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
