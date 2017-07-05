package com.iam.here.hereiam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    /**
     * Inicia a aplicação se a mesma já foi criada
     */
    @Override
    protected void onStart() {
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

    public String getStringFromEdit(int id) {
        EditText input = (EditText) findViewById(id);
        return input.getText().toString();
    }

    /**
     * Classe do banco de Dados SQLiteOpenHelper
     * Definição da classe para o banco de dados
     * E implementação dos metodos da Classe SQLiteOpenHelper
     * Classe possui os atributos da Tabela de usuarios
     * Tabela de usuarios ficará armazenada as informaçoes dos usuarios
     * Cria o Banco de Dados
     */
    public class Banco extends SQLiteOpenHelper {
        private static final String Nome_Banco = "banco.db";
        private static final String Tabela_usuarios = "usuarios";
        private static final String Matricula = "matricula";
        private static final String Nome = "nome";
        private static final String Email = "email";
        private static final String Senha = "senha";
        private static final String Perfil = "perfil";
        private static final int VERSAO = 1;

        /**
         * Construtor que passa as informações a a super classe criada
         * As informações é do -> local, and, -> versão do banco
         *
         * @param context
         */
        public Banco(Context context) {
            super(context, Nome_Banco, null, VERSAO);
        }

        /**
         * Cria a tabela com os atributos definidos
         * A tabela será usada para realizar Inserção de dados
         * A tabela será usada também para manipulação de dados
         *
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE" + Tabela_usuarios + "("
                    + Matricula + "integer primary key,"
                    + Nome + "text,"
                    + Email + "text,"
                    + Senha + "text,"
                    + Perfil + "text"
                    + ")";
            db.execSQL(sql);
        }

        /**
         * Atualiza as informações que serão inseridas na Tabela criada
         * Apaga a tabela se a mesma existir;
         * E chama novamente o metodo para recria-la com as alterações feitas
         *
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS" + Tabela_usuarios);
            onCreate(db);

        }
    }

    /**
     * Classe é Responsásvel pela Inserção de Dados no Banco
     * Classe Responsavel pelo controle do banco de dados
     * O atributo db recebe os atributos do metodo getWritableDatabase, que diz que o banco fará leitura e escrita de dados
     * O metodo insert recebe os parâmetros da tabela que será feito o manipulamento dos dados
     * Ao finalizar as operações é correto finalizar a conexão do banco de dados
     */
    public class BancoController {
        private SQLiteDatabase db;
        private cadastroActivity.Banco banco;

        public BancoController(Context context) {
            banco = new cadastroActivity.Banco(context);
        }

        public String insereDado(String Matricula, String Nome, String Email, String Senha, String Perfil) {
            ContentValues valores;
            long resultado;

            db = banco.getWritableDatabase();
            valores = new ContentValues();
            valores.put(cadastroActivity.Banco.Matricula, Matricula);
            valores.put(cadastroActivity.Banco.Nome, Nome);
            valores.put(cadastroActivity.Banco.Email, Email);
            valores.put(cadastroActivity.Banco.Senha, Senha);
            valores.put(cadastroActivity.Banco.Perfil, Perfil);

            resultado = db.insert(cadastroActivity.Banco.Tabela_usuarios, null, valores);
            db.close();

            if (resultado == -1)
                return "Erro ao inserir registro";
            else
                return "Registro Inserido com sucesso";
        }


    }

    /**
     * Classe ambém faz controle do Banco de Dados
     * Os Dados inseridos Serão convertios em String
     * O metodo insereDados passa por parametro as informações que serão adicionadas
     * Ao final da operação será exibito uma mesagem (Toast) mostrando se a operação foi realizada com sucesso ou não
     */
    public class InsereDado extends Activity {

        @Override
        protected void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            setContentView(R.layout.activity_cadastro_turma);

            Button botao = (Button)findViewById(R.id.button);

            botao.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cadastroActivity.BancoController crud = new cadastroActivity.BancoController(getBaseContext());
                    EditText Nome = (EditText) findViewById(R.id.nome);
                    EditText Matricula = (EditText) findViewById(R.id.email);
                    EditText Email = (EditText) findViewById(R.id.matricula);
                    EditText Senha = (EditText) findViewById(R.id.senha);
                    EditText Perfil = (EditText) findViewById(R.id.perfil);
                    String nomeString = Nome.getText().toString();
                    String matriculaString = Matricula.getText().toString();
                    String emailString = Email.getText().toString();
                    String senhaString = Senha.getText().toString();
                    String perfilString = Perfil.getText().toString();
                    String resultado;

                    resultado = crud.insereDado(nomeString, matriculaString, emailString, senhaString, perfilString);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                }

            });
        }
    }
}
