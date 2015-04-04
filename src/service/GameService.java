package service;

import java.awt.Point;
import java.util.Random;

import dto.GameDto;
import entity.GameAct;

/**
 * ��Ϸ�߼�
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
	 * ��ת
	 */
	public void moveUp()
	{
		this.dto.getGameAct().round(this.dto.getGameMap());
	}

	/**
	 * ���ƣ��������������ѻ�
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
		
		// TODO �ж��Ƿ�����	���в���
		// TODO ��ֲ���
		// TODO �ж��Ƿ�����	��������
		
		// ˢ��һ���µķ���
		this.dto.getGameAct().init();
	}

	/**
	 * ����
	 */
	public void moveLeft()
	{
		this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
	}

	/**
	 * ����
	 */
	public void moveRight()
	{
		this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
	}

	
}
