package id.radenyaqien.jetpackdicoding

import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.utils.Mapper

class MoviesMapping : Mapper<MoviesEntity, PopularMovies> {
    override fun from(e: PopularMovies) = MoviesEntity(
        e.id,
        e.adult,
        e.backdropPath,
        e.genreIds[0],
        e.originalLanguage, e.originalTitle,
        e.overview, e.popularity, e.posterPath, e.releaseDate, e.title,
        e.video, e.voteAverage, e.voteCount
    )

    override fun to(t: MoviesEntity): PopularMovies {
        val aray = ArrayList<Int>()

        aray.add(t.genreIds)

        return PopularMovies(
            t.adult,
            t.backdropPath,
            aray,
            t.id,
            t.originalLanguage,
            t.originalTitle,
            t.overview,
            t.popularity,
            t.posterPath,
            t.releaseDate,
            t.title,
            t.video,
            t.voteAverage,
            t.voteCount
        )


    }
}