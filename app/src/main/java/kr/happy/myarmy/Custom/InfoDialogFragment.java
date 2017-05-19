package kr.happy.myarmy.Custom;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by JY on 2017-05-18.
 */

public class InfoDialogFragment extends DialogFragment {

    private int curViewPosition = 0;
    private Context context;

    public InfoDialogFragment() {
    }

    public void setCurViewPosition(int curViewPosition) {
        this.curViewPosition = curViewPosition;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        switch (curViewPosition) {
            case 1: //생년월일
                return birthDialog();
            case 2: //희망직종
                break;
            case 5: //최종학력
                break;
            case 6: //거주지역
                break;
            case 8://연락처
                break;
        }
        return null;
    }

    /*
    for birthday
     */
    protected DatePickerDialog birthDialog() {
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR) -20;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), dateSetListener  , year, month , day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.d("jy", String.valueOf(year) + String.valueOf(month+1) + String.valueOf(dayOfMonth));
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
