import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Http, Service, Thrift}
import com.twitter.util.{Await, Future}


object Main extends App {
  val server = Thrift.server.serveIface(":8081", new UserStore())

//  val service = new Service[Request, Response] {
//    override def apply(request: Request): Future[Response] = Future.value(Response(Status.Ok))
//  }
//
//  val server = Http.serve(":8081", service)

  Await.ready(server)
}

