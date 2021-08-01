package br.com.zupacademy.sergio.transaction

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class TransactionListener @Autowired constructor(
  private val transactionRepository: TransactionRepository
) {

  private val logger: Logger = LoggerFactory.getLogger(javaClass)

  @Transactional
  @KafkaListener(topics = ["\${spring.kafka.transactions-topic}"])
  fun listen(transactionDto: TransactionDto) {
    this.logger.info("Consumed $transactionDto")
    this.transactionRepository.save(transactionDto.toTransaction())
  }

}
