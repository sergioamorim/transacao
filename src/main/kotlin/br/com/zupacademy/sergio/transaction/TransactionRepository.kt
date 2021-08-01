package br.com.zupacademy.sergio.transaction

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {
  fun findByCreditCardId(creditCardId: String, pageable: Pageable): Page<Transaction>
}
