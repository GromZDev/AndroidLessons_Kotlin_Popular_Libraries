package q3_kotlin.popular_libraries.myapplication.view.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentPopularFilmsBinding
import q3_kotlin.popular_libraries.myapplication.presenter.popular.PopularFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener

class PopularFilmsFragment : MvpAppCompatFragment(), PopularFilmsView, BackButtonListener {

    private val presenter: PopularFilmsPresenter by moxyPresenter {
        PopularFilmsPresenter()
            .apply {
                App.instance.appComponent.inject(this)
                App.instance.appComponent
            }
    }

    private var adapter: PopularFilmsRVAdapter? = null
    private var vb: FragmentPopularFilmsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentPopularFilmsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = PopularFilmsRVAdapter(presenter.popularFilmsListPresenter)
            .apply {
                App.instance.appComponent.inject(this)
            }
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}