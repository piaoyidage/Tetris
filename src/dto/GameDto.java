package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entity.GameAct;

public class GameDto
{
	/**
	 * ���ݿ��¼
	 */
	private List<Player> databaseRecord;
	
	/**
	 * ���ؼ�¼
	 */
	private List<Player> localRecord;
	
	/**
	 * ��Ϸ��ͼ
	 */
	// TODO ����
	private boolean[][] gameMap;
	
	/**
	 * ���䷽��
	 */
	private GameAct gameAct;
	
	/**
	 * ��һ������
	 */
	private int next;
	
	/**
	 * ��ǰ��Ϸ�ȼ�
	 */
	private int curLevel;
	
	/**
	 * ��ǰ��Ϸ�÷�
	 */
	private int curPoint;
	
	/**
	 * ��ǰ������
	 */
	private int curRemoveRowLines;
	
	public GameDto()
	{
		
		init();
	}
	
	/**
	 * ��ʼ��gameMap
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
	 * ���ü�¼������Ϊ5 ��������
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
