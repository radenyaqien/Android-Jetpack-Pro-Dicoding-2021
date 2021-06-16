package id.radenyaqien.jetpackdicoding.retrofit


import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.models.PopularTv
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubInterface {

    @GET("3/tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") token: String,
    ): MyResponse<PopularTv>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") token: String

    ): MyResponse<PopularMovies>
}