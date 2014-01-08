package se.mah.kingdom_v02;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;

import se.mah.kingdom_v02.ObservableScrollView;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;


public class PM_Fragment extends Fragment implements ObservableScrollView.Callbacks{
	
	private TextView mStickyView;
    private View mPlaceholderView;
    private ObservableScrollView mObservableScrollView;
	
	private MediaPlayer event_player;
	private AudioManager event_am;

	private ResourcesKingdom resourcePrefs;
	public String optionEvent1 = null;
	public String optionEvent2 = null;
	public String optionEvent3 = null;
	public String optionEvent4 = null;
	public String resourceEffect1 = null;
	public String resourceEffect2 = null;
	public String resourceEffect3 = null;
	public String resourceEffect4 = null;


	private Timer timer;
	private int update;
	private int timesHappend;
	private Calendar time;
	private double increaseInterval;
	private int increaseRate;
	private double totalTimeElapsed;
	private int likenessOfEmergency;
	private boolean emergencyHappening;
	
   @Override
   public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
      /**
       * Inflate the layout for this fragment
       */
	   Toast.makeText(getActivity(), "PM", Toast.LENGTH_SHORT).show();

	   ViewGroup rootView = (ViewGroup) inflater
               .inflate(R.layout.pm_fragment, container, false);
	   
       mObservableScrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll_view);
       mObservableScrollView.setCallbacks(this);

       mStickyView = (TextView) rootView.findViewById(R.id.sticky);
       mStickyView.setText(R.string.sticky_item);
       mPlaceholderView = rootView.findViewById(R.id.placeholder);

       mObservableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
               new ViewTreeObserver.OnGlobalLayoutListener() {
                   @Override
                   public void onGlobalLayout() {
                       onScrollChanged(mObservableScrollView.getScrollY());
                   }
               });

       
      
      
		event_am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		int musicfileid;
		Resources res = this.getResources();
		musicfileid = getResources().getIdentifier("event_voice", "raw",
				"se.mah.kingdom_v02");
		event_player = MediaPlayer.create(getActivity(), musicfileid);
		event_player.start();

		resourcePrefs = new ResourcesKingdom(getActivity().getApplicationContext());
		/*
		Resources resStory = getResources();
		String[] eventArrey = resStory.getStringArray(R.array.event_picker);
		Random r = new Random();
		int min = 0;
		int max = eventArrey.length;
		int i1 = r.nextInt(max - min) + min;
		Log.i("id ", Integer.toString(i1));
		String eventid = eventArrey[i1];
		String eventCont = "a";
		String eventOption1 = "a";
		String eventOption2 = "a";
		String eventOption3 = "a";
		String eventOption4 = "a";
		String eventChain = null;// getting this from resources
		String[] optionSplit = eventid.split("/");
		eventCont = optionSplit[1];
		eventOption1 = optionSplit[2];
		eventOption2 = optionSplit[3];
		eventOption3 = optionSplit[4];
		eventOption4 = optionSplit[5];
		TextView eventtext = (TextView) getActivity().findViewById(R.id.TextView1);
		String[] optionChain = optionSplit[6].split("¤");
		int optionNumber = Integer.parseInt(optionChain[0]);
		if (optionNumber == 2) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2);

			eventtext.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");

			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];

			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];

			eventChain = optionChain[1];
		}
		if (optionNumber == 3) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2 + " " + eventOption3);
			eventtext.setText(eventCont);

		timer = new Timer();
		time = Calendar.getInstance();
		update = 1;
		increaseInterval = 1 * 60;
		timesHappend = 0;
		increaseRate = 1;
		totalTimeElapsed = 0;
		likenessOfEmergency = 1;
		emergencyHappening = false;

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");
			String[] optionEffect3 = eventOption3.split("¤");

			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			optionEvent3 = optionEffect3[0];
			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];
			resourceEffect3 = optionEffect3[1];

			eventChain = optionChain[1];
		}
		if (optionNumber == 4) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2 + " " + eventOption3 + " " + eventOption4);
			eventtext.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");
			String[] optionEffect3 = eventOption3.split("¤");
			String[] optionEffect4 = eventOption4.split("¤");
			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			optionEvent3 = optionEffect3[0];
			optionEvent4 = optionEffect4[0];
			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];
			resourceEffect3 = optionEffect3[1];
			resourceEffect4 = optionEffect4[1];

			eventChain = optionChain[1];
		}
		if (eventChain != null) {

			if (eventChain == "e1p2") {
				Log.i("chain", "saved " + eventChain);

			}
		}*/
		return rootView;
   }

@Override
public void onScrollChanged(int scrollY) {
	 mStickyView.setTranslationY(Math.max(mPlaceholderView.getTop(), scrollY));
	
}

@Override
public void onDownMotionEvent() {
	// TODO Auto-generated method stub
	
}

@Override
public void onUpOrCancelMotionEvent() {
	// TODO Auto-generated method stub
	
}
}