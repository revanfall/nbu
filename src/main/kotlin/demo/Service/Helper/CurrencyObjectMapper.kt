package demo.Service.Helper

import demo.CurrencyRepository.bean.CurrencyEntity
import demo.Service.CurrencyBO
import kotlin.streams.toList

class CurrencyObjectMapper {

    companion object {
        fun mapToBO(toMap: List<CurrencyEntity>): List<CurrencyBO> {
            return toMap.stream().map { e -> mapToBO(e) }.toList()
        }

        fun mapToBO(toMap: CurrencyEntity): CurrencyBO {
            return CurrencyBO(toMap.alias, toMap.rate, toMap.updatedDate)
        }

        fun mapToEntity(toMap: List<CurrencyBO>): List<CurrencyEntity> {
            return toMap.stream().map { e -> mapToEntity(e) }.toList()
        }

        fun mapToEntity(toMap: CurrencyBO): CurrencyEntity {
            return CurrencyEntity(toMap.alias, toMap.rate, toMap.updated)
        }
    }
}