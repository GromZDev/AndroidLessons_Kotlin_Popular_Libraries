package q3_kotlin.popular_libraries.myapplication.view.popular

import q3_kotlin.popular_libraries.myapplication.view.RVItemView

interface PopularFilmsItemView : RVItemView {

    fun setTitle(text: String)
    fun loadImage(url: String)
    fun setRating(rating: Float)
    fun setDate(date: String)
    fun setPopularity(votes: Float)

}
