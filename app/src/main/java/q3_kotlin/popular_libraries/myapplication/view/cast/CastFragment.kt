package q3_kotlin.popular_libraries.myapplication.view.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.imageview.ShapeableImageView
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.R
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentCastBinding
import q3_kotlin.popular_libraries.myapplication.presenter.cast.CastPresenter
import q3_kotlin.popular_libraries.myapplication.presenter.cast.CastPresenterFactory
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader
import q3_kotlin.popular_libraries.myapplication.view.AbsFragment
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener
import javax.inject.Inject

class CastFragment : AbsFragment(R.layout.fragment_cast), CastView, BackButtonListener {

    @Inject
    lateinit var castPresenterFactory: CastPresenterFactory

    @Inject
    lateinit var imageLoader: ImageLoader<ShapeableImageView>

    companion object {
        const val BUNDLE_EXTRA = "MY_Cast"
        fun newInstance(bundle: Bundle): CastFragment {
            val fragment = CastFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val castPresenter: CastPresenter by moxyPresenter {
        castPresenterFactory.create(arguments?.getParcelable(BUNDLE_EXTRA))
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
        castAdapter = CastRVAdapter(castPresenter.castListPresenter, imageLoader)
        vb?.rvCasts?.adapter = castAdapter
    }

    override fun updateList() {
        castAdapter?.notifyDataSetChanged()
    }

    override fun backPressed() = castPresenter.backPressed()
}