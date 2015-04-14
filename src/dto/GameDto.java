package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.GameConfig;
import entity.GameAct;

public class GameDto
{
	/**
	 * 游戏地图的宽度和高度
	 */
	public int GAME_ZONE_W = GameConfig.getSystemConfig().getMaxCol() + 1;
	public int GAME_ZONE_H = GameConfig.getSystemConfig().getMaxRow() + 1;
	
	/**
	 * 数据库记录
	 */
	private List<Player> databaseRecord;
	
	/**
	 * 本地记录
	 */
	private List<Player> localRecord;
	
	/**
	 * 是否显示阴影
	 */
	private boolean isShowShadow;
	
	public boolean isShowShadow()
	{
		return isShowShadow;
	}

	public void changedShowShadow()
	{
		this.isShowShadow = !this.isShowShadow;
	}

	private boolean isPause;
	
	
	/**
	 * 游戏地图
	 */
	// TODO 配置
	private boolean[][] gameMap;
	
	/**
	 * 下落方块
	 */
	private GameAct gameAct;
	
	/**
	 * 下一个方块
	 */
	private int next;
	
	/**
	 * 当前游戏等级
	 */
	private int curLevel;
	
	/**
	 * 当前游戏得分
	 */
	private int curPoint;
	
	/**
	 * 当前消行数
	 */
	private int curRemoveRowLines;
	
	private boolean start;
	
	public GameDto()
	{
		
		init();
	}
	
	/**
	 * 初始化gameMap
	 */
	private void init()
	{
		
		gameMap = new boolean[GAME_ZONE_H][GAME_ZONE_W];
	}

	public List<Player> getDatabaseRecord()
	{
		return databaseRecord;
	}

	public void setDatabaseRecord(List<Player> databaseRecord)
	{
		this.databaseRecord = setFullRecord(databaseRecord);
	}

	/**
	 * 设置记录数至少为5 并且排序
	 * @param players
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Player> setFullRecord(List<Player> players)
	{
		if (players == null)
		{
			players = new ArrayList<Player>();
		}
		while (players.size() < 5)
		{
			players.add(new Player("no data", 0));
		}
		Collections.sort(players);
		return players;
	}
	
	public List<Player> getLocalRecord()
	{
		return localRecord;
	}

	public void setLocalRecord(List<Player> localRecord)
	{
		this.localRecord = setFullRecord(localRecord);
	}

	public boolean[][] getGameMap()
	{
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap)
	{
		this.gameMap = gameMap;
	}

	public GameAct getGameAct()
	{
		return gameAct;
	}

	public void setGameAct(GameAct gameAct)
	{
		this.gameAct = gameAct;
	}

	public int getNext()
	{
		return next;
	}

	public void setNext(int next)
	{
		this.next = next;
	}

	public int getCurLevel()
	{
		return curLevel;
	}

	public void setCurLevel(int curLevel)
	{
		this.curLevel = curLevel;
	}

	public int getCurPoint()
	{
		return curPoint;
	}

	public void setCurPoint(int curPoint)
	{
		this.curPoint = curPoint;
	}

	public int getCurRemoveRowLines()
	{
		return curRemoveRowLines;
	}

	public void setCurRemoveRowLines(int curRemoveRowLines)
	{
		this.curRemoveRowLines = curRemoveRowLines;
	}

	public boolean isStart()
	{
		return start;
	}

	public void setStart(boolean start)
	{
		this.start = start;
	}

	public boolean isPause()
	{
		return isPause;
	}

	public void changePause()
	{
		this.isPause = !this.isPause;
	}

	
}
