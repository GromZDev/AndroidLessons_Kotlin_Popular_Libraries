package q3_kotlin.popular_libraries.myapplication.presenter.current

import dagger.assisted.AssistedFactory
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie

@AssistedFactory
interface CurrentFilmPresenterFactory {

    fun create(movie: Movie?): CurrentFilmPresenter
}