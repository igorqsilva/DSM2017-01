package com.iam.here.hereiam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * Activity Lifecycle - Clico de vida da aplicação
     * Cria a aplicação para ser visivel ao usuário
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Faz com que o Botão de Cadastro de Turma redirecione para a tela de cadastro de turma
     * Metado chama outra Activity e faz a mudança de tela
     * @param view
     */
    public void telaCadastroTurma(View view){

        Intent intent1 = new Intent(getApplicationContext(), professor.class);
        startActivity(intent1);

        Intent intent2 = new Intent(getApplicationContext(), aluno.class);
        startActivity(intent1);
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
