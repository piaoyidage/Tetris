package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 游戏Frame
 * @author Ben
 *
 */
public class FrameGame extends JFrame
{
	public FrameGame()
	{
		// 1.设置标题
		this.setTitle("Java俄罗斯方块");
		// 2.设置关闭属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3.设置窗口大小
		this.setSize(1200, 700);
		// 4.设置不允许窗口大小调整
		this.setResizable(false);
		// 5.使用Toolkit来调整窗口位置
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = (d.width - this.getWidth()) / 2;
		int y = (d.height - this.getHeight()) / 2 - 18;
		this.setLocation(x, y);
		// 6.设置panel
		this.setContentPane(new PanelGame());
	}
}
