package id.github.bagaswirapradana.moovie.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.VideosList;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public TrailerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageTrailer);
    }

    public void bindData(VideosList videosList,Context context){
        Picasso.with(context)
                .load("https://img.youtube.com/vi/"+videosList.getKey()+"/hqdefault.jpg")
                .into(imageView);
    }
}
