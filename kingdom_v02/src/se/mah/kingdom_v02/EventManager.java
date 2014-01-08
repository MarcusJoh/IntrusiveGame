package se.mah.kingdom_v02;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class EventManager extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      EventFragment pm_fragment = new EventFragment();
      fragmentTransaction.replace(android.R.id.content, pm_fragment);
   
      fragmentTransaction.commit();
   }
    
}