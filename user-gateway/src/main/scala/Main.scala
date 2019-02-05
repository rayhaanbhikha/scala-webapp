import Filters.HeaderFilter
import Routes.{Filtered, Home, NotFound}
import com.twitter.finagle.http.path.{Root, _}
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Method, Request, Response}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.Await

object Main extends App {

  val HomeRoute = new Home()
  val NotFound = new NotFound()
  val FilteredRoute = new Filtered()

  val HeaderFilter = new HeaderFilter()

  val service: Service[Request, Response] = RoutingService.byMethodAndPathObject({
      case (Method.Get, Root) => HomeRoute
      case (Method.Get, Root / "filtered" ) => HeaderFilter andThen FilteredRoute
      case _ => NotFound
  })


  val server = Http.serve(":8080", service)
  Await.ready(server)
}