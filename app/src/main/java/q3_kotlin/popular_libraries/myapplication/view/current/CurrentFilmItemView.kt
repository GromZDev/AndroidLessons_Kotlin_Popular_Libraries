package q3_kotlin.popular_libraries.myapplication.view.current

interface CurrentFilmItemView : CurrentItemView {

    fun setRuntime(text: String)
    fun setRevenue(url: String)
    fun setBudget(budget: Int)
    fun setVote(vote: Float)

}
