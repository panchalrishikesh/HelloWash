package com.hw.hellowash.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;
import com.hw.hellowash.activities.SelectedWashTypeActivity;
import com.hw.hellowash.models.WashTypeSelection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WashTypeSelectionAdapter extends RecyclerView.Adapter<WashTypeSelectionAdapter.ViewHolder> {
    private List<WashTypeSelection> data;
    Context mContext;
    Handler mHandler = new Handler();

    public WashTypeSelectionAdapter(Context mContext, List<WashTypeSelection> data) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wash_type_selection_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        WashTypeSelection mModel = data.get(position);

        LinearLayout.LayoutParams card_lp = new LinearLayout.LayoutParams((int) (Utilities.screenWidth * 0.40), (int) (Utilities.screenWidth * 0.40)/*RelativeLayout.LayoutParams.MATCH_PARENT*/);
        card_lp.setMargins(30, 30, 30, 30);
        card_lp.gravity = Gravity.CENTER;//addRule(RelativeLayout.CENTER_IN_PARENT);
        holder.tile_circular_bg.setLayoutParams(card_lp);

        Glide.with(mContext).load(mModel.getWashTypeImage()).into(holder.wastype_img);

        //holder.wastype_img.setImageResource(mModel.getWashTypeImage());
        holder.washtype_name.setText(mModel.getWashTypeName());

        holder.item_parent_view.setTag(mModel.getWashTypeName());
        holder.item_parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedWash = new Intent(mContext, SelectedWashTypeActivity.class);
                selectedWash.putExtra("SelectedWashType", v.getTag().toString());
                mContext.startActivity(selectedWash);
                ((AppCompatActivity)mContext).overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_parent_view)
        RelativeLayout item_parent_view;

        /*@BindView(R.id.item_view)
        CardView item_view;*/

        @BindView(R.id.wastype_img)
        ImageView wastype_img;

        @BindView(R.id.washtype_name)
        TextView washtype_name;

        @BindView(R.id.tile_circular_bg)
        RelativeLayout tile_circular_bg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
