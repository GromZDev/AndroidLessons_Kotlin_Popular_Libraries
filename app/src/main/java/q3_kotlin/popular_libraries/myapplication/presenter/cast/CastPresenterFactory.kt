package q3_kotlin.popular_libraries.myapplication.presenter.cast

import dagger.assisted.AssistedFactory
import q3_kotlin.popular_libraries.myapplication.model.popular.Movie

@AssistedFactory
interface CastPresenterFactory {

    fun create(movie: Movie?): CastPresenter
}