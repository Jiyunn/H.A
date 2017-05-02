package kr.happy.myarmy;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class EditTextOnKey  implements View.OnKeyListener {

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_ENTER ){
           v.clearFocus();
            InputMethodManager inputManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        else if(keyCode == event.KEYCODE_BACK){ //how to remove focus edit text?

        }

        return false;
    }
}
