 class ScoreTable {
     var result=0
     var jamb: Int? = null
     set(score){
         if(jamb==null){
             field=score
         }
     }
    var poker: Int? = null
        set(score){
            if(poker==null){
                field=score
            }
        }
    var full: Int? = null
        set(score){
            if(full==null){
                field=score
            }
        }
    var triling: Int? = null
        set(score){
            if(triling==null){
                field=score
            }
        }
    var scale: Int? = null
        set(score){
            if(scale==null){
                field=score
            }
        }

     fun addToResult(score: Int){
         result+=score
     }

     fun isFilled(): Boolean{
         val values= setOf(jamb, scale, triling, poker, full)
         for(value in values){
             if(value==null){
                 return false
             }
         }
         return true
     }

}