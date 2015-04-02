package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import config.ConfigFactory;
import config.GameConfig;

/**
 * 游戏Panel
 * @author Ben
 *
 */
public class JPanelGame extends JPanel
{
	private Layer layers[] = null;
	
	public JPanelGame()
	{
		// TODO 将数字或者字符串定义成常量，或者写入配置文件
		layers = new Layer[]
				{
					new LayerBackground(0, 0, 1174, 660),
					new LayDatabase(40, 32, 340, 282),
					new LayerDisk(40, 346, 340, 282),
					new LayerGame(417, 32, 340, 596),
					new LayerButton(794, 32, 340, 125),
					new LayerNext(794, 189, 165, 125),
					new LayerLevel(969, 189, 165, 125),
					new LayerPoint(794, 346, 340, 125),
					new LayerAbout(794, 503, 340, 125),
				};
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		for(int i = 0; i < layers.length; i++)
		{
			layers[i].paint(g);
		}
	}
}
