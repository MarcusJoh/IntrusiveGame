package se.mah.kingdom;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Resources {
	private static int food = 100;
	private static int gold;
	private static int happyness;
	public static final String KEY_PREFS_FOOD = "Food";
	  private static final String APP_SHARED_PREFS = Resources.class.getSimpleName(); //  Name of the file -.xml
	private static SharedPreferences resourcePref;
	private static Editor resourceEditor;

	public Resources(Context context) {
		this.resourcePref = context.getSharedPreferences(APP_SHARED_PREFS,
				Activity.MODE_PRIVATE);
		this.resourceEditor = resourcePref.edit();
	}

	public static int getFood() {
		return resourcePref.getInt(KEY_PREFS_FOOD, food);
	}

	public static void setFood(int setFood) {
		food = setFood;
	}

	public static void setFoodChange(int setFood) {
		food =  resourcePref.getInt(KEY_PREFS_FOOD, food) + setFood;
		resourceEditor.putInt(KEY_PREFS_FOOD, food);
		resourceEditor.commit();
	}

	public static int getGold() {
		return gold;
	}

	public static void setGold(int setGold) {
		gold = setGold;
	}

	public static void setGoldChange(int setGold) {
		gold = gold + setGold;
	}

	public static int getHappyness() {
		return happyness;
	}

	public static void setHappyness(int setHappyness) {
		happyness = setHappyness;
	}

	public static void setHappynessChange(int setHappyness) {
		happyness = happyness + setHappyness;
	}

}
