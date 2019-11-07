package ie.sesh.Services

import java.util

import ie.sesh.Models.{Message, MessageDAO}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageService @Autowired() (val messageDAO: MessageDAO)  {

  def createMessage(message: Message){messageDAO.createMessage(message)}

  def sendMessage(from_user_id: Long, recipient_id: Long){}

  def getRecentMessages(conv_id: Long): AnyRef = { return messageDAO.getRecentMessages(conv_id)}
}

