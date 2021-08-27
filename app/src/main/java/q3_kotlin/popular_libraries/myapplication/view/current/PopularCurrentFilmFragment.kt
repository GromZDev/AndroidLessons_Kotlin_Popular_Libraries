package q3_kotlin.popular_libraries.myapplication.view.current

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.imageview.ShapeableImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.api.ApiHolderCurrentMovie
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentPopularCurrentFilmBinding
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovie
import q3_kotlin.popular_libraries.myapplication.navigation.CastScreen
import q3_kotlin.popular_libraries.myapplication.presenter.current.CurrentFilmPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.current.RetrofitCurrentFilmRepo
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener
import q3_kotlin.popular_libraries.myapplication.view.cast.CastFragment

class PopularCurrentFilmFragment(
    private val imageLoader: ImageLoader<ShapeableImageView> = GlideImageLoader(),
) : MvpAppCompatFragment(), CurrentFilmView, BackButtonListener {

    companion object {
        const val BUNDLE_EXTRA = "MY_Film"
        fun newInstance(bundle: Bundle): PopularCurrentFilmFragment {
            val fragment = PopularCurrentFilmFragment()
            App.instance.router
            fragment.arguments = bundle
            return fragment
        }
    }

    private val presenter: CurrentFilmPresenter by moxyPresenter {
        CurrentFilmPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitCurrentFilmRepo(ApiHolderCurrentMovie.api),
            App.instance.router,
            arguments?.getParcelable(BUNDLE_EXTRA)!!
        )
    }

    private var vb: FragmentPopularCurrentFilmBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentPopularCurrentFilmBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedMovie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        val imagePath = receivedMovie?.posterPath

        vb?.twPopularFilmName?.text = receivedMovie?.title

        vb?.twPopularFilmDescription?.text = receivedMovie?.overview
        vb?.twPopularFilmDescription?.movementMethod = ScrollingMovementMethod()

        vb?.iwPopularFilmImage?.let {
            imageLoader.loadInto(
                "https://image.tmdb.org/t/p/w500/$imagePath",
                it
            )
        }

        /** И затемняем фото: */
        vb?.iwPopularFilmImage?.setColorFilter(
            Color.rgb(123, 123, 123),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )

        vb?.twPopularFilmRating?.text = receivedMovie?.rating.toString()

        vb?.twPopularFilmYear?.text = receivedMovie?.releaseDate

        vb?.twPopularFilmTime?.text = receivedMovie?.popularity.toString()

        vb?.iwCastClick?.setOnClickListener {
            val router = App.instance.router

            val bundle = Bundle()
            bundle.putParcelable(CastFragment.BUNDLE_EXTRA, receivedMovie)
            router.navigateTo(CastScreen.create(bundle))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init(currentMovie: CurrentMovie) {

        vb?.twPopularFilmTime?.text = presenter.pres.film[0].runtime.toString()
        vb?.twPopularFilmBudget?.text = presenter.pres.film[0].budget.toString()
        vb?.twPopularFilmRevenue?.text = presenter.pres.film[0].revenue.toString()
        vb?.twPopularFilmVotes?.text = presenter.pres.film[0].voteCount.toString()
    }

    override fun backPressed() = presenter.backPressed()

}