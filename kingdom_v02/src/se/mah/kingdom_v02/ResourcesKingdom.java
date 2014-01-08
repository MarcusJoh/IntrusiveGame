package se.mah.kingdom_v02;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ResourcesKingdom {
	private static int food = 100;
	private static int gold = 100;
	private static int happy = 100;
	private static boolean gameOver = true;
	private static final String KEY_PREFS_GAME = "Game";
	private static final String KEY_PREFS_FOOD = "Food";
	private static final String KEY_PREFS_GOLD = "Gold";
	private static final String KEY_PREFS_HAPPY = "Happy";

	private static final String APP_SHARED_PREFS = ResourcesKingdom.class
			.getSimpleName(); // Name of the file -.xml
	private static SharedPreferences resourcePref;
	private static Editor resourceEditor;

	public ResourcesKingdom(Context context) {
		ResourcesKingdom.resourcePref = context.getSharedPreferences(APP_SHARED_PREFS,
				Context.MODE_PRIVATE);
		ResourcesKingdom.resourceEditor = resourcePref.edit();
	}

	public static boolean setstateGame() {
		resourcePref.getBoolean(KEY_PREFS_GAME, gameOver);

		resourceEditor.putBoolean(KEY_PREFS_GAME,
				!resourcePref.getBoolean(KEY_PREFS_GAME, gameOver));
		resourceEditor.commit();
		return gameOver;
	}

	public static boolean getstateGame() {
		return resourcePref.getBoolean(KEY_PREFS_GAME, gameOver);
	}

	public static void resetGame() {
		if (resourcePref.getBoolean(KEY_PREFS_GAME, gameOver)) {
		food = 100;
		resourceEditor.putInt(KEY_PREFS_FOOD, food);
		gold = 100;
		resourceEditor.putInt(KEY_PREFS_GOLD, gold);
		happy = 100;
		resourceEditor.putInt(KEY_PREFS_HAPPY, happy);
		resourceEditor.commit();
			
		}
	}

	public static int getFood() {
		return resourcePref.getInt(KEY_PREFS_FOOD, food);
	}

	public static void setFood(int setFood) {
		food = setFood;
	}

	public static void setFoodChange(int setFood) {
		food = resourcePref.getInt(KEY_PREFS_FOOD, food) + setFood;
		resourceEditor.putInt(KEY_PREFS_FOOD, food);
		resourceEditor.commit();
	}

	public static int getGold() {
		return resourcePref.getInt(KEY_PREFS_GOLD, gold);
	}

	public static void setGold(int setGold) {
		gold = setGold;
	}

	public static void setGoldChange(int setGold) {
		gold = resourcePref.getInt(KEY_PREFS_GOLD, gold) + setGold;
		resourceEditor.putInt(KEY_PREFS_GOLD, gold);
		resourceEditor.commit();
	}

	public static int getHappy() {
		return resourcePref.getInt(KEY_PREFS_HAPPY, happy);
	}

	public static void setHappy(int setHappy) {
		happy = setHappy;
	}

	public static void setHappyChange(int setHappy) {
		happy = resourcePref.getInt(KEY_PREFS_HAPPY, happy) + setHappy;
		resourceEditor.putInt(KEY_PREFS_HAPPY, happy);
		resourceEditor.commit();

	}

	public static int CombinedResources()
	{

		return ((food+happy)+gold)/3;
	}

	
	
	public static boolean isSomthingBad()
	{
		return (food <= 0 || gold <= 0|| happy <= 0) ? true : false;
	}

}
