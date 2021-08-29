package q3_kotlin.popular_libraries.myapplication.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import q3_kotlin.popular_libraries.myapplication.retrofit.topRated.TopRatedMovieApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolderTopRated {

    val api: TopRatedMovieApi by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TopRatedMovieApi::class.java)
    }

}