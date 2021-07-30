package br.com.zupacademy.sergio.transaction

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConfiguration @Autowired constructor(
  private val kafkaProperties: KafkaProperties
) {

  @Bean
  fun transactionConsumerFactory(): ConsumerFactory<String, TransactionDto> =
    DefaultKafkaConsumerFactory(
      mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to this.kafkaProperties.bootstrapServers,
        ConsumerConfig.GROUP_ID_CONFIG to this.kafkaProperties.consumer.groupId,
        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to this.kafkaProperties.consumer.autoOffsetReset
      ),
      StringDeserializer(),
      TransactionDtoDeserializer()
    )

  @Bean
  fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TransactionDto> {
    val listenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, TransactionDto>()
    listenerContainerFactory.consumerFactory = this.transactionConsumerFactory()
    return listenerContainerFactory
  }

}
