trait BankAccount {

  def closeAccount(): Unit

  def getBalance: Option[Int]

  def incrementBalance(increment: Int): Option[Int]
}

private class RealAccount extends BankAccount {
  private var open: Boolean = true
  private var balance: Int = 0

  override def closeAccount(): Unit = open = false

  override def getBalance: Option[Int] = if (open) Some(balance) else None

  override def incrementBalance(increment: Int): Option[Int] =
    if (open) {
      this.synchronized {
        balance += increment
      }
      getBalance
    } else None
}

object Bank {
  def openAccount(): BankAccount = new RealAccount()
}

