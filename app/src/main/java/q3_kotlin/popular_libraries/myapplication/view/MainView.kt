package q3_kotlin.popular_libraries.myapplication.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView

//{
//
//    fun init() // для первичной инициализации списка
//    fun updateList() // для обновления содержимого списка
//
//}
