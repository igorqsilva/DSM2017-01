package com.iam.here.hereiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iam.here.bancodados.BancoDados;
import com.iam.here.bancodados.Turma;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe do perfil do activity_professor
 */
public class professor extends AppCompatActivity {

    ListView listViewTurmas;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;


    /**
     * Cria a aplicação do perfil do activity_professor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        /**
         * Chama metodo para mostrar turmas cadastradas
         */
        listarTurmas();
    }

    /**
     * Faz com que o Botão de cadastrar turmas redirecione para a pagina de cadastro de turmas
     * @param view
     */
    public void CadastrarTurma(View view){

        Intent intent2 = new Intent(getApplicationContext(), cadastrarTurma.class);
        startActivity(intent2);
    }

    /**
     * Faz com que o Botão de editar turmas redirecione para a pagina de edição de turmas
     * @param view
     */
    public void editarCadastroTurma(View view){

        Intent intent3 = new Intent(getApplicationContext(), editarDadosTurmas.class);
        startActivity(intent3);
    }

    /**
     *  Cria  e mostra A listagem de Turmas
     */
    public void listarTurmas(){

        List<Turma> turmas = db.listaTodasTurmas();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(professor.this, android.R.layout.simple_list_item_1, arrayList);

        listViewTurmas.setAdapter(adapter);

        for (Turma c : turmas){
            arrayList.add(c.getCodigo() + "-" + c.getTurma());
            adapter.notifyDataSetChanged();
        }
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
