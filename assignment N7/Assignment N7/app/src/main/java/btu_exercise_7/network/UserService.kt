import ge.george.btu_exercise_8.model.Base
import ge.george.btu_exercise_8.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("users")
    fun getUsers(
        @Query("page") page: Int = 1
    ): Call<Base<List<User>>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<Base<User>>
}