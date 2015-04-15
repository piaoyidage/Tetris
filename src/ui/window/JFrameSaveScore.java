package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.FrameUtil;
import control.GameControl;

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
	private JLabel labelErrorMsg;
	
	private GameControl gameControl;
	
	public JFrameSaveScore(GameControl gameControl)
	{
		this.gameControl = gameControl;
		
		this.setTitle("玩家得分");
		this.setSize(256, 128);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.createComponent();
		this.createAction();
		FrameUtil.setFrameCenter(this);
		
	}
	
	private void createComponent()
	{
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelScore = new JLabel();
		north.add(labelScore);
		labelErrorMsg = new JLabel();
		labelErrorMsg.setForeground(Color.RED);
		north.add(labelErrorMsg);
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
	
	public void createAction()
	{
		btnOk.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = tfName.getText();
				if (name.length() > 16 || name == null || name.equals(""))
				{
					labelErrorMsg.setText("用户名错误");
				}
				else
				{
					setVisible(false);
					gameControl.saveScore(name);
				}
			}
		});
	}

	public void showSaveWindow(int point)
	{
		this.labelScore.setText("您的得分是：" + point);
		this.setVisible(true);
	}
}
