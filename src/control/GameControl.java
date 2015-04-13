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
	/**
	 * ��Ϸ����
	 */
	private GameDto dto;
	
	private JFrameConfig userConfig;
	
	public GameControl()
	{
		// ������Ϸ����Դ
		this.dto = new GameDto();
		// ������Ϸ�߼��㣨��������Դ��
		this.gameService = new GameTetris(dto);
		// �����ݽӿ�A������ݿ��¼
		this.dataA = this.createDataObject(GameConfig.getDataConfig().getDataA());
		// �������ݿ��¼����Ϸ
		this.dto.setDatabaseRecord(dataA.loadData());
		// �����ݽӿ�B��ȡ���ؼ�¼
		this.dataB = this.createDataObject(GameConfig.getDataConfig().getDataB());
		// ���ñ��ؼ�¼����Ϸ
		this.dto.setLocalRecord(dataB.loadData());
		// ������Ϸ��壨��������Դ ������Ϸ��������
		this.panelGame = new JPanelGame(this, dto);
		
		this.setActionConfig();
		// ��ʼ���û����ƴ���
		this.userConfig = new JFrameConfig(this);
		
		// ������Ϸ���棬��װ��Ϸ���
		new JFrameGame(this.panelGame).setTitle("����˹����");
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

	/**
	 * ��ʾ��ҿ��ƴ���
	 */
	public void showUserConfig()
	{
		// �����ö�
		this.userConfig.setAlwaysOnTop(true);
		this.userConfig.setVisible(true);
	}

	public void setOver()
	{
		this.panelGame.repaint();
		this.setActionConfig();
	}

	/**
	 * ��ʼ
	 */
	public void start()
	{
		this.panelGame.setButtonStatus(false);
		this.gameService.startMainThread();
		this.panelGame.repaint();
	}

}
