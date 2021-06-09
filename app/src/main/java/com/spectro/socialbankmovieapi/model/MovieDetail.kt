package com.spectro.socialbankmovieapi.model

data class MovieList(
    val Search: List<Movie>,
    val Response: Boolean
)

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)

data class MovieDetail (
    val Genre: String,
    val Plot: String,
    val Poster: String,
    val Released: String,
    val Title: String,
    val Year: String,
    val imdbRating: String,
    val imdbID: String,
    val Type: String
)