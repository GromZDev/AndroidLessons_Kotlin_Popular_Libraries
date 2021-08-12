package q3_kotlin.popular_libraries.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentUserDetailsBinding
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.presenter.UserDetailsPresenter


class UserDetailsFragment : MvpAppCompatFragment(), UserView {

    companion object {
        private const val BUNDLE_EXTRA = "MY_GithubUser"

        fun newInstance(user: GithubUser) = UserDetailsFragment().apply {
            arguments = bundleOf(BUNDLE_EXTRA to user)
        }
    }

    private val user: GithubUser by lazy {
        arguments?.getParcelable<GithubUser>("MY_GithubUser") as GithubUser
    }

    private var vb: FragmentUserDetailsBinding? = null

    private val userDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            App.instance.router,
            user
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserDetailsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun showUserLogin(user: GithubUser) {
        vb?.userLogin?.text = user.login
    }


}