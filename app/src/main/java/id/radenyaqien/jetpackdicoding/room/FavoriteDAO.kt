package id.radenyaqien.jetpackdicoding.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface FavoriteDAO {
    //Movies
    @Query("SELECT * FROM movies_table")
    fun getAllmovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movies_table WHERE id = :id")
    fun getMoviesbyId(id: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMoviesList(movies: List<MoviesEntity>)

    @Delete
    suspend fun deleteMovies(movies: MoviesEntity)


    //TV SHOWS
    @Query("SELECT * FROM tv_table")
    fun getAllTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tv_table WHERE id = :id")
    fun getTvbyId(id: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTv(tv: TvEntity)

    @Delete
    suspend fun deleteTv(tv: TvEntity)

}