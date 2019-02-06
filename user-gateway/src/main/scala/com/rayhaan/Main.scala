package com.rayhaan

import com.rayhaan.Filters.HeaderFilter
import com.rayhaan.Routes.{Filtered, Home, NotFound, Users}
import com.twitter.finagle.http.path._
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Method, Request, Response}
import com.twitter.finagle.{Http, ListeningServer, Service}
import com.twitter.util.Await

object Main extends App {

  // Routes
  val HomeRoute = new Home()
  val NotFound = new NotFound()
  val FilteredRoute = new Filtered()
  val UsersRoute = new Users()

  // filters
  val HeaderFilter = new HeaderFilter()


  val service: Service[Request, Response] = RoutingService.byMethodAndPathObject({
    case (Method.Get, Root) => HomeRoute // Standard Get Route
    case (Method.Get, Root / "filtered") => HeaderFilter andThen FilteredRoute // Route which needs a Filter
    case (Method.Get, Root / "users") => UsersRoute // Route which uses thrift.
    case _ => NotFound
  })

  val server: ListeningServer = Http.serve(":8080", service)
  Await.ready(server)
}

