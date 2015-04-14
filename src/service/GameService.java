package service;

import java.util.List;

import dto.Player;

public interface GameService
{
	/**
	 * 旋转
	 */
	public boolean moveUp();
	/**
	 * 左移
	 */
	public boolean moveLeft();
	/**
	 * 下移
	 */
	public boolean moveDown();
	/**
	 * 右移
	 */
	public boolean moveRight();
	/**
	 * 上功能键
	 */
	public boolean keyUp();
	/**
	 * 左功能键
	 */
	public boolean keyLeft();
	/**
	 * 下功能键
	 */
	public boolean keyDown();
	/**
	 * 右功能键
	 */
	public boolean keyRight();
	
	/**
	 * 开始游戏
	 */
	public void startGame();
	
}
