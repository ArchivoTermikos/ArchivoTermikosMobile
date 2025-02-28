package com.termikos.archivotermikosmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<ElementoTarjeta> cardItemList;
    private Context context;

    public CardAdapter(List<ElementoTarjeta> cardItemList, Context context) {
        this.cardItemList = cardItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ElementoTarjeta cardItem = cardItemList.get(position);
        holder.cardTitle.setText(cardItem.getTitle());
        holder.cardSubtitle.setText(cardItem.getSubtitle());
        holder.cardImage.setImageResource(cardItem.getCurrent());
        holder.itemView.setOnClickListener(v -> {
            Glide.with(context)
                    .load(cardItem.change())
                    .into(holder.cardImage);
        });
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        TextView cardTitle;
        TextView cardSubtitle;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardSubtitle = itemView.findViewById(R.id.card_subtitle);
        }
    }
}