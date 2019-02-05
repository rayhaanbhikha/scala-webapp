package Routes

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.Future


class Filtered extends Service[Request, Response] {
  override def apply(request: Request): Future[Response] = {
    val response = Response(Status.Ok)
    response.setContentTypeJson()
    response.contentString = """{"page": "Fitlered Page"}"""
    Future.value(response)
  }
}