package ua.hasperskyi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public abstract class FieldLevel extends JPanel{

	private boolean endLevel = false;
	private boolean gameOver = false;
	protected ImageIcon castle = new ImageIcon(getClass().getResource("/images/castle_small.png"));
	protected ImageIcon rock = new ImageIcon(getClass().getResource("/images/rock_small.png"));
	protected ImageIcon tree = new ImageIcon(getClass().getResource("/images/tree_small.png"));
	protected ImageIcon hole = new ImageIcon(getClass().getResource("/images/hole_small.png"));
	protected ImageIcon lake = new ImageIcon(getClass().getResource("/images/lake_small.png"));
	protected Bonuses[] bonuses = new Bonuses[10];
	protected Coins[] coins = new Coins[10];
	protected Enemy[] enemy = new Enemy[5];
	
	Dimension labelsize = new Dimension(50, 50);
	Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

	JLabel temp = new JLabel();
	JLabel[][] label = new JLabel[10][10];
	
	public void setBonuses() {
		for (int i = 0; i < bonuses.length; i++) {
			if (bonuses[i] == null) {
				int x = (int) (Math.random() * 2);
				if (x == 0)
					bonuses[i] = new Medical();
				else
					bonuses[i] = new SuperML();
			}
		}
	}

	public void setCoins() {
		for (int i = 0; i < coins.length; i++) {
			if (coins[i] == null)
				coins[i] = new Coins();
		}
	}

	public void setEnemy() {
		for (int i = 0; i < enemy.length; i++) {
			if (enemy[i] == null) {
				int x = (int) (Math.random() * 2);
				if (x == 0)
					enemy[i] = new Eagle();
				else
					enemy[i] = new Turtle();

			}
		}
	}

	public void setEmptyField() {
	}

	public void setHero(Hero hero) {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				label[0][0].setIcon(hero.getImageIconSmall());
				label[0][0].setText(hero.getName());
				label[0][0].setPreferredSize(labelsize);
				label[0][0].setBorder(solidBorder);
				hero.setCurrentPositionX(0);
				hero.setCurrentPositionY(0);
			}
		}
	}

	public void setEnemyOnField() {
		for (int k = 0; k < enemy.length; k++) {
			if (enemy[k] != null) {
				int x = (int) (Math.random() * label.length);
				int y = (int) (Math.random() * label.length);
				if (label[y][x].getText().equals("")) {
					label[y][x].setText(enemy[k].getName());
					label[y][x].setIcon(enemy[k].getIconEnemy());
					label[y][x].setPreferredSize(labelsize);
					label[y][x].setBorder(solidBorder);
					enemy[k] = null;
				}
			}
		}

	}

	public void setBonusesOnField() {
		for (int k = 0; k < bonuses.length; k++) {
			if (bonuses[k] != null) {
				int x = (int) (Math.random() * label.length);
				int y = (int) (Math.random() * label.length);
				if (label[y][x].getText().equals("")) {
					label[y][x].setText(bonuses[k].getName());
					label[y][x].setIcon(bonuses[k].getIconBonus());
					label[y][x].setPreferredSize(labelsize);
					label[y][x].setBorder(solidBorder);
					bonuses[k] = null;
				}
			}
		}
	}

	public void setCoinsOnField() {
		for (int k = 0; k < coins.length; k++) {
			if (coins[k] != null) {
				int x = (int) (Math.random() * label.length);
				int y = (int) (Math.random() * label.length);
				if (label[y][x].getText().equals("")) {
					label[y][x].setText(coins[k].getName());
					label[y][x].setIcon(coins[k].getIconCoins());
					label[y][x].setPreferredSize(labelsize);
					label[y][x].setBorder(solidBorder);
					coins[k] = null;
				}
			}
		}
	}

	public void setField() {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				add(label[i][j]);
			}
		}
	}

	public void setMoveRight(Hero hero) {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText().equals(hero.getName())) {
					if (hero.getCurrentPositionX() < label[i].length - 1) {
						if (i == label.length - 1 && j + 1 == label[i].length - 1) {
							label[i][j].setIcon(null);
							label[i][j].setText("");
							hero.setCurrentPositionX(j + 1);
							if (hero.getSuperHeroMove() > 0)
								hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
							endLevel = true;
							break;
						} else {
							if (label[i][j + 1].getText().equals("Tree") || label[i][j + 1].getText().equals("Rock"))
								break;
							else {
								if (label[i][j + 1].getText().equals("Hole")
										|| label[i][j + 1].getText().equals("Lake")) {
									label[i][j].setIcon(null);
									label[i][j].setText("");
									hero.setCurrentPositionX(j + 1);
									if (hero.getSuperHeroMove() > 0)
										hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
									gameOver = true;
									System.out.println("GameOver (Field): " + gameOver);
									break;
								} else {
									if (label[i][j + 1].getText().equals("Turtle")) {
										if (hero.getSuperHeroMove() > 0) {
											hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
											if (hero.getSuperHeroMove() > 0) {
												temp.setText("");
												temp.setIcon(null);
												label[i][j + 1].setIcon(label[i][j].getIcon());
												label[i][j + 1].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionX(j + 1);
												break;
											} else {
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i][j + 1].setIcon(hero.getImageIconSmall());
												else
													label[i][j + 1].setIcon(hero.getImageIconBig());
												label[i][j + 1].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionX(j + 1);
												break;
											}
										} else {
											if (hero.getHealth() <= 50) {
												label[i][j].setIcon(null);
												label[i][j].setText("");
												hero.setCurrentPositionX(j + 1);
												hero.setHealth(hero.getHealth() - 50);
												gameOver = true;
												break;
											} else {
												hero.setCurrentPositionX(j + 1);
												hero.setHealth(hero.getHealth() - 50);
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i][j + 1].setIcon(hero.getImageIconSmall());
												else
													label[i][j + 1].setIcon(label[i][j].getIcon());
												label[i][j + 1].setText(label[i][j].getText());
												label[i][j].setText(temp.getText());
												label[i][j].setIcon(temp.getIcon());
												break;
											}
										}
									} else {
										if (label[i][j + 1].getText().equals("Eagle")) {
											if (hero.getSuperHeroMove() > 0) {
												hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
												if (hero.getSuperHeroMove() > 0) {
													temp.setText("");
													temp.setIcon(null);
													label[i][j + 1].setIcon(label[i][j].getIcon());
													label[i][j + 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j + 1);
													break;
												} else {
													temp.setText("");
													temp.setIcon(null);
													if (hero.getHealth() <= 100)
														label[i][j + 1].setIcon(hero.getImageIconSmall());
													else
														label[i][j + 1].setIcon(hero.getImageIconBig());
													label[i][j + 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j + 1);
													break;
												}
											} else {
												if (hero.getHealth() <= 100) {
													label[i][j].setIcon(null);
													label[i][j].setText("");
													hero.setCurrentPositionX(j + 1);
													hero.setHealth(hero.getHealth() - 100);
													gameOver = true;
													break;
												} else {
													hero.setCurrentPositionX(j + 1);
													hero.setHealth(hero.getHealth() - 100);
													temp.setText("");
													temp.setIcon(null);
													if (hero.getHealth() <= 100)
														label[i][j + 1].setIcon(hero.getImageIconSmall());
													else
														label[i][j + 1].setIcon(label[i][j].getIcon());
													label[i][j + 1].setText(label[i][j].getText());
													label[i][j].setText(temp.getText());
													label[i][j].setIcon(temp.getIcon());
													break;
												}
											}
										} else {
											if (label[i][j + 1].getText().equals("Medical")) {
												if (hero.getSuperHeroMove() > 0) {
													hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
													if (hero.getSuperHeroMove() > 0) {
														temp.setText("");
														temp.setIcon(null);
														label[i][j + 1].setIcon(label[i][j].getIcon());
														label[i][j + 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j + 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													} else {
														if (hero.getHealth() <= 100) {
															temp.setText("");
															temp.setIcon(null);
															label[i][j + 1].setIcon(hero.getImageIconSmall());
															label[i][j + 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j + 1);
															hero.setHealth(hero.getHealth() + 100);
															break;
														} else {
															temp.setText("");
															temp.setIcon(null);
															label[i][j + 1].setIcon(hero.getImageIconBig());
															label[i][j + 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j + 1);
															hero.setHealth(hero.getHealth() + 100);
															break;
														}
													}
												} else {
													if (hero.getHealth() <= 100) {
														temp.setText("");
														temp.setIcon(null);
														label[i][j + 1].setIcon(hero.getImageIconSmall());
														label[i][j + 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j + 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													} else {
														temp.setText("");
														temp.setIcon(null);
														label[i][j + 1].setIcon(hero.getImageIconBig());
														label[i][j + 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j + 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													}
												}

											} else {
												if (label[i][j + 1].getText().equals("Super")) {
													if (hero.getSuperHeroMove() > 0) {
														hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
														temp.setText("");
														temp.setIcon(null);
														label[i][j + 1].setIcon(label[i][j].getIcon());
														label[i][j + 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j + 1);
														hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
														break;
													} else {
														temp.setText("");
														temp.setIcon(null);
														label[i][j + 1].setIcon(hero.getImageIconSuperHero());
														label[i][j + 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j + 1);
														hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
														break;
													}
												} else {
													if (label[i][j + 1].getText().equals("Coins")) {
														if (hero.getSuperHeroMove() > 0) {
															hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
															if (hero.getSuperHeroMove() > 0) {
																temp.setText("");
																temp.setIcon(null);
																label[i][j + 1].setIcon(label[i][j].getIcon());
																label[i][j + 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j + 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															} else {
																if (hero.getHealth() <= 100) {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i][j + 1].setIcon(hero.getImageIconSmall());
																	label[i][j + 1].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionX(j + 1);
																	hero.setCoins(hero.getCoins() + 1);
																	break;
																} else {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i][j + 1].setIcon(hero.getImageIconBig());
																	label[i][j + 1].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionX(j + 1);
																	hero.setCoins(hero.getCoins() + 1);
																	break;
																}
															}
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i][j + 1].setIcon(hero.getImageIconSmall());
																label[i][j + 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j + 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i][j + 1].setIcon(hero.getImageIconBig());
																label[i][j + 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j + 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															}
														}
													} else {
														if (hero.getSuperHeroMove() > 0) {
															hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
															if (hero.getSuperHeroMove() > 0) {
																temp.setText("");
																temp.setIcon(null);
																label[i][j + 1].setIcon(label[i][j].getIcon());
																label[i][j + 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j + 1);
																break;
															} else {
																if (hero.getHealth() <= 100) {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i][j + 1].setIcon(hero.getImageIconSmall());
																	label[i][j + 1].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionX(j + 1);
																	break;
																} else {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i][j + 1].setIcon(hero.getImageIconBig());
																	label[i][j + 1].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionX(j + 1);
																	break;
																}
															}
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i][j + 1].setIcon(hero.getImageIconSmall());
																label[i][j + 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j + 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i][j + 1].setIcon(hero.getImageIconBig());
																label[i][j + 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j + 1);
																break;
															}
														}
													}
												}

											}
										}

									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void setMoveLeft(Hero hero) {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText().equals(hero.getName())) {
					if (hero.getCurrentPositionX() > 0) {
						if (label[i][j - 1].getText().equals("Tree") || label[i][j - 1].getText().equals("Rock"))
							break;
						else {
							if (label[i][j - 1].getText().equals("Hole") || label[i][j - 1].getText().equals("Lake")) {
								label[i][j].setIcon(null);
								label[i][j].setText("");
								hero.setCurrentPositionX(j - 1);
								if (hero.getSuperHeroMove() > 0)
									hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
								gameOver = true;
								System.out.println("GameOver (Field): " + gameOver);
								break;
							} else {
								if (label[i][j - 1].getText().equals("Turtle")) {
									if (hero.getSuperHeroMove() > 0) {
										hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
										if (hero.getSuperHeroMove() > 0) {
											temp.setText("");
											temp.setIcon(null);
											label[i][j - 1].setIcon(label[i][j].getIcon());
											label[i][j - 1].setText(label[i][j].getText());
											label[i][j].setIcon(temp.getIcon());
											label[i][j].setText(temp.getText());
											hero.setCurrentPositionX(j - 1);
											break;
										} else {
											temp.setText("");
											temp.setIcon(null);
											if (hero.getHealth() <= 100)
												label[i][j - 1].setIcon(hero.getImageIconSmall());
											else
												label[i][j - 1].setIcon(hero.getImageIconBig());
											label[i][j - 1].setText(label[i][j].getText());
											label[i][j].setIcon(temp.getIcon());
											label[i][j].setText(temp.getText());
											hero.setCurrentPositionX(j - 1);
											break;
										}
									} else {
										if (hero.getHealth() <= 50) {
											label[i][j].setIcon(null);
											label[i][j].setText("");
											hero.setCurrentPositionX(j - 1);
											hero.setHealth(hero.getHealth() - 50);
											gameOver = true;
											break;
										} else {
											hero.setCurrentPositionX(j - 1);
											hero.setHealth(hero.getHealth() - 50);
											temp.setText("");
											temp.setIcon(null);
											if (hero.getHealth() <= 100)
												label[i][j - 1].setIcon(hero.getImageIconSmall());
											else
												label[i][j - 1].setIcon(label[i][j].getIcon());
											label[i][j - 1].setText(label[i][j].getText());
											label[i][j].setText(temp.getText());
											label[i][j].setIcon(temp.getIcon());
											break;
										}
									}
								} else {
									if (label[i][j - 1].getText().equals("Eagle")) {
										if (hero.getSuperHeroMove() > 0) {
											hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
											if (hero.getSuperHeroMove() > 0) {
												temp.setText("");
												temp.setIcon(null);
												label[i][j - 1].setIcon(label[i][j].getIcon());
												label[i][j - 1].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionX(j - 1);
												break;
											} else {
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i][j - 1].setIcon(hero.getImageIconSmall());
												else
													label[i][j - 1].setIcon(hero.getImageIconBig());
												label[i][j - 1].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionX(j - 1);
												break;
											}
										} else {
											if (hero.getHealth() <= 100) {
												label[i][j].setIcon(null);
												label[i][j].setText("");
												hero.setCurrentPositionX(j - 1);
												hero.setHealth(hero.getHealth() - 100);
												gameOver = true;
												break;
											} else {
												hero.setCurrentPositionX(j - 1);
												hero.setHealth(hero.getHealth() - 100);
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i][j - 1].setIcon(hero.getImageIconSmall());
												else
													label[i][j - 1].setIcon(label[i][j].getIcon());
												label[i][j - 1].setText(label[i][j].getText());
												label[i][j].setText(temp.getText());
												label[i][j].setIcon(temp.getIcon());
												break;
											}
										}
									} else {
										if (label[i][j - 1].getText().equals("Medical")) {
											if (hero.getSuperHeroMove() > 0) {
												hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
												if (hero.getSuperHeroMove() > 0) {
													temp.setText("");
													temp.setIcon(null);
													label[i][j - 1].setIcon(label[i][j].getIcon());
													label[i][j - 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j - 1);
													hero.setHealth(hero.getHealth() + 100);
													break;
												} else {
													if (hero.getHealth() <= 100) {
														temp.setText("");
														temp.setIcon(null);
														label[i][j - 1].setIcon(hero.getImageIconSmall());
														label[i][j - 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j - 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													} else {
														temp.setText("");
														temp.setIcon(null);
														label[i][j - 1].setIcon(hero.getImageIconBig());
														label[i][j - 1].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionX(j - 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													}
												}
											} else {
												if (hero.getHealth() <= 100) {
													temp.setText("");
													temp.setIcon(null);
													label[i][j - 1].setIcon(hero.getImageIconSmall());
													label[i][j - 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j - 1);
													hero.setHealth(hero.getHealth() + 100);
													break;
												} else {
													temp.setText("");
													temp.setIcon(null);
													label[i][j - 1].setIcon(hero.getImageIconBig());
													label[i][j - 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j - 1);
													hero.setHealth(hero.getHealth() + 100);
													break;
												}
											}

										} else {
											if (label[i][j - 1].getText().equals("Super")) {
												if (hero.getSuperHeroMove() > 0) {
													hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
													temp.setText("");
													temp.setIcon(null);
													label[i][j - 1].setIcon(label[i][j].getIcon());
													label[i][j - 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j - 1);
													hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
													break;
												} else {
													temp.setText("");
													temp.setIcon(null);
													label[i][j - 1].setIcon(hero.getImageIconSuperHero());
													label[i][j - 1].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionX(j - 1);
													hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
													break;
												}
											} else {
												if (label[i][j - 1].getText().equals("Coins")) {
													if (hero.getSuperHeroMove() > 0) {
														hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
														if (hero.getSuperHeroMove() > 0) {
															temp.setText("");
															temp.setIcon(null);
															label[i][j - 1].setIcon(label[i][j].getIcon());
															label[i][j - 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j - 1);
															hero.setCoins(hero.getCoins() + 1);
															break;
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i][j - 1].setIcon(hero.getImageIconSmall());
																label[i][j - 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j - 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i][j - 1].setIcon(hero.getImageIconBig());
																label[i][j - 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j - 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															}
														}
													} else {
														if (hero.getHealth() <= 100) {
															temp.setText("");
															temp.setIcon(null);
															label[i][j - 1].setIcon(hero.getImageIconSmall());
															label[i][j - 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j - 1);
															hero.setCoins(hero.getCoins() + 1);
															break;
														} else {
															temp.setText("");
															temp.setIcon(null);
															label[i][j - 1].setIcon(hero.getImageIconBig());
															label[i][j - 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j - 1);
															hero.setCoins(hero.getCoins() + 1);
															break;
														}
													}
												} else {
													if (hero.getSuperHeroMove() > 0) {
														hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
														if (hero.getSuperHeroMove() > 0) {
															temp.setText("");
															temp.setIcon(null);
															label[i][j - 1].setIcon(label[i][j].getIcon());
															label[i][j - 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j - 1);
															break;
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i][j - 1].setIcon(hero.getImageIconSmall());
																label[i][j - 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j - 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i][j - 1].setIcon(hero.getImageIconBig());
																label[i][j - 1].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionX(j - 1);
																break;
															}
														}
													} else {
														if (hero.getHealth() <= 100) {
															temp.setText("");
															temp.setIcon(null);
															label[i][j - 1].setIcon(hero.getImageIconSmall());
															label[i][j - 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j - 1);
															break;
														} else {
															temp.setText("");
															temp.setIcon(null);
															label[i][j - 1].setIcon(hero.getImageIconBig());
															label[i][j - 1].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionX(j - 1);
															break;
														}
													}
												}
											}

										}
									}

								}
							}
						}
					}
				}
			}
		}
	}

	public void setMoveUp(Hero hero) {

		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText().equals(hero.getName())) {
					if (hero.getCurrentPositionY() > 0) {
						if (label[i-1][j].getText().equals("Tree") || label[i-1][j].getText().equals("Rock"))
							break;
						else {
							if (label[i-1][j].getText().equals("Hole") || label[i-1][j].getText().equals("Lake")) {
								label[i][j].setIcon(null);
								label[i][j].setText("");
								hero.setCurrentPositionY(i - 1);
								if (hero.getSuperHeroMove() > 0)
									hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
								gameOver = true;
								System.out.println("GameOver (Field): " + gameOver);
								break;
							} else {
								if (label[i-1][j].getText().equals("Turtle")) {
									if (hero.getSuperHeroMove() > 0) {
										hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
										if (hero.getSuperHeroMove() > 0) {
											temp.setText("");
											temp.setIcon(null);
											label[i-1][j].setIcon(label[i][j].getIcon());
											label[i-1][j].setText(label[i][j].getText());
											label[i][j].setIcon(temp.getIcon());
											label[i][j].setText(temp.getText());
											hero.setCurrentPositionY(i - 1);
											break;
										} else {
											temp.setText("");
											temp.setIcon(null);
											if (hero.getHealth() <= 100)
												label[i-1][j].setIcon(hero.getImageIconSmall());
											else
												label[i-1][j].setIcon(hero.getImageIconBig());
											label[i-1][j].setText(label[i][j].getText());
											label[i][j].setIcon(temp.getIcon());
											label[i][j].setText(temp.getText());
											hero.setCurrentPositionY(i - 1);
											break;
										}
									} else {
										if (hero.getHealth() <= 50) {
											label[i][j].setIcon(null);
											label[i][j].setText("");
											hero.setCurrentPositionY(i - 1);
											hero.setHealth(hero.getHealth() - 50);
											gameOver = true;
											break;
										} else {
											hero.setCurrentPositionY(i - 1);
											hero.setHealth(hero.getHealth() - 50);
											temp.setText("");
											temp.setIcon(null);
											if (hero.getHealth() <= 100)
												label[i-1][j].setIcon(hero.getImageIconSmall());
											else
												label[i-1][j].setIcon(label[i][j].getIcon());
											label[i-1][j].setText(label[i][j].getText());
											label[i][j].setText(temp.getText());
											label[i][j].setIcon(temp.getIcon());
											break;
										}
									}
								} else {
									if (label[i-1][j].getText().equals("Eagle")) {
										if (hero.getSuperHeroMove() > 0) {
											hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
											if (hero.getSuperHeroMove() > 0) {
												temp.setText("");
												temp.setIcon(null);
												label[i-1][j].setIcon(label[i][j].getIcon());
												label[i-1][j].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionY(i - 1);
												break;
											} else {
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i-1][j].setIcon(hero.getImageIconSmall());
												else
													label[i-1][j].setIcon(hero.getImageIconBig());
												label[i-1][j].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionY(i - 1);
												break;
											}
										} else {
											if (hero.getHealth() <= 100) {
												label[i][j].setIcon(null);
												label[i][j].setText("");
												hero.setCurrentPositionY(i - 1);
												hero.setHealth(hero.getHealth() - 100);
												gameOver = true;
												break;
											} else {
												hero.setCurrentPositionY(i - 1);
												hero.setHealth(hero.getHealth() - 100);
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i-1][j].setIcon(hero.getImageIconSmall());
												else
													label[i-1][j].setIcon(label[i][j].getIcon());
												label[i-1][j].setText(label[i][j].getText());
												label[i][j].setText(temp.getText());
												label[i][j].setIcon(temp.getIcon());
												break;
											}
										}
									} else {
										if (label[i-1][j].getText().equals("Medical")) {
											if (hero.getSuperHeroMove() > 0) {
												hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
												if (hero.getSuperHeroMove() > 0) {
													temp.setText("");
													temp.setIcon(null);
													label[i-1][j].setIcon(label[i][j].getIcon());
													label[i-1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i - 1);
													hero.setHealth(hero.getHealth() + 100);
													break;
												} else {
													if (hero.getHealth() <= 100) {
														temp.setText("");
														temp.setIcon(null);
														label[i-1][j].setIcon(hero.getImageIconSmall());
														label[i-1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i - 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													} else {
														temp.setText("");
														temp.setIcon(null);
														label[i-1][j].setIcon(hero.getImageIconBig());
														label[i-1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i - 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													}
												}
											} else {
												if (hero.getHealth() <= 100) {
													temp.setText("");
													temp.setIcon(null);
													label[i-1][j].setIcon(hero.getImageIconSmall());
													label[i-1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i - 1);
													hero.setHealth(hero.getHealth() + 100);
													break;
												} else {
													temp.setText("");
													temp.setIcon(null);
													label[i-1][j].setIcon(hero.getImageIconBig());
													label[i-1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i - 1);
													hero.setHealth(hero.getHealth() + 100);
													break;
												}
											}

										} else {
											if (label[i-1][j].getText().equals("Super")) {
												if (hero.getSuperHeroMove() > 0) {
													hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
													temp.setText("");
													temp.setIcon(null);
													label[i-1][j].setIcon(label[i][j].getIcon());
													label[i-1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i - 1);
													hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
													break;
												} else {
													temp.setText("");
													temp.setIcon(null);
													label[i-1][j].setIcon(hero.getImageIconSuperHero());
													label[i-1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i - 1);
													hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
													break;
												}
											} else {
												if (label[i-1][j].getText().equals("Coins")) {
													if (hero.getSuperHeroMove() > 0) {
														hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
														if (hero.getSuperHeroMove() > 0) {
															temp.setText("");
															temp.setIcon(null);
															label[i-1][j].setIcon(label[i][j].getIcon());
															label[i-1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i - 1);
															hero.setCoins(hero.getCoins() + 1);
															break;
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i-1][j].setIcon(hero.getImageIconSmall());
																label[i-1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i - 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i-1][j].setIcon(hero.getImageIconBig());
																label[i-1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i - 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															}
														}
													} else {
														if (hero.getHealth() <= 100) {
															temp.setText("");
															temp.setIcon(null);
															label[i-1][j].setIcon(hero.getImageIconSmall());
															label[i-1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i - 1);
															hero.setCoins(hero.getCoins() + 1);
															break;
														} else {
															temp.setText("");
															temp.setIcon(null);
															label[i-1][j].setIcon(hero.getImageIconBig());
															label[i-1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i - 1);
															hero.setCoins(hero.getCoins() + 1);
															break;
														}
													}
												} else {
													if (hero.getSuperHeroMove() > 0) {
														hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
														if (hero.getSuperHeroMove() > 0) {
															temp.setText("");
															temp.setIcon(null);
															label[i-1][j].setIcon(label[i][j].getIcon());
															label[i-1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i - 1);
															break;
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i-1][j].setIcon(hero.getImageIconSmall());
																label[i-1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i - 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i-1][j].setIcon(hero.getImageIconBig());
																label[i-1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i - 1);
																break;
															}
														}
													} else {
														if (hero.getHealth() <= 100) {
															temp.setText("");
															temp.setIcon(null);
															label[i-1][j].setIcon(hero.getImageIconSmall());
															label[i-1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i - 1);
															break;
														} else {
															temp.setText("");
															temp.setIcon(null);
															label[i-1][j].setIcon(hero.getImageIconBig());
															label[i-1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i - 1);
															break;
														}
													}
												}
											}

										}
									}

								}
							}
						}
					}
				}
			}
		}
	}

	public void setMoveDown(Hero hero) {
		for (int i = label.length - 1; i >= 0; i--) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText().equals(hero.getName())) {
					if (hero.getCurrentPositionY() < label.length - 1) {
						if (i + 1 == label.length - 1 && j == label[i].length - 1) {
							label[i][j].setIcon(null);
							label[i][j].setText("");
							hero.setCurrentPositionY(i + 1);
							if (hero.getSuperHeroMove() > 0)
								hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
							endLevel = true;
							System.out.println("EndLevel (Field): " + endLevel);
							break;
						} else {
							if (label[i+1][j].getText().equals("Tree") || label[i+1][j].getText().equals("Rock"))
								break;
							else {
								if (label[i+1][j].getText().equals("Hole")
										|| label[i+1][j].getText().equals("Lake")) {
									label[i][j].setIcon(null);
									label[i][j].setText("");
									hero.setCurrentPositionY(i + 1);
									if (hero.getSuperHeroMove() > 0)
										hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
									gameOver = true;
									System.out.println("GameOver (Field): " + gameOver);
									break;
								} else {
									if (label[i+1][j].getText().equals("Turtle")) {
										if (hero.getSuperHeroMove() > 0) {
											hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
											if (hero.getSuperHeroMove() > 0) {
												temp.setText("");
												temp.setIcon(null);
												label[i+1][j].setIcon(label[i][j].getIcon());
												label[i+1][j].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionY(i + 1);
												break;
											} else {
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i+1][j].setIcon(hero.getImageIconSmall());
												else
													label[i+1][j].setIcon(hero.getImageIconBig());
												label[i+1][j].setText(label[i][j].getText());
												label[i][j].setIcon(temp.getIcon());
												label[i][j].setText(temp.getText());
												hero.setCurrentPositionY(i + 1);
												break;
											}
										} else {
											if (hero.getHealth() <= 50) {
												label[i][j].setIcon(null);
												label[i][j].setText("");
												hero.setCurrentPositionY(i + 1);
												hero.setHealth(hero.getHealth() - 50);
												gameOver = true;
												break;
											} else {
												hero.setCurrentPositionY(i + 1);
												hero.setHealth(hero.getHealth() - 50);
												temp.setText("");
												temp.setIcon(null);
												if (hero.getHealth() <= 100)
													label[i+1][j].setIcon(hero.getImageIconSmall());
												else
													label[i+1][j].setIcon(label[i][j].getIcon());
												label[i+1][j].setText(label[i][j].getText());
												label[i][j].setText(temp.getText());
												label[i][j].setIcon(temp.getIcon());
												break;
											}
										}
									} else {
										if (label[i+1][j].getText().equals("Eagle")) {
											if (hero.getSuperHeroMove() > 0) {
												hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
												if (hero.getSuperHeroMove() > 0) {
													temp.setText("");
													temp.setIcon(null);
													label[i+1][j].setIcon(label[i][j].getIcon());
													label[i+1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i + 1);
													break;
												} else {
													temp.setText("");
													temp.setIcon(null);
													if (hero.getHealth() <= 100)
														label[i+1][j].setIcon(hero.getImageIconSmall());
													else
														label[i+1][j].setIcon(hero.getImageIconBig());
													label[i+1][j].setText(label[i][j].getText());
													label[i][j].setIcon(temp.getIcon());
													label[i][j].setText(temp.getText());
													hero.setCurrentPositionY(i + 1);
													break;
												}
											} else {
												if (hero.getHealth() <= 100) {
													label[i][j].setIcon(null);
													label[i][j].setText("");
													hero.setCurrentPositionY(i + 1);
													hero.setHealth(hero.getHealth() - 100);
													gameOver = true;
													break;
												} else {
													hero.setCurrentPositionY(i + 1);
													hero.setHealth(hero.getHealth() - 100);
													temp.setText("");
													temp.setIcon(null);
													if (hero.getHealth() <= 100)
														label[i+1][j].setIcon(hero.getImageIconSmall());
													else
														label[i+1][j].setIcon(label[i][j].getIcon());
													label[i+1][j].setText(label[i][j].getText());
													label[i][j].setText(temp.getText());
													label[i][j].setIcon(temp.getIcon());
													break;
												}
											}
										} else {
											if (label[i+1][j].getText().equals("Medical")) {
												if (hero.getSuperHeroMove() > 0) {
													hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
													if (hero.getSuperHeroMove() > 0) {
														temp.setText("");
														temp.setIcon(null);
														label[i+1][j].setIcon(label[i][j].getIcon());
														label[i+1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i + 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													} else {
														if (hero.getHealth() <= 100) {
															temp.setText("");
															temp.setIcon(null);
															label[i+1][j].setIcon(hero.getImageIconSmall());
															label[i+1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i + 1);
															hero.setHealth(hero.getHealth() + 100);
															break;
														} else {
															temp.setText("");
															temp.setIcon(null);
															label[i+1][j].setIcon(hero.getImageIconBig());
															label[i+1][j].setText(label[i][j].getText());
															label[i][j].setIcon(temp.getIcon());
															label[i][j].setText(temp.getText());
															hero.setCurrentPositionY(i + 1);
															hero.setHealth(hero.getHealth() + 100);
															break;
														}
													}
												} else {
													if (hero.getHealth() <= 100) {
														temp.setText("");
														temp.setIcon(null);
														label[i+1][j].setIcon(hero.getImageIconSmall());
														label[i+1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i + 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													} else {
														temp.setText("");
														temp.setIcon(null);
														label[i+1][j].setIcon(hero.getImageIconBig());
														label[i+1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i + 1);
														hero.setHealth(hero.getHealth() + 100);
														break;
													}
												}

											} else {
												if (label[i+1][j].getText().equals("Super")) {
													if (hero.getSuperHeroMove() > 0) {
														hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
														temp.setText("");
														temp.setIcon(null);
														label[i+1][j].setIcon(label[i][j].getIcon());
														label[i+1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i + 1);
														hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
														break;
													} else {
														temp.setText("");
														temp.setIcon(null);
														label[i+1][j].setIcon(hero.getImageIconSuperHero());
														label[i+1][j].setText(label[i][j].getText());
														label[i][j].setIcon(temp.getIcon());
														label[i][j].setText(temp.getText());
														hero.setCurrentPositionY(i + 1);
														hero.setSuperHeroMove(hero.getSuperHeroMove() + 5);
														break;
													}
												} else {
													if (label[i+1][j].getText().equals("Coins")) {
														if (hero.getSuperHeroMove() > 0) {
															hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
															if (hero.getSuperHeroMove() > 0) {
																temp.setText("");
																temp.setIcon(null);
																label[i+1][j].setIcon(label[i][j].getIcon());
																label[i+1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i + 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															} else {
																if (hero.getHealth() <= 100) {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i+1][j].setIcon(hero.getImageIconSmall());
																	label[i+1][j].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionY(i + 1);
																	hero.setCoins(hero.getCoins() + 1);
																	break;
																} else {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i+1][j].setIcon(hero.getImageIconBig());
																	label[i+1][j].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionY(i + 1);
																	hero.setCoins(hero.getCoins() + 1);
																	break;
																}
															}
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i+1][j].setIcon(hero.getImageIconSmall());
																label[i+1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i + 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i+1][j].setIcon(hero.getImageIconBig());
																label[i+1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i + 1);
																hero.setCoins(hero.getCoins() + 1);
																break;
															}
														}
													} else {
														if (hero.getSuperHeroMove() > 0) {
															hero.setSuperHeroMove(hero.getSuperHeroMove() - 1);
															if (hero.getSuperHeroMove() > 0) {
																temp.setText("");
																temp.setIcon(null);
																label[i+1][j].setIcon(label[i][j].getIcon());
																label[i+1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i + 1);
																break;
															} else {
																if (hero.getHealth() <= 100) {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i+1][j].setIcon(hero.getImageIconSmall());
																	label[i+1][j].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionY(i + 1);
																	break;
																} else {
																	temp.setText("");
																	temp.setIcon(null);
																	label[i+1][j].setIcon(hero.getImageIconBig());
																	label[i+1][j].setText(label[i][j].getText());
																	label[i][j].setIcon(temp.getIcon());
																	label[i][j].setText(temp.getText());
																	hero.setCurrentPositionY(i + 1);
																	break;
																}
															}
														} else {
															if (hero.getHealth() <= 100) {
																temp.setText("");
																temp.setIcon(null);
																label[i+1][j].setIcon(hero.getImageIconSmall());
																label[i+1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i + 1);
																break;
															} else {
																temp.setText("");
																temp.setIcon(null);
																label[i+1][j].setIcon(hero.getImageIconBig());
																label[i+1][j].setText(label[i][j].getText());
																label[i][j].setIcon(temp.getIcon());
																label[i][j].setText(temp.getText());
																hero.setCurrentPositionY(i + 1);
																break;
															}
														}
													}
												}

											}
										}

									}
								}
							}
						}
					}
				}
			}
		}
	}

	public int getFieldSize() {
		return label.length;
	}

	public boolean getEndLevel() {
		return endLevel;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public int getCurrentPositionX(Hero hero) {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText().equals(hero.getName())) {
					return j;
				}
			}
		}
		return 0;
	}
	
	public int getCurrentPositionY(Hero hero) {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText().equals(hero.getName())) {
					return i;
				}
			}
		}
		return 0;
	}

	public void getInfo() {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getText() != "")
					System.out.println(label[i][j].getText() + " Current i:" + i + " , Current j:" + j);
			}
		}
	}

	public void getInfoField() {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (label[i][j].getIcon() != null)
					System.out.println(label[i][j].getIcon() + " Current i:" + i + " , Current j:" + j);
			}
		}
	}

	public void getInfoEnemy() {
		for (int i = 0; i < enemy.length; i++) {
			System.out.println(enemy[i]);
		}
	}
	
}
