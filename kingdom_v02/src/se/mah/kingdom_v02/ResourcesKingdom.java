package se.mah.kingdom_v02;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ResourcesKingdom {
	private static int food = 500;
	private static int gold = 500;
	private static int happy = 500;
	private static String eventName = "e1";
	private static int eventOption = 1;
	private static boolean gameOver = true;// true= its a dead player
	private static boolean newPlayer = true;// true= its a new player
	private static final String KEY_PREFS_EVENTOPTION = "EventOption";
	private static final String KEY_PREFS_EVENTNAME = "Event";
	private static final String KEY_PREFS_GAME = "Game";
	private static final String KEY_PREFS_PLAYER = "Player";
	private static final String KEY_PREFS_FOOD = "Food";
	private static final String KEY_PREFS_GOLD = "Gold";
	private static final String KEY_PREFS_HAPPY = "Happy";

	private static final String APP_SHARED_PREFS = ResourcesKingdom.class
			.getSimpleName(); // Name of the file -.xml
	private static SharedPreferences resourcePref;
	private static Editor resourceEditor;

	public ResourcesKingdom(Context context) {
		ResourcesKingdom.resourcePref = context.getSharedPreferences(
				APP_SHARED_PREFS, Context.MODE_PRIVATE);
		ResourcesKingdom.resourceEditor = resourcePref.edit();
	}

	public static boolean setstateGame() {
		resourcePref.getBoolean(KEY_PREFS_GAME, gameOver);

		resourceEditor.putBoolean(KEY_PREFS_GAME,
				!resourcePref.getBoolean(KEY_PREFS_GAME, gameOver));
		resourceEditor.commit();
		return gameOver;
	}

	public static boolean newPlayerstate() {
		resourcePref.getBoolean(KEY_PREFS_PLAYER, newPlayer);

		return newPlayer;
	}

	public static void newPlayerChange() {
		resourcePref.getBoolean(KEY_PREFS_PLAYER, newPlayer);
		resourceEditor.putBoolean(KEY_PREFS_PLAYER,
				!resourcePref.getBoolean(KEY_PREFS_PLAYER, newPlayer));
		resourceEditor.commit();

	}

	public static String getEventName() {
		return resourcePref.getString(KEY_PREFS_EVENTNAME, eventName);
	}

	public static void setEventName(String event) {
		eventName = event;
		resourceEditor.putString(KEY_PREFS_EVENTNAME, eventName);
		resourceEditor.commit();
	}

	public static void setEventOption(int option) {
		eventOption = option;
		resourceEditor.putInt(KEY_PREFS_EVENTOPTION, eventOption);
		resourceEditor.commit();
	}

	public static int getEventOption() {
		return resourcePref.getInt(KEY_PREFS_EVENTOPTION, eventOption);
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
			eventName = "e1";
			resourceEditor.putString(KEY_PREFS_EVENTNAME, eventName);
			eventOption = 1;
			resourceEditor.putInt(KEY_PREFS_EVENTOPTION, eventOption);
			setstateGame();
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

	public static int CombinedResources() {

		return ((food + happy) + gold) / 3;
	}

	public static boolean ResourceIsZero() {
		return (food <= 0 || gold <= 0 || happy <= 0) ? true : false;
	}

}
