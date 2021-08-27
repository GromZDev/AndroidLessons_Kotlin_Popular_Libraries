package q3_kotlin.popular_libraries.myapplication.presenter.popular

import q3_kotlin.popular_libraries.myapplication.view.RVItemView

interface IListPresenter<V : RVItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}