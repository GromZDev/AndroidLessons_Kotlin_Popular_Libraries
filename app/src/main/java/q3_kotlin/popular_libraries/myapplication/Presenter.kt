package q3_kotlin.popular_libraries.myapplication

class Presenter(val view: MainView) {

    private val model = Model()

    fun counterClickOne() {
        val nextValue = model.next(0)
        view.setButtonTextOne(nextValue.toString())
    }

    fun counterClickTwo() {
        val nextValue = model.next(1)
        view.setButtonTextTwo(nextValue.toString())
    }

    fun counterClickThree() {
        val nextValue = model.next(2)
        view.setButtonTextThree(nextValue.toString())
    }

}
