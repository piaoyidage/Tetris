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
 * Swing���棬���ư�ť
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
	 * ������
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
		
		btnOk = new JButton("ȷ��");
		btnCancel = new JButton("ȡ��");
		btnExecute = new JButton("Ӧ��");
		
		errorMsg = new JLabel();
		
		this.initTexts(20, 60, 60, 20);
		this.setTitle("����");
		// �߽� ����
		this.getContentPane().setLayout(new BorderLayout());;
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		
		this.setSize(715, 375);
		this.setResizable(false);
		// ������ʾ
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
		
		// ��ȡ��λ����
		readConfig();
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
		
		for (TextCtrl text : keyTexts)
		{
			jp.add(text);
		}
		
		return jp;
	}

	private JPanel createButtonPanel()
	{
		// ������
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
	 * ��ȡ�����ļ���λ������Ϣ
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
	 * ����λ����д���ļ�
	 */
	private boolean writeConfig()
	{
		// �����ͼ���ӳ��
		HashMap<Integer, String> keyMap = new HashMap<Integer, String>();
		for (TextCtrl text : keyTexts)
		{
			if (text.getKeyCode() == 0)
			{
				this.errorMsg.setText("���󰴼�");
				return false;
			}
			keyMap.put(text.getKeyCode(), text.getMethodName());
		}
		if (keyMap.size() < 8)
		{
			this.errorMsg.setText("�����ظ�");
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
