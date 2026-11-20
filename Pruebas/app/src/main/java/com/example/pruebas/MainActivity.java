package com.example.pruebas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i("Ejemplo", "Estoy en on Create");
    }

    protected void onStart(){
        super.onStart();
        Log.i("Ejemplo", "Estoy en on Start");
    }

    protected void onRestart(){
        super.onRestart();
        Log.i("Ejemplo", "Estoy en on Restart");
    }

    protected void onResume(){
        super.onResume();
        Log.i("Ejemplo", "Estoy en on Resume");
    }

    protected void onPause(){
        super.onPause();
        Log.i("Ejemplo", "Estoy en on Pause");
    }

    protected void onStop(){
        super.onStop();
        Log.i("Ejemplo", "Estoy en on Stop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("Ejemplo", "Estoy en on Destroy");
        Intent ejemplo = new Intent(this, AbrirGoogle.class);
        startActivity(ejemplo);
    }
}