package ua.hasperskyi;

import javax.swing.ImageIcon;

public class Mario extends Hero {
				
	Mario(String name, int health, int coins) {
		super(name, health, coins);
	}

	@Override
	public void setIconHeroSmall() {
		iconHeroSmall = new ImageIcon(getClass().getResource("/images/mario_small.png"));
	}
	
	@Override
	public void setIconHeroBig() {
		iconHeroBig = new ImageIcon(getClass().getResource("/images/mario_big.png"));
	}
	
	@Override
	public void setIconSuperHero() {
		iconSuperHero = new ImageIcon(getClass().getResource("/images/mario_super.png"));
	}
		
}
