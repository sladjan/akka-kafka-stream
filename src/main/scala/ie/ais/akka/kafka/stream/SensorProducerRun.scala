package ie.ais.kafka.stream

import akka.actor.ActorSystem
import akka.kafka.{ ProducerMessage, ProducerSettings }
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Sink, Source }
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ ByteArraySerializer, StringSerializer }
import org.slf4j.LoggerFactory._
import scala.util.Random

object SensorProducerRun {

  lazy val logger = getLogger(getClass.getName)

  val config = ConfigFactory.load()
  implicit val system = ActorSystem.create("akka-stream-kafka-producer", config)
  implicit val mat = ActorMaterializer()

  val producerSettings = ProducerSettings(system, new ByteArraySerializer, new StringSerializer)
    .withBootstrapServers("localhost:9092")

  def main(args: Array[String]) {

    println("Sending messages...")
    var time1 = System.currentTimeMillis()
    val random = new Random()

    def createSensor: Sensor_v1 = {
      val id = java.util.UUID.randomUUID.toString
      val weatherstationId = Constants.WeatherstationId(random.nextInt(Constants.NumStations))
      val deviceId = Constants.DeviceId(random.nextInt(Constants.NumIds))
      val eventType = Constants.EventType(random.nextInt(Constants.NumTypes))
      val captureTime = System.currentTimeMillis()
      val dValue = random.nextDouble() //.until(10)
      val sensor = new Sensor_v1(id, weatherstationId, deviceId, eventType, captureTime, dValue)
      sensor
    }

    var record = new ProducerRecord[Array[Byte], Array[Byte]]("topic1", new SensorEncoder(null).toBytes(createSensor))
    var msgNo = 10000
    val done = Source(1 to msgNo)
      .map { n =>
        // val partition = math.abs(n) % 2
        val partition = 0
        val msg = new SensorJson().toJson(createSensor)
        ProducerMessage.Message(new ProducerRecord[Array[Byte], String](
          "topic1", partition, null, msg
        ), n)
      }
      .via(Producer.flow(producerSettings))
      .map { result =>
        val record = result.message.record
        //        println(s"${record.topic}/${record.partition} ${result.offset}: ${record.value}" +
        //          s"(${result.message.passThrough})")
        result
      }
      .runWith(Sink.ignore)

    val time2 = System.currentTimeMillis() - time1
    logger.info(s"Sent $msgNo messages for $time2 miliseconds!")

    Thread sleep 5000
    system.terminate()

  }

}
