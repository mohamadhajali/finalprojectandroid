package com.example.androidproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Room> roomList;
//    private int[] imageIds;
//    private String[] description;


    public RecyclerAdapter(Context context, ArrayList<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_card,
                parent,
                false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Room room = roomList.get(position);
        CardView cardView = holder.cardView;

        ImageView imageView = (ImageView) cardView.findViewById(R.id.roomImage);
        //Picasso.with(context).load(room.getImage()).into(imageView);
        Glide.with(context).load(room.getImage()).override(800, 800).into(holder.img_android);

        // Glide.with(context).load(room.getImage()).into(imageView);
        TextView roomId = (TextView)cardView.findViewById(R.id.roomId);
        roomId.setText(String.valueOf(room.getId()));
        TextView roomCapacity = (TextView)cardView.findViewById(R.id.roomCapacity);
        roomCapacity.setText(String.valueOf(room.getCapacity()));
        TextView roomPriceByDay = (TextView)cardView.findViewById(R.id.roomPriceByDay);
        roomPriceByDay.setText(String.valueOf(room.getPriceByDay()));

        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }
    /*
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pizza pizza = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        Glide.with(context).load(pizza.getImage()).into(imageView);
        TextView txt = (TextView)cardView.findViewById(R.id.txtName);
        txt.setText(pizza.getName());
        cardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //
            }
        });
    }
     */







    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        ImageView img_android;
        public ViewHolder(CardView cardView){
            super(cardView);
            img_android = cardView.findViewById(R.id.roomImage);
            this.cardView = cardView;
        }
    }

}
