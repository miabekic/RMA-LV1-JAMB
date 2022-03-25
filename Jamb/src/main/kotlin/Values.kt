enum class Values : ICalculator {
    TRILING{
        override fun calculate(diceValues: MutableList<Int>): Int {
            var result = 0
            val sameValues=diceValues.groupingBy { it }.eachCount().filterValues { it>2 }
            return if(sameValues.values.size>1){
                val sameValuesMax=sameValues.maxByOrNull { it.key }
                result=sameValuesMax!!.key*3+20
                result
            } else if(sameValues.values.size==1){
                result=sameValues.keys.elementAt(0)*3+20
                result
            } else result
        }
    },
    SCALE{
        override fun calculate(diceValues: MutableList<Int>): Int {
            var result=0
            val smallScale= setOf(1,2,3,4,5)
            val bigScale= setOf(2,3,4,5,6)
            return if(diceValues.containsAll(smallScale)) {
                result=30
                result
            } else if(diceValues.containsAll(bigScale)){
                result=40
                result
            } else result
        }
         },
    FULL {
        override fun calculate(diceValues: MutableList<Int>): Int {
            var result = 0
            val sameValues=diceValues.groupingBy { it }.eachCount()
            val sameThreeValues=sameValues.filterValues { it in 3..4 }
            return if(sameThreeValues.values.size>1){
                val sameValuesMax= sameThreeValues.maxOf { it.key }
                val sameValuesMin= sameThreeValues.minOf { it.key }
                result=sameValuesMax*3+sameValuesMin*2+30
                result
            } else if(sameThreeValues.values.size==1){
                val sameTwoValues=sameValues.filterValues { it>=2 } - sameThreeValues.keys.elementAt(0)
                if(sameTwoValues.values.size==1){
                result=sameTwoValues.keys.elementAt(0)*2+sameThreeValues.keys.elementAt(0)*3+30
                }
                result
            } else result
        }
    },
    POKER {
        override fun calculate(diceValues: MutableList<Int>): Int {
            var result = 0
            val sameValues=diceValues.groupingBy { it }.eachCount().filterValues { it==4 }
            return if(sameValues.values.size==1){
                result=sameValues.keys.elementAt(0)*4+40
                result
            } else result
        }
    },
    JAMB {
        override fun calculate(diceValues: MutableList<Int>): Int {
            var result = 0
            val sameValues=diceValues.groupingBy { it }.eachCount().filterValues { it==5 }
            return if(sameValues.values.size==1){
                result=sameValues.keys.elementAt(0)*5+50
                result
            } else result
        }
    }
}