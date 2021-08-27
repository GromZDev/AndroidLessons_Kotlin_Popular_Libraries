package q3_kotlin.popular_libraries.myapplication.view.popular

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PopularFilmsView : MvpView {
    fun init()
    fun updateList()
}
