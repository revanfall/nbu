package demo.CurrencyRepository

import demo.CurrencyRepository.bean.CurrencyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface CurrencyRepository: JpaRepository<CurrencyEntity, String> {

}