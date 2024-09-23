package com.example.rakibapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rakibapplication.data_model.ItemsModel;
import com.example.rakibapplication.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

     List<ItemsModel> itemsList;
     Context context;
     String act_tag;

    public DataAdapter(Context context, List<ItemsModel> itemsList,String act_tag) {
        this.itemsList = itemsList;
        this.context = context;
        this.act_tag = act_tag;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_model_style,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        setImage(holder);

        holder.tvTitle.setText(itemsList.get(position).getTitle());
        holder.tvWriter.setText(itemsList.get(position).getWriter());

    }

    private void setImage(DataAdapter.ViewHolder holder) {
        if (act_tag.equals("gaan")) {
            holder.ivImage.setImageResource(R.drawable.music);
        }
        if (act_tag.equals("gazal")) {
            holder.ivImage.setImageResource(R.drawable.gazal);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface ItemClickListener {
        void onItemClick();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage,btnDel, btnFav;
        TextView tvTitle,tvWriter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.itemPhoto);
            btnDel = itemView.findViewById(R.id.btnDel);
            btnFav = itemView.findViewById(R.id.btnFav);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvWriter = itemView.findViewById(R.id.tvWriter);
        }
    }
}
