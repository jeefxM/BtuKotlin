import androidx.recyclerview.widget.RecyclerView
import coil.load
import ge.george.btu_exercise_8.`interface`.OnUserClickListener
import ge.george.btu_exercise_8.databinding.ItemUserBinding
import ge.george.btu_exercise_8.model.User

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User?, listener: OnUserClickListener) {
        user?.let {
            binding.apply {
                clUser.setOnClickListener { listener.onUserClicked(user) }

                tvUserName.text = "${user.firstName} ${user.lastName}"
                tvUserEmail.text = user.email

                ivAvatar.load(user.avatar)
            }
        }
    }
}