package com.rayhaan.Routes

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.Future

class NotFound extends Service[Request, Response] {
  override def apply(request: Request): Future[Response] = {
    val response = Response(Status.BadRequest)
    response.contentString =
      s"""
        |{
        | "Error": ${Status.BadRequest},
        | "message": "404 page does not exist"
        |}
        | """.stripMargin

    Future.value(response)
  }
}
