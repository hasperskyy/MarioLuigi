package ua.hasperskyi;

import javax.swing.ImageIcon;

/**
 * Main bonus class.
 *
 * @author Oleksii Hasperskyi
 * @version 1.0
 */

public abstract class Bonuses {

	protected String name;
	protected ImageIcon iconBonus;

	Bonuses() {
		setName();
		setIconBonus();
	}

	
	public void setName() {}

	public void setIconBonus() {}

	public String getName() {
		return name;
	}

	public ImageIcon getIconBonus() {
		return iconBonus;
	}
}