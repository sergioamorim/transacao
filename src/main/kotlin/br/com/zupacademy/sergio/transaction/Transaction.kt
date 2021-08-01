package br.com.zupacademy.sergio.transaction

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Transaction(
  @Id
  val id: String,

  val price: BigDecimal,

  @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
  val store: Store,

  @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
  val creditCard: CreditCard,

  val timestamp: LocalDateTime
)

class TransactionDetail(transaction: Transaction) {
  val price: BigDecimal = transaction.price
  val store = StoreDetail(transaction.store)
  val timestamp = transaction.timestamp
}
