
package com.example.branimir.movies.repository.remote.httpclient.dto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.domain.model.PageableMovies;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageableResult {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<MovieItem> movieItems = null;

    public PageableMovies toDomainModel() {
        List<Movie> movies = new ArrayList<>(movieItems.size());
        for (MovieItem movieItem: movieItems) {
            movies.add(movieItem.toDomainModel());
        }

        return new PageableMovies(
                page,
                totalResults,
                totalPages,
                movies
        );
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieItem> getMovieItems() {
        return movieItems;
    }

    public void setMovieItems(List<MovieItem> movieItems) {
        this.movieItems = movieItems;
    }

}
