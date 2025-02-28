package com.termikos.archivotermikosmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;

import java.util.List;

public class AulasActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aulas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerAula);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //CardItem List of 3 classrooms
        List<ElementoTarjeta> cardItemList = List.of(
                new ElementoTarjeta("Aula 1","1ºBachillerato C",R.drawable.aula,R.drawable.aula),
                new ElementoTarjeta("Aula 2", "4ºESO C",R.drawable.aula,R.drawable.aula),
                new ElementoTarjeta("Aula 3", "Sala de padres",R.drawable.aula,R.drawable.aula)
        );
        recyclerView.setAdapter(new CardAdapter(cardItemList, this));
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomAula);
        bottomNavigationView.setSelectedItemId(R.id.navaula);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent cambio;
            if (itemId == R.id.navinicio) {
                cambio = new Intent(this, MainActivity.class);
                startActivity(cambio);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else if (itemId == R.id.navequipo) {
                cambio = new Intent(this, AboutActivity.class);
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