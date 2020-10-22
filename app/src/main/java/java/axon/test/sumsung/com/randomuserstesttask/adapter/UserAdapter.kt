package java.axon.test.sumsung.com.randomuserstesttask.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_item_layout.view.*
import java.axon.test.sumsung.com.randomuserstesttask.R
import java.axon.test.sumsung.com.randomuserstesttask.loadImage
import java.axon.test.sumsung.com.randomuserstesttask.pojo.User

class UserAdapter(private val listener : Listener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    interface Listener {
        fun userInfo(user : User)
    }

    private var mUsers = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(view)
    }

    fun update(users : List<User>) {
        mUsers = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mUsers.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mUsers[position]
        holder.view.user_name.text = "${user.name.first} ${user.name.last}"
        val logo = holder.view.circle_photo;
        logo.loadImage(user.picture.large)
        holder.view.card_view.setOnClickListener({ listener.userInfo(user) })
    }

}