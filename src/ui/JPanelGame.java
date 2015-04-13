package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
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
	
	private static final ImageIcon IMG_START = new ImageIcon("graphics/picture/start.png"); 
	private static final ImageIcon IMG_SET = new ImageIcon("graphics/picture/set.png"); 
	
	/**
	 * 用户配置按钮
	 */
	private JButton btnUserConfig;

	/**
	 * 开始按钮
	 */
	private JButton btnStart;

	private GameControl gameControl;
	
	public JPanelGame(GameControl gameControl, GameDto dto)
	{
		this.gameControl = gameControl;
		
		this.setLayout(null);
		// 初始化组件
		initConponent();
		// 初始化层
		initLayer(dto);
		// 安装键盘监听
		this.addKeyListener(new PlayerControl(gameControl));
	}
	

	private void initConponent()
	{
		// 初始化按钮
		btnStart = new JButton(IMG_START);
		// 从cfg.xml获取
		btnStart.setBounds(GameConfig.getFrameConfig().getButtonConfig().getStartX(), 
						   GameConfig.getFrameConfig().getButtonConfig().getStartY(),
						   GameConfig.getFrameConfig().getButtonConfig().getStartW(),
						   GameConfig.getFrameConfig().getButtonConfig().getStartH());
		this.add(btnStart);
		btnStart.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameControl.start();
			}
		});
		
		btnUserConfig = new JButton(IMG_SET);
		btnUserConfig.setBounds(GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(), 
				   				GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(),
				   				GameConfig.getFrameConfig().getButtonConfig().getUserConfigW(),
				   				GameConfig.getFrameConfig().getButtonConfig().getUserConfigH());
		this.add(btnUserConfig);
		btnUserConfig.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				gameControl.showUserConfig();
			}
		});
		
	}
	
	/**
	 * 初始化层
	 */
	private void initLayer(GameDto dto)
	{
		try
		{
			// 获得游戏配置信息
			FrameConfig frameConfig = GameConfig.getFrameConfig();
			// 层配置
			List<LayerConfig> layersConfig = frameConfig.getLayersConfig();
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
				layer.setDto(dto);
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
	
	/**
	 * 设置按钮状态，是否可点击
	 * @param status
	 */
	public void setButtonStatus(boolean status)
	{
		this.btnStart.setEnabled(status);
		this.btnUserConfig.setEnabled(status);
	}
}
