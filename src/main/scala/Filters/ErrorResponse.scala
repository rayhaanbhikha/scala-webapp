package Filters

import com.twitter.finagle.http.{Response, Status}
import com.twitter.util.Future

object ErrorResponse {
  def apply(status: Status, message: String): Future[Response] = {
    val response = Response(status)
    response.contentString =
      s"""
         | {
         |  "message" : $message
         | }
         """.stripMargin
    Future.value(response)
  }
}
