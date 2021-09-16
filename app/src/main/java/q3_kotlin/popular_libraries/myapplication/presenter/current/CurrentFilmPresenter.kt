package q3_kotlin.popular_libraries.myapplication.presenter.current

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovie
import q3_kotlin.popular_libraries.myapplication.model.current.CurrentMovieRepo
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie
import q3_kotlin.popular_libraries.myapplication.view.current.CurrentFilmItemView
import q3_kotlin.popular_libraries.myapplication.view.current.CurrentFilmView

class CurrentFilmPresenter @AssistedInject constructor(
    private val uiScheduler: Scheduler,
    private val movieRepo: CurrentMovieRepo,
    private val router: Router,
    @Assisted private val movie: Movie
) : MvpPresenter<CurrentFilmView>() {

    class CurrentOneFilmPresenter : CurrentMoviesPresenter {
        val film = mutableListOf<CurrentMovie>()

        override fun bindView(view: CurrentFilmItemView) {
            film.let {
                view.setRuntime(film[0].runtime.toString())
                view.setBudget(film[0].budget)
                view.setRevenue(film[0].revenue.toString())
                view.setVote(film[0].rating)
            }
        }
    }

    val pres = CurrentOneFilmPresenter()

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        movie.id?.let { loadData(it) }
    }

    private fun loadData(movieId: Long) {

        val oneMovie = movieRepo.getCurrentMovie(movieId)
            .observeOn(uiScheduler)
            .subscribe { repos ->
                pres.film.clear()
                pres.film.addAll(listOf(repos))
                viewState.init(pres.film[0])
            }

        disposable.add(oneMovie)
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