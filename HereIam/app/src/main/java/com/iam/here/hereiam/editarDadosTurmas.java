package com.iam.here.hereiam;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.iam.here.bancodados.BancoDados;
import com.iam.here.bancodados.Turma;

import java.util.ArrayList;
import java.util.List;

/**
 * EDita cadastro de uma turma existente e também salva uma nova
 */
public class editarDadosTurmas extends AppCompatActivity {

    /**
     * Declara as variáveis para bOtões e editTexs
     */
    EditText editTurma, editHorario,  editDia, editChave, editCodigo;
    RadioGroup editLocal;
    Button btnLimpar, btnSalvar, btnExcluir;
    ListView listViewTurmas;

    /**
     * banco de Dados
     */
    BancoDados db = new BancoDados(this);

    /**
     * Declara uma lista para mostrar a lista de turmas
     */
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
        setContentView(R.layout.activity_editar_dados_turmas);

        /**
         * Relaciona os Botões e EditTexts
         */
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editTurma = (EditText) findViewById(R.id.editTurma);
        editHorario = (EditText) findViewById(R.id.editHorario);
        editDia = (EditText) findViewById(R.id.editDia);
        editChave = (EditText) findViewById(R.id.editChave);
        editLocal = (RadioGroup) findViewById(R.id.localAulaEdit);

        btnLimpar = (Button)findViewById(R.id.btnLimpar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnExcluir = (Button)findViewById(R.id.btnExcluir);

        imp = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        listViewTurmas = (ListView) findViewById(R.id.listViewTurmas);

        listarTurmas();

        btnLimpar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        /**
         * Permite que uma turma seja selecionada para exclusão, alteração
         */
        listViewTurmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listViewTurmas.getItemAtPosition(position);

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Turma turma = db.selecionarTurma(Integer.parseInt(codigo));

                editCodigo.setText(String.valueOf(turma.getCodigo()));
                editTurma.setText(turma.getTurma());
                editHorario.setText(turma.getHorario());
                editDia.setText(turma.getDia());
                editChave.setText(turma.getChave());
                editLocal.clearCheck();
            }
        });

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
                String local = editLocal.toString();

                if (turma.isEmpty()){
                    editTurma.setError("Esse campos é Obrigatório");
                } else if (horario.isEmpty()){
                    editHorario.setError("Esse campos é Obrigatório");
                } else if (dia.isEmpty()){
                    editDia.setError("Esse campos é Obrigatório");
                } else if (chave.isEmpty()){
                    editChave.setError("Esse campos é Obrigatório");
                } else if (local.isEmpty()){
                    editLocal.clearCheck();
                } else if  (codigo.isEmpty()){
                    db.addTurma(new Turma(turma, horario, dia, chave, local));
                    Toast.makeText(editarDadosTurmas.this, "Tuma adicionada com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarTurmas();
                    ocultaTeclado();

                }else {
                    db.atualizaTurma(new Turma(Integer.parseInt(codigo), turma, horario, dia, chave, local));

                    Toast.makeText(editarDadosTurmas.this, "Turma atualizada com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarTurmas();
                    ocultaTeclado();
                }
            }
        });

        /**
         * Exclui uma turma
         * Verifica se a turma foi selecionada
         * verifica se a turma já existe ou não
         */
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = editCodigo.getText().toString();

                if (codigo.isEmpty()){
                    Toast.makeText(editarDadosTurmas.this, "Nenhuma Turma selecionada", Toast.LENGTH_LONG).show();
                } else {
                    Turma turma= new Turma();
                    turma.setCodigo(Integer.parseInt(codigo));
                    db.apagarTurma(turma);

                    Toast.makeText(editarDadosTurmas.this, "Turma Excluída", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarTurmas();
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

        adapter = new ArrayAdapter<String>(editarDadosTurmas.this, android.R.layout.simple_list_item_1, arrayList);

        listViewTurmas.setAdapter(adapter);

        for (Turma c : turmas){
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
        editLocal.clearCheck();
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
