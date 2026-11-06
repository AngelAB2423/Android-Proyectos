package com.example.gestioncuentabar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText totalCuenta;
    private CheckBox propina;
    private SeekBar porcentaje;
    private RadioGroup botones;
    private RatingBar calificacion;
    private Button calcular;
    private TextView resultado;
    private AutoCompleteTextView autoCamarero;
    private double propinaTotal;
    double cuentaFinalConPropina;

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

        totalCuenta = findViewById(R.id.totalCuenta);
        propina = findViewById(R.id.propina);
        porcentaje = findViewById(R.id.porcentaje);
        botones = findViewById(R.id.botones);
        calificacion = findViewById(R.id.calificacion);
        calcular = findViewById(R.id.calcular);
        resultado = findViewById(R.id.resultado);
        autoCamarero = findViewById(R.id.autoCamarero);

        String[] listaCamareros = {"Angel", "Carlos", "María", "Juan"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listaCamareros);
        autoCamarero.setAdapter(adaptador);

        propina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                porcentaje.setEnabled(isChecked);
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoTotal = totalCuenta.getText().toString().trim();
                String nombreCamarero = autoCamarero.getText().toString().trim();

                int idSeleccionadoBoton = botones.getCheckedRadioButtonId();
                String metodoPago = "";
                if (idSeleccionadoBoton == R.id.efectivo) {
                    metodoPago = getString(R.string.pago_efectivo);
                } else if (idSeleccionadoBoton == R.id.tarjeta) {
                    metodoPago = getString(R.string.pago_tarjeta);
                }

                if (textoTotal.isEmpty()) {
                    resultado.setTextColor(android.graphics.Color.RED);
                    resultado.setText("Falta meter el valor de la cuenta");
                    return;
                }

                double numeroTotal = Double.parseDouble(textoTotal);

                if (numeroTotal <= 0) {
                    resultado.setTextColor(android.graphics.Color.RED);
                    resultado.setText("No vale 0 ni numeros negativos");
                    return;
                } else {
                    resultado.setTextColor(android.graphics.Color.BLACK);

                    if (propina.isChecked()) {
                        int porcentajeInt = porcentaje.getProgress();
                        propinaTotal = (numeroTotal * porcentajeInt) / 100.0;
                        cuentaFinalConPropina = numeroTotal + propinaTotal;
                        resultado.setText("Camarero: " + nombreCamarero + "\nEl precio total es de " + numeroTotal + " con la propina total del " + porcentaje.getProgress() + "% la suma total es de " + cuentaFinalConPropina
                                + " el pago se hizo con " + metodoPago + " el servicio se califico con " + calificacion.getRating() + " estrellas");
                    } else {
                        resultado.setText("Camarero: " + nombreCamarero + "\nEl precio total es de " + numeroTotal + " el pago se hizo con " + metodoPago + " el servicio se califico con " + calificacion.getRating() + " estrellas");
                    }
                }
            }
        });
    }
}