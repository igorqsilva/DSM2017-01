package com.iam.here.hereiam;

import android.app.Service;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.iam.here.bancodados.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Cadastra Uma nova turma
 */
public class cadastrarTurma extends Activity {

    /**
     * Variáveis para cadastro de uma nova turma
     */
    EditText editTurma, editHorario,  editDia, editChave, editLocal, editCodigo;
    Button btnSalvar;
    ListView listViewTurmas;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imp;

    /**
     * Pega os dados do Layout e atribui as variáveis
     * Valores das variáveis serão gardados no banco de Dados
     * Instancia o Botão btnSalvar do Layout
     * Grava os dados No Banco de Dados
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_turma);

        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editTurma = (EditText) findViewById(R.id.editTurma);
        editHorario = (EditText) findViewById(R.id.editHorario);
        editDia = (EditText) findViewById(R.id.editDia);
        editChave = (EditText) findViewById(R.id.editChave);
        editLocal = (EditText) findViewById(R.id.editLocal);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        imp = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        listViewTurmas = (ListView) findViewById(R.id.listViewTurmas);
        listarTurmas();

        /**
         * Cadastra Uma turma na conta do professor
         * Verifica se os campos estão em branco
         * Verifica a turma já existe
         * Chama os metodos de limpar campo
         * atualiza a lista de turmas
         */
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = editCodigo.getText().toString();
                String turma = editTurma.getText().toString();
                String horario = editHorario.getText().toString();
                String dia = editDia.getText().toString();
                String chave = editChave.getText().toString();
                String local = editLocal.getText().toString();

                if (turma.isEmpty()){
                    editTurma.setError("Esse campos é Obrigatório");
                } else if (horario.isEmpty()){
                    editHorario.setError("Esse campos é Obrigatório");
                } else if (dia.isEmpty()){
                    editDia.setError("Esse campos é Obrigatório");
                } else if (chave.isEmpty()){
                    editChave.setError("Esse campos é Obrigatório");
                } else if (local.isEmpty()){
                    editLocal.setError("Esse campos é Obrigatório");
                } else if  (codigo.isEmpty()){
                    db.addTurma(new Turma(turma, horario, dia, chave, local));
                    Toast.makeText(cadastrarTurma.this, "Cliente Adicionado com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarTurmas();
                    ocultaTeclado();

                }
            }
        });
    }

    /**
     * Cria e mostra A listagem de Turmas
     */
    public void listarTurmas(){

        List<Turma> turmas = db.listaTodasTurmas();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(cadastrarTurma.this, android.R.layout.simple_list_item_1, arrayList);

        listViewTurmas.setAdapter(adapter);

        for (Turma c : turmas){
            //Log.d("Lista", "\nID: " + c.getCodigo() + " Nome :" + c.getNome());
            arrayList.add(c.getCodigo() + "-" + c.getTurma());
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Oculta o teclado quando o metodo é chamado
     */
    void ocultaTeclado(){

        imp.hideSoftInputFromWindow(editTurma.getWindowToken(), 0);
    }

    /**
     * limpa os campos quando for solicitado
     * Quando o metodo é chamado
     * Após finalizar um cadastro
     * Após finalizar uma alteração ou exclusão
     */
    void limpaCampos(){

        editCodigo.setText("");
        editTurma.setText("");
        editDia.setText("");
        editHorario.setText("");
        editLocal.setText("");
        editChave.setText("");

        editTurma.requestFocus();
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