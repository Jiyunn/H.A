package kr.happy.myarmy.Custom;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-05-19.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] spinnerArray;
    private LayoutInflater inflater;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] spinnerArray) {
        super(context, resource, spinnerArray);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            inflater= LayoutInflater.from(context);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView tv=(TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(spinnerArray[position]);
        tv.setTextColor(ContextCompat.getColor(context, R.color.fora));
        tv.setTextSize(15);

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.xspinner, parent, false);
        }

        TextView tv = (TextView) convertView
                .findViewById(android.R.id.text1);
        tv.setText(spinnerArray[position]);

        tv.setTextSize(14);
        return convertView;
    }


}
