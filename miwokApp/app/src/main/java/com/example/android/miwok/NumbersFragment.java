package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    //    handles the playback of all audio files
    private MediaPlayer mMediaPlayer;

    //    handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (i == AudioManager.AUDIOFOCUS_GAIN)   {
                mMediaPlayer.start();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS)    {
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        //        create and set up the {@link Audio Manager} to request focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);



        //create an arrayList of words
        final ArrayList<Word> words = new ArrayList<Word>();
//        words.add(new Word("english", "miwok", imageResource));
        words.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six", "Temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "Wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "Na'aacha", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceID());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });


        return rootView;
    }

}
