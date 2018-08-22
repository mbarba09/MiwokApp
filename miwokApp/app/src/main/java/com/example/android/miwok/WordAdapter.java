package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>  {

//    resource ID for the background color for this list of words
    private int mColorResourceID;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceID) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceID = colorResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link AndroidFlavor} object located at this position in the list
            Word currentWord = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            // Get the miwok translation of the word and
            // set this text on the miwok TextView
            miwokTextView.setText(currentWord.getMiwokTranslation());

            // Find the TextView in the list_item.xml layout with the ID version_number
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            // Get the word in the default system language and
            // set this text on the default TextView
            defaultTextView.setText(currentWord.getDefaultTranslation());

//            set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
//        find the colorthat the resource id maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
//        set the background color of the text view container
        textContainer.setBackgroundColor(color);

        //            find the imageView in the list_item.xml layout with the id image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
                //            get the image associated with the word and set this image on the image ImageView
                imageView.setImageResource(currentWord.getImageResourceID());
            } else {
            imageView.setVisibility(View.GONE);
        }
            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            return listItemView;

        }

    }

