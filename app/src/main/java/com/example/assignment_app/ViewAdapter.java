package com.example.assignment_app;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private ArrayList<ViewModel> viewModels = new ArrayList<>();
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;


    }



    public ViewAdapter(Context context, ArrayList<ViewModel> viewModels) {
        this.viewModels = viewModels;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_list, viewGroup, false);
        return new ViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.item_desc.setText(viewModels.get(i).getTitle());


        Picasso.get().load(viewModels.get(i).getThumbnailUrl()).into(viewHolder.item_image);

    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_image;
        private TextView item_desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = (ImageView) itemView.findViewById(R.id.item_image);
            item_desc = (TextView) itemView.findViewById(R.id.item_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
