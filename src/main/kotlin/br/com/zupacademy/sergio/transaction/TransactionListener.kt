package br.com.zupacademy.sergio.transaction

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class TransactionListener {

  private val logger: Logger = LoggerFactory.getLogger(javaClass)

  @KafkaListener(topics = ["\${spring.kafka.transactions-topic}"])
  fun listen(transactionDto: TransactionDto) {
    this.logger.info("Consumed $transactionDto")
  }

}
