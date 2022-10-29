package ua.hasperskyi;

import javax.swing.ImageIcon;

public class Eagle extends Enemy{
	
	@Override
	public void setName() {
		name = "Eagle";
	}
		
	@Override
	public void setIconEnemy() {
		iconEnemy = new ImageIcon(getClass().getResource("/images/eagle_small_2_1.png"));
	}

}
