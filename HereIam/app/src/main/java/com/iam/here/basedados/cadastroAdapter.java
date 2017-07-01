package com.iam.here.basedados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.iam.here.hereiam.R;

import java.util.List;

/**
 * Created by eliezer on 01/07/2017.
 */

/**
 * Classe para mostrar Dados
 */
public class cadastroAdapter extends BaseAdapter{

    private Context context;

    private List<cadastroTurmaBase> cadastros;
    private LayoutInflater inflater;

    /**
     *
     * @param context
     * @param cadastros
     */
    public cadastroAdapter(Context context, List<cadastroTurmaBase> cadastros) {

        super();
        this.context = context;
        this.cadastros = cadastros;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Exibe mensagem de Erro
     */
    @Override
    public void notifyDataSetChanged(){

        try{
            super.notifyDataSetChanged();
        }catch (Exception e){
            trace("ERRO: " + e.getMessage());
        }

    }

    /**
     * exibe a msg de erro
     * @param msg
     */
    private void trace(String msg) {
        toast(msg);
    }

    /**
     * passa o parametro da mensagem
     * @param msg
     */
    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return cadastros.size();
    }

    /**
     * Remove item
     * @param cadastroTurmaBase
     */
    public void remove(final cadastroTurmaBase cadastroTurmaBase){
        this.cadastros.remove(cadastroTurmaBase);
    }

    /**
     * Adiciona Item
     * @param cadastroTurmaBase
     */
    public void add(final cadastroTurmaBase cadastroTurmaBase){
        this.cadastros.add(cadastroTurmaBase);
    }

    /**
     * Posição na tabela do Item
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return cadastros.get(position);
    }

    /**
     * Posição na tabela do Item
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Mostra a Listagem de Tuma na tela para o usuário
     * @param position
     * @param convertView
     * @param viewGroup2
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup2) {

        try {
            cadastroTurmaBase cadastroTurmaBase = cadastros.get(position);
            ViewHolder holder;

            if (convertView == null){
                convertView = inflater.inflate(R.layout.cadastros_de_turmas, null);

                holder = new ViewHolder();
                holder.nomeTurma = (TextView) convertView.findViewById(R.id.nomeTurma);
                holder.diaAulas = (TextView) convertView.findViewById(R.id.diaAulas);
                holder.horarioAulas = (TextView) convertView.findViewById(R.id.horarioAulas);
                holder.chaveAcesso = (TextView) convertView.findViewById(R.id.chaveAcesso);
                holder.local = (TextView) convertView.findViewById(R.id.local);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nomeTurma.setText(cadastroTurmaBase.getNomeTurma());
            holder.diaAulas.setText(cadastroTurmaBase.getDiaAulas());
            holder.horarioAulas.setText(cadastroTurmaBase.getHorarioAulas());
            holder.chaveAcesso.setText(cadastroTurmaBase.getChaveAcesso());
            holder.local.setText(cadastroTurmaBase.getLocal());

            return convertView;

        } catch (Exception e){
            trace("Erro:" + e.getMessage());
        }

        return convertView;
    }

    /**
     * Variáveis que guardam os valores dos campos
     * e Mostram  na tela os resultados cadastrados
     */
    static class ViewHolder{
        public TextView nomeTurma;
        public TextView diaAulas;
        public TextView horarioAulas;
        public TextView chaveAcesso;
        public TextView local;
    }
}
