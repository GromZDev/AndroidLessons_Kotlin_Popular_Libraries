package q3_kotlin.popular_libraries.myapplication.view

import android.os.Bundle
import moxy.MvpAppCompatActivity
import q3_kotlin.popular_libraries.myapplication.R

class MainActivity : MvpAppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ImageConverterFragment.newInstance()
                )
                .commit()
        }
    }
}