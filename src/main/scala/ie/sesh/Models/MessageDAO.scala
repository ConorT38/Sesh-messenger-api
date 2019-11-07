package ie.sesh.Models

import java.util

trait MessageDAO {

  def createMessage(message: Message)

  def sendMessage(from_user_id: Long, recipient_id: Long)

  def getRecentMessages(conv_id: Long): AnyRef

}
