import Routes.{Home, NotFound}
import com.twitter.finagle.http.path.Root
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Method, Request, Response}
import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}

object Main extends App {

  val HomeRoute = new Home()
  val NotFound = new NotFound()

  val service: Service[Request, Response] = RoutingService.byMethodAndPathObject({
      case (Method.Get, Root) => HomeRoute
      case _ => NotFound
    })

  val server = Http.serve(":8080", service)
  Await.ready(server)
}


