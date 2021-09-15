package q3_kotlin.popular_libraries.myapplication.view.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.R
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentPopularFilmsBinding
import q3_kotlin.popular_libraries.myapplication.model.popular.PopularFilmsRepo
import q3_kotlin.popular_libraries.myapplication.presenter.popular.PopularFilmsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.view.AbsFragment
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener
import javax.inject.Inject

class PopularFilmsFragment : AbsFragment(R.layout.fragment_popular_films), PopularFilmsView,
    BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Scheduler

    @Inject
    lateinit var moviesRepo: PopularFilmsRepo

    private val presenter: PopularFilmsPresenter by moxyPresenter {
        PopularFilmsPresenter(
            uiScheduler = schedulers,
            moviesRepo = moviesRepo,
            router = router
        )
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
        adapter = PopularFilmsRVAdapter(presenter.popularFilmsListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}