import com.rayhaan.{User, UserDatastore, UsersListResponse}
import com.twitter.util.Future

class UserStore extends UserDatastore.MethodPerEndpoint {
  override def getUsers(): Future[UsersListResponse] = Future.value(Users.getUsers)
}

object Users {
  def getUsers: UsersListResponse = UsersListResponse(Seq(
    User("Rayhaan Bhikha", 23, "Male", "blue"),
    User("Tom Lloyd", 24, "Male", "red"),
    User("Jennifer O'leary", 26, "Female", "green"),
    User("Edward Andrews", 23, "Male", "yellow")
  ))
}