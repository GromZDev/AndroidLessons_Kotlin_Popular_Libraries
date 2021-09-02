package q3_kotlin.popular_libraries.myapplication.presenter.topRated

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie
import q3_kotlin.popular_libraries.myapplication.model.topRated.TopRatedFilmsRepo
import q3_kotlin.popular_libraries.myapplication.navigation.PopularCurrentFilmScreen
import q3_kotlin.popular_libraries.myapplication.view.current.PopularCurrentFilmFragment
import q3_kotlin.popular_libraries.myapplication.view.topRatedFilms.TopRatedFilmsItemView
import q3_kotlin.popular_libraries.myapplication.view.topRatedFilms.TopRatedFilmsView
import javax.inject.Inject

class TopRatedFilmsPresenter(
    private val topRatedMovies: TopRatedFilmsRepo
) : MvpPresenter<TopRatedFilmsView>() {

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var router: Router

    class TopRatedFilmsListPresenter : TopRatedMoviesListPresenter {
        val topRatedFilms = mutableListOf<Movie>()
        override var itemClickListener: ((TopRatedFilmsItemView) -> Unit)? = null

        override fun getCount() = topRatedFilms.size

        override fun bindView(view: TopRatedFilmsItemView) {
            val movie = topRatedFilms[view.pos]
            val imagePath = "https://image.tmdb.org/t/p/w500/"
            movie.let {
                it.title?.let { it1 -> view.setTitle(it1) }
            }
            movie.let {
                view.loadImage(imagePath + it.posterPath.toString())
            }
            movie.let {
                view.setDate(it.releaseDate.toString())
            }
            movie.let {
                it.rating?.let { rate -> view.setRating(rate) }
            }
            movie.let {
                it.popularity?.let { popularity -> view.setPopularity(popularity) }
            }
        }
    }

    val topRatedListPresenter = TopRatedFilmsListPresenter()

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val movies = topRatedMovies.getTopRatedFilms()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                topRatedListPresenter.topRatedFilms.clear()
                topRatedListPresenter.topRatedFilms.addAll(repos.topRatedMovies)
                viewState.updateList()
            }, {
                println("Error: $it")
            })

        disposable.add(movies)

        topRatedListPresenter.itemClickListener = { itemView ->

            val currentFilm = topRatedListPresenter.topRatedFilms[itemView.pos]
            val bundle = Bundle()
            bundle.putParcelable(PopularCurrentFilmFragment.BUNDLE_EXTRA, currentFilm)
            router.navigateTo(PopularCurrentFilmScreen().create(bundle))

        }

        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}