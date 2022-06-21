package com.obi.project_1901010156;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HitungVolumeBola extends AppCompatActivity implements View.OnClickListener {
        private EditText jari;
        private Button hasil;
        private TextView hasilvbola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_volume_bola);

        jari = findViewById(R.id.jarijari);
        hasil = findViewById(R.id.btn_hitung);
        hasilvbola = findViewById(R.id.hasil_vbola);

        hasil.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hitung) {
            String Inputjari = jari.getText().toString().trim();


            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(Inputjari)) {
                isEmptyFields = true;
                jari.setError("Field ini tidak boleh kosong");
            }

            Double jarijari = toDouble(Inputjari);


            if (jarijari == null) {
                isInvalidDouble = true;
                jari.setError("Field ini harus berupa nomer yang valid");
            }


            if (!isEmptyFields && !isInvalidDouble) {
                double volume = 1.33333333 * 3.14285714 * jarijari * jarijari * jarijari;
                hasilvbola.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}