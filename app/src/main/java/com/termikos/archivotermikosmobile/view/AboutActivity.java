package com.termikos.archivotermikosmobile.view;

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
import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.model.MiembroCard;
import com.termikos.archivotermikosmobile.strategy.EquipoStrategy;

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

        List<MiembroCard> cardItemList = new ArrayList<>();
        cardItemList.add(new MiembroCard("Nieves Tejeda", "Profesora", R.drawable.nieves1, R.drawable.nieves2));
        cardItemList.add(new MiembroCard("Guillermo Baquero", "Backend PHP, Android", R.drawable.guille1, R.drawable.guille2));
        cardItemList.add(new MiembroCard("Carlos Dominguez", "Backend PHP, Android", R.drawable.carlosd1, R.drawable.carlosd2));
        cardItemList.add(new MiembroCard("Ivan Ruiz Alcalá", "Frontend Web", R.drawable.ivan1, R.drawable.ivan2));
        cardItemList.add(new MiembroCard("Raquel Ramirez", "Community Manager", R.drawable.raquel1, R.drawable.raquel2));

        CardAdapter adapter = new CardAdapter(cardItemList, this,R.layout.team_card, new EquipoStrategy());
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