package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import dto.Player;
import entity.GameAct;

/**
 * ��Ϸ�߼�
 * @author Ben
 *
 */
public class GameTetris implements GameService
{
	private GameDto dto;
	
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	
	private Random random;
	private static int RANDOM_NUM = 7;

	public GameTetris(GameDto dto)
	{
		this.dto = dto;
		this.random = new Random();
//		GameAct gameAct = new GameAct();
//		dto.setGameAct(gameAct);
	}

	/**
	 * ��ת
	 * @return 
	 */
	public boolean moveUp()
	{
		if (this.dto.isPause())
		{
			return false;
		}
		synchronized (this.dto)
		{
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}

	/**
	 * ���ƣ��������������ѻ�
	 * @return 
	 */
	public boolean moveDown()
	{
		if (this.dto.isPause())
		{
			return false;
		}
		synchronized (this.dto)
		{
			if(this.dto.getGameAct().move(0, 1, this.dto.getGameMap()))
			{
				return false;
			}
			boolean[][] gameMap = this.dto.getGameMap();
			Point[] actPoints = this.dto.getGameAct().getActPoints();
			for (int i = 0; i < actPoints.length; i++)
			{
				gameMap[actPoints[i].y][actPoints[i].x] = true;
			}
			
			// �ж��Ƿ�����  ���в��� ���Ӿ���ֵ
			int exp = this.addExp();
			if (exp > 0)
			{
				this.addPoint(exp);
			}
			// TODO ��ֲ���
			// TODO �ж��Ƿ�����	��������
			
			// ˢ��һ���µķ���
			this.dto.getGameAct().init(this.dto.getNext());
			int next = random.nextInt(RANDOM_NUM);
			this.dto.setNext(next);
			// �ж���Ϸ�Ƿ����
			if (this.isGameOver())
			{
				this.dto.setStart(false);
			}
		}
		return true;
	}
	
	

	/**
	 * ��Ϸ�Ƿ����
	 * @return
	 */
	private boolean isGameOver()
	{
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		boolean[][] gameMap = this.dto.getGameMap();
		for (Point point : actPoints)
		{
			if (gameMap[point.y][point.x])
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * ����
	 * @return 
	 */
	public boolean moveLeft()
	{
		if (this.dto.isPause())
		{
			return false;
		}
		synchronized (this.dto)
		{
			return this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
		}
	}

	/**
	 * ����
	 * @return 
	 */
	public boolean moveRight()
	{
		if (this.dto.isPause())
		{
			return false;
		}
		synchronized (this.dto)
		{
			return this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
		}
	}


	@Override
	public boolean keyUp()
	{
		// ��ͣ����
		if (this.dto.isStart())
		{
			this.dto.changePause();
		}
		return false;
	}

	@Override
	public boolean keyLeft()
	{
		// ��Ӱ����
		this.dto.changedShowShadow();
		return false;
	}

	@Override
	public boolean keyDown()
	{
		if (this.dto.isPause())
		{
			return false;
		}
		// ��������
		while (!this.moveDown());
		return true;
		
	}

	@Override
	public boolean keyRight()
	{
		// ���׼�λ
//		int point = this.dto.getCurPoint();
//		int level = this.dto.getCurLevel();
//		int rmLines = this.dto.getCurRemoveRowLines();
//		point += 10;
//		level += 1;
//		rmLines += 1;
//		this.dto.setCurLevel(level);
//		this.dto.setCurPoint(point);
//		this.dto.setCurRemoveRowLines(rmLines);
		
		this.dto.setCheat(true);
		addPoint(4);
		
		return true;
	}
	

	/**
	 * ��������
	 * @param exp
	 */
	private void addPoint(int exp)
	{
		this.dto.setCurRemoveRowLines(this.dto.getCurRemoveRowLines()+exp);
		int rmLines = this.dto.getCurRemoveRowLines();
		if (rmLines !=0 && rmLines % LEVEL_UP + LEVEL_UP == LEVEL_UP)
		{
			this.dto.setCurLevel(this.dto.getCurLevel()+1);
		}
		this.dto.setCurPoint(this.dto.getCurPoint() + GameConfig.getSystemConfig().getAddPoint().get(exp));
	}

	/**
	 * ���Ӿ���ֵ
	 */
	private int addExp()
	{
		boolean[][] gameMap = this.dto.getGameMap();
		int exp = 0;
		for (int y = 0; y < this.dto.GAME_ZONE_H; y++)
		{
			if (isRemoveable(y, gameMap))
			{
				removeLine(y, gameMap);
				exp++;
			}
			
		}
		return exp;
	}

	/**
	 * ��ǰ���Ƿ��������
	 * @param y
	 * @param gameMap
	 * @return
	 */
	private boolean isRemoveable(int y, boolean[][] gameMap)
	{
		for (int x = 0; x < this.dto.GAME_ZONE_W; x++)
		{
			if (!gameMap[y][x])
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * ���в���
	 * @param y ��
	 * @param gameMap
	 */
	private void removeLine(int row, boolean[][] gameMap)
	{
		for (int x = 0; x < this.dto.GAME_ZONE_W; x++)
		{
			for (int y = row; y > 0; y--)
			{
				gameMap[y][x] = gameMap[y-1][x];
			}
			// ���⴦���һ��
			gameMap[0][x] = false;
		}
	}

	/**
	 * ��ʼ��Ϸ
	 */
	@Override
	public void startGame()
	{
		// ���������һ��������
		this.dto.setNext(random.nextInt(RANDOM_NUM));
		GameAct gameAct = new GameAct();
		this.dto.setGameAct(gameAct);
		// ����Ϸ״̬��Ϊ��ʼ
		this.dto.setStart(true);
		this.dto.init();
	}
}
