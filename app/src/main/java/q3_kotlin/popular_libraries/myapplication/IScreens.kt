package q3_kotlin.popular_libraries.myapplication

import android.os.Bundle
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetails(bundle: Bundle): Screen
}
