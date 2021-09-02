package q3_kotlin.popular_libraries.myapplication.dagger.module

import com.google.android.material.imageview.ShapeableImageView
import dagger.Module
import dagger.Provides
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Provides
    fun imageLoader(): ImageLoader<ShapeableImageView> =
        GlideImageLoader()
}