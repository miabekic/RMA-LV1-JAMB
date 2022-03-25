class DiceLocker(private val dice: List<Die>) {

    fun lockDice(dicePositionNumber: List<Int>){
        for(positionNumber in dicePositionNumber){
            when(positionNumber){
                1 -> dice[0].lock()
                2 -> dice[1].lock()
                3 -> dice[2].lock()
                4 -> dice[3].lock()
                5 -> dice[4].lock()
                6 -> dice[5].lock()
            }
        }
    }
}