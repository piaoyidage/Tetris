package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entity.GameAct;

public class GameDto
{
	/**
	 * 数据库记录
	 */
	private List<Player> databaseRecord;
	
	/**
	 * 本地记录
	 */
	private List<Player> localRecord;
	
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
	
	public GameDto()
	{
		
		init();
	}
	
	/**
	 * 初始化gameMap
	 */
	private void init()
	{
		
		gameMap = new boolean[18][10];
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

	
}
