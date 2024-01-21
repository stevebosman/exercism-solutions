import ForthError.ForthError

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.matching.Regex

case class Numeric(value: Int) extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => Right(ForthState(x.stack.push(this)))
      case Left(x) => Left(x)
    }

  override def toString: String = value.toString
}

object Add extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 2 => Left(ForthError.StackUnderflow)
        case _ =>
          (x.stack.pop(), x.stack.pop()) match {
            case (v1: Numeric, v2: Numeric) =>
              Right(ForthState(x.stack.push(Numeric(v1.value + v2.value))))
            case _ =>
              Left(ForthError.InvalidWord)
          }
      }
      case Left(x) => Left(x)
    }
}

object Subtract extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 2 => Left(ForthError.StackUnderflow)
        case _ =>
          (x.stack.pop(), x.stack.pop()) match {
            case (v1: Numeric, v2: Numeric) =>
              Right(ForthState(x.stack.push(Numeric(v2.value - v1.value))))
            case _ =>
              Left(ForthError.InvalidWord)
          }
      }
      case Left(x) => Left(x)
    }
}

object Multiply extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 2 => Left(ForthError.StackUnderflow)
        case _ =>
          (x.stack.pop(), x.stack.pop()) match {
            case (v1: Numeric, v2: Numeric) =>
              Right(ForthState(x.stack.push(Numeric(v1.value * v2.value))))
            case _ =>
              Left(ForthError.InvalidWord)
          }
      }
      case Left(x) => Left(x)
    }
}

object Divide extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 2 => Left(ForthError.StackUnderflow)
        case _ =>
          (x.stack.pop(), x.stack.pop()) match {
            case (v1: Numeric, _: Numeric) if v1.value == 0 =>
              Left(ForthError.DivisionByZero)
            case (v1: Numeric, v2: Numeric) =>
              Right(ForthState(x.stack.push(Numeric(v2.value / v1.value))))
            case _ =>
              Left(ForthError.InvalidWord)
          }
      }
      case Left(x) => Left(x)
    }
}

object Duplicate extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 1 => Left(ForthError.StackUnderflow)
        case _ => Right(ForthState(x.stack.push(x.stack.head)))
      }
      case Left(x) => Left(x)
    }
}

object Drop extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 1 => Left(ForthError.StackUnderflow)
        case _ => Right(ForthState(x.stack.tail))
      }
      case Left(x) => Left(x)
    }
}

object Swap extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 2 => Left(ForthError.StackUnderflow)
        case _ =>
          val v1 = x.stack.pop()
          val v2 = x.stack.pop()
          Right(ForthState(x.stack.push(v1).push(v2)))
      }
      case Left(x) => Left(x)
    }
}

object Over extends Definition {
  override def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state.asInstanceOf[Either[ForthError, ForthState]] match {
      case Right(x) => x.stack match {
        case e if e.length < 2 => Left(ForthError.StackUnderflow)
        case _ => Right(ForthState(x.stack.push(x.stack(1))))
      }
      case Left(x) => Left(x)
    }
}

case class ForthState(stack: mutable.Stack[Definition]) extends ForthEvaluatorState {
  override def toString: String = stack.reverse.mkString(" ")
}

class Forth extends ForthEvaluator {
  private def addUserWordDetails(text: List[String], userWords: Map[String, List[String]] = Map()): Map[String, List[String]] = {
    userWords + (text.head -> text
      .tail
      .filter(e => e != ";")
      .flatMap(e => e match {
        case e if userWords.contains(e) => userWords(e)
        case e => List(e)
      }))
  }

  @tailrec
  private def evalAll(text: List[String],
                      state: Either[ForthError, ForthEvaluatorState] = Right(ForthState(mutable.Stack())),
                      userWords: Map[String, List[String]] = Map()
                     ): Either[ForthError, ForthEvaluatorState] = {
    if (text.isEmpty) return state

    state match {
      case Left(x) => Left(x)
      case Right(_) => text.head.toLowerCase match {
        case s if s.matches("\\d+") => evalAll(text.tail, Numeric(s.toInt).evaluate(state), userWords)
        case s if userWords.contains(s) => evalAll(userWords(s) ++ text.tail, state, userWords)
        case s if s.startsWith(":") && s.length > 1 => evalAll(text.tail, state, addUserWordDetails(s.split(" ").toList.tail, userWords))
        case "+" => evalAll(text.tail, Add.evaluate(state), userWords)
        case "-" => evalAll(text.tail, Subtract.evaluate(state), userWords)
        case "*" => evalAll(text.tail, Multiply.evaluate(state), userWords)
        case "/" => evalAll(text.tail, Divide.evaluate(state), userWords)
        case "dup" => evalAll(text.tail, Duplicate.evaluate(state), userWords)
        case "drop" => evalAll(text.tail, Drop.evaluate(state), userWords)
        case "swap" => evalAll(text.tail, Swap.evaluate(state), userWords)
        case "over" => evalAll(text.tail, Over.evaluate(state), userWords)
        case ";" => evalAll(text.tail, state, userWords)
        case s =>
          println(s)
          Left(ForthError.UnknownWord)
      }
    }
  }

  private val wordPattern: Regex = """: [^\d;]+ [^;]+ ;|[^ ]+""".r

  def eval(text: String): Either[ForthError, ForthEvaluatorState] = {
    evalAll(wordPattern.findAllIn(text).toList)
  }
}
