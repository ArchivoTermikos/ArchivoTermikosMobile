package com.termikos.archivotermikosmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.model.ElementoTarjetaDato;
import com.termikos.archivotermikosmobile.strategy.AbstractStrategy;

import java.util.List;

public class CardAdapterData extends CardAdapter {
    public CardAdapterData(List<? extends ElementoTarjeta> cardItemList, Context context, int layout, AbstractStrategy strategy) {
        super(cardItemList, context, layout, strategy);
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
        ImageView infoImageView = holder.itemView.findViewById(R.id.info);
        infoImageView.setVisibility(View.GONE);
        if (cardItem instanceof ElementoTarjetaDato) {
            infoImageView.setVisibility(View.VISIBLE);
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
}
