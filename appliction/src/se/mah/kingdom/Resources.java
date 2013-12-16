package se.mah.kingdom;

public class Resources {
	private static int food;
	private static int gold;
	private static int happyness;
	public static int getFood() {
		return food;
	}
	public static void setFood(int setFood) {
		food = setFood;
	}
	public static void setFoodChange(int setFood) {
		food = food+setFood;
	}
	public static int getGold() {
		return gold;
	}
	public static void setGold(int setGold) {
		gold = setGold;
	}
	public static void setGoldChange(int setGold) {
		gold = gold+setGold;
	}
	public static int getHappyness() {
		return happyness;
	}
	public static void setHappyness(int setHappyness) {
		happyness = setHappyness;
	}
	public static void setHappynessChange(int setHappyness) {
		happyness = happyness+setHappyness;
	}

	
	
}
