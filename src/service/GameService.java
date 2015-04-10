package service;

import java.util.List;

import dto.Player;

public interface GameService
{
	/**
	 * ��ת
	 */
	public void moveUp();
	/**
	 * ����
	 */
	public void moveLeft();
	/**
	 * ����
	 */
	public void moveDown();
	/**
	 * ����
	 */
	public void moveRight();
	/**
	 * �Ϲ��ܼ�
	 */
	public void keyUp();
	/**
	 * ���ܼ�
	 */
	public void keyLeft();
	/**
	 * �¹��ܼ�
	 */
	public void keyDown();
	/**
	 * �ҹ��ܼ�
	 */
	public void keyRight();
	
	/**
	 * �������ݿ��¼
	 * @param players
	 */
	public void setDatabaseRecord(List<Player> players);
	
	/**
	 * ���ñ��ؼ�¼
	 * @param players
	 */
	public void setLocalRecord(List<Player> players);
}
