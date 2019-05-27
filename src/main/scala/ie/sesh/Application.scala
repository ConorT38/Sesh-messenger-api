package ie.sesh

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}

@SpringBootApplication
class Application

object Application extends App {
  SpringApplication.run(classOf[Application])
}