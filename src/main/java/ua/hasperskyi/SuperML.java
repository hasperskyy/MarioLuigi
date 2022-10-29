package ua.hasperskyi;

import javax.swing.ImageIcon;

public class SuperML extends Bonuses{
	
	@Override
	public void setName() {
		name = "Super";
	}
		
	@Override
	public void setIconBonus() {
		iconBonus = new ImageIcon(getClass().getResource("/images/super_small.png"));
	}
}
