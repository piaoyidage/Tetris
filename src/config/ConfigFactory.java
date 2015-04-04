package config;

/**
 * ʹ�õ���ģʽ��������ȡ���õ�Ψһ����
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
	 * ���Ψһ�����ö���
	 * @return
	 */
	public static GameConfig getGameConfig()
	{
		return gameConfig;
	}
}
