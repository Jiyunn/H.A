package kr.happy.myarmy.Custom;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.roughike.bottombar.BottomBar;

import kr.happy.myarmy.MainActivity;
import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-05-03.
 */

public class LPEdittext extends android.support.v7.widget.AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener{

    private Drawable clearX;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;
    private BottomBar bottomBar;



    public LPEdittext(Context context) {
        super(context);
        initDrawable();
    }

    public LPEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDrawable();
    }

    public LPEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDrawable();
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }



    private void initDrawable(){
        Drawable tempDrawable= ContextCompat.getDrawable(getContext(), R.drawable.cancel_24dp);
        clearX= DrawableCompat.wrap(tempDrawable); //for lower than lollipop
        DrawableCompat.setTintList(clearX, getHintTextColors()); //match hint color
        clearX.setBounds(0,0 , (int)(clearX.getIntrinsicWidth() /1.5), (int)(clearX.getIntrinsicHeight() /1.5)); //set size

        setClearIconVisible(false);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        bottomBar=(BottomBar)(((MainActivity)getContext()).findViewById(R.id.bottom_bar));

        if(hasFocus) {
            setClearIconVisible(getText().length() > 0); //글씨치는지에따라 보여지게, 안보여지게하기.
            v.getBackground().setColorFilter(getCurrentHintTextColor(), PorterDuff.Mode.SRC_IN); //밑줄색깔
            bottomBar.getShySettings().hideBar();

        }else {
            setClearIconVisible(false);
            v.getBackground().setColorFilter(null);
            bottomBar.getShySettings().showBar();
        }
        if(onFocusChangeListener !=null)
            onFocusChangeListener.onFocusChange(v, hasFocus);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if( isFocused())
            setClearIconVisible(s.length() >0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setClearIconVisible(boolean visible) {
        clearX.setVisible(visible, false);
        setCompoundDrawables(null, null, visible ? clearX : null, null); }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x= (int) event.getX();

        //when btn x is visible, and event's lo = x., clear text
        if(clearX.isVisible() && x > getWidth() - getPaddingRight() - clearX.getIntrinsicWidth()){
            if (event.getAction() == event.ACTION_UP){
                setError(null);
                setText(null);
            }
            return true;
        }
        if(onTouchListener !=null){
            return onTouchListener.onTouch(v, event);
        } else {
            return false;
        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_ENTER  || keyCode == event.KEYCODE_BACK){
            clearFocus();
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        return super.onKeyPreIme(keyCode, event);
    }

}
