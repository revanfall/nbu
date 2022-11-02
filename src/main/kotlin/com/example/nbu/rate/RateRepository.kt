package com.example.nbu.rate

import org.springframework.data.jpa.repository.JpaRepository

interface RateRepository:JpaRepository<Rate, Int> {

}