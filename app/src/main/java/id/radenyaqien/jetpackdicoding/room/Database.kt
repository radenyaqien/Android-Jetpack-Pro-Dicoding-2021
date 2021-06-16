package id.radenyaqien.jetpackdicoding.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TvEntity::class, MoviesEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun FavDao(): FavoriteDAO

}