package ie.sesh.Controllers

import ie.sesh.Models.Message
import ie.sesh.Services.MessageService
import ie.sesh.Utils.MessageUtils
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(path = Array("/"))
class MessageController(@Autowired val messageService: MessageService,
                        @Autowired val dataSource: DataSource,
                        @Autowired val messageUtils: MessageUtils) {


  /*@GetMapping(path = Array("/recent/messages"))
  def getRecentMessages(): Iterable[Message] = {
    messageService.getRecentMessages
  }*/

  @PostMapping(path = Array("/create/message"))
  def createUser(@RequestBody message_body: String): ResponseEntity[Unit] = {
    val message: Message = messageUtils.buildMessage(message_body)
    val id = messageService.createMessage(message)
    return new ResponseEntity(id, new HttpHeaders, HttpStatus.CREATED)
  }
}




