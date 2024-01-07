class Triangle(var l1:Double, var l2:Double, var l3:Double) {
  def legal: Boolean = {
    var legal = l1 > 0 && l2 > 0 && l3 > 0
    if (legal) {
      val longest = List.apply(l1, l2, l3).max
      legal = longest < (l1 + l2 + l3) - longest
    }
    legal    
  }
  
  def equilateral: Boolean = {
    legal && l1 == l2 && l2 == l3    
  }

  def isosceles: Boolean = {
    legal && l1 == l2 || l1 == l3 || l2 == l3     
  }

  def scalene: Boolean = {
    legal && l1 != l2 && l1 != l3 && l2 != l3    
  }
}

object Triangle {
    def apply(l1:Double, l2:Double, l3:Double): Triangle = {
        new Triangle(l1, l2, l3)
    }
}

