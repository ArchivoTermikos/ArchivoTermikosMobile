package com.termikos.archivotermikosmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.model.IntegranteEquipo;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<IntegranteEquipo> cardItemList = new ArrayList<>();
        cardItemList.add(new IntegranteEquipo("Nieves Tejeda", "Profesora", R.drawable.nieves1, R.drawable.nieves2));
        cardItemList.add(new IntegranteEquipo("Guillermo Baquero", "Backend PHP", R.drawable.guille1, R.drawable.guille2));
        cardItemList.add(new IntegranteEquipo("Carlos Dominguez", "Backend PHP", R.drawable.carlosd1, R.drawable.carlosd2));
        cardItemList.add(new IntegranteEquipo("Ivan Ruiz Alcalá", "Frontend HTML&CSS&JS", R.drawable.ivan1, R.drawable.ivan2));
        cardItemList.add(new IntegranteEquipo("Manuel Lemus Gil", "Cálculo de variables", R.drawable.manu1, R.drawable.manu2));
        cardItemList.add(new IntegranteEquipo("Carlos Cano", "Diseñador de presentacion", R.drawable.carlosc1, R.drawable.carlosc2));
        cardItemList.add(new IntegranteEquipo("Raquel Ramirez", "Community Manager y memoria", R.drawable.raquel1, R.drawable.raquel2));
        cardItemList.add(new IntegranteEquipo("Ramón Rodriguez", "Diseñador gráfico", R.drawable.ramon1, R.drawable.ramon2));
        cardItemList.add(new IntegranteEquipo("David Guerrero", "Ayudante de proyecto", R.drawable.david1, R.drawable.david2));

        CardAdapter adapter = new CardAdapter(cardItemList, this);
        recyclerView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.navequipo);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent cambio;
            if (itemId == R.id.navinicio) {
                cambio = new Intent(this, MainActivity.class);
                startActivity(cambio);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else if (itemId == R.id.navaula) {
                cambio = new Intent(this, AulasActivity.class);
                startActivity(cambio);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
            return true;
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}