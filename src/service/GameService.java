package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import dto.GameDto;
import dto.Player;
import entity.GameAct;

/**
 * 游戏逻辑
 * @author Ben
 *
 */
public class GameService
{
	private GameDto dto;
	
	private Random random;
	private static int RANDOM_NUM = 7;

	public GameService(GameDto dto)
	{
		this.dto = dto;
		this.random = new Random();
		GameAct gameAct = new GameAct();
		dto.setGameAct(gameAct);
	}

	/**
	 * 翻转
	 */
	public void moveUp()
	{
		this.dto.getGameAct().round(this.dto.getGameMap());
	}

	/**
	 * 下移，如果不能下移则堆积
	 */
	public void moveDown()
	{
		if(this.dto.getGameAct().move(0, 1, this.dto.getGameMap()))
		{
			return;
		}
		boolean[][] gameMap = this.dto.getGameMap();
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		for (int i = 0; i < actPoints.length; i++)
		{
			gameMap[actPoints[i].y][actPoints[i].x] = true;
		}
		
		// TODO 判断是否消行	消行操作
		// TODO 算分操作
		// TODO 判断是否升级	升级操作
		
		// 刷新一个新的方块
		this.dto.getGameAct().init(this.dto.getNext());
		int next = random.nextInt(RANDOM_NUM);
		this.dto.setNext(next);
	}

	/**
	 * 左移
	 */
	public void moveLeft()
	{
		this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
	}

	/**
	 * 右移
	 */
	public void moveRight()
	{
		this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
	}

	// TODO:测试专用
	public void test()
	{
		int point = this.dto.getCurPoint();
		int level = this.dto.getCurLevel();
		int rmLines = this.dto.getCurRemoveRowLines();
		point += 10;
		level += 1;
		rmLines += 1;
		this.dto.setCurLevel(level);
		this.dto.setCurPoint(point);
		this.dto.setCurRemoveRowLines(rmLines);
		
	}

	// TODO：测试 设置数据库记录
	public void setDatabaseRecord(List<Player> players)
	{
		this.dto.setDatabaseRecord(players);
	}
}
