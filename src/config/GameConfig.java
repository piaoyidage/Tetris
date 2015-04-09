package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏的配置 对应于cfg.xml
 * @author Ben
 *
 */
public class GameConfig
{
	private static FrameConfig FRAME_CONFIG;
	private static SystemConfig SYSTEM_CONFIG;
	private static DataConfig DATA_CONFIG;
	
	static
	{
		try
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
			FRAME_CONFIG = new FrameConfig(frame);
			// 读取系统配置
			Element system = game.element("system");
			SYSTEM_CONFIG = new SystemConfig(system);
			// 读取数据配置
			Element data = game.element("data");
			DATA_CONFIG = new DataConfig(data);
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}
	
	public static FrameConfig getFrameConfig()
	{
		return FRAME_CONFIG;
	}

	public static SystemConfig getSystemConfig()
	{
		return SYSTEM_CONFIG;
	}

	public static DataConfig getDataConfig()
	{
		return DATA_CONFIG;
	}

	private GameConfig()
	{
		
	}
	
}
