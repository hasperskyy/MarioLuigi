package ua.hasperskyi;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FieldLevel2 extends FieldLevel {
			
	public FieldLevel2() {
		JPanel gameField = new JPanel();
		setLayout(new GridLayout(10, 10));
		setCoins();
		setBonuses();
		setEnemy();
		setEmptyField();
	}

	public void setEmptyField() {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (i == label.length - 1 && j == label[i].length - 1) {
					label[i][j] = new JLabel();
					label[i][j].setIcon(castle);
					label[i][j].setText("Castle");
					label[i][j].setPreferredSize(labelsize);
					label[i][j].setBorder(solidBorder);
				} else {
					if (i == 0 && j == 2 || i == 0 && j == 3 || i == 5 && j == 2 || i == 5 && j == label.length - 1
							|| i == 7 && j == 6 || i == 8 && j == 5) {
						label[i][j] = new JLabel();
						label[i][j].setIcon(tree);
						label[i][j].setText("Tree");
						label[i][j].setPreferredSize(labelsize);
						label[i][j].setBorder(solidBorder);
					} else {
						if (i == 2 && j == 0 || i == 2 && j == 8 || i == 3 && j == 2 || i == 3 && j == 8
								|| i == 4 && j == 5 || i == 6 && j == 2 || i == 8 && j == label.length - 1) {
							label[i][j] = new JLabel();
							label[i][j].setIcon(rock);
							label[i][j].setText("Rock");
							label[i][j].setPreferredSize(labelsize);
							label[i][j].setBorder(solidBorder);
						} else {
							if (i == 0 && j == label.length - 1 || i == 1 && j == 3 || i == 4 && j == 6
									|| i == 8 && j == 7 || i == 9 && j == 1) {
								label[i][j] = new JLabel();
								label[i][j].setIcon(hole);
								label[i][j].setText("Hole");
								label[i][j].setPreferredSize(labelsize);
								label[i][j].setBorder(solidBorder);
							} else {
								if (i == 0 && j == 1 || i == 1 && j == 7 || i == 3 && j == 5 || i == 5 && j == 1
										|| i == 5 && j == 8 || i == 7 && j == 5) {
									label[i][j] = new JLabel();
									label[i][j].setIcon(lake);
									label[i][j].setText("Lake");
									label[i][j].setPreferredSize(labelsize);
									label[i][j].setBorder(solidBorder);
								} else {
									label[i][j] = new JLabel();
									label[i][j].setPreferredSize(labelsize);
									label[i][j].setBorder(solidBorder);
								}
							}
						}
					}
				}
			}
		}
	}

}
