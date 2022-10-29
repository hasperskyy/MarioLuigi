package ua.hasperskyi;

import javax.swing.ImageIcon;

public class Medical extends Bonuses{

	@Override
	public void setName() {
		name = "Medical";
	}
		
	@Override
	public void setIconBonus() {
		iconBonus = new ImageIcon(getClass().getResource("/images/medical_small.png"));
	}
}

