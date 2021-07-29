package q3_kotlin.popular_libraries.myapplication

class Presenter(val view: MainView) {
    private val model = Model()

    //Архитектурная ошибка. В качестве практического задания - исправить
    fun counterClick(id: Int) {
        when (id) {
            R.id.button_one -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            R.id.button_two -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            R.id.button_three -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }
}