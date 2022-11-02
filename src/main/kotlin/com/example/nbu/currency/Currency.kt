package com.example.nbu.currency


import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="currency")
class Currency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val Id:Int,
    @Column(name="currency_code")
    val currencyCode: String){

    override fun toString(): String {
        return "Currency(Id=$Id, currencyCode='$currencyCode')"
    }

}