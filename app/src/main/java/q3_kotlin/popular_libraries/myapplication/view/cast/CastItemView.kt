package q3_kotlin.popular_libraries.myapplication.view.cast

import q3_kotlin.popular_libraries.myapplication.view.RVItemView

interface CastItemView : RVItemView {

    fun setName(text: String)
    fun setCharacter(hero: String)
    fun setImage(img: String)

}
