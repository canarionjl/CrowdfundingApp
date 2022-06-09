package com.joseluis.crowfundingapp.projectList;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

    private final View.OnClickListener clickListener;
    private List<ProjectItem> itemList;
    private Context context;

    public ProjectListAdapter(Context context,View.OnClickListener listener) {
        this.context = context;
        itemList= new ArrayList<>();
        clickListener = listener;
    }

    public void setItems(List<ProjectItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);

        holder.title.setText(itemList.get(position).title.toUpperCase(Locale.ROOT));
        holder.category.setText(itemList.get(position).category);
        holder.author.setText(itemList.get(position).author);
        //Glide.with(context).load(itemList.get(position).picture).into(holder.image);
        holder.image.setImageResource(R.drawable.alarma_icon);


    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView category;
        TextView author;
        ImageView image;

        ViewHolder(View view) {
            super(view);
            title =view.findViewById(R.id.textViewTitleDetailProject);
            category=view.findViewById(R.id.textViewCategoryDetailProject);
            author=view.findViewById(R.id.textViewAuthorDetailProject);
            image = view.findViewById(R.id.imageViewDetailProject);
        }
    }

    public static String TAG = ProjectListAdapter.class.getSimpleName();
}

