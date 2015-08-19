package minesweeper.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import minesweeper.controller.Controller;
import minesweeper.model.ClickState;
import minesweeper.model.Field;
import minesweeper.model.Model;
import minesweeper.model.Range;
import minesweeper.model.UpdateBox;

public class DefaultView extends JFrame implements View, ActionListener {
	private static final long serialVersionUID = -1786179871578950490L;
	
	private static final int MAX_HORIZONTAL_MINES = 30;
	private static final int MAX_VERTICAL_MINES = 16;
	
	private MineButton[][] mineBtns = new MineButton[MAX_VERTICAL_MINES][MAX_HORIZONTAL_MINES];
	private int horizontalMines;
	private int verticalMines;
	private int minesAmount;
	private int minesLeft;
	
	private Controller controller;
	private JLabel lblMinesLeft, lblWinOrLose;
	private JButton restartBtn, settingsBtn;
	private SetGameDialog setGameDialog;
	
	
	
	public DefaultView() throws IOException {
		super("MineSweeper by wojtas626");
		init();
		constructFields();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setVisible(true);
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
		applyController();
	}

	private void applyController() {
		forEach(m -> m.addMouseListener(controller.getMineBtnController()));
		restartBtn.addActionListener(controller.getRestartBtnController());
		setGameDialog = new SetGameDialog(this);
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
	}

	private void updateMinesLeft(Model model) {
		minesLeft = model.getMinesLeft();
		lblMinesLeft.setText("Mines left: "+Integer.toString(minesLeft));
	}

	private void updateRestart(Model model) {
		horizontalMines = model.getHorizontalNumberOfMines();
		verticalMines = model.getVerticalNumberOfMines();
		updateMinesLeft(model);
		minesAmount = minesLeft;
		
		//TODO
		preprareFieldsForGame();
		
		this.repaint();
		forEach(btn -> btn.resetImage());
	}

	
	
	private void constructFields() {
		for (int i = 0; i < MAX_VERTICAL_MINES; i++) {
			for (int j = 0; j < MAX_HORIZONTAL_MINES; j++) {
				mineBtns[i][j] = new MineButton(i, j, this);
				mineBtns[i][j].setLocation(80 + j * MineButton.WIDTH, 80 + i * MineButton.HEIGHT);
				add(mineBtns[i][j]);
			}
		}
	}
	
	private void preprareFieldsForGame() {
		forEach(m -> m.setVisible(false));
		
		for (int i = 0; i < verticalMines; i++)
			for (int j = 0; j < horizontalMines; j++) {
				mineBtns[i][j].setVisible(true);
			}
				
		
		if (horizontalMines > 12) {
			setSize(160 + MineButton.WIDTH * horizontalMines, 140 + MineButton.HEIGHT * verticalMines);
		} else {
			setSize(160 + MineButton.WIDTH * 13, 140 + MineButton.HEIGHT * verticalMines);
		}
	}
	
	private void forEach(Consumer<MineButton> action) {
		for (int i = 0; i < MAX_VERTICAL_MINES; i++) {
			for (int j = 0; j < MAX_HORIZONTAL_MINES; j++) {
				action.accept(mineBtns[i][j]);
			}
		}
	}
	
	
	private void init() {
		setLayout(null);
		setSize(1024, 800);
		setLocation(100, 100);

		lblMinesLeft = new JLabel("Mines Left: " + 99);
		lblMinesLeft.setBounds(80, 20, 200, 40);
		lblMinesLeft.setFont(new Font("Arial", 0, 28));
		add(lblMinesLeft);

		lblWinOrLose = new JLabel("");
		lblWinOrLose.setBounds(300, 20, 150, 40);
		lblWinOrLose.setFont(new Font("Arial", 0, 28));
		add(lblWinOrLose);

		restartBtn = new JButton("RESTART");
		restartBtn.setBounds(450, 20, 100, 40);
		add(restartBtn);

		settingsBtn = new JButton("Settings");
		settingsBtn.setBounds(570, 20, 100, 40);
		settingsBtn.addActionListener(this);
		add(settingsBtn);
	}
	
	
	
	public int getMinesWidth() {
		return horizontalMines;
	}

	public void setMinesWidth(int minesWidth) {
		this.horizontalMines = minesWidth;
	}

	public int getMinesHeight() {
		return verticalMines;
	}

	public void setMinesHeight(int minesHeight) {
		this.verticalMines = minesHeight;
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
	
	public int getMinesAmount() {
		return minesAmount;
	}

	public void setMinesAmount(int minesAmount) {
		this.minesAmount = minesAmount;
	}

	public SetGameDialog getSetGameDialog() {
		return setGameDialog;
	}

	public MineButton get(int x, int y) {
		return mineBtns[x][y];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == settingsBtn) {
			setGameDialog.showDialog();
		}
		
	}

}
