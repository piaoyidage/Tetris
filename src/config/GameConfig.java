package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ��Ϸ������ ��Ӧ��cfg.xml
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
			// ����XML��ȡ��
			SAXReader reader = new SAXReader();
			// ��ȡXML�ļ�
			Document doc = reader.read("config/cfg.xml");
			// ��Ԫ��
			Element game = doc.getRootElement();
			// game���ӽڵ�frame
			Element frame = game.element("frame");
			// ��ȡframe������
			FRAME_CONFIG = new FrameConfig(frame);
			// ��ȡϵͳ����
			Element system = game.element("system");
			SYSTEM_CONFIG = new SystemConfig(system);
			// ��ȡ��������
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
