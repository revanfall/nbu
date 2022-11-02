package com.example.nbu.rate

import java.time.LocalDate
import javax.persistence.*

@Entity
class Rate(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id:Int,
        @Column(name="alias")
        var alias:String,
        @Column(name="rate")
        var rate:Double,
        @Column(name="date")
        var date: LocalDate
) {

    constructor( alias: String, rate: Double, date: LocalDate):this(0,alias, rate, date)

    override fun toString(): String {
        return "Currency(id=$id, currencyCode='$alias', rate=$rate, date=$date)"
    }

}