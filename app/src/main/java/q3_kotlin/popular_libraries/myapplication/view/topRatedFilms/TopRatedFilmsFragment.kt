package q3_kotlin.popular_libraries.myapplication.view.topRatedFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.api.ApiHolderTopRated
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentTopRatedFilmsBinding
import q3_kotlin.popular_libraries.myapplication.presenter.topRated.TopRatedFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.RetrofitTopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener

class TopRatedFilmsFragment : MvpAppCompatFragment(), TopRatedFilmsView, BackButtonListener {

    private val presenter: TopRatedFilmsPresenter by moxyPresenter {
        TopRatedFilmsPresenter(
            RetrofitTopRatedFilmsRepo(ApiHolderTopRated.api),
            popularFilmsPage
        ).apply {
            App.instance.appComponent.inject(this)
            App.instance.appComponent
        }
    }

    private var adapter: TopRatedFilmsRVAdapter? = null
    private var vb: FragmentTopRatedFilmsBinding? = null
    private var popularFilmsPage = 1

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
        adapter = TopRatedFilmsRVAdapter(presenter.topRatedListPresenter)
            .apply {
                App.instance.appComponent.inject(this)
            }
        vb?.rvTopRated?.adapter = adapter
        attachPopularMoviesOnScrollListener()
    }

    override fun updateList() {
        adapter?.notifyItemRangeInserted(
            presenter.topRatedListPresenter.topRatedFilms.size,
            presenter.itemsAdded
        )
    }

    override fun loadNewMovies(page: Int) {
        presenter.loadData(page)
        adapter?.appendMovies(presenter.topRatedListPresenter.topRatedFilms)
        adapter?.notifyItemRangeInserted(
            presenter.topRatedListPresenter.topRatedFilms.size,
            presenter.itemsAdded
        )
        attachPopularMoviesOnScrollListener()
    }

    override fun backPressed() = presenter.backPressed()

    private fun attachPopularMoviesOnScrollListener() {
        vb?.rvTopRated?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lm = vb?.rvTopRated?.layoutManager as LinearLayoutManager
                val totalItemCount = lm.itemCount
                val visibleItemCount = lm.childCount
                val firstVisibleItem = lm.findFirstVisibleItemPosition()
                vb?.includedLoadingLayout?.loadingLayout?.visibility = View.GONE
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    vb?.includedLoadingLayout?.loadingLayout?.visibility = View.VISIBLE
                    vb?.rvTopRated?.removeOnScrollListener(this)
                    popularFilmsPage++
                    loadNewMovies(popularFilmsPage)
                }
            }
        })
    }
}