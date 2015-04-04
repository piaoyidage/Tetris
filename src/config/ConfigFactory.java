package config;

/**
 * 使用单例模式，创建读取配置的唯一对象
 * @author Ben
 *
 */
public class ConfigFactory
{
	private static GameConfig gameConfig = null;
	
	static
	{
		try
		{
			gameConfig = new GameConfig();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得唯一的配置对象
	 * @return
	 */
	public static GameConfig getGameConfig()
	{
		return gameConfig;
	}
}
