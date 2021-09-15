package q3_kotlin.popular_libraries.myapplication.view.current

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import com.google.android.material.imageview.ShapeableImageView
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.R
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentPopularCurrentFilmBinding
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovie
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovieRepo
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie
import q3_kotlin.popular_libraries.myapplication.navigation.CastScreen
import q3_kotlin.popular_libraries.myapplication.presenter.current.CurrentFilmPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader
import q3_kotlin.popular_libraries.myapplication.view.AbsFragment
import q3_kotlin.popular_libraries.myapplication.view.BackButtonListener
import q3_kotlin.popular_libraries.myapplication.view.cast.CastFragment
import javax.inject.Inject

class PopularCurrentFilmFragment(
    private val imageLoader: ImageLoader<ShapeableImageView> = GlideImageLoader(),
) : AbsFragment(R.layout.fragment_popular_films), CurrentFilmView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Scheduler

    @Inject
    lateinit var currentMovie: CurrentMovieRepo

    companion object {
        const val BUNDLE_EXTRA = "MY_Film"
        fun newInstance(bundle: Bundle): PopularCurrentFilmFragment {
            val fragment = PopularCurrentFilmFragment()
            //  App.instance.router
            fragment.arguments = bundle
            return fragment
        }
    }

    private val presenter: CurrentFilmPresenter by moxyPresenter {
        CurrentFilmPresenter(
            uiScheduler = schedulers,
            movieRepo = currentMovie,
            router = router,
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
            //  val router = App.instance.router

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