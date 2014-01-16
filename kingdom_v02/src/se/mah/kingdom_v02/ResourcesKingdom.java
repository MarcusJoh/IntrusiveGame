package se.mah.kingdom_v02;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ResourcesKingdom {
	private static int food = 100;
	private static int gold = 100;
	private static int happy = 100;
	private static final int maxEvents = 12;
	private static int amountOfEvents = 11;
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
	private static final String KEY_PREFS_EVENTSMADE = "EventsMade";

	private static final String APP_SHARED_PREFS = ResourcesKingdom.class
			.getSimpleName(); // Name of the file -.xml
	private static SharedPreferences resourcePref;
	private static Editor resourceEditor;

	public ResourcesKingdom(Context context) {
		ResourcesKingdom.resourcePref = context.getSharedPreferences(
				APP_SHARED_PREFS, Context.MODE_PRIVATE);
		ResourcesKingdom.resourceEditor = resourcePref.edit();
	}

	public static int getMaxEvents() {
		return maxEvents;
	}

	public static boolean setstateGame() {
		resourcePref.getBoolean(KEY_PREFS_GAME, gameOver);

		resourceEditor.putBoolean(KEY_PREFS_GAME,
				!resourcePref.getBoolean(KEY_PREFS_GAME, gameOver));
		resourceEditor.commit();
		return gameOver;
	}

	public static boolean newPlayerstate() {
		return resourcePref.getBoolean(KEY_PREFS_PLAYER, newPlayer);
	}

	public static boolean newPlayerChange(boolean playerNew) {

		newPlayer = playerNew;
		resourceEditor.putBoolean(KEY_PREFS_PLAYER, newPlayer);
		resourceEditor.commit();
		return newPlayer;

	}

	public static void setAmountOfEventsMadeChange() {
		amountOfEvents = resourcePref.getInt(KEY_PREFS_EVENTSMADE,
				amountOfEvents) + 1;
		resourceEditor.putInt(KEY_PREFS_EVENTSMADE, amountOfEvents);
		resourceEditor.commit();
	}

	public static int getAmountOfEventsMade() {
		return resourcePref.getInt(KEY_PREFS_EVENTSMADE, amountOfEvents);
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
			amountOfEvents = 0;
			resourceEditor.putInt(KEY_PREFS_EVENTSMADE, amountOfEvents);
			eventOption = 1;
			resourceEditor.putInt(KEY_PREFS_EVENTOPTION, eventOption);
			gameOver = true;
			resourceEditor.putBoolean(KEY_PREFS_GAME, gameOver);
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
		boolean trying = false;
		try {
			trying = (getFood() <= 0 || getGold() <= 0 || getHappy() <= 0 || getAmountOfEventsMade() >= getMaxEvents()) ? true
					: false;
		} catch (Exception e) {
			trying = true;
		}
		return trying;
	}

}
