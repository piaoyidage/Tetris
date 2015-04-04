package service;

import java.awt.Point;
import java.util.Random;

import dto.GameDto;
import entity.GameAct;

/**
 * 游戏逻辑
 * @author Ben
 *
 */
public class GameService
{
	private GameDto dto;

	public GameService(GameDto dto)
	{
		this.dto = dto;
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
		this.dto.getGameAct().init();
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

	
}
