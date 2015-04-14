package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.GameConfig;
import entity.GameAct;

public class GameDto
{
	/**
	 * ��Ϸ��ͼ�Ŀ�Ⱥ͸߶�
	 */
	public int GAME_ZONE_W = GameConfig.getSystemConfig().getMaxCol() + 1;
	public int GAME_ZONE_H = GameConfig.getSystemConfig().getMaxRow() + 1;
	
	/**
	 * ���ݿ��¼
	 */
	private List<Player> databaseRecord;
	
	/**
	 * ���ؼ�¼
	 */
	private List<Player> localRecord;
	
	/**
	 * �Ƿ���ʾ��Ӱ
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
	
	private boolean start;
	
	public GameDto()
	{
		
		init();
	}
	
	/**
	 * ��ʼ��gameMap
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
	 * ���ü�¼������Ϊ5 ��������
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
