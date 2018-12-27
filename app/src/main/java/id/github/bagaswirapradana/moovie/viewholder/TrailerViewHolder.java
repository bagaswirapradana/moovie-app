package id.github.bagaswirapradana.moovie.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.github.bagaswirapradana.moovie.R;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public TrailerViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageTrailer);
    }
}
