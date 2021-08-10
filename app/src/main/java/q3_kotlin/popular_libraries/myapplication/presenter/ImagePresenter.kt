package q3_kotlin.popular_libraries.myapplication.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import q3_kotlin.popular_libraries.myapplication.model.Image
import q3_kotlin.popular_libraries.myapplication.model.ImageConverter

class ImagePresenter(private val uiScheduler: Scheduler, private val converter: ImageConverter) :
    MvpPresenter<ImageView>() {

    private var disposable: Disposable? = null

    fun doConversion() {
        viewState.makeConversion()
    }

    fun imageSelected(image: Image) {
        viewState.inProgress()
        val newImageBytes = image.data
        disposable = converter.convert(image)
            .observeOn(uiScheduler)
            .subscribe({
                viewState.closeProgress()
                if (newImageBytes != null) {
                    viewState.onSuccess(newImageBytes)
                }
            }, {
                viewState.closeProgress()
                viewState.onError()
            })
    }

    fun convertCancel() {
        disposable?.dispose()
        viewState.closeProgress()
        viewState.onCancel()
    }

}
