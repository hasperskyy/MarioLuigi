package ua.hasperskyi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Main game class.
 *
 * @author Oleksii Hasperskyi
 * @version 1.0
 */

public class MarioLuigiMainFrame extends JFrame {
	Hero mario;
	Hero luigi;
	JPanel welcomePanel;
	JPanel choicePanel;
	JPanel choicePanel2;
	JPanel playerPanel;
	JPanel controlPanel;
	JPanel gamePanel;
	JPanel resultsPanel;
	JPanel gameOverPanel;
	JPanel finalPanel;
	FieldLevel gameField;
	private boolean gameOverCheck;
	private boolean endLevelCheck;
	private int levelCount;

	ImageIcon OKIcon = new ImageIcon(getClass().getResource("/images/OK_small.png"));
	JButton buttonOK = new JButton(OKIcon);
	ImageIcon welcomeIcon = new ImageIcon(getClass().getResource("/images/Mario-PNG-Image-49292.png"));
	ImageIcon welcome_2_Icon = new ImageIcon(getClass().getResource("/images/start-png-44886.png"));
	JButton buttonWelcome = new JButton(welcome_2_Icon);
	ImageIcon chooseIcon = new ImageIcon(getClass().getResource("/images/chooseIcon_2.jpg"));
	ImageIcon iconMario = new ImageIcon(getClass().getResource("/images/mario_1.png"));
	JButton buttonMario = new JButton(iconMario);
	ImageIcon iconLuigi = new ImageIcon(getClass().getResource("/images/luigi_1.png"));
	JButton buttonLuigi = new JButton(iconLuigi);
	ImageIcon iconPlay = new ImageIcon(getClass().getResource("/images/play.jpg"));
	JButton buttonPlay = new JButton(iconPlay);
	ImageIcon startIcon = new ImageIcon(getClass().getResource("/images/OK_small.png"));
	JButton startButton = new JButton(startIcon);
	ImageIcon nextIcon = new ImageIcon(getClass().getResource("/images/next.png"));
	JButton nextButton = new JButton(nextIcon);
	ImageIcon gameOverIcon = new ImageIcon(getClass().getResource("/images/gameOver.jpg"));
	JButton gameOverButton = new JButton(gameOverIcon);
	ImageIcon tryAgainIcon = new ImageIcon(getClass().getResource("/images/tryAgain.png"));
	JButton tryAgainButton = new JButton(tryAgainIcon);
	ImageIcon exitIcon = new ImageIcon(getClass().getResource("/images/exit.png"));
	JButton exitButton = new JButton(exitIcon);
	ImageIcon winnerIcon = new ImageIcon(getClass().getResource("/images/winner.jpg"));
	JButton winnerButton = new JButton(winnerIcon);
	JLabel welcomeLabel;
	JLabel coinLabel;
	JLabel healthLabel;
	JLabel superHeroLabel;
	private Integer health;
	private Integer coins;
	private Integer superHero;

