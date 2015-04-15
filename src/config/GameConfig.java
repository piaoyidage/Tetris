package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	
	/**
	 * 是否是开发中
	 */
	private static boolean IS_DEBUG = false;
	
	static
	{
		try
		{
			if (IS_DEBUG)
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
			else
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/frameConfig.dat"));
				FRAME_CONFIG = (FrameConfig)ois.readObject();
				
				ois = new ObjectInputStream(new FileInputStream("data/systemConfig.dat"));
				SYSTEM_CONFIG = (SystemConfig)ois.readObject();
				
				ois = new ObjectInputStream(new FileInputStream("data/dataConfig.dat"));
				DATA_CONFIG = (DataConfig)ois.readObject();
				
				ois.close();
			}
			
		}
		catch (Exception e)
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
	
	public static void main(String[] args) throws Exception
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/frameConfig.dat"));
		oos.writeObject(FRAME_CONFIG);
		
		oos = new ObjectOutputStream(new FileOutputStream("data/systemConfig.dat"));
		oos.writeObject(SYSTEM_CONFIG);
		
		oos = new ObjectOutputStream(new FileOutputStream("data/dataConfig.dat"));
		oos.writeObject(DATA_CONFIG);
		
		oos.close();
	}
	
}
