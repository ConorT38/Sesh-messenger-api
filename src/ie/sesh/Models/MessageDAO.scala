package ie.sesh.Models

trait MessageDAO {

  def createMessage(message: Message)

  def sendMessage(from_user_id: Long, recipient_id: Long)

}
