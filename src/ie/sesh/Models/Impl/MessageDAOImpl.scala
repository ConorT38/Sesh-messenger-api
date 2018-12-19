package ie.sesh.Models.Impl

import java.sql.ResultSet

import ie.sesh.Models.{Message, MessageDAO}
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}
import org.springframework.stereotype.Component
import collection.JavaConversions._


@Component
class MessageDAOImpl @Autowired() (val dataSource: DataSource) extends MessageDAO {

  private val jdbcTemplate = new JdbcTemplate(dataSource)
  private val insertMessage = new SimpleJdbcInsert(dataSource).withTableName("messages").usingGeneratedKeyColumns("id")

  def createMessage(message: Message) {

    val parameters: Map[String, AnyRef] = Map("message_text" -> message.message_text,
                                              "user_id" -> java.lang.Long.valueOf(message.user_id),
                                              "recipient_id" -> java.lang.Long.valueOf(message.recipient_id),
                                              "time_sent" -> message.time_sent)

    //Need to import collection.JavaConversions._ to make this line work
    val id = insertMessage.executeAndReturnKey(parameters)
    id.longValue()

    return true

  }

  def sendMessage(from_user_id: Long, recipient_id: Long){}


}

object MessageMapper extends RowMapper[Message] {
  def mapRow(rs: ResultSet, rowNum: Int): Message = {

    val message = new Message()

    val user_id = rs.getLong("user_id")
    val recipient_id = rs.getLong("recipient_id")
    val message_text = rs.getString("message_text")

    message.user_id = user_id
    message.recipient_id = recipient_id
    message.message_text = message_text

    return message
  }
}