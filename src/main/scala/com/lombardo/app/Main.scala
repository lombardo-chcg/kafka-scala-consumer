package com.lombardo.app

import ch.qos.logback.classic.{Level,Logger}
import org.slf4j.LoggerFactory

import com.lombardo.app.services.KafkaConsumerService

object Main {
  def main(args: Array[String]) {
    LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[Logger].setLevel(Level.INFO)

    val logger = LoggerFactory.getLogger(getClass)
    logger.info("Hello from sbt starter pack!")

    val kafkaHost = sys.env("KAFKA_HOST")
    val kafkaPort = sys.env("KAFKA_PORT")
    val consumer = new KafkaConsumerService(kafkaHost, kafkaPort)

    consumer.processStream()
  }
}
