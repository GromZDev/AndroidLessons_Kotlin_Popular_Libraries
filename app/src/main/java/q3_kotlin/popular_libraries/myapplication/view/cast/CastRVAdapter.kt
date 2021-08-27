package q3_kotlin.popular_libraries.myapplication.view.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import q3_kotlin.popular_libraries.myapplication.databinding.ItemCastRvBinding
import q3_kotlin.popular_libraries.myapplication.presenter.cast.PopularCastPresenter
import q3_kotlin.popular_libraries.myapplication.retrofit.ImageLoader

class CastRVAdapter(
    private val presenter: PopularCastPresenter,
    val imageLoader: ImageLoader<ShapeableImageView>
) :
    RecyclerView.Adapter<CastRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCastRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemCastRvBinding) :
        RecyclerView.ViewHolder(vb.root), CastItemView {

        override var pos = -1

        override fun setName(text: String) = with(vb) {
            itemCastName.text = text
        }

        override fun setCharacter(hero: String) = with(vb) {
            itemCastCharacter.text = hero
        }

        override fun setImage(img: String) {
            imageLoader.loadInto(img, vb.itemCastImage)
        }

    }

}

