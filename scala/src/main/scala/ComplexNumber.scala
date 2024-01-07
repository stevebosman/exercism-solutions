class ComplexNumber(val real:Double, val imaginary:Double) {
  def +(that: ComplexNumber): ComplexNumber = new ComplexNumber(
    this.real + that.real,
    this.imaginary + that.imaginary)
  
  def -(that: ComplexNumber): ComplexNumber = new ComplexNumber(
    this.real - that.real,
    this.imaginary - that.imaginary)
  
  def *(that: ComplexNumber): ComplexNumber = new ComplexNumber(
    this.real * that.real - this.imaginary * that.imaginary,
    this.imaginary * that.real + this.real * that.imaginary,
  )
  
  def /(that: ComplexNumber): ComplexNumber = {
    val size = that.abs2
    new ComplexNumber(
      (this.real * that.real + this.imaginary * that.imaginary) / size,
      (this.imaginary * that.real - this.real * that.imaginary) / size,
    )
  }
  
  def conjugate: ComplexNumber = new ComplexNumber(this.real, -this.imaginary)
  
  def abs2: Double = this.real * this.real + this.imaginary * this.imaginary

  def abs: Double = Math.sqrt(this.abs2)
}

object ComplexNumber {
    def apply(real:Double = 0, imaginary:Double = 0): ComplexNumber = {
        new ComplexNumber(real, imaginary)
    }

    def exp(that: ComplexNumber): ComplexNumber = {
      val size = Math.exp(that.real)
      new ComplexNumber(
        size * Math.cos(that.imaginary), 
        size * Math.sin(that.imaginary)
      )
    }
}

