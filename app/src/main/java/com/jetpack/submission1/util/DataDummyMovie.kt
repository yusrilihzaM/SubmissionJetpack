package com.jetpack.submission1.util


import com.jetpack.submission1.data.source.local.entity.MovieEntity
import java.util.ArrayList

object DataDummyMovie {

    fun getDummyRemoteMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                122,
                "Aragorn is revealed as the heir to the ancient kings as he, Gandalf and the other members of the broken fellowship struggle to save Gondor from Sauron's forces. Meanwhile, Frodo and Sam take the ring closer to the heart of Mordor, the dark lord's realm.",
                "The Lord of the Rings: The Return of the King",
                "The Lord of the Rings: The Return of the King",
                "rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
                122,
                false
            ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        movies.add(MovieEntity(
            0, "", "", "", "", 0, false
        ))
        return movies
    }
    fun getDummyMovieById():MovieEntity=
        MovieEntity(
            122,
            "Aragorn is revealed as the heir to the ancient kings as he, Gandalf and the other members of the broken fellowship struggle to save Gondor from Sauron's forces. Meanwhile, Frodo and Sam take the ring closer to the heart of Mordor, the dark lord's realm.",
            "The Lord of the Rings: The Return of the King",
            "The Lord of the Rings: The Return of the King",
            "rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
            122,
            false
        )
}