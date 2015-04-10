package service;

import java.util.List;

import dto.Player;

public interface GameService
{
	/**
	 * 旋转
	 */
	public void moveUp();
	/**
	 * 左移
	 */
	public void moveLeft();
	/**
	 * 下移
	 */
	public void moveDown();
	/**
	 * 右移
	 */
	public void moveRight();
	/**
	 * 上功能键
	 */
	public void keyUp();
	/**
	 * 左功能键
	 */
	public void keyLeft();
	/**
	 * 下功能键
	 */
	public void keyDown();
	/**
	 * 右功能键
	 */
	public void keyRight();
	
	/**
	 * 设置数据库记录
	 * @param players
	 */
	public void setDatabaseRecord(List<Player> players);
	
	/**
	 * 设置本地记录
	 * @param players
	 */
	public void setLocalRecord(List<Player> players);
}
