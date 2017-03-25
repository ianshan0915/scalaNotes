// Start writing your ScalaFiddle code here
abstract class Notification
case class Email(sourceEmail: String, title: String, body: String) extends Notification
case class SMS(sourceNumber: String, message: String) extends Notification
case class VoiceRecording(contactName: String, link: String) extends Notification
def showNotification(notification: Notification): String = {
  notification match {
    case Email(email, title, _) =>
      "You got an email from " + email + " with title: " + title
    case SMS(number, message) =>
      "You got an SMS from " + number + "! Message: " + message
    case VoiceRecording(name, link) =>
      "you received a Voice Recording from " + name + "! Click the link to hear it: " + link
  }
}

def showNotificationSpecial(notification: Notification, specialEmail: String, specialNumber: String): String = {
  notification match {
    case Email(email, _, _) if email == specialEmail =>
      "You got an email from special someone!"
    case SMS(number, _) if number == specialNumber =>
      "You got an SMS from special someone!"
    case other =>
      showNotification(other) // nothing special, delegate to our original showNotification function   
  }
}
val SPECIAL_NUMBER = "55555"
val SPECIAL_EMAIL = "jane@mail.com"
val someSms = SMS("12345", "Are you there?")
val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
val specialEmail = Email("jane@mail.com", "Drinks tonight?", "I'm free after 5!")
val specialSms = SMS("55555", "I'm here! Where are you?")
println(showNotificationSpecial(someSms, SPECIAL_EMAIL, SPECIAL_NUMBER))
println(showNotificationSpecial(someVoiceRecording, SPECIAL_EMAIL, SPECIAL_NUMBER))
println(showNotificationSpecial(specialEmail, SPECIAL_EMAIL, SPECIAL_NUMBER))
println(showNotificationSpecial(specialSms, SPECIAL_EMAIL, SPECIAL_NUMBER))
// prints: 
// You got an SMS from 12345! Message: Are you there?
// you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
// You got an email from special someone!
// You got an SMS from special someone!
