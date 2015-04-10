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
		// ���÷�����
		this.methodName = methodName;
		// ����λ��
		this.setBounds(x, y, width, height);
		// �󶨼�����
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
