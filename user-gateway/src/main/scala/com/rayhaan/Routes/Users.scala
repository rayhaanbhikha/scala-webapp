package com.rayhaan.Routes

import com.rayhaan.{UserDatastore, UsersListResponse}
import com.twitter.finagle.{Service, Thrift}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.Future
import org.json4s.{DefaultFormats, Extraction, JValue}
import org.json4s.jackson.JsonMethods.{pretty, render}

class Users extends Service[Request, Response] {
  private val userDatastore = Thrift.client.build[UserDatastore.MethodPerEndpoint]("localhost:8081", "thrift_client")

  override def apply(request: Request): Future[Response] =  userDatastore.getUsers().map(transformResponse)

  def transformResponse(usersListResponse: UsersListResponse): Response = {
    val users = usersListResponse.users
    val json: JValue = Extraction.decompose(users)(DefaultFormats)

    val response = Response(Status.Ok)
    response.setContentTypeJson()
    response.contentString = pretty(render(json))
    response
  }
}