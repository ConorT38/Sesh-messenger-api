package ie.sesh.Utils

import java.util

import com.google.gson.Gson
import ie.sesh.Models.Message
import org.springframework.stereotype.Component
import org.json4s.DefaultFormats
import org.json4s.jackson.parseJson
import scala.collection.JavaConverters._

@Component
class MessageUtils {

  def buildMessage(message_body: String): Message ={

    implicit val formats = DefaultFormats
    val message: Message = new Message

    val message_json = parseJson(message_body).values.asInstanceOf[Map[String,AnyRef]]
    System.out.println(message_json.get("user_id").get)

    message.message_text = message_json.get("message_text").get.toString
    message.recipient_id = message_json.get("recipient_id").get.asInstanceOf[BigInt].toLong
    message.user_id = message_json.get("user_id").get.asInstanceOf[BigInt].toLong

    return message

  }

  def buildJSON(messageList: util.ArrayList[Message]): String = {
    return new Gson().toJson(messageList)

  }

}
