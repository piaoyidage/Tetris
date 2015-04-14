package service;

import java.util.List;

import dto.Player;

public interface GameService
{
	/**
	 * ��ת
	 */
	public boolean moveUp();
	/**
	 * ����
	 */
	public boolean moveLeft();
	/**
	 * ����
	 */
	public boolean moveDown();
	/**
	 * ����
	 */
	public boolean moveRight();
	/**
	 * �Ϲ��ܼ�
	 */
	public boolean keyUp();
	/**
	 * ���ܼ�
	 */
	public boolean keyLeft();
	/**
	 * �¹��ܼ�
	 */
	public boolean keyDown();
	/**
	 * �ҹ��ܼ�
	 */
	public boolean keyRight();
	
	/**
	 * ��ʼ��Ϸ
	 */
	public void startGame();
	
}
