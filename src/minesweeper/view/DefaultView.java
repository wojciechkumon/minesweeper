package minesweeper.view;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import minesweeper.controller.Controller;
import minesweeper.model.ClickState;
import minesweeper.model.Field;
import minesweeper.model.Model;
import minesweeper.model.Range;
import minesweeper.model.UpdateBox;

public class DefaultView extends JFrame implements View {
	private static final long serialVersionUID = -1786179871578950490L;
	
	private MineButton[][] mineBtns = new MineButton[16][30];
	private int minesWidth;
	private int minesHeight;
	private Controller controller;
	private JLabel lblMinesLeft, lblWinOrLose;
	private JButton btnRestart, btnSettings;
	
	private int minesLeft;
	
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
		if (arg != null) {
			Model model = (Model)o;
			UpdateBox updateBox = (UpdateBox)arg;
			if (updateBox.isNeededRestart()) {
				updateRestart(model);
			} else {
				if (updateBox.isMinesLeftToUpdate())
					updateMinesLeft(model);
				if (updateBox.getFieldsToUpdate() != null)
					updateFields(model, updateBox.getFieldsToUpdate());
				if (updateBox.isLose())
					lose();
				else if (updateBox.isWin())
					win();
			}
		}
	}

	
	private void win() {
		// TODO Auto-generated method stub
		System.out.println("win");
	}

	private void lose() {
		// TODO Auto-generated method stub
		System.out.println("lose");
	}

	private void updateFields(Model model, ArrayList<Field> fieldsToUpdate) {
		for (Field field: fieldsToUpdate) {
			updateField(model, field);
		}
	}
	
	private void updateField(Model model, Field fieldToUpdate) {
		if (fieldToUpdate.getClickState() == ClickState.CLICKED) {
			updateClickedField(fieldToUpdate);
		} else if (fieldToUpdate.getClickState() == ClickState.FLAG) {
			updateFlaggedField(fieldToUpdate);
		} else if (fieldToUpdate.getClickState() == ClickState.NOT_CLICKED) {
			updateNotClickedField(fieldToUpdate);
		}
	}

	private void updateClickedField(Field fieldToUpdate) {
		MineButton mineBtn = get(fieldToUpdate.getX(), fieldToUpdate.getY());
		Backgrounds bg = Backgrounds.getInstace();
		
		mineBtn.setImage(bg.getMinesCounterImage(fieldToUpdate.getMineState().getSurroundingMines()));
		mineBtn.repaint();
	}
	
	private void updateFlaggedField(Field fieldToUpdate) {
		MineButton mineBtn = get(fieldToUpdate.getX(), fieldToUpdate.getY());
		Backgrounds bg = Backgrounds.getInstace();

		if ((mineBtn.getMousePosition() != null)
				&& Range.isPointInBounds(mineBtn.getMousePosition(), 0, 0,
						MineButton.WIDTH, MineButton.HEIGHT))
			mineBtn.setImage(bg.hoveredFlag);
		else
			mineBtn.setImage(bg.flag);
		mineBtn.repaint();
	}
	
	private void updateNotClickedField(Field fieldToUpdate) {
		MineButton mineBtn = get(fieldToUpdate.getX(), fieldToUpdate.getY());
		Backgrounds bg = Backgrounds.getInstace();
		
		if ((mineBtn.getMousePosition() != null)
				&& Range.isPointInBounds(mineBtn.getMousePosition(), 0, 0,
						MineButton.WIDTH, MineButton.HEIGHT))
			mineBtn.setImage(bg.hoveredField);
		else
			mineBtn.setImage(bg.field);
		mineBtn.repaint();
	}

	private void updateMinesLeft(Model model) {
		minesLeft = model.getMinesLeft();
		lblMinesLeft.setText(Integer.toString(minesLeft));
	}

	private void updateRestart(Model model) {
		// TODO Auto-generated method stub
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

	public int getMinesLeft() {
		return minesLeft;
	}

	public void setMinesLeft(int minesLeft) {
		this.minesLeft = minesLeft;
	}
	
	public MineButton get(int x, int y) {
		return mineBtns[x][y];
	}

}
