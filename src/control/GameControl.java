package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import service.GameTetris;
import ui.JFrameGame;
import ui.JPanelGame;
import ui.config.JFrameConfig;
import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dto.GameDto;


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
	/**
	 * 游戏数据
	 */
	private GameDto dto;
	
	private JFrameConfig userConfig;
	
	public GameControl()
	{
		// 创建游戏数据源
		this.dto = new GameDto();
		// 创建游戏逻辑层（连接数据源）
		this.gameService = new GameTetris(dto);
		// 从数据接口A获得数据库记录
		this.dataA = this.createDataObject(GameConfig.getDataConfig().getDataA());
		// 设置数据库记录到游戏
		this.dto.setDatabaseRecord(dataA.loadData());
		// 从数据接口B获取本地记录
		this.dataB = this.createDataObject(GameConfig.getDataConfig().getDataB());
		// 设置本地记录到游戏
		this.dto.setLocalRecord(dataB.loadData());
		// 创建游戏面板（连接数据源 连接游戏控制器）
		this.panelGame = new JPanelGame(this, dto);
		
		this.setActionConfig();
		// 初始化用户控制窗口
		this.userConfig = new JFrameConfig(this);
		
		// 创建游戏界面，安装游戏面板
		new JFrameGame(this.panelGame).setTitle("俄罗斯方块");
	}

	private void setActionConfig()
	{
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

	/**
	 * 显示玩家控制窗口
	 */
	public void showUserConfig()
	{
		// 窗口置顶
		this.userConfig.setAlwaysOnTop(true);
		this.userConfig.setVisible(true);
	}

	public void setOver()
	{
		this.panelGame.repaint();
		this.setActionConfig();
	}

	/**
	 * 开始
	 */
	public void start()
	{
		this.panelGame.setButtonStatus(false);
		this.gameService.startMainThread();
		this.panelGame.repaint();
	}

}
