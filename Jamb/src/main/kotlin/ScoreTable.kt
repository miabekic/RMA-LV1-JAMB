 class ScoreTable {
     var result=0
     var yamb: Int? = null
     set(score){
         if(yamb==null){
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
    var trilling: Int? = null
        set(score){
            if(trilling==null){
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
         val values= setOf(yamb, scale, trilling, poker, full)
         for(value in values){
             if(value==null){
                 return false
             }
         }
         return true
     }

}