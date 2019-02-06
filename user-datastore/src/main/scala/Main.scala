import com.twitter.finagle.Thrift
import com.twitter.util.Await


object Main extends App {
  val server = Thrift.server.serveIface(":8081", new UserStore())
  Await.ready(server)
}

