package com.moringaschool.myproperty.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.moringaschool.myproperty.R;
import com.moringaschool.myproperty.models.Defect;
import com.moringaschool.myproperty.ui.DefectsActivity;
import com.moringaschool.myproperty.ui.PropertyDetailsActivity;

import java.io.Serializable;
import java.util.List;

public class DefectRecAdapter extends RecyclerView.Adapter<DefectRecAdapter.myHolder> {
    List<Defect> allDefects;
    Context cont;

    public DefectRecAdapter(List<Defect> allDefects, Context cont) {
        this.allDefects = allDefects;
        this.cont = cont;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.property, parent, false);
        return new myHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.setData(allDefects.get(position));

    }

    @Override
    public int getItemCount() {
        return allDefects.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ShapeableImageView img;

        public myHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.propertyName);
            description = itemView.findViewById(R.id.propertyDescription);
            img = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Intent intent = new Intent(cont, DefectsActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("allDefects", (Serializable) allDefects);
                    cont.startActivity(intent);
                    Toast.makeText(cont, "Coming Wait and Relax ", Toast.LENGTH_LONG).show();
                }
            });

        }

        public void setData(Defect defect){
            name.setText(defect.getProperty_name());
            description.setText(defect.getDescription());
            Glide.with(cont)
                    .asBitmap()
                    .load(defect.getString_uri())
                    .into(img);
        }
    }
}
