package ui.config;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class TextCtrl extends JTextField
{
	private int keyCode;
	private final String methodName;
	
	public TextCtrl(int x, int y, int width, int height, String methodName)
	{
		// 设置方法名
		this.methodName = methodName;
		// 设置位置
		this.setBounds(x, y, width, height);
		// 绑定监听器
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				setKeyCode(e.getKeyCode());
			}
		});
	}

	public int getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(keyCode));
	}

	public String getMethodName()
	{
		return methodName;
	}
	
	
}
