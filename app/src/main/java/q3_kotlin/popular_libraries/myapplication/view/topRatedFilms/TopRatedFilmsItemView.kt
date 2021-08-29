package q3_kotlin.popular_libraries.myapplication.view.topRatedFilms

import q3_kotlin.popular_libraries.myapplication.view.RVItemView

interface TopRatedFilmsItemView : RVItemView {

    fun setTitle(text: String)
    fun loadImage(url: String)
    fun setRating(rating: Float)
    fun setDate(date: String)
    fun setPopularity(votes: Float)

}
