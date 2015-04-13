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
 * ��ϷPanel
 * @author Ben
 *
 */
public class JPanelGame extends JPanel
{
	private List<Layer> layers = null;
	
	private static final ImageIcon IMG_START = new ImageIcon("graphics/picture/start.png"); 
	private static final ImageIcon IMG_SET = new ImageIcon("graphics/picture/set.png"); 
	
	/**
	 * �û����ð�ť
	 */
	private JButton btnUserConfig;

	/**
	 * ��ʼ��ť
	 */
	private JButton btnStart;

	private GameControl gameControl;
	
	public JPanelGame(GameControl gameControl, GameDto dto)
	{
		this.gameControl = gameControl;
		
		this.setLayout(null);
		// ��ʼ�����
		initConponent();
		// ��ʼ����
		initLayer(dto);
		// ��װ���̼���
		this.addKeyListener(new PlayerControl(gameControl));
	}
	

	private void initConponent()
	{
		// ��ʼ����ť
		btnStart = new JButton(IMG_START);
		// ��cfg.xml��ȡ
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
	 * ��ʼ����
	 */
	private void initLayer(GameDto dto)
	{
		try
		{
			// �����Ϸ������Ϣ
			FrameConfig frameConfig = GameConfig.getFrameConfig();
			// ������
			List<LayerConfig> layersConfig = frameConfig.getLayersConfig();
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
	
	/**
	 * ���ð�ť״̬���Ƿ�ɵ��
	 * @param status
	 */
	public void setButtonStatus(boolean status)
	{
		this.btnStart.setEnabled(status);
		this.btnUserConfig.setEnabled(status);
	}
}
