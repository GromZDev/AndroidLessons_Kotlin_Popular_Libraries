package q3_kotlin.popular_libraries.myapplication.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import q3_kotlin.popular_libraries.myapplication.Converter
import q3_kotlin.popular_libraries.myapplication.R
import q3_kotlin.popular_libraries.myapplication.databinding.FragmentConverterBinding
import q3_kotlin.popular_libraries.myapplication.model.Image
import q3_kotlin.popular_libraries.myapplication.presenter.ImagePresenter
import q3_kotlin.popular_libraries.myapplication.presenter.ImageView

class ImageConverterFragment : MvpAppCompatFragment(), ImageView {

    companion object {
        fun newInstance() = ImageConverterFragment()
    }

    private val presenter: ImagePresenter by moxyPresenter {
        ImagePresenter(
            AndroidSchedulers.mainThread(),
            Converter(context)
        )
    }

    private var vb: FragmentConverterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentConverterBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb?.buttonDoConvert?.setOnClickListener {
            presenter.doConversion()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun makeConversion() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 44)
    }

    private var convertDialog: Dialog? = null
    override fun inProgress() {
        vb?.mainFragmentLoadingLayout?.visibility = View.VISIBLE
        context?.let {
            convertDialog = AlertDialog.Builder(it)
                .setMessage(getString(R.string.in_progress))
                .setNegativeButton(R.string.cancel) { _, _ ->
                    presenter.convertCancel()
                }


                .create()
            convertDialog?.show()

        }
    }

    override fun closeProgress() {
        vb?.mainFragmentLoadingLayout?.visibility = View.GONE
        convertDialog?.dismiss()
    }

    override fun onSuccess(newImageInBytes: ByteArray) {
        vb?.mainFragmentLoadingLayout?.visibility = View.GONE
        val newImage = BitmapFactory.decodeByteArray(newImageInBytes, 0, newImageInBytes.lastIndex)
        vb?.imageView?.setImageBitmap(newImage)
        Toast.makeText(context, getString(R.string.on_success), Toast.LENGTH_LONG).show()
    }

    override fun onCancel() {
        Toast.makeText(context, getString(R.string.on_cancel), Toast.LENGTH_LONG).show()
    }

    override fun onError() {
        Toast.makeText(context, getString(R.string.on_error), Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 44) {
            if (resultCode == Activity.RESULT_OK) {
                data?.data?.let { uri ->
                    val bytes = context?.contentResolver?.openInputStream(uri)?.buffered()?.use {
                        it.readBytes()
                    }
                    bytes.let {
                        presenter.imageSelected(Image(bytes))
                    }
                }
            }
        }
    }


}