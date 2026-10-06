package com.example.fittracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    Spinner spinnerEntrenamiento;

    RadioGroup radioGroupIntensidad;

    CheckBox checkCalentamiento;
    CheckBox checkHidratacion;
    CheckBox checkEstiramiento;

    ProgressBar progressBar;

    RatingBar ratingBar;

    Button btnGuardar;

    RecyclerView recyclerViewActividades;

    ArrayList<Actividad> listaActividades;

    ActividadAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_registro);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.mainRegistro),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        spinnerEntrenamiento =
                findViewById(R.id.spinnerEntrenamiento);

        radioGroupIntensidad =
                findViewById(R.id.radioGroupIntensidad);

        checkCalentamiento =
                findViewById(R.id.checkCalentamiento);

        checkHidratacion =
                findViewById(R.id.checkHidratacion);

        checkEstiramiento =
                findViewById(R.id.checkEstiramiento);

        progressBar =
                findViewById(R.id.progressBar);

        ratingBar =
                findViewById(R.id.ratingBar);

        btnGuardar =
                findViewById(R.id.btnGuardar);

        recyclerViewActividades =
                findViewById(R.id.recyclerViewActividades);

        cargarSpinner();

        configurarRecyclerView();

        btnGuardar.setOnClickListener(v -> guardarActividad());
    }

    private void cargarSpinner() {

        String[] tiposEntrenamiento = {
                "Fuerza",
                "Cardio",
                "Yoga",
                "Calistenia"
        };

        ArrayAdapter<String> adapterSpinner =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        tiposEntrenamiento
                );

        adapterSpinner.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerEntrenamiento.setAdapter(adapterSpinner);
    }

    private void configurarRecyclerView() {

        listaActividades = new ArrayList<>();

        adapter = new ActividadAdapter(listaActividades);

        recyclerViewActividades.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerViewActividades.setAdapter(adapter);
    }

    private void guardarActividad() {

        String tipo =
                spinnerEntrenamiento
                        .getSelectedItem()
                        .toString();

        int radioSeleccionado =
                radioGroupIntensidad
                        .getCheckedRadioButtonId();

        if (radioSeleccionado == -1) {

            Toast.makeText(
                    this,
                    "Seleccione una intensidad",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        RadioButton radioButton =
                findViewById(radioSeleccionado);

        String intensidad =
                radioButton.getText().toString();

        String aspectos = "";

        if (checkCalentamiento.isChecked()) {
            aspectos += "Calentamiento ";
        }

        if (checkHidratacion.isChecked()) {
            aspectos += "Hidratación ";
        }

        if (checkEstiramiento.isChecked()) {
            aspectos += "Estiramiento";
        }

        if (aspectos.isEmpty()) {
            aspectos = "Ninguno";
        }

        float esfuerzo =
                ratingBar.getRating();

        Actividad nuevaActividad =
                new Actividad(
                        tipo,
                        intensidad,
                        aspectos,
                        esfuerzo
                );

        listaActividades.add(nuevaActividad);

        adapter.notifyItemInserted(
                listaActividades.size() - 1
        );

        int progreso =
                listaActividades.size() * 20;

        if (progreso > 100) {
            progreso = 100;
        }

        progressBar.setProgress(progreso);

        Toast.makeText(
                this,
                "Entrenamiento guardado",
                Toast.LENGTH_SHORT
        ).show();
        // Limpiar formulario después de guardar
        radioGroupIntensidad.clearCheck();

        checkCalentamiento.setChecked(false);
        checkHidratacion.setChecked(false);
        checkEstiramiento.setChecked(false);

        ratingBar.setRating(0);

        spinnerEntrenamiento.setSelection(0);
    }
}