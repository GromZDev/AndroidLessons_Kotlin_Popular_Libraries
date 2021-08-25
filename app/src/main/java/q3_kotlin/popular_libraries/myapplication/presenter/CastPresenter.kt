package q3_kotlin.popular_libraries.myapplication.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.Cast
import q3_kotlin.popular_libraries.myapplication.model.CastRepo
import q3_kotlin.popular_libraries.myapplication.model.Movie
import q3_kotlin.popular_libraries.myapplication.view.CastItemView
import q3_kotlin.popular_libraries.myapplication.view.CastView

class CastPresenter(
    private val uiScheduler: Scheduler,
    private val castRepo: CastRepo,
    private val router: Router,
    private val movie: Movie
) : MvpPresenter<CastView>() {

    class CastListPresenter : PopularCastPresenter {
        val casts = mutableListOf<Cast>()
        override var itemClickListener: ((CastItemView) -> Unit)? = null

        override fun getCount() = casts.size

        override fun bindView(view: CastItemView) {
            val cast = casts[view.pos]
            val imagePath = "https://image.tmdb.org/t/p/w342/"

            cast.let {
                view.setCharacter(it.character)
            }
            cast.let {
                view.setName(it.name)
            }
            cast.let {
                view.setImage(imagePath + it.profile_path.toString())
            }
        }
    }

    val castListPresenter = CastListPresenter()

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        movie.id?.let { loadData(it) }
    }

    private fun loadData(movieId: Long) {
        val casts = castRepo.getPopularFilmsCast(movieId)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                castListPresenter.casts.clear()
                castListPresenter.casts.addAll(repos.cast)
                viewState.updateList()
            }, {
                println("Error: $it")
            })

        disposable.add(casts)

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