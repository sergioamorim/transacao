package br.com.zupacademy.sergio.transaction

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CreditCard(@Id val id: String, val email: String)
