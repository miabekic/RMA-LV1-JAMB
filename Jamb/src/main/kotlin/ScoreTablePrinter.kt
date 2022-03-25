class ScoreTablePrinter(private val scoreTable: ScoreTable) {
    fun display(){
        println("${Category.YAMB.name}: ${scoreTable.yamb ?: " "}")
        println("${Category.FULL.name}: ${scoreTable.full ?: " "}")
        println("${Category.SCALE.name}: ${scoreTable.scale ?: " "}")
        println("${Category.POKER.name}: ${scoreTable.poker ?: " "}")
        println("${Category.TRILLING.name}: ${scoreTable.trilling ?: " "}")
        println("UKUPNO: ${scoreTable.result}")
    }

}