package buggy.plandroid.com.plandroidbugs;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import buggy.plandroid.com.plandroidbugs.room.UserEntity;

public class UserAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<UserEntity> userEntities = new ArrayList<>();

    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_box, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) holder.itemView.findViewById(R.id.email_text)).setText(userEntities.get(position).email);
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
