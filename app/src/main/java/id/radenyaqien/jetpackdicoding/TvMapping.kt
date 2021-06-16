package id.radenyaqien.jetpackdicoding

import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.room.TvEntity
import id.radenyaqien.jetpackdicoding.utils.Mapper

class TvMapping : Mapper<TvEntity, PopularTv> {
    override fun from(e: PopularTv): TvEntity {
        return TvEntity(
            e.id,
            e.backdropPath,
            e.firstAirDate,
            e.genreIds[0],
            e.name,
            e.originCountry[0],
            e.originalLanguage, e.originalName,
            e.overview, e.popularity, e.posterPath, e.voteAverage, e.voteCount
        )

    }

    override fun to(t: TvEntity): PopularTv {
        val aray = ArrayList<Int>()
        val arayS = ArrayList<String>()
        aray.add(t.genreIds)
        arayS.add(t.originCountry)
        return PopularTv(
            t.backdropPath,
            t.firstAirDate,
            aray, t.id,
            t.name,
            arayS,
            t.originalLanguage, t.originalName,
            t.overview, t.popularity, t.posterPath, t.voteAverage, t.voteCount
        )
    }
}