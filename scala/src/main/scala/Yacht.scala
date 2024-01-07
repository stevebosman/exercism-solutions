import scala.collection.immutable.ListMap

object Yacht {

  def score(dices: List[Int], category: String): Int = {
    (category) match {
      case "ones" => dices.filter(_==1).length
      case "twos" => dices.filter(_==2).length * 2
      case "threes" => dices.filter(_==3).length * 3
      case "fours" => dices.filter(_==4).length * 4
      case "fives" => dices.filter(_==5).length * 5
      case "sixes" => dices.filter(_==6).length * 6
      case "full house" => {
        val diceGrouping = dices.groupBy(x=>x)
        if ( diceGrouping.size == 2 && diceGrouping.filter(_._2.length==3).size == 1) dices.foldLeft(0)(_ + _) else 0
      }
      case "four of a kind" => {
        val diceGrouping = dices.groupBy(x=>x)
        if ( diceGrouping.size <= 2 ) {
          val commonDice = diceGrouping.filter(_._2.length>=4)
          if (commonDice.size == 1) {
            4 * commonDice.head._1 
          } else {
            0
          }
        } else {
          0
        }
      }
      case "little straight" => if (List(1,2,3,4,5).filterNot(dices.contains(_)).length == 0) 30 else 0
      case "big straight" => if (List(2,3,4,5,6).filterNot(dices.contains(_)).length == 0) 30 else 0
      case "choice" => dices.foldLeft(0)(_ + _)
      case "yacht" => if ( dices.distinct.length == 1 ) 50 else 0
      case "_" => 0
    }
    
  }
}
