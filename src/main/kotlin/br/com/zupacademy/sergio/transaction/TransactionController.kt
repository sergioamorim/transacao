package br.com.zupacademy.sergio.transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController @Autowired constructor(
  private val transactionRepository: TransactionRepository
) {

  @GetMapping("/credit-cards/{creditCardId}/transactions")
  fun readTransaction(
    @PathVariable creditCardId: String
  ): ResponseEntity<Page<TransactionDetail>> =
    this.transactionsDetailsPageOrNotFound(
      transactionsPage = this.transactionRepository.findByCreditCardId(
        creditCardId = creditCardId,
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "timestamp"))
      )
    )

  private fun transactionsDetailsPageOrNotFound(
    transactionsPage: Page<Transaction>
  ): ResponseEntity<Page<TransactionDetail>> =
    if (transactionsPage.isEmpty) {
      ResponseEntity.notFound().build()
    } else {
      ResponseEntity.ok(transactionsPage.map { TransactionDetail(it) })
    }

}
