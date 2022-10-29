package ua.hasperskyi;

import javax.swing.ImageIcon;

/**
 * Main enemy class.
 *
 * @author Oleksii Hasperskyi
 * @version 1.0
 */

public abstract class Enemy {

	protected String name;
	protected ImageIcon iconEnemy;
	
	Enemy() {
		setName();
		setIconEnemy();
	}
	
	public void setName() {}

	public void setIconEnemy() {}

	public String getName() {
		return name;
	}

	public ImageIcon getIconEnemy() {
		return iconEnemy;
	}
	
	@Override
	public String toString() {
		return "[Name: " + name + "]";
	}
}