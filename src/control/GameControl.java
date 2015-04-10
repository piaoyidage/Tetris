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
	 * key 和 action的映射
	 */
	private Map<Integer, Method> keyActions;
	
	/**
	 * 游戏界面层
	 */
	private JPanelGame panelGame;
	/**
	 * 游戏逻辑层
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
		
		// TODO: 测试
		// 从数据接口A获得数据库记录
		this.dataA = this.createDataObject(GameConfig.getDataConfig().getDataA());
		// 设置数据库记录到游戏
		this.gameService.setDatabaseRecord(dataA.loadData());
		// 从数据接口B获取本地记录
		this.dataB = this.createDataObject(GameConfig.getDataConfig().getDataB());
		// 设置本地记录到游戏
		this.gameService.setLocalRecord(dataB.loadData());
	}

	/**
	 * 创建数据对象
	 * @param dataConfig 数据配置信息
	 * @return
	 */
	private Data createDataObject(DataInterfaceConfig dataConfig)
	{
		try
		{
			// 获取类对象
			Class<?> cls = Class.forName(dataConfig.getClassName());
			// 获取构造器
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
//	 * 旋转
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
//	// TODO：测试专用
//	public void test()
//	{
//		this.gameService.test();
//		this.panelGame.repaint();
//	}

	/**
	 * 根据keyCode选择移动或者旋转
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
