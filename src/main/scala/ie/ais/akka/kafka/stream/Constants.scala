package ie.ais.kafka.stream

object Constants {
  val NumPublishers = 5
  val NumAdvertisers = 3
  val NumStations = 1000
  val NumTypes = 10
  val NumIds = 100

  val Publishers = (0 to NumPublishers).map("publisher_" +)
  val Advertisers = (0 to NumAdvertisers).map("advertiser_" +)
  val UnknownGeo = "unknown"
  val Geos = Seq("NY", "CA", "FL", "MI", "HI", UnknownGeo)
  val NumWebsites = 10000
  val NumCookies = 10000

  val WeatherstationId = (0 to NumStations).map("weatherstationid_" +)
  val DeviceType = (0 to NumTypes).map("deviceType_" +)
  val DeviceId = (0 to NumIds).map("deviceId_" +)
  val DeviceAgent = (0 to NumTypes).map("deviceAgent_" +)
  val EventType = (0 to NumTypes).map("eventType_" +)
  val Description = (0 to NumTypes).map("description_" +)

  val KafkaTopic = "sensor-topic"
}