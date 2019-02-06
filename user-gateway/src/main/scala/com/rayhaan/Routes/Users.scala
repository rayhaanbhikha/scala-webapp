package com.rayhaan.Routes

import com.rayhaan.utils.ThriftSerializer
import com.rayhaan.{User, UserDatastore, UsersListResponse}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Service, Thrift}
import com.twitter.util.Future
import org.json4s.jackson.JsonMethods.{pretty, render}
import org.json4s.{DefaultFormats, Extraction, JValue}

class Users extends Service[Request, Response] {
  implicit val formats = DefaultFormats + ThriftSerializer
  private val userDatastore = Thrift.client.build[UserDatastore.MethodPerEndpoint]("localhost:8081", "thrift_client")

  override def apply(request: Request): Future[Response] = userDatastore.getUsers().map(transformResponse)

  def transformResponse(usersListResponse: UsersListResponse): Response = {
    val users: Seq[User] = usersListResponse.users
    val json: JValue = Extraction.decompose(users)

    val response = Response(Status.Ok)
    response.setContentTypeJson()
    response.contentString = pretty(render(json))
    response
  }
}