package com.xtensolution.kptt.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.BuildConfig;
import com.xtensolution.kptt.KPTApp;


import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * USED FOR OPEN CAMERA OR GALLERY
 */
public class ImagePickerDialog {
    private static final String TAG = ImagePickerDialog.class.getSimpleName();
    private static final int SELECT_FILE = 102;
    public static final int REQUEST_CAMERA = 103;
    private static final int REQUEST_CROP = 101;


    private Activity mActivity;
    private File mTempFile, mFileTemp;
    private Bitmap mBitmapProfile;
    private static String TEMP_PHOTO_FILE_NAME = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss")
            .format(new Date()) + ".jpg";
    private Uri mURI;
    private ImagePickerListener mListener;
    private String mCurrentPhotoPath;
    private String mIMG_FILE_NAME = "";
    private File directory;
    private ImageCompressTask mTask;
    private Base64CompressCallBack mCallBack;

    public ImagePickerDialog(Activity activity, ImagePickerListener listener) {
        mActivity = activity;
        mListener = listener;
        String state = Environment.getExternalStorageState();
        directory = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" +
                KPTApp.getInstance().getPackageName());
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(),
                    TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(mActivity.getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }
    }

    /**
     * @param uri
     * @return
     */
    public String getPath(final Uri uri) {
        final String[] projection = {MediaStore.Images.Media.DATA};
        final Cursor cursor = mActivity.getContentResolver().query(uri, projection, null,
                null, null);
        final int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /**
     *
     */
    public void shareFile() {
        try {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(final Void... params) {
                    return null;
                }

                @Override
                protected void onPostExecute(final Void result) {
                    super.onPostExecute(result);
                }

            }.execute();
        } catch (final Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    /**
     *
     */
    private Uri saveInSdcard() {
        Uri uri = null;
        FileOutputStream fo = null;
        try {
            mTempFile = new File(directory, System.currentTimeMillis() + ".jpg");

            fo = new FileOutputStream(mTempFile);

            mBitmapProfile.compress(Bitmap.CompressFormat.JPEG, 100, fo);
            fo.flush();
            fo.close();
            MediaStore.Images.Media.insertImage(mActivity.getContentResolver(),
                    mTempFile.getAbsolutePath(), mTempFile.getName(),
                    mTempFile.getName());
//            uri = Uri.fromFile(mTempFile);
            FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".provider", mTempFile);


        } catch (final Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
        return uri;
    }

    /**
     * @param bitmap
     * @return
     */
    private Bitmap getMutableBitmap(final Bitmap bitmap) {
        try {
            final Bitmap mutBmp = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            final Canvas can = new Canvas(mutBmp);
            can.drawBitmap(bitmap, 0, 0, new Paint());
            return mutBmp;
        } catch (final Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    /**
     *
     */
    public void openImagePickedFromDialog() {
        final CharSequence[] items = {"Take Photo", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int item) {
                if (items[item].equals("Take Photo")) {
                    openCamera();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    //

    /**
     *
     */
    /*public void openImagePickedFromGalllery() {
        final CharSequence[] items = {"Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int item) {
                if (items[item].equals("Choose from Gallery")) {
                    openGallery();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }*/

   /* public void openImagePickedFromGalllery() {
        final CharSequence[] items = {"Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int item) {
                if (items[item].equals("Choose from Gallery")) {
                    selectFile();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }*/
    //

    /* //

     */

    /**
     *
     *//*
    public void openImagePickedFromDialog() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int item) {
                if (items[item].equals("Take Photo")) {
                    openCamera();
                } else if (items[item].equals("Choose from Gallery")) {
                    openGallery();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }
    //*/
    //change
  /*  public void openGallery() {
        final Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        mActivity.startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                SELECT_FILE);
    }*/

  //change





    public void openCamera() {
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) { ///version change N to N_MR1

//            Implament After
            //change
            cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                mURI =
                        FileProvider.getUriForFile(KPTApp.getInstance().getApplicationContext(),
                                BuildConfig.APPLICATION_ID + ".provider",
                                createImageFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //change

            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mURI);
        } else {*/
        cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            final File f = createImageFile();
              /*  mURI = Uri.fromFile(f);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));*/
            mURI = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".provider", f);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mActivity.startActivityForResult(cameraIntent,REQUEST_CAMERA);
}





    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent data) {
        if (resultCode == mActivity.RESULT_OK) {
            File f = new File(Environment.getExternalStorageDirectory()
                    .toString());

            if (requestCode == REQUEST_CAMERA) {
                AppLog.d(TAG, new Gson().toJson(mURI));
                AppLog.d(TAG, mCurrentPhotoPath);
                for (final File temp : f.listFiles()) {
                    if (temp.getName().equals(mIMG_FILE_NAME)) {
                        mFileTemp = temp;
                        break;
                    }
                }
                mBitmapProfile = BitmapFactory.decodeFile(mCurrentPhotoPath);
                Uri uri = saveInSdcard();
                mListener.getBitmapImageFromPhone(mTempFile, uri);
                f.delete();
            } else if (requestCode == SELECT_FILE) {
                try {
                    AppLog.d(TAG, new Gson().toJson(data.getData()));
                    mBitmapProfile = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver()
                            , data.getData());
                    saveInSdcard();

                    mListener.getBitmapImageFromPhone(mTempFile, data.getData());
                    f.delete();
                } catch (Exception e) {
                    AppLog.e(TAG, e.getMessage(), e);
                }




            } else {
                Toast.makeText(mActivity, "Something going to wrong.Try again later.", Toast.LENGTH_LONG).show();
                AppLog.e(TAG, "error in pic...");
            }
        }
    }



    /**
     * @param is
     * @param os
     */

    public static void copyStream(final InputStream is, final OutputStream os) {
        final int buffer_size = 1024;
        try {
            final byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                // Read byte from input stream
                final int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                // Write byte from output stream
                os.write(bytes, 0, count);
            }
        } catch (final Exception ex) {
            AppLog.e(TAG, ex.getMessage(), ex);
        }
    }


    public void getImagesFrom(int type) {
        if (type == 0) {
            final Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            final File f = new File(Environment
                    .getExternalStorageDirectory(),
                    TEMP_PHOTO_FILE_NAME);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            mActivity.startActivityForResult(intent, REQUEST_CAMERA);
        } else if (type == 1) {
            final Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            mActivity.startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    SELECT_FILE);
        }
    }

    public String getPath() {
        String path = "";
        if (mFileTemp != null) {
            path = mFileTemp.getPath();
        }
        return path;
    }

    public Bitmap getBitmapFromFile(File file) {
        try {
            if (file != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inSampleSize = 8;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
                return bitmap;
            } else {
                return null;
            }
        } catch (Exception e) {
            com.xtensolution.kptt.utils.AppLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public interface ImagePickerListener {
        void getBitmapImageFromPhone(File file, Uri fileUri);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mIMG_FILE_NAME = "Img_" + timeStamp + ".jpg";
//        File storageDir = new File(Environment.getExternalStorageDirectory(), "images");
        File file = new File(directory, mIMG_FILE_NAME);
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = file.getAbsolutePath();//"file:" + image.getAbsolutePath();
        return file;
    }

    public String encodeTobase64(File image) {
        Bitmap immagex = getBitmapFromFile(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public void getBase64StringFromImage(File file, Base64CompressCallBack callBack) {
        mCallBack = callBack;
        mTask = new ImageCompressTask();
        mTask.execute(file);

    }

    private class ImageCompressTask extends AsyncTask<File, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(File... params) {
            File image = params[0];
            String imageEncoded = "";
            try {
                if (image != null) {
                    Bitmap immagex = getBitmapFromFile(image);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    if (immagex != null) {
                        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] b = baos.toByteArray();
                        imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
                    }
                }
            } catch (Exception e) {
                com.xtensolution.kptt.utils.AppLog.e(TAG, e.getMessage(), e);
            }
            return imageEncoded;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (mCallBack != null) {
                mCallBack.getCompressString(s);
            }

        }
    }

    public interface Base64CompressCallBack {
        void getCompressString(String base64String);
    }

    public void cancleCall() {
        if (mTask != null && !mTask.isCancelled()) {
            mTask.cancel(true);
        }
    }
}
