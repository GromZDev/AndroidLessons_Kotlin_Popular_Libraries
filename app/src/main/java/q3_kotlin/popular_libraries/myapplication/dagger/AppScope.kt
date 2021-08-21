package q3_kotlin.popular_libraries.myapplication.dagger

import javax.inject.Scope

/** Аннотация сохраняется в рантайме */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
