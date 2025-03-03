package com.termikos.archivotermikosmobile.strategy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.model.ClaseCard;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.model.MiembroCard;
import com.termikos.archivotermikosmobile.view.SensorActivity;

import java.util.List;

public class ClaseStrategy extends AbstractStrategy {

    @Override
    public void onClick(Context context, List<? extends ElementoTarjeta> lista, int listaindex, CardAdapter.CardViewHolder holder){
        Intent intent = new Intent(context, SensorActivity.class);
        ClaseCard claseCard = (ClaseCard) lista.get(listaindex);
        intent.putExtra("elemento", listaindex);
        intent.putExtra("imagen", claseCard.getImageResId());
        intent.putExtra("nombre", claseCard.getSubtitle());
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
