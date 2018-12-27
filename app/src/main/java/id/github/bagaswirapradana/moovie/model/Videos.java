package id.github.bagaswirapradana.moovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Videos {

    @SerializedName("results")
    @Expose
    private List<VideosList> results = new ArrayList<>();

    public List<VideosList> getResults() {
        return results;
    }

    public void setResults(List<VideosList> results) {
        this.results = results;
    }
}
