package se.mah.kingdom_v02;

import java.util.Random;

public class JesterActivity{

	private String[][] lines = new String[2][50];
	
	protected void onCreate() {
		AddString();
	}

	
	private void AddString()
	{
		lines[0][0] = "food";
		lines[1][0] = "Your foodsupply is almost as big as you!";
		lines[0][1] = "food";
		lines[1][1] = "You don't have much food and with your meals if will be empty by tomorrow!";
		lines[0][2] = "food";
		lines[1][2] = "Your food storage is not empty nor full, so no more of your grand parties!";
		
		
		lines[0][3] = "gold";
		lines[1][3] = "Your gold is low. No more horses for you!";
		lines[0][4] = "gold";
		lines[1][4] = "With your gold you are just middle-class!";
		lines[0][5] = "gold";
		lines[1][5] = "Your gold is through the roof! Maybe you should go for a swim in your fortune?";
		
		
		lines[0][6] = "happy";
		lines[1][6] = "Are you trying to force a revolt? Maybe it's time to cheer up your people.";
		lines[0][7] = "happy";
		lines[1][7] = "Not much effort to make your people happy huh? Well they aren't sad either so...";
		lines[0][8] = "happy";
		lines[1][8] = "Are you trying to become the man of they year? I didn't even know people could be that happy";
		
		
	}
	
	public String ShowString()
	{
		String toShow = "";
		RandomizeResources();
		int gold = ResourcesKingdom.getGold();
		int happy = ResourcesKingdom.getHappy();
		int food = ResourcesKingdom.getFood();
		Random rand = new Random();
		int showResource = rand.nextInt(2);

		if (showResource == 0) 
		{
			if (food >= 70)
				toShow = lines[1][0];
			else if (food < 70 && food > 40)
				toShow = lines[1][2];
			else if (food <= 40)
				toShow = lines[1][1];
		} 
		
		else if (showResource == 1) 
		{
			if (gold >= 70)
				toShow = lines[1][5];
			else if (gold < 70 && gold > 40)
				toShow = lines[1][4];
			else if (gold <= 40)
				toShow = lines[1][3];
		}

		else if (showResource == 2) 
		{
			if (happy >= 70)
				toShow = lines[1][8];
			else if (happy < 70 && happy > 40)
				toShow = lines[1][7];
			else if (happy <= 40)
				toShow = lines[1][6];
		}

		return toShow;
	}

	private void RandomizeResources()
	{
		Random rand = new Random();
		int setFood = rand.nextInt(99)+1;
		int setGold = rand.nextInt(99)+1;
		int setHappy = rand.nextInt(99)+1;
		ResourcesKingdom.setFood(setFood);
		ResourcesKingdom.setGold(setGold);
		ResourcesKingdom.setHappy(setHappy);
	}

}
