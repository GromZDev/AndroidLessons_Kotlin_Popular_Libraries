package q3_kotlin.popular_libraries.myapplication.presenter.popular

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie
import q3_kotlin.popular_libraries.myapplication.model.popular.PopularFilmsRepo
import q3_kotlin.popular_libraries.myapplication.navigation.PopularCurrentFilmScreen
import q3_kotlin.popular_libraries.myapplication.view.current.PopularCurrentFilmFragment
import q3_kotlin.popular_libraries.myapplication.view.popular.PopularFilmsItemView
import q3_kotlin.popular_libraries.myapplication.view.popular.PopularFilmsView
import javax.inject.Inject

class PopularFilmsPresenter : MvpPresenter<PopularFilmsView>() {

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var moviesRepo: PopularFilmsRepo

    class PopularFilmsListPresenter : PopularMoviesListPresenter {
        val films = mutableListOf<Movie>()
        override var itemClickListener: ((PopularFilmsItemView) -> Unit)? = null

        override fun getCount() = films.size

        override fun bindView(view: PopularFilmsItemView) {
            val movie = films[view.pos]
            val imagePath = "https://image.tmdb.org/t/p/w500/"
            movie.let {
                view.setTitle(it.title.toString())
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

    val popularFilmsListPresenter = PopularFilmsListPresenter()

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val movies = moviesRepo.getPopularFilms()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                popularFilmsListPresenter.films.clear()
                popularFilmsListPresenter.films.addAll(repos.movies)
                viewState.updateList()
            }, {
                println("Error: $it")
            })

        disposable.add(movies)

        popularFilmsListPresenter.itemClickListener = { itemView ->

            val currentFilm = popularFilmsListPresenter.films[itemView.pos]
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