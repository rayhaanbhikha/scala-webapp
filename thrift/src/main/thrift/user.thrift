namespace java com.rayhaan
#@namespace scala com.rayhaan

struct User {
    1: required string name
    2: required i16 age
    3: required string gender
    4: required string faveColour
}

struct UsersListResponse {
    1: required list<User> users
}

service UserDatastore {
    UsersListResponse getUsers()
}