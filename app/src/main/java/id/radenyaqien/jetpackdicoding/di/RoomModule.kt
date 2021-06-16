package id.radenyaqien.jetpackdicoding.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.jetpackdicoding.room.Database
import id.radenyaqien.jetpackdicoding.room.FavoriteDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private val mDatabaseName = "favorite_db"

    @Singleton
    @Provides
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app, Database::class.java,
            mDatabaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase: Database): FavoriteDAO {
        return appDatabase.FavDao()
    }
}