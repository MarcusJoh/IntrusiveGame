package se.mah.kingdom;

public class Resources {
	private int food;
	private int gold;
	private int happyness;
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public void setFoodChange(int food) {
		this.food = this.food+food;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public void setGoldChange(int gold) {
		this.gold = this.gold+gold;
	}
	public int getHappyness() {
		return happyness;
	}
	public void setHappyness(int happyness) {
		this.happyness = happyness;
	}
	public void setHappynessChange(int happyness) {
		this.happyness = this.happyness+happyness;
	}

	
	
}
