package demo.CurrencyRepository.bean

import org.hibernate.annotations.UpdateTimestamp
import javax.persistence.Table;
import java.util.*
import javax.persistence.*

@Entity
@Table(name="currency")
class CurrencyEntity(
    @Id
    @Column(nullable = false)
    var alias: String,

    @Column()
    var rate: Double,

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateddate")
    val updatedDate: Date?

    )