package com.example.homemadeproductssale.NavigationDrawer.Fragements;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.homemadeproductssale.R;

import java.io.IOException;

public class HomeUserInfoFragment extends Fragment {

    private ImageView userImage;
    private static final int PICK_IMAGE = 1;
    EditText twitterLink, facebookLink;
    Uri imageUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragement_home_userinfo, container, false);

        userImage = rootView.findViewById(R.id.userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });

        twitterLink = rootView.findViewById(R.id.twitterLink);
        facebookLink = rootView.findViewById(R.id.facebookLink);

        twitterLink.setMovementMethod(LinkMovementMethod.getInstance());
        facebookLink.setMovementMethod(LinkMovementMethod.getInstance());

        Spannable spannable = new SpannableString("www.twitter.com");
        Linkify.addLinks(spannable, Linkify.WEB_URLS);
        CharSequence text = TextUtils.concat(spannable, "\u200B");
        twitterLink.setText(text);

        Spannable spannable2 = new SpannableString("www.facebook.com");
        Linkify.addLinks(spannable, Linkify.WEB_URLS);
        CharSequence text2 = TextUtils.concat(spannable2, "\u200B");
        facebookLink.setText(text2);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUrl = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),imageUrl);
                userImage.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
