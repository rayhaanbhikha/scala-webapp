package Filters

import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.Future

class HeaderFilter extends SimpleFilter[Request, Response]{
  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {

    val clientHeader: Option[String] = request.headerMap.get("client")

    if(clientHeader.isDefined)
      service(request)
    else
      ErrorResponse(Status.BadRequest, "client header is missing")
  }
}