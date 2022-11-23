import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import ge.george.btu_exercise_8.databinding.ActivityProfileBinding
import ge.george.btu_exercise_8.model.Base
import ge.george.btu_exercise_8.model.User
import ge.george.btu_exercise_8.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private var _binding: ActivityProfileBinding? = null
    private val binding: ActivityProfileBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadUser()
    }

    private fun loadUser() {
        binding.progress.isVisible = true
        val userId = intent.getIntExtra("user_id", -1)

        MyRetrofit.getUserService().getUser(userId)
            .enqueue(object : Callback<Base<User>> {
                override fun onResponse(call: Call<Base<User>>, response: Response<Base<User>>) {
                    binding.progress.isVisible = false
                    response.body()?.let {
                        it.data?.let { user ->
                            binding.ivAvatar.load(user.avatar)
                            binding.userName.text = "${user.firstName} ${user.lastName}"
                            binding.userEmail.text = user.email
                        }
                    }
                }

                override fun onFailure(call: Call<Base<User>>, t: Throwable) {
                    binding.progress.isVisible = false
                    t.printStackTrace()
                    Toast.makeText(this@ProfileActivity, "Cant load user", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}