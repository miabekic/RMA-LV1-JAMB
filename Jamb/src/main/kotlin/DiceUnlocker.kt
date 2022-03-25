class DiceUnlocker(private val dice: List<Die> ) {

    fun unlockDice(dicePositionNumbers: List<Int>){
        for(positionNumber in dicePositionNumbers){
            when(positionNumber) {
                1 -> dice[0].unlock()
                2 -> dice[1].unlock()
                3 -> dice[2].unlock()
                4 -> dice[3].unlock()
                5 -> dice[4].unlock()
                6 -> dice[5].unlock()
            }
        }
    }
}