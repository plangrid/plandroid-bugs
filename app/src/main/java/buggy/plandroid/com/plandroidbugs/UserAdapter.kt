package buggy.plandroid.com.plandroidbugs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buggy.plandroid.com.plandroidbugs.room.UserEntity

class UserAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var userEntities: List<UserEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_box, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.email_text).text = userEntities[position].email
    }

    override fun getItemCount(): Int {
        return userEntities.size
    }

    fun setUsers(userEntities: List<UserEntity>) {
        this.userEntities = userEntities
        notifyDataSetChanged()
    }

}