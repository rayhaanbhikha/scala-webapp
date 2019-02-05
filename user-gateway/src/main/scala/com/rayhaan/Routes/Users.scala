package com.rayhaan.Routes

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.Future

class Users extends Service[Request, Response]{
  override def apply(request: Request): Future[Response] = Future {
    val response = Response(Status.Ok)
    response.contentString = """{"page":"users page"}"""
    response.setContentTypeJson()
    response
  }
}
