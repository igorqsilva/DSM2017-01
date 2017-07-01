package com.iam.here.hereiam;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import com.iam.here.basedados.cadastroAdapter;
import com.iam.here.basedados.cadastroTurmaBase;
import com.iam.here.basedados.cadastroTurmaDAO;
import com.iam.here.basedados.baseDAO;

/**
 * Created by eliezer on 01/07/2017.
 */

public class listagemTurmas extends ListActivity {

    private cadastroTurmaDAO cadastroTurmaDAO;
    List<cadastroTurmaBase> cadastros;
    cadastroAdapter adapter;
    baseDAO baseDAO;
    int posicao = 0;

    //@Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_turma);

        baseDAO = new baseDAO(this);

        Button btCadastro2 = (Button) findViewById(R.id.btCadastro2);
        btCadastro2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(listagemTurmas.this, cadastrarTurma.class ));
            }
        });
    }

    @Override
    protected void onResume(){
        cadastroTurmaDAO = new cadastroTurmaDAO(this);
        cadastroTurmaDAO.open();
        cadastros = cadastroTurmaDAO.lerCadastro();

        adapter = new cadastroAdapter(this, cadastros);
        setListAdapter(adapter);

        super.onResume();
    }
}
