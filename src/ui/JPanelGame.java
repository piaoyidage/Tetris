package ui;

import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import control.PlayerControl;
import dto.GameDto;

/**
 * ��ϷPanel
 * @author Ben
 *
 */
public class JPanelGame extends JPanel
{
	private List<Layer> layers = null;
	
	// ����Դ
	private GameDto dto = null;
	
	public JPanelGame(GameDto dto)
	{
		this.dto = dto;
		// ��ʼ�����
		initConponent();
		// ��ʼ����
		initLayer();
		
	}
	
	/**
	 * ��װ��ҿ�����
	 * @param playControl
	 */
	public void setGameControl(PlayerControl playControl)
	{
		this.addKeyListener(playControl);
	}

	private void initConponent()
	{
	}
	
	/**
	 * ��ʼ����
	 */
	private void initLayer()
	{
		try
		{
			// �����Ϸ������Ϣ
			GameConfig gameConfig = ConfigFactory.getGameConfig();
			// ������
			List<LayerConfig> layersConfig = gameConfig.getLayersConfig();
			// �����㼯��
			layers = new ArrayList<Layer>(layersConfig.size());
			// ������ȡ
			for (LayerConfig layerConfig : layersConfig)
			{
				// ���ݷ�����ز�ͬ����
				Class<?> cls = Class.forName(layerConfig.getClassName());
				// ѡ���캯��
				Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
				// ���ɶ���
				Layer layer = (Layer)ctr.newInstance(layerConfig.getX(), layerConfig.getY(), layerConfig.getW(), layerConfig.getH());
				layer.setDto(this.dto);
				layers.add(layer);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 
	 * ������Ϸ����
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		// ���û��෽��
		super.paintComponent(g);
		// ���Ʋ�ͬ�Ĳ�
		for(int i = 0; i < layers.size(); layers.get(i++).paint(g));
		// ���ؽ���
		this.requestFocus();
		 
	}
}
