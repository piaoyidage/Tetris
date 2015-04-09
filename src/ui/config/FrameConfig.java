package ui.config;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import util.FrameUtil;

/**
 * Swing���棬���ư�ť
 * @author Ben
 *
 */
public class FrameConfig extends JFrame
{
	private static final Image IMG_PSP = new ImageIcon("data/psp.jpg").getImage();
	
	private JButton btnOk;
	private JButton btnCancel;
	private JButton btnExecute;
	
	TextCtrl tRight = new TextCtrl(20, 55, 60, 20);
	TextCtrl tUp = new TextCtrl(20, 95, 60, 20);
	TextCtrl tLeft = new TextCtrl(20, 125, 60, 20);
	TextCtrl tDown = new TextCtrl(20, 155, 60, 20);
	
	public FrameConfig()
	{
		btnOk = new JButton("ȷ��");
		btnCancel = new JButton("ȡ��");
		btnExecute = new JButton("Ӧ��");
		
		// �߽� ����
		this.getContentPane().setLayout(new BorderLayout());;
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		
		this.setSize(715, 375);
		this.setResizable(false);
		// ������ʾ
		FrameUtil.setFrameCenter(this);
		
		// TODO:��ʱ��ʾ
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * ѡ�
	 * @return
	 */
	private JTabbedPane createMainPanel()
	{
		JTabbedPane jt = new JTabbedPane();
		jt.add("��λ����", this.createControlPanel());
		jt.add("Ƥ������", new JLabel("Ƥ��"));
		return jt;
	}

	private JPanel createControlPanel()
	{
		JPanel jp = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		jp.setLayout(null);
		
		jp.add(tRight);
		jp.add(tUp);
		jp.add(tLeft);
		jp.add(tDown);
		
		return jp;
	}

	private JPanel createButtonPanel()
	{
		// ������
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(btnOk);
		jp.add(btnCancel);
		jp.add(btnExecute);
		return jp;
	}
	
	public static void main(String[] args)
	{
		new FrameConfig();
	}
}
