package id.radenyaqien.jetpackdicoding.retrofit


import retrofit2.http.GET
import retrofit2.http.Query

interface GithubInterface {

    @GET("3/tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") token: String,
    ): MyResponse
}