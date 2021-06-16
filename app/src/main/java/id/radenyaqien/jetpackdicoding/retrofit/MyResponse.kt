package id.radenyaqien.jetpackdicoding.retrofit


import com.google.gson.annotations.SerializedName

data class MyResponse<T>(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<T>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)