package com.termikos.archivotermikosmobile.strategy;

import android.content.Context;

import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;

import java.util.List;

public abstract class AbstractStrategy {

    public abstract void onClick(Context context, List<? extends ElementoTarjeta> lista, int listaindex, CardAdapter.CardViewHolder holder);
}
