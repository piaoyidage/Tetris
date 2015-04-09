package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;

/**
 * ��ϷFrame
 * @author Ben
 *
 */
public class JFrameGame extends JFrame
{
	public JFrameGame(JPanelGame panel)
	{
		// �����Ϸ���� ��������Ϸ
		FrameConfig frameConfig = GameConfig.getFrameConfig();
		// 1.���ñ���
		this.setTitle(frameConfig.getTitle());
		// 2.���ùر�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3.TODO ���ô��ڴ�С
		this.setSize(frameConfig.getWidth(), frameConfig.getHeight());
		// 4.���ò������ڴ�С����
		this.setResizable(false);
		// 5.ʹ��Toolkit����������λ��
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = d.width - this.getWidth() >> 1;
		int y = (d.height - this.getHeight() >> 1) - frameConfig.getWindowUp();
		this.setLocation(x, y);
		// 6.����panel
		this.setContentPane(panel);
		// 7.���ÿɼ�
		this.setVisible(true);
	}
}
