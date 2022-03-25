
fun main(){
    val firstDie=Die()
    val secondDie=Die()
    val thirdDie=Die()
    val fourthDie=Die()
    val fifthDie=Die()
    val sixthDie=Die()
    val dice=listOf(firstDie, secondDie, thirdDie, fourthDie, fifthDie, sixthDie)
    var rolledDiceValues = mutableListOf<Int>()
    var chosenFunctionalityNumber: Int?
    var rollCounter=0
    var numberOfPlayers: Int?
    var playerName: String?
    val players= mutableListOf<Player>()
    var playerCounter=0
    val diceRoller=DiceRoller(dice)
    val diceLocker=DiceLocker(dice)
    val diceUnlocker=DiceUnlocker(dice)
    val positions=listOf(1,2,3,4,5,6)
    var dicePositionNumbers: List<Int>?
    val readNumber: () -> Int? = {try { readLine()?.toInt() } catch (e: NumberFormatException) { null }}
    val readPositionNumbers: () -> List<Int>? = {try { readLine()?.split(',')?.map{ it.toInt()} } catch (e: NumberFormatException) { null } }
    val isEveryPositionCorrect: (List<Int>?) -> Boolean? = {dicePositionNumbers: List<Int>? -> dicePositionNumbers?.all { it in positions }}

    do{
        print("Unesite broj igrača: ")
        numberOfPlayers = readNumber()
    }while(numberOfPlayers==null || numberOfPlayers<=0)

    while(playerCounter!=numberOfPlayers){
        playerCounter++
        do{
            print("Unesite ime $playerCounter. igrača: ")
            playerName= readLine()
        } while (playerName.isNullOrEmpty())
        players.add(Player(playerName))
    }

    var availableFunctionalities: String
    var options: MutableList<Char>
    val categories="${Category.TRILLING.name}->${Category.TRILLING.ordinal}, ${Category.SCALE.name}->${Category.SCALE.ordinal}, " +
            "${Category.FULL.name}->${Category.FULL.ordinal}, ${Category.POKER.name}->${Category.POKER.ordinal}," +
            " ${Category.YAMB.name}->${Category.YAMB.ordinal}"
    var chosenOptionNumber: Int?

    var calculatedValue: Int


    while(!players.all { it.scoreTable.isFilled() }){
        for(player in players){
            println("Na potezu je igrač: ${player.name}")
            do{
                availableFunctionalities = when (rollCounter) {
                    0 -> {
                        "Baci kockice->1"
                    }
                    1, 2 -> {
                        "Baci kockice->1, Provjeri rezultat bacanja->2, Zaključaj kockice->3, Otključaj kockice->4, Unesi u tablicu->5, Prikaži tablicu->6"
                    }
                    else -> "Provjeri rezultat bacanja->2, Unesi u tablicu->5, Prikaži tablicu->6"
                }
                do{
                    println(availableFunctionalities)
                    options=availableFunctionalities.filter{it.isDigit()}.toMutableList()
                    chosenFunctionalityNumber = readNumber()
                }while(chosenFunctionalityNumber?.digitToChar() !in options)
                    when (chosenFunctionalityNumber) {
                        1 -> {
                            rolledDiceValues = diceRoller.rollDice()
                            println("Vrijednosti kockica: ${rolledDiceValues.toString().replace("[", "").replace("]", "")}")
                            rollCounter += 1
                        }
                        2 -> {
                            do{
                                println(categories)
                                options=categories.filter{it.isDigit()}.toMutableList()
                                chosenOptionNumber = readNumber()
                            }while(chosenOptionNumber?.digitToChar() !in options)

                            when (chosenOptionNumber) {
                                Category.SCALE.ordinal -> {
                                    calculatedValue=Category.SCALE.calculate(rolledDiceValues)
                                    println("${Category.SCALE.name}: $calculatedValue")
                                }
                                Category.FULL.ordinal -> {
                                    calculatedValue=Category.FULL.calculate(rolledDiceValues)
                                    println("${Category.FULL.name}: $calculatedValue")
                                }
                                Category.TRILLING.ordinal -> {
                                    calculatedValue=Category.TRILLING.calculate(rolledDiceValues)
                                    println("${Category.TRILLING.name}: $calculatedValue")
                                }
                                Category.POKER.ordinal -> {
                                    calculatedValue=Category.POKER.calculate(rolledDiceValues)
                                    println("${Category.POKER.name}: $calculatedValue")
                                }
                                Category.YAMB.ordinal -> {
                                    calculatedValue = Category.YAMB.calculate(rolledDiceValues)
                                    println("${Category.YAMB.name}: $calculatedValue")
                                }
                            }
                        }
                        3 -> {
                            do{
                                println("Unesite pozicijske brojeve kockica koje želite zaključati; pr. 1,2,3 ili 5,2,1,6")
                                dicePositionNumbers=readPositionNumbers()
                            } while (dicePositionNumbers==null || isEveryPositionCorrect(dicePositionNumbers)!=true )
                            diceLocker.lockDice(dicePositionNumbers)

                        }
                        4 -> {
                            do{
                                println("Unesite pozicijske brojeve kockica koje želite otključati; pr. 1,2,3 ili 5,2,1,6")
                                dicePositionNumbers=readPositionNumbers()
                            } while (dicePositionNumbers==null || isEveryPositionCorrect(dicePositionNumbers) != true)
                            diceUnlocker.unlockDice(dicePositionNumbers)
                        }
                        5 -> {
                            var settableCategories = mapOf<String, Int>()
                            if(player.scoreTable.trilling==null){
                                settableCategories=settableCategories+Pair(Category.TRILLING.name, Category.TRILLING.ordinal)
                            }
                            if(player.scoreTable.scale==null){
                                settableCategories=settableCategories+Pair(Category.SCALE.name, Category.SCALE.ordinal)
                            }
                            if(player.scoreTable.full==null){
                                settableCategories=settableCategories+Pair(Category.FULL.name, Category.FULL.ordinal)
                            }
                            if(player.scoreTable.poker==null){
                                settableCategories=settableCategories+Pair(Category.POKER.name, Category.POKER.ordinal)
                            }
                            if(player.scoreTable.yamb==null){
                                settableCategories=settableCategories+Pair(Category.YAMB.name, Category.YAMB.ordinal)

                            }
                            do{
                                println(settableCategories.toString().replace("{", "").replace("}", "").replace("=", "->"))
                                chosenOptionNumber = readNumber()
                            } while(!settableCategories.values.toMutableList().contains(chosenOptionNumber))
                            when (chosenOptionNumber) {
                                Category.YAMB.ordinal -> {
                                    calculatedValue=Category.YAMB.calculate(rolledDiceValues)
                                    player.scoreTable.yamb=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                Category.FULL.ordinal -> {
                                    calculatedValue=Category.FULL.calculate(rolledDiceValues)
                                    player.scoreTable.full=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                Category.SCALE.ordinal -> {
                                    calculatedValue=Category.SCALE.calculate(rolledDiceValues)
                                    player.scoreTable.scale=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                Category.POKER.ordinal -> {
                                    calculatedValue=Category.POKER.calculate(rolledDiceValues)
                                    player.scoreTable.poker=calculatedValue
                                    player.scoreTable.addToResult(calculatedValue)
                                }
                                Category.TRILLING.ordinal -> {
                                    calculatedValue=Category.TRILLING.calculate(rolledDiceValues)
                                    player.scoreTable.trilling=calculatedValue
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
            diceUnlocker.unlockDice(positions)
            println("Vaš trenutni rezultat je: ${player.scoreTable.result}\n")
        }
    }
    var results= mapOf<String, Int>()
    for (player in players){
        results=results+Pair(player.name, player.scoreTable.result)
    }
    val maxResult=results.maxByOrNull { it.value }
    if(results.filter { it.value==maxResult?.value}.size>1){
        println("Nitko nije pobijedio, nerješeno je.")
    }else println("Pobjednik je: ${maxResult?.key}, s ${maxResult?.value} bodova")

}