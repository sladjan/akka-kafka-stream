package ie.ais.kafka.stream

case class Sensor_v1(id: String, weatherstation_id: String, device_id: String, event_type: String,
  capture_time: Long, value: Double)
