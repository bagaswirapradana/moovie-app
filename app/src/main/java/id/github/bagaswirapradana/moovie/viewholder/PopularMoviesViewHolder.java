package id.github.bagaswirapradana.moovie.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.github.bagaswirapradana.moovie.R;

public class PopularMoviesViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public TextView textView;

    public PopularMoviesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.title);
    }
}
