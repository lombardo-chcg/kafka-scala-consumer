package com.lombardo.app.services

import java.util.Properties

import org.apache.kafka.clients.consumer._
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

class KafkaConsumerService(kafkaHost: String, kafkaPort: String) {
  val logger = LoggerFactory.getLogger(getClass)
  val props = new Properties()

  props.put("bootstrap.servers", s"$kafkaHost:$kafkaPort")
  props.put("group.id", "readings-consumers")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  val topics = List("readings").asJava
  val subscribed = true

  val consumer = new KafkaConsumer[String, String](props)
  consumer.subscribe(topics)

  logger.info(s"kafka consumer group initialized.  topic(s): ${topics.toString}.  Waiting for records...")

  def processStream() = {
    while (subscribed) {
      val records = consumer.poll(100)

      for (record <- records.asScala) {
        val key = record.key
        val input = record.value.toInt
        val output = input * input

        logger.info(s"key: $key / input: $input / output: $output")
      }
    }
  }
}
