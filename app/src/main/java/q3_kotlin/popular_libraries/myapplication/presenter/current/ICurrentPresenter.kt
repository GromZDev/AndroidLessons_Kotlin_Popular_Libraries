package q3_kotlin.popular_libraries.myapplication.presenter.current

import q3_kotlin.popular_libraries.myapplication.view.current.CurrentItemView

interface ICurrentPresenter<V : CurrentItemView> {
    fun bindView(view: V)

}