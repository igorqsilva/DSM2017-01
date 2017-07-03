package com.iam.here.hereiam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastroTurma extends AppCompatActivity {

    /**
     * Variáveis Criadas para cadastro de Turma
     * Para controle do arquivo XML
     */
    EditText nomeTurma;
    EditText diaAulas;
    EditText horarioAulas;
    EditText chaveAcesso;
    EditText local;

    /**
     * Activity Lifecycle - Clico de vida da aplicação
     * Cria a aplicação para ser visivel ao usuário
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeTurma = (EditText)findViewById(R.id.nomeTurma);
        diaAulas = (EditText)findViewById(R.id.diaAulas);
        horarioAulas = (EditText)findViewById(R.id.horarioAulas);
        chaveAcesso = (EditText)findViewById(R.id.chaveAcesso);
        local = (EditText)findViewById(R.id.local);

        Button btSalvar = (Button) findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Click Salvar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     *Inicia a aplicação se a mesma já foi criada
     */
    @Override
    protected void onStart(){

        super.onStart();
    }

    /**
     * O onStop() é chamado quando a Activity não está mais visível
     */
    @Override
    protected void onStop() {

        super.onStop();
    }

    /**
     * é chamado depois que uma Activity foi interrompida
     */
    @Override
    protected void onRestart() {

        super.onRestart();
    }

    /**
     * o onDestroy() é chamado por dois motivos, primeiro se você solicitou a finalização da Activity utilizando o método finish() O
     * Ou se o sistema operacional destruiu a instância Activity para economizar espaço
     */
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
