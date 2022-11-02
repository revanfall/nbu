package com.example.nbu.currency

import org.springframework.data.jpa.repository.JpaRepository

interface CurrencyRepository:JpaRepository<Currency, Int> {
}