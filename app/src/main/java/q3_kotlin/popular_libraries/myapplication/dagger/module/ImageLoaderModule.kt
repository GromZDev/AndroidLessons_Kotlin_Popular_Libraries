package q3_kotlin.popular_libraries.myapplication.dagger.module

import com.google.android.material.imageview.ShapeableImageView
import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.dagger.AppScope
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader

@Module
class ImageLoaderModule {

    @AppScope
    @Provides
    fun imageLoader(): ImageLoader<ShapeableImageView> =
        GlideImageLoader()
}