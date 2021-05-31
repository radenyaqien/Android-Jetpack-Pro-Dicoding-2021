package id.radenyaqien.jetpackdicoding.retrofit


import com.google.gson.annotations.SerializedName
import id.radenyaqien.jetpackdicoding.models.PopularMovies

data class MyResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<PopularMovies>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)