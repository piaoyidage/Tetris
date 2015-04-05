package control;

import service.GameService;
import ui.JPanelGame;


/**
 * 游戏控制器 
 * 接受玩家键盘控制
 * 控制游戏逻辑
 * @author Ben
 *
 */
public class GameControl
{
	
	/**
	 * 游戏界面层
	 */
	private JPanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	
	public GameControl(JPanelGame jpanelGame, GameService gameService)
	{
		this.panelGame = jpanelGame;
		this.gameService = gameService;
	}

	/**
	 * 旋转
	 */
	public void moveUp()
	{
		// TODO
		this.gameService.moveUp();
		this.panelGame.repaint();
	}

	public void moveDown()
	{
		this.gameService.moveDown();
		this.panelGame.repaint();
	}

	public void moveLeft()
	{
		this.gameService.moveLeft();
		this.panelGame.repaint();
	}

	public void moveRight()
	{
		this.gameService.moveRight();
		this.panelGame.repaint();
	}

}
