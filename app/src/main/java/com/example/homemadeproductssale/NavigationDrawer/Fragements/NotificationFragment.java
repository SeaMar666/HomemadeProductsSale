package com.example.homemadeproductssale.NavigationDrawer.Fragements;

import static com.example.homemadeproductssale.App.CHANNEL_1_ID;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.homemadeproductssale.NotificationReceiver;
import com.example.homemadeproductssale.R;

public class NotificationFragment extends Fragment {

    EditText messageEdit, titleEdit;
    Button sendChannel1;
    NotificationManagerCompat notificationManagerCompat;
    String title, message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.remember_fragment, container, false);

        titleEdit = rootView.findViewById(R.id.titleEdit);
        messageEdit = rootView.findViewById(R.id.messageEdit);
        sendChannel1 = rootView.findViewById(R.id.sendChannel1);

        notificationManagerCompat = NotificationManagerCompat.from(getContext());

        title = titleEdit.getText().toString();
        message = messageEdit.getText().toString();

        sendChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1(rootView);
            }
        });
        return rootView;
    }

    public void sendOnChannel1(View v)
    {
        title = titleEdit.getText().toString();
        message = messageEdit.getText().toString();

        Intent activityIntent = new Intent(getActivity(), NotificationFragment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(getContext(), NotificationReceiver.class);
        PendingIntent actionIntent = PendingIntent.getBroadcast(getContext(), 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_1_ID)
                .setSmallIcon(R.drawable.fav)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManagerCompat.notify(1, notification);

    }
}
