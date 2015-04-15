package util;

public class GameFuction
{
	/**
	 * 根据等级设置休眠时间
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
