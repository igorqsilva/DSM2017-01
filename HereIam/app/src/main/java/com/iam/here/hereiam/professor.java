package com.iam.here.hereiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class professor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor);
    }

    public void telaCadastroTurma(View view){

        Intent intent1 = new Intent(getApplicationContext(), cadastrarTurma.class);
        startActivity(intent1);
    }
}
