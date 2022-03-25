class DiceRoller(private val dice: List<Die>) {

    fun rollDice(): MutableList<Int>{
        val diceValues = mutableListOf<Int>()
        for(die in dice){
            die.roll()
            diceValues.add(die.getCurrentValue())
        }
        return diceValues
    }

}