package control;

import service.GameService;
import ui.JPanelGame;
import dao.Data;
import dao.DataTest;


/**
 * 游戏控制器 
 * 接受玩家键盘控制
 * 控制游戏逻辑
 * @author Ben
 *
 */
public class GameControl
{
	// TODO 测试
	private Data dataA;
	private Data dataB;
	
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
		
		// TODO: 测试
		// 从数据接口A获得数据库记录
		this.dataA = new DataTest();
		// 设置数据库记录到游戏
		this.gameService.setDatabaseRecord(dataA.loadData());
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

	// TODO：测试专用
	public void test()
	{
		this.gameService.test();
		this.panelGame.repaint();
	}

}
