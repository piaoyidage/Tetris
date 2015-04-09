package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;

/**
 * 游戏Frame
 * @author Ben
 *
 */
public class JFrameGame extends JFrame
{
	public JFrameGame(JPanelGame panel)
	{
		// 获得游戏配置 来设置游戏
		FrameConfig frameConfig = GameConfig.getFrameConfig();
		// 1.设置标题
		this.setTitle(frameConfig.getTitle());
		// 2.设置关闭属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3.TODO 设置窗口大小
		this.setSize(frameConfig.getWidth(), frameConfig.getHeight());
		// 4.设置不允许窗口大小调整
		this.setResizable(false);
		// 5.使用Toolkit来调整窗口位置
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = d.width - this.getWidth() >> 1;
		int y = (d.height - this.getHeight() >> 1) - frameConfig.getWindowUp();
		this.setLocation(x, y);
		// 6.设置panel
		this.setContentPane(panel);
		// 7.设置可见
		this.setVisible(true);
	}
}
