package q3_kotlin.popular_libraries.myapplication.view.topRatedFilms

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TopRatedFilmsView : MvpView {
    fun init()
    fun updateList()
    fun loadNewMovies(page: Int)
}
