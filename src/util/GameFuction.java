package util;

public class GameFuction
{
	/**
	 * ���ݵȼ���������ʱ��
	 * @param level
	 * @return
	 */
	public static int getSleepTimeByLevel(int level)
	{
		int sleepTime = 1100 - 100 * level;
		sleepTime = sleepTime > 100 ? sleepTime : 100;
		return sleepTime;
	}
}
