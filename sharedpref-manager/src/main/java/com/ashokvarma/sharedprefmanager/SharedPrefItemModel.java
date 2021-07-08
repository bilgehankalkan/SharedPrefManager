package com.ashokvarma.sharedprefmanager;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class description
 *
 * @author ashokvarma
 * @version 1.0
 * @since 22 Jun 2017
 */
class SharedPrefItemModel implements Comparable<SharedPrefItemModel> {
    @NonNull
    private final String mKey;
    @NonNull
    private final Object mValue;

    @NonNull
    private final String mDisplayText;

    @NonNull
    private final SpannableString mTitle;

    SharedPrefItemModel(@NonNull String key, @NonNull Object value, @ColorInt int keyColor) {
        this.mKey = key;
        this.mValue = value;

        String valueString = mValue.toString();

        if (mValue instanceof String) {
            // check if there is a better way  to do this
            try {
                valueString = new JSONObject(valueString).toString(4);
            } catch (JSONException ex) {
                try {
                    valueString = new JSONArray(valueString).toString(4);
                } catch (JSONException ex1) {

                }
            }
        }

        SpannableString titleText = new SpannableString(key);
        titleText.setSpan(new ForegroundColorSpan(keyColor), 0, mKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, mKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleText.setSpan(new RelativeSizeSpan(1.5f), 0, mKey.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mDisplayText = valueString;
        mTitle = titleText;
    }

    @NonNull
    String getKey() {
        return mKey;
    }

    @NonNull
    Object getValue() {
        return mValue;
    }

    @NonNull
    String getDisplayText() {
        return mDisplayText;
    }

    @NonNull
    SpannableString getTitleText() {
        return mTitle;
    }

    @Override
    public int compareTo(@NonNull SharedPrefItemModel model) {
        return mDisplayText.toString().compareTo(model.mDisplayText.toString());
    }
}