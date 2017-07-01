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
 * Created by eliez on 01/07/2017.
 */

public class cadastroAdapter extends BaseAdapter{

    private Context context;

    private List<cadastroTurmaBase> cadastros;
    private LayoutInflater inflater;

    public cadastroAdapter(Context context, List<cadastroTurmaBase> cadastros) {

        super();
        this.context = context;
        this.cadastros = cadastros;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void notifyDataSetChanged(){

        try{
            super.notifyDataSetChanged();
        }catch (Exception e){
            trace("ERRO: " + e.getMessage());
        }

    }

    private void trace(String msg) {
        toast(msg);
    }

    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return cadastros.size();
    }

    public void remove(final cadastroTurmaBase cadastroTurmaBase){
        this.cadastros.remove(cadastroTurmaBase);
    }

    public void add(final cadastroTurmaBase cadastroTurmaBase){
        this.cadastros.add(cadastroTurmaBase);
    }

    @Override
    public Object getItem(int position) {
        return cadastros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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

    static class ViewHolder{
        public TextView nomeTurma;
        public TextView diaAulas;
        public TextView horarioAulas;
        public TextView chaveAcesso;
        public TextView local;
    }
}