	MarioLuigiMainFrame() {
		super("Mario & Luigi. Hasperskyi Oleksii v.1.0");
		setLayout(new BorderLayout());
		add(getWelcomePanel());
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	JPanel getWelcomePanel() {
		welcomePanel = new JPanel();
		welcomeLabel = new JLabel();
		welcomeLabel.setIcon(welcomeIcon);
		Dimension labelSize = new Dimension(500, 400);
		welcomeLabel.setPreferredSize(labelSize);
		welcomePanel.add(welcomeLabel);
		Dimension buttonSize = new Dimension(400, 150);
		buttonWelcome.setPreferredSize(buttonSize);
		welcomePanel.add(buttonWelcome);

		buttonWelcome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				welcomePanel.setVisible(false);
				add(getChoicePanel());
			}
		});
		return welcomePanel;
	}

	JPanel getChoicePanel() {
		choicePanel = new JPanel();
		choicePanel.setLayout(new BorderLayout());
		JLabel chooseLabel = new JLabel();
		Dimension labelSize = new Dimension(400, 100);
		chooseLabel.setIcon(chooseIcon);
		chooseLabel.setPreferredSize(labelSize);
		choicePanel.add(chooseLabel, BorderLayout.NORTH);
		choicePanel.add(getPlayerPanel(), BorderLayout.SOUTH);
		return choicePanel;
	}

	JPanel getPlayerPanel() {
		playerPanel = new JPanel();
		setLayout(new BorderLayout());
		playerPanel.add(buttonMario, BorderLayout.WEST);
		buttonMario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mario = null;
				mario = new Mario("Mario", 100, 0);
				levelCount = 1;
				choicePanel.setVisible(false);
				add(getGamePanel());
			}
		});

		playerPanel.add(buttonLuigi, BorderLayout.EAST);
		buttonLuigi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				luigi = null;
				luigi = new Luigi("Luigi", 100, 0);
				levelCount = 1;
				choicePanel.setVisible(false);
				add(getGamePanel());
			}
		});
		return playerPanel;
	}

	JPanel getGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(getFieldLevelPanel(), BorderLayout.WEST);
		gamePanel.add(getStatisticPanel(), BorderLayout.EAST);
		gamePanel.add(getControlPanel(), BorderLayout.SOUTH);
		return gamePanel;
	}

	JPanel getStatisticPanel() {
		JPanel statisticPanel = new JPanel();
		statisticPanel.setLayout(new BorderLayout());
		Dimension labelSize = new Dimension(70, 70);
		Font font = new Font("Verdana", Font.PLAIN, 14);

		coinLabel = new JLabel();
		coinLabel.setFont(font);
		coinLabel.setVerticalTextPosition(JLabel.BOTTOM);
		coinLabel.setHorizontalTextPosition(JLabel.CENTER);
		coinLabel.setPreferredSize(labelSize);
		ImageIcon coinIcon = new ImageIcon(getClass().getResource("/images/coin_small.png"));
		coinLabel.setIcon(coinIcon);

		healthLabel = new JLabel();
		healthLabel.setFont(font);
		healthLabel.setVerticalTextPosition(JLabel.BOTTOM);
		healthLabel.setHorizontalTextPosition(JLabel.CENTER);
		healthLabel.setPreferredSize(labelSize);
		ImageIcon healthIcon = new ImageIcon(getClass().getResource("/images/medical_small.png"));
		healthLabel.setIcon(healthIcon);

		superHeroLabel = new JLabel();
		superHeroLabel.setFont(font);
		superHeroLabel.setVerticalTextPosition(JLabel.BOTTOM);
		superHeroLabel.setHorizontalTextPosition(JLabel.CENTER);
		superHeroLabel.setPreferredSize(labelSize);
		ImageIcon superHeroIcon = new ImageIcon(getClass().getResource("/images/super_small.png"));
		superHeroLabel.setIcon(superHeroIcon);

		statisticPanel.add(coinLabel, BorderLayout.NORTH);
		statisticPanel.add(healthLabel, BorderLayout.CENTER);
		statisticPanel.add(superHeroLabel, BorderLayout.SOUTH);
		return statisticPanel;
	}

	JPanel getControlPanel() {

		controlPanel = new JPanel();
		
		ImageIcon iconUp = new ImageIcon(getClass().getResource("/images/up_small.png"));
		JButton buttonUp = new JButton(iconUp);
		ImageIcon iconDown = new ImageIcon(getClass().getResource("/images/down_small.png"));
		JButton buttonDown = new JButton(iconDown);
		ImageIcon iconLeft = new ImageIcon(getClass().getResource("/images/left_small.png"));
		JButton buttonLeft = new JButton(iconLeft);
		ImageIcon iconRight = new ImageIcon(getClass().getResource("/images/right_small.png"));
		JButton buttonRight = new JButton(iconRight);

		controlPanel.add(buttonUp);
		controlPanel.add(buttonDown);
		controlPanel.add(buttonLeft);
		controlPanel.add(buttonRight);
		
		buttonRight.addActionListener(evt -> {
				if (mario != null) {
					gameField.setMoveRight(mario);

					health = mario.getHealth();
					coins = mario.getCoins();
					superHero = mario.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());

				} else {
					gameField.setMoveRight(luigi);

					health = luigi.getHealth();
					coins = luigi.getCoins();
					superHero = luigi.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());
				}
				gameField.setField();
				endLevelCheck = gameField.getEndLevel();
				gameOverCheck = gameField.getGameOver();

				if (gameOverCheck) {
					gamePanel.setVisible(false);
					add(getGameOverPanel());
				} else {
					if (endLevelCheck) {
						gamePanel.setVisible(false);
						if (levelCount < 2) {
							levelCount++;
							add(getResultsPanel());
						} else
							add(getFinalPanel());
					}
				}
		});
		
		buttonLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mario != null) {
					gameField.setMoveLeft(mario);
					health = mario.getHealth();
					coins = mario.getCoins();
					superHero = mario.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());

				}

				else {
					gameField.setMoveLeft(luigi);
					health = luigi.getHealth();
					coins = luigi.getCoins();
					superHero = luigi.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());
				}

				gameField.setField();
				endLevelCheck = gameField.getEndLevel();
				gameOverCheck = gameField.getGameOver();

				if (gameOverCheck) {
					gamePanel.setVisible(false);
					add(getGameOverPanel());
				} else {
					if (endLevelCheck) {
						gamePanel.setVisible(false);
						if (levelCount < 2) {
							levelCount++;
							add(getResultsPanel());
						} else
							add(getFinalPanel());
					}
				}
			}
		});

		buttonUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mario != null) {
					gameField.setMoveUp(mario);
					health = mario.getHealth();
					coins = mario.getCoins();
					superHero = mario.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());
				}

				else {
					gameField.setMoveUp(luigi);
					health = luigi.getHealth();
					coins = luigi.getCoins();
					superHero = luigi.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());
				}

				gameField.setField();
				endLevelCheck = gameField.getEndLevel();
				gameOverCheck = gameField.getGameOver();

				if (gameOverCheck) {
					gamePanel.setVisible(false);
					add(getGameOverPanel());
				} else {
					if (endLevelCheck) {
						gamePanel.setVisible(false);
						if (levelCount < 2) {
							levelCount++;
							add(getResultsPanel());
						} else
							add(getFinalPanel());
					}
				}
			}
		});

		buttonDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mario != null) {
					gameField.setMoveDown(mario);
					health = mario.getHealth();
					coins = mario.getCoins();
					superHero = mario.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());
				} else {
					gameField.setMoveDown(luigi);
					health = luigi.getHealth();
					coins = luigi.getCoins();
					superHero = luigi.getSuperHeroMove();
					healthLabel.setText(health.toString());
					coinLabel.setText(coins.toString());
					superHeroLabel.setText(superHero.toString());
				}

				gameField.setField();
				endLevelCheck = gameField.getEndLevel();
				gameOverCheck = gameField.getGameOver();

				if (gameOverCheck) {
					gamePanel.setVisible(false);
					add(getGameOverPanel());
				} else {
					if (endLevelCheck) {
						gamePanel.setVisible(false);
						if (levelCount < 2) {
							levelCount++;
							add(getResultsPanel());
						} else
							add(getFinalPanel());
					}
				}
			}
		});
		
		KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
		controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKey, "Right");
		controlPanel.getActionMap().put("Right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
            	System.out.println("You pressed Right");
            	buttonRight.doClick();
            }
        });
		
		KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
		controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKey, "Left");
		controlPanel.getActionMap().put("Left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
            	System.out.println("You pressed Left");
            	buttonLeft.doClick();
            }
        });
		
		KeyStroke upKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
		controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKey, "Up");
		controlPanel.getActionMap().put("Up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
            	System.out.println("You pressed Up");
            	buttonUp.doClick();
            }
        });
		
		KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
		controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKey, "Down");
		controlPanel.getActionMap().put("Down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
            	System.out.println("You pressed Down");
            	buttonDown.doClick();
            }
        });

		buttonRight.setFocusable(false);
		buttonLeft.setFocusable(false);
		buttonUp.setFocusable(false);
		buttonDown.setFocusable(false);
		
		
		return controlPanel;
	}

	JPanel getFieldLevelPanel() {

		switch (levelCount) {
		case 1:
			gameField = new FieldLevel1();
			break;
		case 2:
			gameField = new FieldLevel2();
			break;
		// case 3: gameField = new FieldLevel3(); break;
		// case 4: gameField = new FieldLevel4(); break;
		// case 5: gameField = new FieldLevel5(); break;
		// case 6: gameField = new FieldLevel6(); break;
		// case 7: gameField = new FieldLevel7(); break;
		// case 8: gameField = new FieldLevel8(); break;
		// case 9: gameField = new FieldLevel9(); break;
		// case 10: gameField = new FieldLevel10(); break;
		}
		gameField.setEmptyField();

		if (mario != null) {
			gameField.setHero(mario);
			mario.setHealth(100);
			System.out.println(mario.getName() + " Current Position i: " + mario.getCurrentPositionY()
					+ ", Current Position j: " + mario.getCurrentPositionX());
			System.out.println("Coins: " + mario.getCoins());
			System.out.println("Health: " + mario.getHealth());
		} else {
			gameField.setHero(luigi);
			luigi.setHealth(100);
			System.out.println(luigi.getName() + " Current Position i: " + luigi.getCurrentPositionY()
					+ ", Current Position j: " + luigi.getCurrentPositionX());
		}
		gameField.setEnemyOnField();
		gameField.setBonusesOnField();
		gameField.setCoinsOnField();
		gameField.setField();
		return gameField;
	}

	JPanel getResultsPanel() {
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BorderLayout());
		JLabel congratulationLabel = new JLabel();
		Dimension labelSize = new Dimension(600, 200);
		ImageIcon congratulationIcon = new ImageIcon(getClass().getResource("/images/Congratulation.png"));
		congratulationLabel.setIcon(congratulationIcon);
		congratulationLabel.setPreferredSize(labelSize);
		resultsPanel.add(congratulationLabel, BorderLayout.NORTH);
		resultsPanel.add(getCoinsPanel(), BorderLayout.CENTER);
		resultsPanel.add(getChoicePanel2(), BorderLayout.SOUTH);
		return resultsPanel;
	}

	JPanel getCoinsPanel() {
		JPanel coinsPanel = new JPanel();
		coinsPanel.setLayout(new BorderLayout());
		Font font = new Font("Verdana", Font.PLAIN, 50);
		Dimension labelSize = new Dimension(300, 300);

		JLabel coinsLabel = new JLabel();
		ImageIcon coinsIcon = new ImageIcon(getClass().getResource("/images/coins.png"));
		coinsLabel.setIcon(coinsIcon);
		coinsLabel.setPreferredSize(labelSize);

		JLabel coinsResLabel = new JLabel();
		coinsResLabel.setFont(font);
		coinsResLabel.setVerticalTextPosition(JLabel.CENTER);
		coinsResLabel.setHorizontalTextPosition(JLabel.RIGHT);
		coinsResLabel.setPreferredSize(labelSize);
		coinsResLabel.setText("  " + coins.toString());

		coinsPanel.add(coinsLabel, BorderLayout.WEST);
		coinsPanel.add(coinsResLabel, BorderLayout.EAST);
		return coinsPanel;
	}

	JPanel getChoicePanel2() {
		choicePanel2 = new JPanel();
		choicePanel2.setLayout(new BorderLayout());
		choicePanel2.add(nextButton, BorderLayout.WEST);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resultsPanel.setVisible(false);
				gamePanel.removeAll();
				add(getGamePanel());
			}
		});

		choicePanel2.add(exitButton, BorderLayout.EAST);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		return choicePanel2;
	}

	JPanel getGameOverPanel() {
		gameOverPanel = new JPanel();
		setLayout(new BorderLayout());
		gameOverPanel.add(gameOverButton, BorderLayout.NORTH);
		gameOverPanel.add(getChoicePanel3(), BorderLayout.SOUTH);
		return gameOverPanel;
	}

	JPanel getChoicePanel3() {
		JPanel choicePanel3 = new JPanel();
		setLayout(new BorderLayout());
		choicePanel3.add(tryAgainButton, BorderLayout.WEST);
		tryAgainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mario != null)
					mario = null;
				else
					luigi = null;
				gameOverPanel.setVisible(false);
				gamePanel.removeAll();
				add(getChoicePanel());
			}
		});
		choicePanel3.add(exitButton, BorderLayout.EAST);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		return choicePanel3;
	}

	JPanel getFinalPanel() {
		finalPanel = new JPanel();
		setLayout(new BorderLayout());
		finalPanel.add(winnerButton);
		finalPanel.add(getChoicePanel4(), BorderLayout.SOUTH);
		return finalPanel;
	}

	JPanel getChoicePanel4() {
		JPanel choicePanel4 = new JPanel();
		setLayout(new BorderLayout());
		choicePanel4.add(tryAgainButton, BorderLayout.WEST);
		tryAgainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mario != null)
					mario = null;
				else
					luigi = null;
				finalPanel.setVisible(false);
				gamePanel.removeAll();
				add(getChoicePanel());
			}
		});
		choicePanel4.add(exitButton, BorderLayout.EAST);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		return choicePanel4;
	}

}
