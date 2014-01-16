package se.mah.kingdom_v02;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;

public class EventManager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		EventFragment pm_fragment = new EventFragment();
		fragmentTransaction.replace(android.R.id.content, pm_fragment);

		fragmentTransaction.commit();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getWindow()
				.clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}

	@Override
	public void onResume() {
		super.onResume();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}

	@Override
	public void onPause() {
		super.onPause();
		getWindow()
				.clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}
}