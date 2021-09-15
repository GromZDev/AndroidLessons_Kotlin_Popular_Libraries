package q3_kotlin.popular_libraries.myapplication.view.topRatedFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.R
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentTopRatedFilmsBinding
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.presenter.topRated.TopRatedFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.view.AbsFragment
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener
import javax.inject.Inject

class TopRatedFilmsFragment : AbsFragment(R.layout.fragment_top_rated_films), TopRatedFilmsView,
    BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Scheduler

    @Inject
    lateinit var topRatedMovies: TopRatedFilmsRepo

    private val presenter: TopRatedFilmsPresenter by moxyPresenter {
        TopRatedFilmsPresenter(
            uiScheduler = schedulers,
            topRatedMovies = topRatedMovies,
            router = router
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