package com.example.godeeadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private final List<UserModel> profileList;


    public ProfileAdapter( List<UserModel> userModelList) {
        this.profileList = userModelList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView UserID, phoneNumber, userAge;

        public ViewHolder(View itemView) {
            super(itemView);
            UserID = itemView.findViewById(R.id.UserId);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            userAge = itemView.findViewById(R.id.userAge);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = profileList.get(position);
        holder.UserID.setText(userModel.getName());
        holder.phoneNumber.setText(userModel.getPhoneNumber());
        holder.userAge.setText(userModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }
}
