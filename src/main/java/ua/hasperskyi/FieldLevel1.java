package ua.hasperskyi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FieldLevel1 extends FieldLevel {

	public FieldLevel1() {
		JPanel gameField = new JPanel();
		setLayout(new GridLayout(10, 10));
		setCoins();
		setBonuses();
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
					if (i == 0 && j == 2 || i == 1 && j == 2 || i == 1 && j == 7 || i == 4 && j == 1 || i == 4 && j == 3
							|| i == 4 && j == 5 || i == 5 && j == label.length - 1 || i == 8 && j == 0) {
						label[i][j] = new JLabel();
						label[i][j].setIcon(tree);
						label[i][j].setText("Tree");
						label[i][j].setPreferredSize(labelsize);
						label[i][j].setBorder(solidBorder);
					} else {
						if (i == 0 && j == 3 || i == 1 && j == 3 || i == 2 && j == 3 || i == 2 && j == 6
								|| i == 6 && j == 4 || i == 6 && j == label.length - 1 || i == 7 && j == 6
								|| i == 8 && j == 2) {
							label[i][j] = new JLabel();
							label[i][j].setIcon(rock);
							label[i][j].setText("Rock");
							label[i][j].setPreferredSize(labelsize);
							label[i][j].setBorder(solidBorder);
						} else {
							if (i == 0 && j == label.length - 1 || i == 3 && j == 1 || i == 8 && j == 6) {
								label[i][j] = new JLabel();
								label[i][j].setIcon(hole);
								label[i][j].setText("Hole");
								label[i][j].setPreferredSize(labelsize);
								label[i][j].setBorder(solidBorder);
							} else {
								if (i == 2 && j == 7 || i == 4 && j == 4 || i == 5 && j == 8 || i == 7 && j == 0
										|| i == label.length - 1 && j == 4) {
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
