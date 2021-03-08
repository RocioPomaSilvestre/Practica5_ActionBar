package com.rocio.poma.practica5_actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity {
    TextView txt1;
    String CHANNEL_ID="CANAL_DE_DE_NOTIF_APP";
    String CHANNEL_NAME="NOTIFICACION TEC_ENERG";
    String CHANNEL_DESC="Sistema de notificacion de la App Informatica";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar0);
        setSupportActionBar(toolbar);
        txt1=findViewById(R.id.txt1);
        crearNotificacionChannel1();
    }

    //NOTIFICACIONES
    public void crearNotificacionChannel1(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            int importancia= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,importancia);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager notificationManager= getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void mostrarNotificacion(String titulo, String texto){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                .setSmallIcon(R.drawable.iconoregresar1_foreground)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(10001,builder.build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menutoolbar,menu);
        MenuItem item = menu.findItem(R.id.itembuscar);
        SearchView searchview =(SearchView) item.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txt1.setText("Buscando ..."+query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txt1.setText(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.itemregresar){
            mostrarNotificacion("HOLA","Hizo clic en volver a la pantalla principal");
            //txt1.setText("Hizo clic en Regresar");

        }
        return true;
    }
}