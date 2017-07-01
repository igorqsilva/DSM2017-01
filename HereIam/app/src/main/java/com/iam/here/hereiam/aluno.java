package com.iam.here.hereiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Classe do Perfil de Aluno
 */
public class aluno extends AppCompatActivity {

    /**
     * Cria a aplicação para tela do perfil de aluno
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aluno);
    }

    /**
     * Faz com que o Botão de buscar turma redirecione para a tela de busca de turma para cadastro
     * Metado chama outra Activity e faz a mudança de tela
     * @param view
     */
    public void buscar(View view){

        Intent intent1 = new Intent(getApplicationContext(), cadastroAlunoTurma.class);
        startActivity(intent1);

    }
}
