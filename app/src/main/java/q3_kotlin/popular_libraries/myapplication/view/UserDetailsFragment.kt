package q3_kotlin.popular_libraries.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentUserDetailsBinding
import q3_kotlin.popular_libraries.myapplication.model.GithubUser


class UserDetailsFragment : MvpAppCompatFragment() {

    companion object {
        const val BUNDLE_EXTRA = "MY_GithubUser"
        fun newInstance(bundle: Bundle) : UserDetailsFragment{
            val fragment = UserDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var vb: FragmentUserDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserDetailsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getParcelable<GithubUser>(BUNDLE_EXTRA)
        vb?.userLogin?.text = user?.login
    }


    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }


}