package q3_kotlin.popular_libraries.myapplication.view

interface PopularFilmsItemView : RVItemView {

    fun setTitle(text: String)
    fun loadImage(url: String)
    fun setRating(rating: Float)
    fun setDate(date: String)
    fun setPopularity(votes: Float)

}
