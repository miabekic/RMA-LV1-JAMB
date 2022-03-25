class ScoreTablePrinter(private val scoreTable: ScoreTable) {
    fun display(){
        println("${Values.JAMB}: ${scoreTable.jamb ?: " "}")
        println("${Values.FULL}: ${scoreTable.full ?: " "}")
        println("${Values.SCALE}: ${scoreTable.scale ?: " "}")
        println("${Values.POKER}: ${scoreTable.poker ?: " "}")
        println("${Values.TRILING}: ${scoreTable.triling ?: " "}")
        println("UKUPNO: ${scoreTable.result}")
    }

}