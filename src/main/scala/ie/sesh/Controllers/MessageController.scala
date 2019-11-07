package ie.sesh.Controllers

import java.util

import ie.sesh.Models.Message
import ie.sesh.Services.MessageService
import ie.sesh.Utils.MessageUtils
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(path = Array("/api/messenger"))
class MessageController(@Autowired val messageService: MessageService,
                        @Autowired val dataSource: DataSource,
                        @Autowired val messageUtils: MessageUtils) {


  @GetMapping(path = Array("/recent/{conv_id}"))
  def getRecentMessages(@PathVariable conv_id: Long) : ResponseEntity[String] = {
    try{
      return new ResponseEntity(messageUtils.buildJSON(messageService.getRecentMessages(conv_id).asInstanceOf[util.ArrayList[Message]]), new HttpHeaders, HttpStatus.OK)
    }catch {
      case e: Exception => return new ResponseEntity("Couldn't get messages", new HttpHeaders, HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PostMapping(path = Array(""))
  def createMessage(@RequestBody message_body: String): ResponseEntity[String] = {
    val message: Message = messageUtils.buildMessage(message_body)
    try {
      messageService.createMessage(message)
      return new ResponseEntity("Message sent",new HttpHeaders, HttpStatus.CREATED)
    } catch {
      case e: Exception => return new ResponseEntity(e.getMessage, new HttpHeaders, HttpStatus.INTERNAL_SERVER_ERROR)    }
  }
}




