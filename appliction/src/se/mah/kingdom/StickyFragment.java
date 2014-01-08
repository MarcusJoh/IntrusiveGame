/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.mah.kingdom;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class StickyFragment extends Fragment implements ObservableScrollView.Callbacks {
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

    public StickyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_content, container, false);

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
				"se.mah.kingdom");
		event_player = MediaPlayer.create(getActivity(), musicfileid);
		event_player.start();
		resourcePrefs = new ResourcesKingdom(getActivity().getApplicationContext());

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
		
		TextView eventtext = (TextView) getActivity().findViewById(R.id.text_event);
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
		}
		timer = new Timer();
		time = Calendar.getInstance();
		update = 1;
		increaseInterval = 1 * 60;
		timesHappend = 0;
		increaseRate = 1;
		totalTimeElapsed = 0;
		likenessOfEmergency = 1;
		emergencyHappening = false;
		
		
		return rootView;
    }

    @Override
    public void onScrollChanged(int scrollY) {
        mStickyView.setTranslationY(Math.max(mPlaceholderView.getTop(), scrollY));
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent() {
    }
    public void onStart() {
		event_player.start();
		super.onStart();
	}

	public void onDestroy() {
		event_player.stop();
		event_player.release();
		super.onDestroy();
	}

	public void onPause() {
		event_player.pause();
		super.onPause();
		getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}

	public void onStop() {
		super.onStop();

	}

	public void btnSound(View v) {
		Toast.makeText(getActivity(), "music set", Toast.LENGTH_SHORT).show();
		if (event_am.getMode() == AudioManager.MODE_IN_CALL) {
			event_am.setMode(AudioManager.MODE_NORMAL);
		} else {
			event_player.pause();
			event_am.setMode(AudioManager.MODE_IN_CALL);
			event_player.start();
		}
	}

	public void btnOption1(View v) {
		TextView text = (TextView) getActivity().findViewById(R.id.text_options);

		text.setText(optionEvent1);
	}

	public void btnOption2(View v) {
		TextView text = (TextView) getActivity().findViewById(R.id.text_options);

		text.setText(optionEvent2);

	}

	public void btnOption3(View v) {
		TextView text = (TextView) getActivity().findViewById(R.id.text_options);

		text.setText(optionEvent3);

	}

	public void btnOption4(View v) {
		TextView text = (TextView) getActivity().findViewById(R.id.text_options);

		text.setText(optionEvent4);

	}

	public void btnConfirm(View v) {
		TextView text = (TextView) getActivity().findViewById(R.id.text_options);
		if (text.getText().toString().equals(optionEvent1)) {
			if (optionEvent1 != null) {
				if (resourceEffect1.length() <= 6) {
					String[] resourceEffect = resourceEffect1.split("#");
					Log.i("resourceEffect", resourceEffect[0] + ""
							+ resourceEffect[1]);
					
				} else {Toast.makeText(getActivity(), "option1 selected", Toast.LENGTH_SHORT)
							.show();
				}
			}
		} else if (text.getText().toString().equals(optionEvent2)) {
			if (optionEvent1 != null) {
				String[] resourceEffect = resourceEffect2.split("#");
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);

				Toast.makeText(getActivity(), "option2 selected", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (text.getText().toString().equals(optionEvent3)) {
			if (optionEvent1 != null) {
				String[] resourceEffect = resourceEffect3.split("#");
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);
			}

			Toast.makeText(getActivity(), "option3 selected", Toast.LENGTH_SHORT).show();
		} else if (text.getText().toString().equals(optionEvent4)) {
			if (optionEvent1 != null) {
				String[] resourceEffect = resourceEffect4.split("#");
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);
			}

			Toast.makeText(getActivity(), "option4 selected", Toast.LENGTH_SHORT).show();
		} else {

			Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
		}

	}

	public void btnStop(View v) {
		Toast.makeText(getActivity(), "music stop", Toast.LENGTH_SHORT).show();
		if (event_player.isPlaying()) {
			event_player.pause();
		} else {
			event_player.start();
		}
	}

	public void btnReplay(View v) {
		Toast.makeText(getActivity(), "music reset", Toast.LENGTH_SHORT).show();
		event_player.stop();
		event_player = MediaPlayer.create(getActivity(), R.raw.event_voice);
		event_player.start();
	}

	public void option1_button(View v) {

		ResourcesKingdom.setFoodChange(-5);
		Toast.makeText(getActivity(), "food yes", Toast.LENGTH_SHORT).show();
	}

	public void option2_button(View v) {
		int food = ResourcesKingdom.getFood();

		Toast.makeText(getActivity(), "food " + Integer.toString(food),
				Toast.LENGTH_SHORT).show();

	}

	public void option3_button(View v) {

		ResourcesKingdom.setGoldChange(-5);
		Toast.makeText(getActivity(), "gold yes", Toast.LENGTH_SHORT).show();
	};

	public void option4_button(View v) {
		int gold = ResourcesKingdom.getGold();

		Toast.makeText(getActivity(), "gold " + Integer.toString(gold),
				Toast.LENGTH_SHORT).show();

	}

	public void option5_button(View v) {

		ResourcesKingdom.setHappyChange(-5);
		Toast.makeText(getActivity(), "happy yes", Toast.LENGTH_SHORT).show();
	}

	public void option6_button(View v) {
		int happy = ResourcesKingdom.getHappy();

		Toast.makeText(getActivity(), "happy " + Integer.toString(happy),
				Toast.LENGTH_SHORT).show();

	}

	public void option7_button(View v) {
		ResourcesKingdom.resetGame();

		Toast.makeText(getActivity(), "game over ", Toast.LENGTH_SHORT).show();

	}

	public void option8_button(View v) {

	}

	public void SomeEmergency() {
		Toast.makeText(getActivity(), "Lord the kingdom is on fire, open the dam!", Toast.LENGTH_SHORT).show();
		emergencyHappening = true;
	}

	public boolean UpdateRate_Increase() {
		if (totalTimeElapsed >= increaseInterval) {
			increaseRate++;
			totalTimeElapsed = 0;
			Toast.makeText(getActivity(), "increaseRate is up ", Toast.LENGTH_LONG)
					.show();
			return true;
		}
		return false;
	}


	public void timer_button(View v) {

	}



}
