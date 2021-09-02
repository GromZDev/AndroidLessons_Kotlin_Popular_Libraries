package q3_kotlin.popular_libraries.myapplication.presenter.topRated

import q3_kotlin.popular_libraries.myapplication.view.RVItemView

interface TopRatedListPresenter<V : RVItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}