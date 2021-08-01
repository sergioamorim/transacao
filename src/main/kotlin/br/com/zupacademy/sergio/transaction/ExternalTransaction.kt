package br.com.zupacademy.sergio.transaction

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.kafka.common.serialization.Deserializer
import java.beans.ConstructorProperties
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto @ConstructorProperties(
  *["id", "valor", "estabelecimento", "cartao", "efetivadaEm"]
) constructor(
  val id: String,
  val price: BigDecimal,
  val store: StoreDto,
  val creditCard: CreditCardDto,

  @JsonDeserialize(using = LocalDateTimeDeserializer::class)
  val timestamp: LocalDateTime
) {
  fun toTransaction() = Transaction(
    id = this.id,
    price = this.price,
    store = this.store.toStore(),
    creditCard = this.creditCard.toCreditCard(),
    timestamp = this.timestamp
  )
}

data class StoreDto @ConstructorProperties(*["nome", "cidade", "endereco"]) constructor(
  val name: String, val city: String, val address: String
) {
  fun toStore() = Store(name = this.name, city = this.city, address = this.address)
}

data class CreditCardDto @ConstructorProperties(*["id", "email"]) constructor(
  val id: String, val email: String
) {
  fun toCreditCard() = CreditCard(id = this.id, email = this.email)
}

class TransactionDtoDeserializer : Deserializer<TransactionDto> {
  private val objectMapper: ObjectMapper = ObjectMapper()

  override fun deserialize(key: String, value: ByteArray): TransactionDto =
    this.objectMapper.readValue(value)
}
