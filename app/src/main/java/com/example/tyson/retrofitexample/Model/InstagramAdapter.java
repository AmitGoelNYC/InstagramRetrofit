package com.example.tyson.retrofitexample.Model;

import android.support.v7.widget.RecyclerView;
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

    private List<InstagramData> mInstagramData;

    public InstagramAdapter(List<InstagramData> instaData){
        mInstagramData = instaData;
    }

    public void addInstagramData(InstagramData instagramData) {
        mInstagramData.add(instagramData);
        notifyDataSetChanged();
    }

    @Override
    public InstagramAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(InstagramAdapter.Holder holder, int position) {
        InstagramData instagramData = mInstagramData.get(position);
        holder.mName.setText(instagramData.mName);
        holder.mCategory.setText(instagramData.mCategory);
        holder.mPrice.setText(Double.toString(instagramData.mPrice));
        holder.mInstructions.setText(instagramData.mInstructions);

        Picasso.with(holder.itemView.getContext())
                .load(instagramData.instagrams.get(position).images.thumbnail.url)
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mInstagramData.size();
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
