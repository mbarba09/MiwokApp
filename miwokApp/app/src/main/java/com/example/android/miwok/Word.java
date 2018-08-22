package com.example.android.miwok;

public class Word {

//    default translation for the word
    private String mDefaultTranslation;

//    Miwok translation for the word
    private String mMiwokTranslation;

//    Image Asset associated with the word
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

//    Sound asset associated with the word/phrase
    private int mAudioResourceID;

//    Create a new Word object.
//    @param defaultTranslation is the word in a language that the user is already familiar with
//    (such as english)
//    @param miwokTranslation is the word in the Miwok language

    public Word (String defaultTranslation, String miwokTranslation, int audioResourceID)    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID = audioResourceID;
    }

//    Create a new Word object.
//    @param defaultTranslation is the word in a language that the user is already familiar with
//    (such as english)
//    @param miwokTranslation is the word in the Miwok language
//    @param imageAsset is the image associated with the word


    public Word (String defaultTranslation, String miwokTranslation, int imageResourceID, int audioResourceID)   {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
        mAudioResourceID = audioResourceID;
    }

//    Get the default translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

//    Get the miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

//    get the image asset associate with the word
    public int getImageResourceID()  {
        return mImageResourceID;
    }

//    check if the current position contains an imageID
    public boolean hasImage()   {
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    };

}
