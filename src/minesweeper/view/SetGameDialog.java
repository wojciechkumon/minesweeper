package minesweeper.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetGameDialog extends JDialog {
	private static final long serialVersionUID = 1478466600960943121L;

	private JLabel lblHorizontalFields, lblVerticalFields, lblMines;
	private JTextField txtFldHorizontalFields, txtFldVerticalFields, txtFldMines;
	private ButtonOk btnOK;
	private final DefaultView owner;
	
	private static final int SIZE_X = 260;
	private static final int SIZE_Y = 220;
	
	public SetGameDialog(DefaultView owner) {
		super(owner, "Settings", true);
		this.owner = owner;
		
		setSize(SIZE_X, SIZE_Y);
		setLayout(null);

		lblHorizontalFields = new JLabel("Horizontal fields (8-30)");
		lblHorizontalFields.setBounds(30, 30, 130, 20);
		add(lblHorizontalFields);

		lblVerticalFields = new JLabel("Vertical fields (8-16)");
		lblVerticalFields.setBounds(30, 60, 130, 20);
		add(lblVerticalFields);

		lblMines = new JLabel("Mines (10-99)");
		lblMines.setBounds(30, 90, 130, 20);
		add(lblMines);

		txtFldHorizontalFields = new JTextField("30");
		txtFldHorizontalFields.setBounds(160, 30, 50, 20);
		add(txtFldHorizontalFields);

		txtFldVerticalFields = new JTextField("16");
		txtFldVerticalFields.setBounds(160, 60, 50, 20);
		add(txtFldVerticalFields);

		txtFldMines = new JTextField("99");
		txtFldMines.setBounds(160, 90, 50, 20);
		add(txtFldMines);

		btnOK = new ButtonOk(this, "OK");
		btnOK.setBounds(80, 130, 80, 30);
		btnOK.addActionListener(owner.getController().getChangeSettingsBtnController());
		add(btnOK);
	

		txtFldHorizontalFields.requestFocusInWindow();
	}
	
	
	
	public String getHorizontalFields() {
		return txtFldHorizontalFields.getText();
	}
	
	public String getVerticalFields() {
		return txtFldVerticalFields.getText();
	}
	
	public String getMines() {
		return txtFldMines.getText();
	}
	
	public DefaultView getOwnerWindow() {
		return owner;
	}

	private void setPosition() {
		int posX = (int)owner.getLocationOnScreen().getX() + ((owner.getWidth() - SIZE_X) / 2);
		int posY = (int)owner.getLocationOnScreen().getY() + ((owner.getHeight() - SIZE_Y) / 2);
		setLocation(posX, posY);
	}
	
	private void resetTextFields() {
		txtFldHorizontalFields.setText(Integer.toString(owner.getHorizontalMineButtonsAmount()));
		txtFldVerticalFields.setText(Integer.toString(owner.getVerticalMineButtonsAmount()));
		txtFldMines.setText(Integer.toString(owner.getMinesAmount()));
	}


	public void showDialog() {
		setPosition();
		resetTextFields();
		setVisible(true);
	}



}
