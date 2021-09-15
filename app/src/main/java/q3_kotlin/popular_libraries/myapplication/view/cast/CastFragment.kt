package q3_kotlin.popular_libraries.myapplication.view.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.R
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentCastBinding
import q3_kotlin.popular_libraries.myapplication.model.cast.CastRepo
import q3_kotlin.popular_libraries.myapplication.presenter.cast.CastPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.view.AbsFragment
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener
import javax.inject.Inject

class CastFragment : AbsFragment(R.layout.fragment_cast), CastView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Scheduler

    @Inject
    lateinit var castRepo: CastRepo

    companion object {
        const val BUNDLE_EXTRA = "MY_Cast"
        fun newInstance(bundle: Bundle): CastFragment {
            val fragment = CastFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val castPresenter: CastPresenter by moxyPresenter {
        CastPresenter(
            uiScheduler = schedulers,
            castRepo = castRepo,
            router = router,
            arguments?.getParcelable(BUNDLE_EXTRA)!!
        )
    }

    private var castAdapter: CastRVAdapter? = null

    private var vb: FragmentCastBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentCastBinding.inflate(inflater, container, false).also {
            vb = it
        }.root


    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvCasts?.layoutManager = LinearLayoutManager(context)
        castAdapter = CastRVAdapter(castPresenter.castListPresenter, GlideImageLoader())
        vb?.rvCasts?.adapter = castAdapter
    }

    override fun updateList() {
        castAdapter?.notifyDataSetChanged()
    }

    override fun backPressed() = castPresenter.backPressed()
}