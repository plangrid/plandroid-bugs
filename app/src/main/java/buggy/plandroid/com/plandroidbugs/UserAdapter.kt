package buggy.plandroid.com.plandroidbugs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buggy.plandroid.com.plandroidbugs.app.PlanDroidApp.Companion.getApp
import buggy.plandroid.com.plandroidbugs.room.UserEntity

class UserAdapter : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var levelDialog: AlertDialog
    private var userEntities: List<UserEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_box, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.email_text).text =
            getDisplayText(userEntities[position])
        holder.itemView.setOnClickListener {
            openRolePickerDialog(
                userEntities[position], holder.itemView.context
            )
        }
    }

    private fun getDisplayText(userEntity: UserEntity): String {
        return "${userEntity.firstName}: ${roleUidsToNames[userEntity.roleUid]}"
    }

    private fun openRolePickerDialog(user: UserEntity, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Role")
        val items = arrayOf(COLLABORATOR, POWER_COLLABORATOR, ADMIN)
        builder.setSingleChoiceItems(items, -1) { _: DialogInterface?, item: Int ->
            var role = COLLABORATOR_ROLE
            when (item) {
                0 -> role = COLLABORATOR_ROLE
                1 -> role = POWER_COLLABORATOR_ROLE
                2 -> role = ADMIN_ROLE
            }
            pgApi.changeUserRole(user.userId, role)
            getApp(context).database.userDao().update(user)
            levelDialog.dismiss()
        }
        levelDialog = builder.create()
        levelDialog.show()
    }

    override fun getItemCount(): Int {
        return userEntities.size
    }

    fun setUsers(userEntities: List<UserEntity>) {
        this.userEntities = userEntities
        notifyDataSetChanged()
    }

    companion object {
        private const val COLLABORATOR = "Collaborator"
        private const val COLLABORATOR_ROLE = "17dce2c5-4931-47e7-8c98-84b35f00ba03"
        private const val POWER_COLLABORATOR = "Power Collaborator"
        private const val POWER_COLLABORATOR_ROLE = "e7295fe5-5312-4559-b784-a74498464fb7"
        private const val ADMIN = "Admin"
        const val ADMIN_ROLE = "9d139e64-cac9-4f23-b4d5-9fd3688b498e"
        private val pgApi = PGApi()
        private val roleUidsToNames = mapOf(
            COLLABORATOR_ROLE to COLLABORATOR,
            POWER_COLLABORATOR_ROLE to POWER_COLLABORATOR,
            ADMIN_ROLE to ADMIN
        )
    }
}