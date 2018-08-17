package buggy.plandroid.com.plandroidbugs;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import buggy.plandroid.com.plandroidbugs.app.PlanDroidApp;
import buggy.plandroid.com.plandroidbugs.room.UserEntity;

public class UserAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String COLLABORATOR_ROLE = "17dce2c5-4931-47e7-8c98-84b35f00ba03";
    private static final String POWER_COLLABORATOR_ROLE = "e7295fe5-5312-4559-b784-a74498464fb7";
    public static final String ADMIN_ROLE ="9d139e64-cac9-4f23-b4d5-9fd3688b498e";

    private static final PGApi pgApi = new PGApi();

    private static HashMap<String, String> roleUidsToNames = new HashMap<>();
    static {
        roleUidsToNames.put(COLLABORATOR_ROLE, "Collaborator");
        roleUidsToNames.put(POWER_COLLABORATOR_ROLE, "Power Collaborator");
        roleUidsToNames.put(ADMIN_ROLE, "Admin");
    }

    private Dialog levelDialog = null;
    List<UserEntity> userEntities = new ArrayList<>();

    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_box, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) holder.itemView.findViewById(R.id.email_text)).setText(getDisplayText(userEntities.get(position)));
        holder.itemView.setOnClickListener(v -> openRolePickerDialog(userEntities.get(position), holder.itemView.getContext()));
    }

    private String getDisplayText(UserEntity userEntity) {
        return userEntity.firstName + ": "+ roleUidsToNames.get(userEntity.roleUid);
    }

    private void openRolePickerDialog(UserEntity user, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Role");
        String[] items = {"Collaborator", "Power Collaborator", "Admin"};
        builder.setSingleChoiceItems(items, -1, (dialog, item) -> {
            String role = COLLABORATOR_ROLE;
            switch(item)
            {
                case 0:
                    role = COLLABORATOR_ROLE;
                    break;
                case 1:
                    role = POWER_COLLABORATOR_ROLE;
                    break;
                case 2:
                    role = ADMIN_ROLE;
                    break;
            }
            pgApi.changeUserRole(user.userId, role);
            PlanDroidApp.getApp(context).getDatabase().userDao().update(user);
            levelDialog.dismiss();
        });
        levelDialog = builder.create();
        levelDialog.show();
    }

    @Override public int getItemCount() {
        return userEntities.size();
    }

    public void setUsers(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
        notifyDataSetChanged();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);
    }
}
