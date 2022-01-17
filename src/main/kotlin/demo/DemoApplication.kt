package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ComponentScan("demo")
@EntityScan("demo.CurrencyRepository")
@EnableJpaRepositories("demo.CurrencyRepository")
@EnableScheduling
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
