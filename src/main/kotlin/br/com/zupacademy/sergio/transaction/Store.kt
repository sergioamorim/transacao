package br.com.zupacademy.sergio.transaction

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Store(val name: String, val city: String, val address: String) {
  @Id
  @GeneratedValue
  private var id: Long? = null
}

class StoreDetail(store: Store) {
  val name: String = store.name
  val city: String = store.city
  val address: String = store.address
}
