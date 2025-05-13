package com.example.appholamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private EditText editText;
    private CalendarView calendarView;
    private Button imprimirButton;
    private ImageButton nextActivityButton;
    private Calendar selectedCalendar = Calendar.getInstance(); // Comienza con la fecha actual

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkBox);
        editText = findViewById(R.id.editText);
        calendarView = findViewById(R.id.calendarView);
        imprimirButton = findViewById(R.id.imprimirButton);
        nextActivityButton = findViewById(R.id.nextActivityButton);

        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Actualiza la fecha seleccionada en el calendario
                selectedCalendar.set(year, month, dayOfMonth);
            }
        });

        imprimirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Botón Imprimir clickeado");
                if (checkBox.isChecked()) {
                    boolean isChecked = checkBox.isChecked();
                    String text = editText.getText().toString();

                    // Formatear la fecha seleccionada
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String selectedDate = dateFormat.format(selectedCalendar.getTime());

                    // Para la obtener la hora actual
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    String currentTime = timeFormat.format(new Date());

                    Log.d("MainActivity", "isChecked: " + isChecked);
                    Log.d("MainActivity", "Texto: " + text);
                    Log.d("MainActivity", "Fecha: " + selectedDate);
                    Log.d("MainActivity", "Hora: " + currentTime);

                    String mensaje = "Checkbox: " + isChecked + "\n" +
                            "Texto: " + text + "\n" +
                            "Fecha: " + selectedDate + "\n" +
                            "Hora: " + currentTime;

                    // Inflar el layout personalizado del Toast
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    // Obtener las referencias a los elementos del layout
                    ImageView image = layout.findViewById(R.id.imageToast);
                    TextView textToast = layout.findViewById(R.id.textToast);

                    // Setear la imagen y el texto
                    image.setImageResource(R.drawable.thumbnail);
                    textToast.setText(mensaje);

                    // Crear y mostrar el Toast personalizado
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                } else {
                    Toast.makeText(MainActivity.this, "Debes marcar el CheckBox para imprimir.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}