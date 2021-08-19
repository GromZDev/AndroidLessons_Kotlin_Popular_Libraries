package q3_kotlin.popular_libraries.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.App
import q3_kotlin.popular_libraries.myapplication.BackButtonListener
import q3_kotlin.popular_libraries.myapplication.api.ApiHolder
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentUserDetailsBinding
import q3_kotlin.popular_libraries.myapplication.model.GithubUser
import q3_kotlin.popular_libraries.myapplication.model.room.AndroidNetworkStatus
import q3_kotlin.popular_libraries.myapplication.model.room.cache.RoomCurrentUserCache
import q3_kotlin.popular_libraries.myapplication.model.room.db.Database
import q3_kotlin.popular_libraries.myapplication.presenter.UserDetailsPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.GlideImageLoader
import q3_kotlin.popular_libraries.myapplication.retrofit.RetrofitGithubSpecificUserRepo


class UserDetailsFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

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
    private var adapter: SpecificUserRVAdapter? = null

    private val userDetailsPresenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubSpecificUserRepo(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                RoomCurrentUserCache(Database.getInstance())
            ),
            user
        ).apply {
            App.instance.appComponent.inject(this)
        }
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


    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = SpecificUserRVAdapter(
            userDetailsPresenter.repositoriesListPresenter
        )
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
        vb?.reposCurrentCount?.text =
            userDetailsPresenter.repositoriesListPresenter.repositories.size.toString()
    }

    override fun showUserInfo(user: GithubUser) {
        vb?.userLogin?.text = user.login
        val imageLoader = GlideImageLoader()
        user.avatarUrl?.let {
            vb?.userImage?.let { container ->
                imageLoader.loadInto(
                    it,
                    container
                )
            }
        }
    }

    override fun backPressed() = userDetailsPresenter.backPressed()

}