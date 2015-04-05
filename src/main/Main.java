package main;

import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;
import config.GameConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

/**
 * 程序入口
 * @author Ben
 *
 */
public class Main
{
	public static void main(String[] args)
	{
		// 创建游戏数据源
		GameDto dto = new GameDto();
		// 创建游戏面板（连接数据源）
		JPanelGame panel = new JPanelGame(dto);
		// 创建游戏逻辑层（连接数据源）
		GameService gameService = new GameService(dto);
		// 创建游戏控制器（连接游戏面板和游戏逻辑）
		GameControl gameControl = new GameControl(panel, gameService);
		// 创建玩家控制器（连接游戏控制器）
		PlayerControl playControl = new PlayerControl(gameControl);
		// 安装玩家控制器
		panel.setGameControl(playControl);
		// 创建游戏界面，安装游戏面板
		new JFrameGame(panel).setTitle("俄罗斯方块");;
	}
}
