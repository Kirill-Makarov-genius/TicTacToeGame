package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

import javax.swing.*;

public class AdvancedTicTacToe implements ActionListener{
	
	private Random random = new Random();
	private JFrame ticTacToe = new JFrame();
	private JPanel titlePanel = new JPanel();
	private JPanel boardPanel = new JPanel();
	private JLabel title = new JLabel();
	private JButton[] buttons = new JButton[9];
	private JPanel menuPanel = new JPanel();
	private JButton restartButton = new JButton();
	private JLabel textTimer = new JLabel();
	private int secondsOfGame = 0;
	private int mlsecondsOfGame = 0;
	boolean player1Turn;
	private Timer timer;
	
	
	public AdvancedTicTacToe(){
		
		//JFrame
		ticTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ticTacToe.setSize(1200, 800);
		ticTacToe.getContentPane().setBackground(new Color(50, 50, 50));
		ticTacToe.setVisible(true);
		titlePanel.setLayout(new BorderLayout());
		
		//Title Panel
		title.setBackground(new Color(25, 25, 25));
		title.setForeground(new Color(25, 255, 100));
		title.setFont(new Font("Ink Free", Font.BOLD, 75));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setText("Tic-tac-toe");
		title.setOpaque(true);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);
		
		//Board Panel
		boardPanel.setLayout(new GridLayout(3, 3));
		boardPanel.setBackground(new Color(200, 200, 50));
		
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			boardPanel.add(buttons[i]);
			
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setEnabled(false);
		}
		//Menu Panel
		menuPanel.setLayout(new FlowLayout());
		
		textTimer.setText("Timer");;
		timer = new Timer(100, new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				textTimer.setText(String.valueOf(secondsOfGame + "." + mlsecondsOfGame + " seconds"));
				if (mlsecondsOfGame == 9 ) {
					secondsOfGame++;
					mlsecondsOfGame = 0;
				}
				mlsecondsOfGame++;
			}
		});
		restartButton.setText("Restart Game");
		restartButton.addActionListener(this);
		
		menuPanel.add(restartButton);
		menuPanel.add(textTimer);
		
		
		titlePanel.add(title);
		ticTacToe.add(titlePanel, BorderLayout.NORTH);
		ticTacToe.add(boardPanel);
		

		ticTacToe.add(boardPanel);
		ticTacToe.add(menuPanel, BorderLayout.SOUTH);
		playerTurn();

		
	}
	
	public void playerTurn(){
		
		
		try {
			Thread.sleep(2000);

			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(true);
		}
		timer.start();
		
		if (random.nextInt(2) == 0) {
			player1Turn = true;
			title.setText("X turn");
		} else {
			player1Turn = false;
			title.setText("O turn");
		}
		
		
	}
	

	
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getSource() instanceof JButton) {
			JButton clickedButton = (JButton) e.getSource();
			if (clickedButton.getText() == "") {
				if(player1Turn) {
					clickedButton.setForeground(new Color(255, 0, 0));
					clickedButton.setText("X");
					player1Turn = false;
					title.setText("O turn");
				}
				else {
					clickedButton.setForeground(new Color(0, 0, 255));
					clickedButton.setText("O");
					player1Turn = true;
					title.setText("X turn");
				}
				check();
			}
			// Restart Game
			if (e.getSource() == restartButton) {
				ticTacToe.remove(boardPanel);
				boardPanel = new JPanel();
				boardPanel.setLayout(new GridLayout(3, 3));
				boardPanel.setBackground(new Color(200, 200, 50));
				for(int i = 0; i < 9; i++) {
					buttons[i] = new JButton();
					boardPanel.add(buttons[i]);
					
					buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
					buttons[i].setFocusable(false);
					buttons[i].addActionListener(this);
					buttons[i].setEnabled(false);
				}
				ticTacToe.add(boardPanel);
				title.setText("Game is rebooting");
				secondsOfGame = 0;
				mlsecondsOfGame = 0;
				playerTurn();
			}
		}

	}
	
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		for (int i=0; i<9; i++) {
//			if(e.getSource() == buttons[i]) {
//				if (buttons[i].getText() == "") {
//					if (player1Turn) {
//						buttons[i].setForeground(new Color(255, 0, 0));
//						buttons[i].setText("X");
//						player1Turn = false;
//						title.setText("O turn");
//						check();	
//					}
//					else {
//						buttons[i].setForeground(new Color(0, 0, 255));
//						buttons[i].setText("O");
//						player1Turn = true;
//						title.setText("X turn");
//						check();
//					}
//				}
//			}
//		}
//		
//	}
	
	
	
	
	public void check() {
		
		// X win conditions
		//Horizontal
		if (
				buttons[0].getText() == "X" &&
				buttons[1].getText() == "X" &&
				buttons[2].getText() == "X"
				){
			xWins(0, 1, 2);
		}
		else if (
				buttons[3].getText() == "X" &&
				buttons[4].getText() == "X" &&
				buttons[5].getText() == "X"
				){
			xWins(3, 4, 5);
		}
		else if (
				buttons[6].getText() == "X" &&
				buttons[7].getText() == "X" &&
				buttons[8].getText() == "X"
				){
			xWins(6, 7, 8);
		}
		// Vertical
		else if (
				buttons[0].getText() == "X" &&
				buttons[3].getText() == "X" &&
				buttons[6].getText() == "X"
				){
			xWins(0, 3, 6);
		}
		else if (
				buttons[1].getText() == "X" &&
				buttons[4].getText() == "X" &&
				buttons[7].getText() == "X"
				){
			xWins(1, 4, 7);
		}
		else if (
				buttons[2].getText() == "X" &&
				buttons[5].getText() == "X" &&
				buttons[8].getText() == "X"
				){
			xWins(2, 5, 8);
		}
		// Diagonal
		else if (
				buttons[0].getText() == "X" &&
				buttons[4].getText() == "X" &&
				buttons[8].getText() == "X"
				){
			xWins(0, 4, 8);
		}
		else if (
				buttons[2].getText() == "X" &&
				buttons[4].getText() == "X" &&
				buttons[6].getText() == "X"
				){
			xWins(2, 4, 6);
		}
		
		
		// O win conditions
		//Horizontal
		if (
				buttons[0].getText() == "O" &&
				buttons[1].getText() == "O" &&
				buttons[2].getText() == "O"
				){
			xWins(0, 1, 2);
		}
		else if (
				buttons[3].getText() == "O" &&
				buttons[4].getText() == "O" &&
				buttons[5].getText() == "O"
				){
			xWins(3, 4, 5);
		}
		else if (
				buttons[6].getText() == "O" &&
				buttons[7].getText() == "O" &&
				buttons[8].getText() == "O"
				){
			xWins(6, 7, 8);
		}
		// Vertical
		else if (
				buttons[0].getText() == "O" &&
				buttons[3].getText() == "O" &&
				buttons[6].getText() == "O"
				){
			xWins(0, 3, 6);
		}
		else if (
				buttons[1].getText() == "O" &&
				buttons[4].getText() == "O" &&
				buttons[7].getText() == "O"
				){
			xWins(1, 4, 7);
		}
		else if (
				buttons[2].getText() == "O" &&
				buttons[5].getText() == "O" &&
				buttons[8].getText() == "O"
				){
			xWins(2, 5, 8);
		}
		// Diagonal
		else if (
				buttons[0].getText() == "O" &&
				buttons[4].getText() == "O" &&
				buttons[8].getText() == "O"
				){
			xWins(0, 4, 8);
		}
		else if (
				buttons[2].getText() == "O" &&
				buttons[4].getText() == "O" &&
				buttons[6].getText() == "O"
				){
			xWins(2, 4, 6);
		}
		
		
		
		
		
		
		
	}

	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		title.setText("X wins");
		timer.stop();
	}
	
	public void yWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		title.setText("O wins");
		timer.stop();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
