package control;

import service.GameService;
import ui.JPanelGame;
import dao.Data;
import dao.DataTest;


/**
 * ��Ϸ������ 
 * ������Ҽ��̿���
 * ������Ϸ�߼�
 * @author Ben
 *
 */
public class GameControl
{
	// TODO ����
	private Data dataA;
	private Data dataB;
	
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
		
		// TODO: ����
		// �����ݽӿ�A������ݿ��¼
		this.dataA = new DataTest();
		// �������ݿ��¼����Ϸ
		this.gameService.setDatabaseRecord(dataA.loadData());
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

	// TODO������ר��
	public void test()
	{
		this.gameService.test();
		this.panelGame.repaint();
	}

}
