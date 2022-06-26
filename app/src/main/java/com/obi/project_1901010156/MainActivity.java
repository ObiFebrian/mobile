package com.obi.project_1901010156;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.obi.project_1901010156.kotlin.MainActivity2;
import com.obi.project_1901010156.kotlin.NoteActivity;

public class MainActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
        private TextView textnama;
//        private Button btnLogout,btnDpa,btnHitunngvbalok,btnHitungvBola, btnkalkulator,btnkreatif;
        private CardView btnDpa,btnHitunngvbalok,btnHitungvBola, btnkalkulator,btnkreatif;
        private ImageView btnLogout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_ex);
        getSupportActionBar().hide();


        textnama = findViewById(R.id.nama);
        btnLogout = findViewById(R.id.btnLogout);
        btnDpa = findViewById(R.id.btnDpa);
        btnHitunngvbalok = findViewById(R.id.btnHitungvbalok);
        btnHitungvBola = findViewById(R.id.btnHitungvbola);
        btnkalkulator = findViewById(R.id.btnAppkalkulator);
        btnkreatif = findViewById(R.id.btnKreatifitas);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        if (firebaseUser!=null) {
            textnama.setText(firebaseUser.getDisplayName());
        }else{
           textnama.setText("login gagal");
        }

        btnDpa.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), DataPembuatApp.class));
        });
        btnHitunngvbalok.setOnClickListener(view -> {
            startActivity((new Intent(getApplicationContext(), HitungVolumeBalok.class)));
        });
        btnHitungvBola.setOnClickListener(view -> {
            startActivity((new Intent(getApplicationContext(), HitungVolumeBola.class)));
        });
        btnkalkulator.setOnClickListener(view -> {
            startActivity((new Intent(getApplicationContext(), AppKalkulator.class)));
        });
        btnkreatif.setOnClickListener(view -> {
            startActivity((new Intent(getApplicationContext(), MainActivity2.class)));
        });
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        });

    }
}