package ui.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import control.GameControl;
import util.FrameUtil;

/**
 * Swing界面，控制按钮
 * @author Ben
 *
 */
public class JFrameConfig extends JFrame
{
	private static final Image IMG_PSP = new ImageIcon("data/psp.jpg").getImage();
	
	private static final String PATH = "data//config.dat";
	
	private JButton btnOk;
	private JButton btnCancel;
	private JButton btnExecute;
	
	private JLabel errorMsg;
	
	private TextCtrl[] keyTexts = new TextCtrl[8];
	/**
	 * 方法名
	 */
	private final String METHOD_NAMES[] = 
	{
		"moveRight", "moveUp", "moveLeft", "moveDown",
		"keyLeft", "keyUp", "keyRight", "keyDown"
	};
	
	private GameControl gameControl;
	
	public JFrameConfig(GameControl gameControl)
	{
		
		this.gameControl = gameControl;
		
		btnOk = new JButton("确定");
		btnCancel = new JButton("取消");
		btnExecute = new JButton("应用");
		
		errorMsg = new JLabel();
		
		this.initTexts(20, 60, 60, 20);
		this.setTitle("设置");
		// 边界 布局
		this.getContentPane().setLayout(new BorderLayout());;
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		
		this.setSize(715, 375);
		this.setResizable(false);
		// 居中显示
		FrameUtil.setFrameCenter(this);
		
	}

	private void initTexts(int x, int y, int w, int h)
	{
		for (int i = 0; i < 4; i++)
		{
			keyTexts[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 30;
		}
		y -= 120;
		x += 600;
		for (int i = 4; i < 8; i++)
		{
			keyTexts[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 30;
		}
		
		// 读取键位设置
		readConfig();
	}


	/**
	 * 选项卡
	 * @return
	 */
	private JTabbedPane createMainPanel()
	{
		JTabbedPane jt = new JTabbedPane();
		jt.add("键位控制", this.createControlPanel());
		jt.add("皮肤控制", new JLabel("皮肤"));
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
		
		for (TextCtrl text : keyTexts)
		{
			jp.add(text);
		}
		
		return jp;
	}

	private JPanel createButtonPanel()
	{
		// 流布局
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnOk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (writeConfig())
				{
					setVisible(false);
					gameControl.setOver();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				gameControl.setOver();
			}
			
		});
		btnExecute.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (writeConfig())
				{
					errorMsg.setText("");
				}
			}
		});
		errorMsg.setForeground(Color.red);
		jp.add(errorMsg);
		jp.add(btnOk);
		jp.add(btnCancel);
		jp.add(btnExecute);
		return jp;
	}
	
	
	/**
	 * 读取配置文件键位控制信息
	 */
	private void readConfig()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
			HashMap<Integer, String> keyMap = (HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer, String>> entrySet = keyMap.entrySet();
			for (Entry<Integer, String> e : entrySet)
			{
				for (TextCtrl text : keyTexts)
				{
					if (text.getMethodName().equals(e.getValue()))
					{
						text.setKeyCode(e.getKey());
					}
				}
			}
			
			ois.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 将键位配置写入文件
	 */
	private boolean writeConfig()
	{
		// 方法和键的映射
		HashMap<Integer, String> keyMap = new HashMap<Integer, String>();
		for (TextCtrl text : keyTexts)
		{
			if (text.getKeyCode() == 0)
			{
				this.errorMsg.setText("错误按键");
				return false;
			}
			keyMap.put(text.getKeyCode(), text.getMethodName());
		}
		if (keyMap.size() < 8)
		{
			this.errorMsg.setText("按键重复");
			return false;
		}
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keyMap);
			oos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
