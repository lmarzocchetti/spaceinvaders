package Invaders;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class SpaceInvader extends JFrame {
	private static final long serialVersionUID = 1L;
	GamePanel gp;
	GameOverPanel gop;
	WinPanel wp;
	JLayeredPane topPanel;
	
	/*
	 * Istance a new GamePanel, GameOverPanel and WinPanel and links all for call the methods in the other class
	 * Add GamePanel, GameOverPanel and WinPanel to a OverlayLayout in different positions, 0 for game and 1 for win or gameover
	 * do normal things 
	 */
	public SpaceInvader() {
		super();
		
		gp = new GamePanel();
		gop = new GameOverPanel();
		wp = new WinPanel();
		gop.setGamePanel(gp);
		gp.setGameOverPanel(gop);
		gp.setWinPanel(wp);
		wp.setGamePanel(gp);
		
		topPanel = new JLayeredPane();
		topPanel.setLayout(new OverlayLayout(topPanel));
		
		topPanel.add(gp, 0);
		topPanel.add(wp, 1);
		topPanel.add(gop, 1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Space Invaders");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - 800)/2, (screenSize.height - 600)/2);
        
		setResizable(false);
		this.setSize(800, 600);
		this.setContentPane(topPanel);
		this.setVisible(true);
	}
	
}
