package kr.happy.myarmy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import kr.happy.myarmy.databinding.XpracBinding;

/**
 * Created by JY on 2017-05-18.
 */

public class XpracActivity extends AppCompatActivity {

   XpracBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.xprac);
        binding.setXactivity(this);

        ArrayList<String> month= new ArrayList<>();
        ArrayList<String> year = new ArrayList<>();
        ArrayList<String> dayOfMonth= new ArrayList<>();


        for(int i=0; i<30; i++)
            year.add(String.valueOf(1980+i)+"년" );

        /*월 넣기*/
        for(int i=0; i< 12; i++)
            month.add(String.valueOf(i+1)+"월" );

        for(int i=0; i<31; i++)
            dayOfMonth.add(String.valueOf(i+1)+"일");

        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<>(this, R.layout.xspinner, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoBirthYear.setAdapter(adapter);
        binding.infoBirthYear.setPrompt("년도를 선택하세요");
        adapter= new ArrayAdapter<>(this, R.layout.xspinner, month);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoBirthMonth.setPrompt("월을 선택하세요");
        binding.infoBirthMonth.setAdapter(adapter);

        adapter= new ArrayAdapter<>(this, R.layout.xspinner, dayOfMonth);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoBirthDOM.setPrompt("일을 선택하세요");
binding.infoBirthDOM.setAdapter(adapter);

    }
}
