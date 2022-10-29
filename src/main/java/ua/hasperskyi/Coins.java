package ua.hasperskyi;

import javax.swing.ImageIcon;

public class Coins {
	
	protected String name;
	protected ImageIcon iconCoins;

	Coins() {
		name = "Coins";
		setIconCoins();
	}
	
	public void setIconCoins() {
		iconCoins = new ImageIcon(getClass().getResource("/images/coin_small.png"));
	}
	
	public String getName() {
		return name;
	}

	public ImageIcon getIconCoins() {
		return iconCoins;
	}
}