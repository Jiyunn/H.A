package kr.happy.myarmy.Custom;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import kr.happy.myarmy.R;


public class BackButtonHandler extends Handler {
    private long backButtonPressedTime = 0;
    private Activity activity;
    private Toast toast;

    public BackButtonHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backButtonPressedTime + 2000) { //2초내에 한번 더 클릭하면 종료
            backButtonPressedTime = System.currentTimeMillis();

            toast= Toast.makeText(activity, R.string.quitApp, Toast.LENGTH_SHORT);
            toast.show();

            return;
        }
        if (System.currentTimeMillis() <= backButtonPressedTime + 2000) {
            toast.cancel();
            activity.finishAffinity();

        }
    }


}
