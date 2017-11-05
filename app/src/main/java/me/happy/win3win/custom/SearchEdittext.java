package me.happy.win3win.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by JY on 2017-05-05.
 */

public class SearchEdittext extends android.support.v7.widget.AppCompatEditText {

    private OnFocusChangeListener onFocusChangeListener;


    public SearchEdittext(Context context) {
        super(context);
    }

    public SearchEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_SEARCH  || keyCode == event.KEYCODE_BACK){
            clearFocus();
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        return super.onKeyPreIme(keyCode, event);
    }

}
