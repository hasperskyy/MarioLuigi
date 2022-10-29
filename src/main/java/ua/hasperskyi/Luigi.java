package ua.hasperskyi;

import javax.swing.ImageIcon;

public class Luigi extends Hero {
		
	Luigi(String name, int health, int coins) {
		super(name, health, coins);
	}

	@Override
	public void setIconHeroSmall() {
		iconHeroSmall = new ImageIcon(getClass().getResource("/images/luigi_small.png"));
	}
	
	@Override
	public void setIconHeroBig() {
		iconHeroBig = new ImageIcon(getClass().getResource("/images/luigi_big.png"));
	}
	
	@Override
	public void setIconSuperHero() {
		iconSuperHero = new ImageIcon(getClass().getResource("/images/luigi_super.png"));
	}

}
