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
 * 游戏Panel
 * @author Ben
 *
 */
public class JPanelGame extends JPanel
{
	private List<Layer> layers = null;
	
	// 数据源
	private GameDto dto = null;
	
	public JPanelGame(GameDto dto)
	{
		this.dto = dto;
		// 初始化组件
		initConponent();
		// 初始化层
		initLayer();
		
	}
	
	/**
	 * 安装玩家控制器
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
	 * 初始化层
	 */
	private void initLayer()
	{
		try
		{
			// 获得游戏配置信息
			GameConfig gameConfig = ConfigFactory.getGameConfig();
			// 层配置
			List<LayerConfig> layersConfig = gameConfig.getLayersConfig();
			// 创建层集合
			layers = new ArrayList<Layer>(layersConfig.size());
			// 遍历读取
			for (LayerConfig layerConfig : layersConfig)
			{
				// 根据反射加载不同的类
				Class<?> cls = Class.forName(layerConfig.getClassName());
				// 选择构造函数
				Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
				// 生成对象
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
	 * 绘制游戏界面
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		// 调用基类方法
		super.paintComponent(g);
		// 绘制不同的层
		for(int i = 0; i < layers.size(); layers.get(i++).paint(g));
		// 返回焦点
		this.requestFocus();
		 
	}
}
