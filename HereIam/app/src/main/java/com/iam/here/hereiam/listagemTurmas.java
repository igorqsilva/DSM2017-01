package com.iam.here.hereiam;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

/**
 * Cria a lisatgem de Turma
 * As variáveis de Listagem
 * e Redireciona para a pagina de cadastro de Turma
 */
public class listagemTurmas extends ListActivity {

    /**
     * Listagem de Turmas Cadastradas
     */
    private cadastroTurmaDAO cadastroTurmaDAO;
    List<cadastroTurmaBase> cadastros;
    cadastroAdapter adapter;
    baseDAO baseDAO;
    int posicao = 0;


    /**
     * Redireciona para o Cadastro de Turmas
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_turma);

        baseDAO = new baseDAO(this);

    }

    public void telaCadastroTurma(View view){

        Intent intent1 = new Intent(getApplicationContext(), cadastrarTurma.class);
        startActivity(intent1);
    }

    /**
     * A aplicação volta a executar a listagem
     */
    @Override
    protected void onResume(){
        cadastroTurmaDAO = new cadastroTurmaDAO(this);
        cadastroTurmaDAO.open();
        cadastros = cadastroTurmaDAO.lerCadastro();

        adapter = new cadastroAdapter(this, cadastros);
        setListAdapter(adapter);

        super.onResume();
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
