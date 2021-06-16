package id.radenyaqien.jetpackdicoding.utils

import id.radenyaqien.jetpackdicoding.MoviesMapping
import id.radenyaqien.jetpackdicoding.TvMapping
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity

object Dummy {
    private val mapper by lazy { TvMapping() }
    private val map by lazy { MoviesMapping() }
    const val image = "https://image.tmdb.org/t/p/w500"

    fun getDummyMovies(): ArrayList<PopularMovies> {
        val popular = ArrayList<PopularMovies>()
        popular.add(
            PopularMovies(
                false,
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                listOf(35, 80),
                337404,
                "en",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                9320.775,
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                "2021-05-26",
                "Cruella",
                false,
                8.7,
                1982
            )
        )

        return popular
    }

    fun getDummyMoviesEntitiy(): ArrayList<MoviesEntity> {
        val popular = ArrayList<MoviesEntity>()
        val movie = map.from(
            (
                    PopularMovies(
                        false,
                        "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                        listOf(35, 80),
                        337404,
                        "en",
                        "Cruella",
                        "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                        9320.775,
                        "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                        "2021-05-26",
                        "Cruella",
                        false,
                        8.7,
                        1982
                    ))
        )
        popular.add(movie)
        return popular
    }

    fun getDummyPopular(): ArrayList<PopularTv> {
        val popular = ArrayList<PopularTv>()
        popular.add(
            PopularTv(
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "2021-03-19",
                listOf(10765, 10759, 18),
                88396,
                "The Falcon and the Winter Soldier",
                listOf("US"),
                "en",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                1708.193,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                7.9,
                5378
            )
        )

        return popular
    }

    fun getDummyTvEntity(): ArrayList<TvEntity> {
        val popular = ArrayList<TvEntity>()
        popular.add(
            mapper.from(
                PopularTv(
                    "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                    "2021-03-19",
                    listOf(10765, 10759, 18),
                    88396,
                    "The Falcon and the Winter Soldier",
                    listOf("US"),
                    "en",
                    "The Falcon and the Winter Soldier",
                    "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                    1708.193,
                    "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                    7.9,
                    5378
                )
            )
        )

        return popular
    }

    fun generateTvEntity(): TvEntity {
        val moduleEntity = mapper.from(getDummyPopular()[0])
        return moduleEntity
    }

    fun generateMoviesEntity(): MoviesEntity {
        val moduleEntity = map.from(getDummyMovies()[0])
        return moduleEntity
    }

    fun getdummyTv(id: Int): TvEntity {
        return TvEntity(
            id, "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg", "2021-03-19",
            88396,
            "The Falcon and the Winter Soldier",
            "US",
            "en",
            "The Falcon and the Winter Soldier",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            1708.193,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            7.9,
            5378
        )

    }

    fun getdummymovies(id: Int): MoviesEntity {
        return MoviesEntity(
            id,
            false,
            "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            35,
            "en",
            "Cruella",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            9320.775,
            "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
            "2021-05-26",
            "Cruella",
            false,
            8.7,
            1982
        )

    }

}