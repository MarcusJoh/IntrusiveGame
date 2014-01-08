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

public class EventFragment extends Fragment implements
		ObservableScrollView.Callbacks {

	private TextView eventText;
	private TextView option1Text;
	private TextView option2Text;
	private TextView option3Text;
	private TextView option4Text;
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * Inflate the layout for this fragment
		 */
		Toast.makeText(getActivity(), "PM", Toast.LENGTH_SHORT).show();

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.event_fragment, container, false);

		mObservableScrollView = (ObservableScrollView) rootView
				.findViewById(R.id.scroll_view);
		mObservableScrollView.setCallbacks(this);

		mPlaceholderView = rootView.findViewById(R.id.placeholder);

		mObservableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						onScrollChanged(mObservableScrollView.getScrollY());
					}
				});

		event_am = (AudioManager) getActivity().getSystemService(
				Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		int musicfileid;
		timer = new Timer();
		time = Calendar.getInstance();
		update = 1;
		increaseInterval = 1 * 60;
		timesHappend = 0;
		increaseRate = 1;
		totalTimeElapsed = 0;
		likenessOfEmergency = 1;
		emergencyHappening = false;
		Resources res = this.getResources();
		musicfileid = getResources().getIdentifier("event_voice", "raw",
				"se.mah.kingdom_v02");
		event_player = MediaPlayer.create(getActivity(), musicfileid);
		event_player.start();

		resourcePrefs = new ResourcesKingdom(getActivity()
				.getApplicationContext());

		Resources resStory = getResources();
		String[] eventArrey = resStory.getStringArray(R.array.event_picker);
		Random r = new Random();
		int min = 0;
		int max = eventArrey.length;
		int i1 = r.nextInt(max - min) + min;
		Log.i("id ", Integer.toString(i1));
		String eventid = eventArrey[i1];
		String eventCont = null;
		String eventOption1 = null;
		String eventOption2 = null;
		String eventOption3 = null;
		String eventOption4 = null;
		String eventChain = null;// getting this from resources
		String[] optionSplit = eventid.split("/");
		eventCont = optionSplit[1];
		eventOption1 = optionSplit[2];
		eventOption2 = optionSplit[3];
		eventOption3 = optionSplit[4];
		eventOption4 = optionSplit[5];
		eventText = (TextView) rootView.findViewById(R.id.eventText);
		option1Text = (TextView) rootView.findViewById(R.id.text_option1);
		option2Text = (TextView) rootView.findViewById(R.id.text_option2);
		option3Text = (TextView) rootView.findViewById(R.id.text_option3);
		option4Text = (TextView) rootView.findViewById(R.id.text_option4);

		String[] optionChain = optionSplit[6].split("¤");
		int optionNumber = Integer.parseInt(optionChain[0]);
		if (optionNumber == 2) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2);

			eventText.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");

			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			option1Text.setText(optionEvent1);
			option2Text.setText(optionEvent2);
			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];

			eventChain = optionChain[1];
		}
		if (optionNumber == 3) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2 + " " + eventOption3);
			eventText.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");
			String[] optionEffect3 = eventOption3.split("¤");

			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			optionEvent3 = optionEffect3[0];
			option1Text.setText(optionEvent1);
			option2Text.setText(optionEvent2);
			option3Text.setText(optionEvent3);
			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];
			resourceEffect3 = optionEffect3[1];

			eventChain = optionChain[1];
		}
		if (optionNumber == 4) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2 + " " + eventOption3 + " " + eventOption4);
			eventText.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");
			String[] optionEffect3 = eventOption3.split("¤");
			String[] optionEffect4 = eventOption4.split("¤");
			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			optionEvent3 = optionEffect3[0];
			optionEvent4 = optionEffect4[0];
			option1Text.setText(optionEvent1);
			option2Text.setText(optionEvent2);
			option3Text.setText(optionEvent3);
			option4Text.setText(optionEvent4);
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
		}
		return rootView;
	}

	@Override
	public void onScrollChanged(int scrollY) {
		eventText.setTranslationY(Math.max(mPlaceholderView.getTop(), scrollY));

	}

	@Override
	public void onDownMotionEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpOrCancelMotionEvent() {
		// TODO Auto-generated method stub

	}

	public void btn_option1_confirm(View v) {

		int resourceNumber = 0;

		if (optionEvent1 != null) {
			if (resourceEffect1.length() <= 7) {
				String[] resourceEffect = resourceEffect1.split("#");

				if (resourceEffect[0].equals("gold")) {
					Log.i("resourceEffect", resourceEffect[0] + ""
							+ resourceEffect[1]);

					resourceNumber = Integer.parseInt(resourceEffect[1]);

					ResourcesKingdom.setGoldChange(resourceNumber);
				}
				if (resourceEffect[0].equals("happ")) {
					Log.i("resourceEffect", resourceEffect[0] + ""
							+ resourceEffect[1]);
					resourceNumber = Integer.parseInt(resourceEffect[1]);

					ResourcesKingdom.setHappyChange(resourceNumber);
				}
				if (resourceEffect[0].equals("food")) {
					Log.i("resourceEffect", resourceEffect[0] + ""
							+ resourceEffect[1]);
					resourceNumber = Integer.parseInt(resourceEffect[1]);

					ResourcesKingdom.setFoodChange(resourceNumber);
				} else {
					Log.i("error", resourceEffect[0] + "" + resourceEffect[1]);
				}

			}

		}
	}
}