package q3_kotlin.popular_libraries.myapplication.view.current

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovie

@StateStrategyType(AddToEndSingleStrategy::class)
interface CurrentFilmView : MvpView {

    fun init(currentMovie: CurrentMovie)
}
