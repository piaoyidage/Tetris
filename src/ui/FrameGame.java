package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ��ϷFrame
 * @author Ben
 *
 */
public class FrameGame extends JFrame
{
	public FrameGame()
	{
		// 1.���ñ���
		this.setTitle("Java����˹����");
		// 2.���ùر�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3.���ô��ڴ�С
		this.setSize(1200, 700);
		// 4.���ò������ڴ�С����
		this.setResizable(false);
		// 5.ʹ��Toolkit����������λ��
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = (d.width - this.getWidth()) / 2;
		int y = (d.height - this.getHeight()) / 2 - 18;
		this.setLocation(x, y);
		// 6.����panel
		this.setContentPane(new PanelGame());
	}
}
