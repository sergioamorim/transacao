package br.com.zupacademy.sergio.transaction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactionApplicationKt {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<TransactionApplicationKt>(*args)
    }
  }
}
