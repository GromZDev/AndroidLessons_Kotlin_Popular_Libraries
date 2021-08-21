package q3_kotlin.popular_libraries.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import q3_kotlin.popular_libraries.myapplication.databinding.ItemUserRvBinding
import q3_kotlin.popular_libraries.myapplication.presenter.IUserListPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader
import javax.inject.Inject

class UsersRVAdapter(
    private val presenter: IUserListPresenter
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader<ShapeableImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemUserRvBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {
        override var pos = -1
        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, vb.userItemImage)
        }
    }

}

