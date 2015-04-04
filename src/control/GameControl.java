package control;

import service.GameService;
import ui.JPanelGame;


/**
 * ��Ϸ������ 
 * ������Ҽ��̿���
 * ������Ϸ�߼�
 * @author Ben
 *
 */
public class GameControl
{
	
	/**
	 * ��Ϸ�����
	 */
	private JPanelGame panelGame;
	/**
	 * ��Ϸ�߼���
	 */
	private GameService gameService;
	
	public GameControl(JPanelGame jpanelGame, GameService gameService)
	{
		this.panelGame = jpanelGame;
		this.gameService = gameService;
	}

	/**
	 * ��ת
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
