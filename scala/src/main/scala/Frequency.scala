import java.util.concurrent.Executors
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService, Future}

object Frequency {
  private def analyze(s: String): Map[Char, Int] =
    s.distinct.filter(c => c.isLetter).map(n => n -> s.count(c => c == n)).toMap

  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] = {
    implicit val context: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(numWorkers))

    def mergeResults(a: Map[Char, Int], b: Map[Char, Int]): Map[Char, Int] =
      (a.keySet ++ b.keySet).map(key => (key, a.getOrElse(key, 0) + b.getOrElse(key, 0))).toMap

    def mergeAll(seq: Seq[Map[Char, Int]]): Map[Char, Int] =
      seq.foldLeft(Map[Char, Int]())((a, b) => mergeResults(a, b))

    Await.result(
      Future.sequence(
          texts.map(line => Future(analyze(line.toLowerCase)))).map(seq => mergeAll(seq)),
      1.second)
  }
}
