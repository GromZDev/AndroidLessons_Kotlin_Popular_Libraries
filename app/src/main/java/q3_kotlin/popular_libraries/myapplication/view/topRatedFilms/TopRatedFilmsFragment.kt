package q3_kotlin.popular_libraries.myapplication.view.topRatedFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.api.ApiHolderTopRated
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentTopRatedFilmsBinding
import q3_kotlin.popular_libraries.myapplication.presenter.topRated.TopRatedFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.RetrofitTopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener

class TopRatedFilmsFragment : MvpAppCompatFragment(), TopRatedFilmsView, BackButtonListener {

    private val presenter: TopRatedFilmsPresenter by moxyPresenter {
        TopRatedFilmsPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitTopRatedFilmsRepo(ApiHolderTopRated.api),
            App.instance.router
        )
    }

    private var adapter: TopRatedFilmsRVAdapter? = null
    private var vb: FragmentTopRatedFilmsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentTopRatedFilmsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvTopRated?.layoutManager = LinearLayoutManager(context)
        adapter = TopRatedFilmsRVAdapter(presenter.topRatedListPresenter, GlideImageLoader())
        vb?.rvTopRated?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}