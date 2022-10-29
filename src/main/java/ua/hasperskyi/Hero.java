package ua.hasperskyi;

import javax.swing.ImageIcon;

/**
 * Main hero class.
 *
 * @author Oleksii Hasperskyi
 * @version 1.0
 */

public abstract class Hero {

	protected String name;
	protected int health = 100;
	protected int coins;
	protected int superHeroMove;
	protected ImageIcon iconHeroSmall;
	protected ImageIcon iconHeroBig;
	protected ImageIcon iconSuperHero;
	protected int currentPositionX;
	protected int currentPositionY;

	Hero () {}
	
	Hero (String name, int health, int coins) {
		this.name = name;
		this.health=health;
		this.coins=coins;
		setIconHeroSmall();
		setIconHeroBig();
		setIconSuperHero();
		System.out.println(toString());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIconHeroSmall() {}

	public void setIconHeroBig() {}
	
	public void setIconSuperHero() {}
	
	public void setHealth(int health) {
		this.health = health;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setSuperHeroMove(int numberOfMove) {
		this.superHeroMove = numberOfMove;
	}
	
	public void setCurrentPositionY(int i) {
		this.currentPositionY = i;
	}

	public void setCurrentPositionX(int j) {
		this.currentPositionX = j;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getCoins() {
		return coins;
	}

	public int getSuperHeroMove() {
		return superHeroMove;
	}
	
	public ImageIcon getImageIconSmall() {
		return iconHeroSmall;
	}
	
	public ImageIcon getImageIconBig() {
		return iconHeroBig;
	}
	
	public ImageIcon getImageIconSuperHero() {
		return iconSuperHero;
	}
	
	public int getCurrentPositionX() {
		return currentPositionX;
	}

	public int getCurrentPositionY() {
		return currentPositionY;
	}


	public String toString() {
		return "[Name: " + name + "; Health: " + health + "; Coins: " 
				+ coins + "; SuperHero Move: " + superHeroMove + "]";
	}
		
}