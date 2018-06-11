package quiniela.tesis.com.mundialprueba2018;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos>{

    ArrayList<PartidoVo> listDatos;

    public AdapterDatos(ArrayList<PartidoVo> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
       try {

           //holder.asignarDatos(listDatos.get(position));
           holder.dato.setText(listDatos.get(position).getNombre());
           holder.dato2.setText(listDatos.get(position).getGrupo());
           holder.foto.setImageResource(listDatos.get(position).getFoto());
       }
       catch ( Exception e)
       {
           e.printStackTrace();
       }

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato,dato2;
        ImageView foto;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato=(TextView) itemView.findViewById(R.id.idDato);
            dato2=(TextView) itemView.findViewById(R.id.idDato2);
            foto=(ImageView) itemView.findViewById(R.id.imageView);
        }

       // public void asignarDatos(String datos) {
         //  dato.setText(datos);
       // }
    }
}
