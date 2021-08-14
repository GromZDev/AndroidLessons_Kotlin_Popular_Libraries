package q3_kotlin.popular_libraries.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import q3_kotlin.popular_libraries.myapplication.databinding.ItemSpecificUserRvBinding
import q3_kotlin.popular_libraries.myapplication.presenter.SpecificUserListPresenter

class SpecificUserRVAdapter(
    private val presenter: SpecificUserListPresenter
) : RecyclerView.Adapter<SpecificUserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemSpecificUserRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemSpecificUserRvBinding) :
        RecyclerView.ViewHolder(vb.root), SpecificUserRepoItemView {
        override var pos = -1

        override fun setForks(text: String) = with(vb) {
            tvForks.text = text
        }

        override fun setName(name: String) = with(vb) {
            tvForksName.text = name
        }

    }

}