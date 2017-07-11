package com.iam.here.hereiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Classe do perfil do activity_professor
 */
public class professor extends AppCompatActivity {

    /**
     * Cria a aplicação do perfil do activity_professor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
    }

    /**
     * Faz a ação do botão de cadastrar turma ir para a tela de cadastro
     * @param view
     */
    public void telaCadastroTurma(View view){

        Intent intent1 = new Intent(getApplicationContext(), cadastrarTurma.class);
        startActivity(intent1);
    }

    public void CadastrarTurma(View view){

        Intent intent2 = new Intent(getApplicationContext(), cadastrarTurma.class);
        startActivity(intent2);
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
