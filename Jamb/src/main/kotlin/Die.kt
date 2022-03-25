class Die{

    private val values=listOf(1, 2, 3, 4, 5, 6)
    private var isLocked = false
    private var currentValue=1

    fun roll(){
        if(!isLocked){
            currentValue=values.random()
        }
    }
    fun getCurrentValue(): Int = currentValue

    fun lock(){ isLocked=true }

    fun unlock(){ isLocked=false }

}

