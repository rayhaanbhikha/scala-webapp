import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._


object Main extends App {
  val service = new Service[Request, Response] {
    override def apply(request: Request): Future[Response] = {
      val response = Response(Status.Ok)

      val json =
        "users" ->
          Users().map { user =>
              ("name" -> user.name) ~
                ("age" -> user.age) ~
                ("gender" -> user.gender)
          }

      response.contentString = compact(render(json))
      Future.value(response)
    }
  }


  val server = Http.serve(":8081", service)
  Await.ready(server)
}

case class User(name: String, age: Int, gender: String)

object Users {

  import Gender._

  def apply(): List[User] = List(
    User("Rayhaan Bhikha", 23, Male),
    User("Tom Lloyd", 24, Male),
    User("Jennifer O'leary", 26, Female),
    User("Edward Andrews", 23, Male)
  )
}

object Gender {
  val Male = "male"
  val Female = "female"
}