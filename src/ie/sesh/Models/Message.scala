package ie.sesh.Models

import java.text.SimpleDateFormat
import java.util.Calendar

import org.springframework.stereotype.Component


@Component
class Message extends Serializable {

  var user_id: Long = _
  var recipient_id: Long = _
  var message_text: String = _
  var time_sent: String = new SimpleDateFormat("dd-mm-yyy HH:mm:ss").format(Calendar.getInstance.getTime)



}
