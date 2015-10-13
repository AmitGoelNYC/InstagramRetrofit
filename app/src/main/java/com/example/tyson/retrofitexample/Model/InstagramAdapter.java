package com.example.tyson.retrofitexample.Model;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyson.retrofitexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstagramAdapter extends RecyclerView.Adapter<InstagramAdapter.Holder> {

    public static String TAG = InstagramAdapter.class.getSimpleName();

    private List<InstagramData.instagramclass> mInstagramData;

    public InstagramAdapter(List<InstagramData.instagramclass> instaData){
        mInstagramData = instaData;
    }

    public void addInstagramData(InstagramData instagramData) {
//        mInstagramData.add(instagramData);
//        notifyDataSetChanged();
    }

    @Override
    public InstagramAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(InstagramAdapter.Holder holder, int position) {
        InstagramData.instagramclass instagramData = mInstagramData.get(position);
        holder.mName.setText(instagramData.user.username);
        holder.mCategory.setText(instagramData.createdTime);
        holder.mPrice.setText(instagramData.link);
        holder.mInstructions.setText(instagramData.id);

        Picasso.with(holder.itemView.getContext())
                .load(instagramData.images.thumbnail.url)
                .into(holder.mImage);
        /*if(!TextUtils.isEmpty(instagramData.images.standardResolution.url)){
            Log.d(TAG, "Standard Resolution ------ " + instagramData.images.standardResolution.url);
        }*/
    }

    @Override
    public int getItemCount() {
        return mInstagramData.size();
    }

    public void setList(List<InstagramData.instagramclass> list) {
        this.mInstagramData = list;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView mName, mCategory, mPrice, mInstructions;
        public ImageView mImage;

        public Holder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.instaThumb);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            mInstructions = (TextView) itemView.findViewById(R.id.flowerInstruction);
        }
    }
}
