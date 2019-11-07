package ie.sesh.Models.Impl

import java.sql.{PreparedStatement, ResultSet, Timestamp}
import java.util
import java.util.ArrayList

import scala.collection.JavaConverters._
import ie.sesh.Models.{Message, MessageDAO}
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.{JdbcTemplate, PreparedStatementSetter, RowMapper}
import org.springframework.stereotype.Component


@Component
class MessageDAOImpl @Autowired() (val dataSource: DataSource) extends MessageDAO {

  private val jdbcTemplate = new JdbcTemplate(dataSource)

  def createMessage(message: Message) {

    jdbcTemplate.update("INSERT INTO messages (user_id,recipient_id,message_text) VALUES(?,?,?)",
      (ps: PreparedStatement) => {
        ps.setLong(1, message.user_id)
        ps.setLong(2, message.recipient_id)
        ps.setString(3, message.message_text)
      })
  }

  def sendMessage(from_user_id: Long, recipient_id: Long){}

  def getRecentMessages(conv_id: Long): AnyRef ={

    val messages = new ArrayList[Message]

      val messageList: util.List[util.Map[String, AnyRef]] = jdbcTemplate.queryForList("SELECT * FROM messages WHERE conv_id = ?", java.lang.Long.valueOf(conv_id))
      val m: Message = new Message

      for(mList <- messageList.asScala){
        m.message_text = mList.get("message_text").asInstanceOf[String]
        m.recipient_id = mList.get("recipient_id").asInstanceOf[Long]
        m.user_id = mList.get("user_id").asInstanceOf[Long]
        m.time_sent = mList.get("time_sent").asInstanceOf[Timestamp].toString

        messages.add(m)
      }

    return messages
  }

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