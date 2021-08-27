package q3_kotlin.popular_libraries.myapplication.view.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.api.ApiHolderCast
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentCastBinding
import q3_kotlin.popular_libraries.myapplication.presenter.cast.CastPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.cast.RetrofitPopularFilmsCastRepo
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener

class CastFragment : MvpAppCompatFragment(), CastView, BackButtonListener {

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
            AndroidSchedulers.mainThread(),
            RetrofitPopularFilmsCastRepo(ApiHolderCast.api),
            App.instance.router,
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