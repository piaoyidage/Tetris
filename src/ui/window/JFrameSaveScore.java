package ui.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.FrameUtil;

/**
 * 
 * @author Ben
 *
 */
public class JFrameSaveScore extends JFrame
{
	private JButton btnOk;
	private JTextField tfName;
	private JLabel labelScore;
	
	public JFrameSaveScore()
	{
		this.setTitle("玩家得分");
		this.setSize(256, 128);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.createComponent();
		
		FrameUtil.setFrameCenter(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void createComponent()
	{
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelScore = new JLabel("您的得分是:8888");
		north.add(labelScore);
		this.add(north, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center.add(new JLabel("请输入您的姓名："));
		tfName = new JTextField(10);
		center.add(tfName);
		this.add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnOk = new JButton("确定");
		south.add(btnOk);
		this.add(south, BorderLayout.SOUTH);
		
	}

	public static void main(String[] args)
	{
		new JFrameSaveScore();
	}
}
