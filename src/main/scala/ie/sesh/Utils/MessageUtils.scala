package ie.sesh.Utils

import ie.sesh.Models.Message
import org.springframework.stereotype.Component
import org.json4s._
import org.json4s.jackson.JsonMethods._
@Component
class MessageUtils {

  def buildMessage(message_body: String): Message ={

    implicit val formats = DefaultFormats

    val message_json = parse(message_body)
    val message = message_json.extract[Message]

    return message

  }

}
