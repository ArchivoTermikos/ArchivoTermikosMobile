package com.termikos.archivotermikosmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.model.ElementoTarjetaDato;
import com.termikos.archivotermikosmobile.model.MiembroCard;
import com.termikos.archivotermikosmobile.strategy.AbstractStrategy;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    protected List<? extends ElementoTarjeta> cardItemList;
    protected Context context;
    protected AbstractStrategy strategy;
    protected int layout;
    public CardAdapter(List<? extends ElementoTarjeta> cardItemList, Context context,int layout, AbstractStrategy strategy) {
        this.cardItemList = cardItemList;
        this.context = context;
        this.layout = layout;
        this.strategy = strategy;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ElementoTarjeta cardItem = cardItemList.get(position);
        holder.cardTitle.setText(cardItem.getTitle());
        holder.cardSubtitle.setText(cardItem.getSubtitle());
        holder.cardImage.setImageResource(cardItem.getCurrent());
        holder.itemView.setOnClickListener(v -> {
            strategy.onClick(context, cardItemList, position, holder);
            System.out.println("oooo");
        });
        ImageView infoImageView;
        if ((infoImageView = holder.itemView.findViewById(R.id.info)) != null){
            infoImageView.setOnClickListener(v -> {
                ElementoTarjetaDato cardItemDato = (ElementoTarjetaDato) cardItem;
                String recomendacion = cardItemDato.getRecomendacion();
                View mensaje = LayoutInflater.from(context).inflate(R.layout.recomendacion, null);
                TextView recomendacionTextView = mensaje.findViewById(R.id.textRecomendacion);
                recomendacionTextView.setText(recomendacion);
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Recomendación")
                        .setView(mensaje)
                        .setPositiveButton("OK", (dialog1, which) -> {
                            dialog1.dismiss();
                        }).create();
                dialog.show();
            });
        }

    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public ImageView cardImage;
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