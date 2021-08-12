package q3_kotlin.popular_libraries.myapplication.retrofit

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)

}