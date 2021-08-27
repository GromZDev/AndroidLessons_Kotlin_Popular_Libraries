package q3_kotlin.popular_libraries.myapplication.view.cast

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CastView : MvpView {
    fun init()
    fun updateList()
}
