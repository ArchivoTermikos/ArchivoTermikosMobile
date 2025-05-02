package com.termikos.archivotermikosmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.termikos.archivotermikosmobile.controller.PHPAulaRetriever;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.strategy.EntryStrategy;

import java.util.List;
import java.util.concurrent.Executors;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sensor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerDatos);
        int elemento = getIntent().getIntExtra("elemento", 0);
        Executors.newCachedThreadPool().execute(new PHPAulaRetriever(recyclerView,elemento,this));
        int imagen = getIntent().getIntExtra("imagen", R.drawable.aula);
        String nombre = getIntent().getStringExtra("nombre");
        ImageView imageView = findViewById(R.id.imagenClase);
        imageView.setImageResource(imagen);
        TextView nombreAula = findViewById(R.id.nombreAula);
        nombreAula.setText(nombre);
        setTitle("Aula "+elemento);
        List<ElementoTarjeta> elementos = List.of(new ElementoTarjeta("Cargando", "Puede tardar un poco", R.drawable.cargando));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(elementos, this,R.layout.minicard, new EntryStrategy()));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomSensor);
        bottomNavigationView.setSelectedItemId(R.id.navaula);
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
            } else if (itemId == R.id.navequipo) {
                cambio = new Intent(this, AboutActivity.class);
                startActivity(cambio);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
            return true;
        });

    }
}