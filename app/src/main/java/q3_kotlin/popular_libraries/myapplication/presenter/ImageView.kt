package q3_kotlin.popular_libraries.myapplication.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun makeConversion()

    fun inProgress()
    fun closeProgress()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onSuccess(newImageInBytes: ByteArray)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onCancel()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onError()
}