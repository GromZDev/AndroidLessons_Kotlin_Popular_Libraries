package q3_kotlin.popular_libraries.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment(), MainView {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val presenter = Presenter(view = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOne.setOnClickListener { presenter.counterClickOne() }
        binding.buttonTwo.setOnClickListener { presenter.counterClickTwo() }
        binding.buttonThree.setOnClickListener { presenter.counterClickThree() }
    }

    override fun setButtonTextOne(text: String) {
        binding.buttonOne.text = text
    }

    override fun setButtonTextTwo(text: String) {
        binding.buttonTwo.text = text
    }

    override fun setButtonTextThree(text: String) {
        binding.buttonThree.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

