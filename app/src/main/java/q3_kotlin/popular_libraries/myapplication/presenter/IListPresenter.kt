package q3_kotlin.popular_libraries.myapplication.presenter

import q3_kotlin.popular_libraries.myapplication.view.RVItemView

interface IListPresenter<V : RVItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}