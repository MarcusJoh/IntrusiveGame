package se.mah.kingdom_v02;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.view.WindowManager;

public class EventManager extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      PM_Fragment pm_fragment = new PM_Fragment();
      fragmentTransaction.replace(android.R.id.content, pm_fragment);
   
      fragmentTransaction.commit();
   }
    
}