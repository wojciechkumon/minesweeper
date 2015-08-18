package minesweeper.view;

import java.awt.Font;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import minesweeper.controller.Controller;

public class DefaultView extends JFrame implements View {
	private static final long serialVersionUID = -1786179871578950490L;
	
	private MineButton[][] mineBtns = new MineButton[16][30];
	private int minesWidth;
	private int minesHeight;
	private Controller controller;
	private JLabel lblMinesLeft, lblWinOrLose;
	private JButton btnRestart, btnSettings;
	
	int minesLeft;
	
	public DefaultView() throws IOException {
		super("MineSweeper by wojtas626");
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setVisible(true);
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
	
	private void init() {
		setLayout(null);
		setSize(1024, 800);
		setLocation(100, 100);

		// TODO
		lblMinesLeft = new JLabel("Mines Left: " + 99);
		lblMinesLeft.setBounds(80, 20, 200, 40);
		lblMinesLeft.setFont(new Font("Arial", 0, 28));
		add(lblMinesLeft);

		lblWinOrLose = new JLabel("");
		lblWinOrLose.setBounds(300, 20, 150, 40);
		lblWinOrLose.setFont(new Font("Arial", 0, 28));
		add(lblWinOrLose);

		btnRestart = new JButton("RESTART");
		btnRestart.setBounds(450, 20, 100, 40);
//		btnRestart.addActionListener(this);
		add(btnRestart);

		btnSettings = new JButton("Settings");
		btnSettings.setBounds(570, 20, 100, 40);
//		btnSettings.addActionListener(this);
		add(btnSettings);
	}
	
	public void makeFields(int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mineBtns[i][j] = new MineButton(i, j, this);
				mineBtns[i][j].setLocation(80 + j * MineButton.WIDTH, 80 + i * MineButton.HEIGHT);

				add(mineBtns[i][j]);
			}
		}
		if (width > 12) {
			setSize(160 + MineButton.WIDTH * width, 140 + MineButton.HEIGHT * height);
		} else {
			setSize(160 + MineButton.WIDTH * 13, 140 + MineButton.HEIGHT * height);
		}

	}
	
	
	
	public int getMinesWidth() {
		return minesWidth;
	}

	public void setMinesWidth(int minesWidth) {
		this.minesWidth = minesWidth;
	}

	public int getMinesHeight() {
		return minesHeight;
	}

	public void setMinesHeight(int minesHeight) {
		this.minesHeight = minesHeight;
	}

	public Controller getController() {
		return controller;
	}

}
