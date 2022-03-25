
fun main(){
    val die1=Die()
    val die2=Die()
    val die3=Die()
    val die4=Die()
    val die5=Die()
    val die6=Die()
    val dice=listOf(die1, die2, die3, die4, die5, die6)
    var rolledDiceValues = mutableListOf<Int>()
    var chosenFunctionalityNumber: Int?
    var rollCounter=0
    var numberOfPlayers: Int?
    var nameOfPlayer: String?
    val players= mutableListOf<Player>()
    var playerCounter=0
    val diceRoller=DiceRoller(dice)
    val diceLocker=DiceLocker(dice)
    val diceUnlocker=DiceUnlocker(dice)
    var dicePositionNumbers: List<Int>?

    do{
        print("Unesite broj igrača: ")
        numberOfPlayers = try { readLine()?.toInt() } catch (e: NumberFormatException) { null }
    }while(numberOfPlayers==null)

    while(playerCounter!=numberOfPlayers){
        playerCounter++
        do{
            print("Unesite ime $playerCounter. igrača: ")
            nameOfPlayer= readLine()
        } while (nameOfPlayer.isNullOrEmpty() || nameOfPlayer.any { it.isDigit() })
        players.add(Player(nameOfPlayer))
    }

    var availableFunctionalities: String
    var options: MutableList<Char>
    while(!players.all { it.scoreTable.isFilled() }){
        for(player in players){
            do{

                availableFunctionalities = when (rollCounter) {
                    0 -> {
                        "Baci kockice->1 (${player.name})"
                    }
                    1, 2 -> {
                        "Baci kockice->1, Provjeri rezultat bacanja->2, Zaključaj kockice->3, Otključaj kockice->4, Unesi u tablicu->5, Prikaži tablicu->6"
                    }
                    else -> "Provjeri rezultat bacanja->2,  Unesi u tablicu->5, Prikaži tablicu->6"
                }
                do{
                    println(availableFunctionalities)
                    options=availableFunctionalities.filter{it.isDigit()}.toMutableList()
                    chosenFunctionalityNumber = try { readLine()?.toInt() } catch (e: NumberFormatException) { null }
                }while(chosenFunctionalityNumber?.digitToChar() !in options)
                    when (chosenFunctionalityNumber) {
                        1 -> {
                            rolledDiceValues = diceRoller.rollDice()
                            println("Vrijednosti kockica: ${rolledDiceValues.toString().replace("[", "").replace("]", "")}")
                            rollCounter += 1
                        }
                        2 -> {
                            val result: Int
                            var chosenOptionNumber: Int?
                            do{
                                println("${Values.SCALE}->1, ${Values.FULL}->2, ${Values.TRILING}->3, ${Values.POKER}->4, ${Values.JAMB}->5")
                                chosenOptionNumber = try { readLine()?.toInt() } catch (e: NumberFormatException) { null }
                            }while(chosenOptionNumber==null || chosenOptionNumber !in 1..5)

                            when (chosenOptionNumber) {
                                1 -> {
                                    result=Values.SCALE.calculate(rolledDiceValues)
                                    println("${Values.SCALE}: $result")
                                }
                                2 -> {
                                    result=Values.FULL.calculate(rolledDiceValues)
                                    println("${Values.FULL}: $result")
                                }
                                3 -> {
                                    result=Values.TRILING.calculate(rolledDiceValues)
                                    println("${Values.TRILING}: $result")
                                }
                                4 -> {
                                    result=Values.POKER.calculate(rolledDiceValues)
                                    println("${Values.POKER}: $result")
                                }
                                5 -> {
                                    result = Values.JAMB.calculate(rolledDiceValues)
                                    println("${Values.JAMB}: $result")
                                }
                            }
                        }
                        3 -> {

                            var isEveryPositionCorrect: Boolean?
                            do{
                                println("Unesite pozicijske brojeve kockica koje želite zaključati; pr. 1,2,3 ili 5,2,1,6")
                                dicePositionNumbers=try { readLine()?.split(',')?.map{ it.toInt()} } catch (e: NumberFormatException) { null }
                                isEveryPositionCorrect=dicePositionNumbers?.all { it in 1..6 }
                            } while (dicePositionNumbers==null || isEveryPositionCorrect!=true )
                            diceLocker.lockDice(dicePositionNumbers)

                        }
                        4 -> {
                            var isEveryPositionCorrect: Boolean?
                            do{
                                println("Unesite pozicijske brojeve kockica koje želite otključati; pr. 1,2,3 ili 5,2,1,6")
                                dicePositionNumbers=try { readLine()?.split(',')?.map{ it.toInt()} } catch (e: NumberFormatException) { null }
                                isEveryPositionCorrect=dicePositionNumbers?.all { it in 1..6 }
                            } while (dicePositionNumbers==null || isEveryPositionCorrect!=true)
                            diceUnlocker.unlockDice(dicePositionNumbers)
                        }
                        5 -> {
                            var chosenOptionNumber: Int?
                            var s = mapOf<String, Int>()
                            if(player.scoreTable.jamb==null){
                                s=s+Pair("${Values.JAMB}", 1)

                            }
                            if(player.scoreTable.full==null){
                                s=s+Pair("${Values.FULL}", 2)

                            }
                            if(player.scoreTable.scale==null){
                                s=s+Pair("${Values.SCALE}", 3)

                            }
                            if(player.scoreTable.poker==null){
                                s=s+Pair("${Values.POKER}", 4)
                            }
                            if(player.scoreTable.triling==null){
                                s=s+Pair("${Values.TRILING}", 5)
                            }
                            val calculatedValue: Int
                            do{
                                println(s.toString().replace("{", "").replace("}", "").replace("=", "->"))
                                chosenOptionNumber = try { readLine()?.toInt() } catch (e: NumberFormatException) { null }
                            } while(!s.values.toMutableList().contains(chosenOptionNumber))
                            when (chosenOptionNumber) {
                                1 -> {
                                    calculatedValue=Values.JAMB.calculate(rolledDiceValues)
                                    player.scoreTable.jamb=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                2 -> {
                                    calculatedValue=Values.FULL.calculate(rolledDiceValues)
                                    player.scoreTable.full=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                3 -> {
                                    calculatedValue=Values.SCALE.calculate(rolledDiceValues)
                                    player.scoreTable.scale=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                4 -> {
                                    calculatedValue=Values.POKER.calculate(rolledDiceValues)
                                    player.scoreTable.poker=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                5 -> {
                                    calculatedValue=Values.TRILING.calculate(rolledDiceValues)
                                    player.scoreTable.triling=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                            }
                        }
                        6 -> {
                            ScoreTablePrinter(player.scoreTable).display()
                        }
                    }
                }while(chosenFunctionalityNumber!=5 && rollCounter <= 3)
            rollCounter=0
            dicePositionNumbers= listOf(1,2,3,4,5,6)
            diceUnlocker.unlockDice(dicePositionNumbers)
            println("Vaš trenutni rezultat je: ${player.scoreTable.result}")
        }
    }
    var results= mapOf<String, Int>()
    for (player in players){
        results=results+Pair(player.name, player.scoreTable.result)
    }
    val maxResult=results.maxByOrNull { it.value }
    println("Pobjednik je: ${maxResult?.key}, s ${maxResult?.value} bodova")

}
