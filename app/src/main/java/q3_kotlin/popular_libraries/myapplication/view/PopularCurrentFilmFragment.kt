package q3_kotlin.popular_libraries.myapplication.view

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.imageview.ShapeableImageView
import moxy.MvpAppCompatFragment
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentPopularCurrentFilmBinding
import q3_kotlin.popular_libraries.myapplication.model.Movie
import q3_kotlin.popular_libraries.myapplication.navigation.CastScreen
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader

class PopularCurrentFilmFragment(
    private val imageLoader: ImageLoader<ShapeableImageView> = GlideImageLoader(),
) : MvpAppCompatFragment() {

    companion object {
        const val BUNDLE_EXTRA = "MY_Film"
        fun newInstance(bundle: Bundle): PopularCurrentFilmFragment {
            val fragment = PopularCurrentFilmFragment()
            App.instance.router
            fragment.arguments = bundle
            return fragment
        }
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
}