package com.hw.hellowash.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private File rootDir;
    public static final int REQUEST_CAMERA = 0, SELECT_IMAGE = 1;

    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.work)
    TextView work;
    @BindView(R.id.other)
    TextView other;

    @BindView(R.id.edit_profile_pic)
    RelativeLayout edit_profile_pic;

    private View rootView;
    private TextView toolbar_title;
    private PopupWindow dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootView);

        if (!TextUtils.isEmpty(Utilities.getPref(getActivity(), "Profile_pic", ""))) {
            Picasso.get().load(new File(Utilities.getPref(getActivity(), "Profile_pic", ""))).into(profile_image);
        }



        rootDir = new File(Environment.getExternalStorageDirectory() + "/" + "HelloWash");
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        createUploadMediaDialod();
        return rootView;
    }

    @OnClick(R.id.edit_profile_pic)
    public void capturePhoto() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        } else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            } else {
                dialog.setAnimationStyle(R.style.DialogAnimation);
                dialog.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
            }
        }

    }


    @OnClick(R.id.home)
    public void performHomeClick() {
        home.setBackgroundResource(R.drawable.address_selected_bg);
        work.setBackgroundResource(R.drawable.address_normal_bg);
        other.setBackgroundResource(R.drawable.address_normal_bg);

        home.setTextColor(getResources().getColor(R.color.white));
        work.setTextColor(getResources().getColor(R.color.color_black));
        other.setTextColor(getResources().getColor(R.color.color_black));

        Utilities.home_Adddress = true;
        Utilities.work_Address = false;
        Utilities.other_Address = false;

    }

    @OnClick(R.id.work)
    public void performWorkClick() {
        home.setBackgroundResource(R.drawable.address_normal_bg);
        work.setBackgroundResource(R.drawable.address_selected_bg);
        other.setBackgroundResource(R.drawable.address_normal_bg);

        home.setTextColor(getResources().getColor(R.color.color_black));
        work.setTextColor(getResources().getColor(R.color.white));
        other.setTextColor(getResources().getColor(R.color.color_black));

        Utilities.home_Adddress = false;
        Utilities.work_Address = true;
        Utilities.other_Address = false;
    }

    @OnClick(R.id.other)
    public void performOtherClick() {
        home.setBackgroundResource(R.drawable.address_normal_bg);
        work.setBackgroundResource(R.drawable.address_normal_bg);
        other.setBackgroundResource(R.drawable.address_selected_bg);

        home.setTextColor(getResources().getColor(R.color.color_black));
        work.setTextColor(getResources().getColor(R.color.color_black));
        other.setTextColor(getResources().getColor(R.color.white));

        Utilities.home_Adddress = false;
        Utilities.work_Address = false;
        Utilities.other_Address = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == REQUEST_CAMERA) {
                    addImageToRecycleView(data, requestCode);
                } else if (requestCode == SELECT_IMAGE) {
                    addImageToRecycleView(data, requestCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addImageToRecycleView(Intent data, int requestCode) {
        Bitmap thumbnail = null;

        File destination = null;
        FileOutputStream fo;
        try {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = Utilities.getPath(selectedImageUri, "Photo", getActivity());
                thumbnail = BitmapFactory.decodeFile(selectedImagePath);
            } else {
                thumbnail = (Bitmap) data.getExtras().get("data");
            }



            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            destination = new File(rootDir, System.currentTimeMillis() + "_img" + ".jpg");
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();

            Utilities.savePref(getActivity(), "Profile_pic", destination.toString());

            Picasso.get().load(new File(Utilities.getPref(getActivity(), "Profile_pic", ""))).into(profile_image);

            Picasso.get().load(new File(Utilities.getPref(getActivity(), "Profile_pic", "")))
                    .into(((CircleImageView)getActivity().findViewById(R.id.header_profile_image)));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void createUploadMediaDialod() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.upload_media_dialog, null);
        dialog = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ImageButton close_popup = (ImageButton) popupView.findViewById(R.id.close_popup);
        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        LinearLayout camera_view = (LinearLayout) popupView.findViewById(R.id.camera_view);
        LinearLayout gallery_view = (LinearLayout) popupView.findViewById(R.id.gallery_view);


        camera_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
                dialog.dismiss();
            }
        });
        gallery_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                gallery_intent.setType("image/*");
                startActivityForResult(Intent.createChooser(gallery_intent, "Photos from Gallery"), SELECT_IMAGE);
                dialog.dismiss();
            }
        });

        dialog.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
    }
}
