package wordCount
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
// version 2.0
object WordCount {
  def main(args: Array[String]) = {
     
    print ("pgm running!")
    //Start the Spark context
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local")
    val sc = new SparkContext(conf)

    //Read some example file to a test RDD
    val test = sc.textFile("src/main/resources/input.txt")

    test.flatMap { line => //for each line
      line.split(" ") //split the line in word by word.
    }
      .map { word => //for each word
        (word, 1) //Return a key/value tuple, with the word as key and 1 as value
      }
      .reduceByKey(_ + _) //Sum all of the value with same key
      .saveAsTextFile("src/main/resources/output") //Save to a text file to this directory 

    //Stop the Spark context
    sc.stop
  }
}
