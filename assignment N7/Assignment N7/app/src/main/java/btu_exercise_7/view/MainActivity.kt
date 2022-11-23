import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ge.george.btu_exercise_8.`interface`.OnUserClickListener
import ge.george.btu_exercise_8.databinding.ActivityMainBinding
import ge.george.btu_exercise_8.model.Base
import ge.george.btu_exercise_8.model.User
import ge.george.btu_exercise_8.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnUserClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadUsers()
    }

    private fun loadUsers() {
        binding.progress.isVisible = true
        MyRetrofit.getUserService().getUsers()
            .enqueue(object : Callback<Base<List<User>>> {
                override fun onResponse(
                    call: Call<Base<List<User>>>,
                    response: Response<Base<List<User>>>
                ) {
                    binding.progress.isVisible = false
                    response.body()?.let { baseResponse ->
                        baseResponse.data?.let { users ->
                            binding.rvUsers.adapter = UserAdapter(this@MainActivity).apply {
                                submitList(users)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Base<List<User>>>, t: Throwable) {
                    binding.progress.isVisible = false
                    t.printStackTrace()
                    Toast.makeText(this@MainActivity, "Cant load...", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onUserClicked(user: User) {
        startActivity(
            Intent(this, ProfileActivity::class.java).apply {
                putExtra("user_id", user.id)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}