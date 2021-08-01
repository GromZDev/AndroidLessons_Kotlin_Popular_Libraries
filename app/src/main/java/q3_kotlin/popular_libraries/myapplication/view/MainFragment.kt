package q3_kotlin.popular_libraries.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentMainBinding
import q3_kotlin.popular_libraries.myapplication.model.GithubUsersRepo
import q3_kotlin.popular_libraries.myapplication.presenter.MainPresenter
import q3_kotlin.popular_libraries.myapplication.view.MainView
import q3_kotlin.popular_libraries.myapplication.view.UsersRVAdapter

class MainFragment : MvpAppCompatFragment(), MainView {

//    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding!!
//
//    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) } // делегируем moxy
//
//    private var adapter: UsersRVAdapter? = null // объявляем адаптер
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = FragmentMainBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//    override fun init() {
//        binding.rvUsers.layoutManager = LinearLayoutManager(context)
//        adapter = UsersRVAdapter(presenter.usersListPresenter)
//        binding.rvUsers.adapter = adapter
//    }
//
//    override fun updateList() {
//        adapter?.notifyDataSetChanged()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        fun newInstance() = MainFragment()
//    }



}

