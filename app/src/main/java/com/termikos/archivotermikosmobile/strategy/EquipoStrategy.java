package com.termikos.archivotermikosmobile.strategy;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.adapters.CardAdapter.CardViewHolder;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.model.MiembroCard;

import java.util.List;

public class EquipoStrategy extends AbstractStrategy {

    @Override
    public void onClick(Context context, List<? extends ElementoTarjeta> lista, int listaindex, CardAdapter.CardViewHolder holder){
        ElementoTarjeta elementoTarjeta = lista.get(listaindex);
        Glide.with(context)
                .load(((MiembroCard)elementoTarjeta).change())
                .into(holder.cardImage);
    }
}
