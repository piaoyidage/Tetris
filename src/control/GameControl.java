package control;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import service.GameTetris;
import ui.JPanelGame;
import ui.config.TextCtrl;
import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;


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
	 * key �� action��ӳ��
	 */
	private Map<Integer, Method> keyActions;
	
	/**
	 * ��Ϸ�����
	 */
	private JPanelGame panelGame;
	/**
	 * ��Ϸ�߼���
	 */
	private GameTetris gameService;
	
	public GameControl(JPanelGame jpanelGame, GameTetris gameService)
	{
		
		this.panelGame = jpanelGame;
		this.gameService = gameService;
		
		keyActions = new HashMap<Integer, Method>();
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data//config.dat"));
			HashMap<Integer, String> keyMap = (HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer, String>> entrySet = keyMap.entrySet();
			for (Entry<Integer, String> e : entrySet)
			{
				keyActions.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
			ois.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
//		try
//		{
//			keyActions.put(KeyEvent.VK_UP, this.gameService.getClass().getMethod("moveUp"));
//			keyActions.put(KeyEvent.VK_DOWN, this.gameService.getClass().getMethod("moveDown"));
//			keyActions.put(KeyEvent.VK_LEFT, this.gameService.getClass().getMethod("moveLeft"));
//			keyActions.put(KeyEvent.VK_RIGHT, this.gameService.getClass().getMethod("moveRight"));
//			keyActions.put(KeyEvent.VK_A, this.gameService.getClass().getMethod("test"));
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		
		// TODO: ����
		// �����ݽӿ�A������ݿ��¼
		this.dataA = this.createDataObject(GameConfig.getDataConfig().getDataA());
		// �������ݿ��¼����Ϸ
		this.gameService.setDatabaseRecord(dataA.loadData());
		// �����ݽӿ�B��ȡ���ؼ�¼
		this.dataB = this.createDataObject(GameConfig.getDataConfig().getDataB());
		// ���ñ��ؼ�¼����Ϸ
		this.gameService.setLocalRecord(dataB.loadData());
	}

	/**
	 * �������ݶ���
	 * @param dataConfig ����������Ϣ
	 * @return
	 */
	private Data createDataObject(DataInterfaceConfig dataConfig)
	{
		try
		{
			// ��ȡ�����
			Class<?> cls = Class.forName(dataConfig.getClassName());
			// ��ȡ������
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			return (Data) ctr.newInstance(dataConfig.getParam());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * ��ת
//	 */
//	public void moveUp()
//	{
//		// TODO
//		this.gameService.moveUp();
//		this.panelGame.repaint();
//	}
//
//	public void moveDown()
//	{
//		this.gameService.moveDown();
//		this.panelGame.repaint();
//	}
//
//	public void moveLeft()
//	{
//		this.gameService.moveLeft();
//		this.panelGame.repaint();
//	}
//
//	public void moveRight()
//	{
//		this.gameService.moveRight();
//		this.panelGame.repaint();
//	}
//
//	// TODO������ר��
//	public void test()
//	{
//		this.gameService.test();
//		this.panelGame.repaint();
//	}

	/**
	 * ����keyCodeѡ���ƶ�������ת
	 * @param keyCode
	 */
	public void actionByKeyCode(int keyCode)
	{
		try
		{
			if (keyActions.containsKey(keyCode))
			{
				keyActions.get(keyCode).invoke(this.gameService);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		panelGame.repaint();
	}

}
