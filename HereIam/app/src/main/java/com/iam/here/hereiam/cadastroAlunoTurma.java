package com.iam.here.hereiam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Cadastra Aluno em uma turma existente
 */
public class cadastroAlunoTurma extends AppCompatActivity {

    /**
     * Activity Lifecycle - Clico de vida da aplicação
     * Cria a aplicação para ser visivel ao usuário
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_aluno_turma);
    }
}
