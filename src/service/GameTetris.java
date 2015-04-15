package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import dto.Player;
import entity.GameAct;

/**
 * 游戏逻辑
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
	 * 翻转
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
	 * 下移，如果不能下移则堆积
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
			
			// 判断是否消行  消行操作 增加经验值
			int exp = this.addExp();
			if (exp > 0)
			{
				this.addPoint(exp);
			}
			// TODO 算分操作
			// TODO 判断是否升级	升级操作
			
			// 刷新一个新的方块
			this.dto.getGameAct().init(this.dto.getNext());
			int next = random.nextInt(RANDOM_NUM);
			this.dto.setNext(next);
			// 判断游戏是否结束
			if (this.isGameOver())
			{
				this.dto.setStart(false);
			}
		}
		return true;
	}
	
	

	/**
	 * 游戏是否结束
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
	 * 左移
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
	 * 右移
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
		// 暂停控制
		if (this.dto.isStart())
		{
			this.dto.changePause();
		}
		return false;
	}

	@Override
	public boolean keyLeft()
	{
		// 阴影控制
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
		// 快速下落
		while (!this.moveDown());
		return true;
		
	}

	@Override
	public boolean keyRight()
	{
		// 作弊键位
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
	 * 升级操作
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
	 * 增加经验值
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
	 * 当前行是否可以消行
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
	 * 消行操作
	 * @param y 行
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
			// 特殊处理第一行
			gameMap[0][x] = false;
		}
	}

	/**
	 * 开始游戏
	 */
	@Override
	public void startGame()
	{
		// 设置随机下一个方块组
		this.dto.setNext(random.nextInt(RANDOM_NUM));
		GameAct gameAct = new GameAct();
		this.dto.setGameAct(gameAct);
		// 将游戏状态设为开始
		this.dto.setStart(true);
		this.dto.init();
	}
}
