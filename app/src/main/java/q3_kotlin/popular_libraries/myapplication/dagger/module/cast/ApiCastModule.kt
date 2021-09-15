package q3_kotlin.popular_libraries.myapplication.dagger.module.cast

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.retrofit.cast.CastApi
import q3_kotlin.popular_libraries.myapplication.retrofit.current.CurrentMovieApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiCastModule {

    @Provides
    fun provideApi(gson: Gson): CastApi =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CastApi::class.java)

}