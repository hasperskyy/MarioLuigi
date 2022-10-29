package ua.hasperskyi;

import javax.swing.ImageIcon;

public class Turtle extends Enemy{
	
	@Override
	public void setName() {
		name = "Turtle";
	}
		
	@Override
	public void setIconEnemy() {
		iconEnemy = new ImageIcon(getClass().getResource("/images/turtle_small.png"));
	}
}
