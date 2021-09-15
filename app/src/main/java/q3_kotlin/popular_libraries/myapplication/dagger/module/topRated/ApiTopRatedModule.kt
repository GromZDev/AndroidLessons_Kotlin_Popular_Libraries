package q3_kotlin.popular_libraries.myapplication.dagger.module.topRated

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.TopRatedMovieApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiTopRatedModule {

    @Provides
    fun provideApi(topGson: Gson): TopRatedMovieApi =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(topGson))
            .build()
            .create(TopRatedMovieApi::class.java)

}