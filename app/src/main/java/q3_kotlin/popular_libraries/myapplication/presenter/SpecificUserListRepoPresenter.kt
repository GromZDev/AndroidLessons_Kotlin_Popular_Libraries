package q3_kotlin.popular_libraries.myapplication.presenter

import q3_kotlin.popular_libraries.myapplication.view.RVItemView
import q3_kotlin.popular_libraries.myapplication.view.SpecificUserRVAdapter

interface SpecificUserListRepoPresenter<V : RVItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: SpecificUserRVAdapter.ViewHolder)
    fun getCount(): Int
}