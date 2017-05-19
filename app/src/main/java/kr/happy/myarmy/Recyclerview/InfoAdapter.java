package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.Custom.InfoDialogFragment;
import kr.happy.myarmy.Custom.LPEdittext;
import kr.happy.myarmy.R;


public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>  implements View.OnTouchListener{

    private Context context;
    private ArrayList<Data> items;
    private int itemLayout;
    private String[] mItemContent; //변경되는 내용을 저장할 배열??
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private InfoDialogFragment dialogFragment;
    private FragmentManager fgManager;

    public InfoAdapter(Context context, ArrayList<Data> items, int itemLayout, LinearLayoutManager mLayoutManager, RecyclerView mRecyclerview, FragmentManager fgManager) {
        this.context = context;
        this.items = items;
        this.mLayoutManager = mLayoutManager;
        this.mRecyclerview = mRecyclerview;
        this.itemLayout = itemLayout;
        this.fgManager = fgManager;

        dialogFragment = new InfoDialogFragment();
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnTouchListener(this);

        return new InfoViewHolder(itemView, new CustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        holder.customEditTextListener.updatePosition(position);
        holder.title.setText(items.get(position).getTitle());
        holder.content.setText(items.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public void setmItemContent(String[] mItemContent) {
        this.mItemContent = mItemContent;
    }

    public String[] getmItemContent() {
        return mItemContent;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int position= mRecyclerview.getChildAdapterPosition(v);
        Log.d("jy", String.valueOf(position));

        if (position == 1 ) {
            dialogFragment.setCurViewPosition(position);
            dialogFragment.show(fgManager, "infoDialog");
        }

        return false;
    }


    /*info view holder*/
    static class InfoViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.item_infoTitle)
        TextView title;
        @Nullable
        @BindView(R.id.item_infoContent)
        LPEdittext content;

        CustomEditTextListener customEditTextListener;

        public InfoViewHolder(View itemView, CustomEditTextListener customEditTextListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.customEditTextListener = customEditTextListener;
            content.addTextChangedListener(customEditTextListener);
            content.setOnEditorActionListener(customEditTextListener);
        }
    }


    /*custom edittext listener can read focused view text */
    private class CustomEditTextListener implements TextWatcher, TextView.OnEditorActionListener {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mItemContent[position] = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            mLayoutManager.scrollToPosition(this.position + 1);

            return false;
        }



    }


}



