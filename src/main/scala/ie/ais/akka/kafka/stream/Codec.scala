package ie.ais.kafka.stream

/**
 * Created by sk001 on 11/02/2017.
 */
import com.novus.salat
import com.novus.salat.global._
import kafka.serializer.{ Decoder, Encoder }
import kafka.utils.VerifiableProperties
import org.apache.commons.io.Charsets

// encode and decode logs in JSON (in this tuto for readability purpose) but it would be better to consider something like AVRO or protobuf)
class SensorDecoder(props: VerifiableProperties) extends Decoder[Sensor_v1] {
  def fromBytes(bytes: Array[Byte]): Sensor_v1 = {
    salat.grater[Sensor_v1].fromJSON(new String(bytes, Charsets.UTF_8))
  }
}

class SensorEncoder(props: VerifiableProperties) extends Encoder[Sensor_v1] {
  def toBytes(sensor_v1: Sensor_v1): Array[Byte] = {
    salat.grater[Sensor_v1].toCompactJSON(sensor_v1).getBytes(Charsets.UTF_8)
  }
}

class SensorJson() {
  def toJson(sensor_v1: Sensor_v1): String = {
    salat.grater[Sensor_v1].toCompactJSON(sensor_v1).toString
  }
}
