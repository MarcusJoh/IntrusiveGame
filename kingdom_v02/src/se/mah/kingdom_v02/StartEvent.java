package se.mah.kingdom_v02;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class StartEvent extends Activity {
	private ResourcesKingdom resourcePrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_event);
		resourcePrefs = new ResourcesKingdom(
				StartEvent.this.getApplicationContext());
	}

	public void newGame(View v) {

		ResourcesKingdom.newPlayerChange();
		Intent intent = new Intent(StartEvent.this, StoryManager.class);

		StartEvent.this.finish();
		startActivity(intent);

	}
}
