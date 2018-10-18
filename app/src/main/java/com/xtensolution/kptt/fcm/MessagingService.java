/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xtensolution.kptt.fcm;


/**
 * Created by MIT on 11-Sep-17.
 */

//public class MessagingService extends FirebaseMessagingService {
//    private static final String TAG = MessagingService.class.getSimpleName();
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Map<String, String> data = remoteMessage.getData();
//
//        Set<String> keys = data.keySet();
//        for (String key : keys) {
//            AppLog.e(TAG, key + " => " + data.get(key));
//        }
//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        AppLog.d(TAG, "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            AppLog.d(TAG, "Message data payload: " + remoteMessage.getData());
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            AppLog.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//        handleNotification(remoteMessage);
//
//    }
//
//    private void sendNotificationBroadcast(String body) {
////        Intent intent = new Intent();
////        intent.setAction(Constants.MESSAGE_RECEIVER);
////        intent.putExtra(Constants.NOTIFICATION_BODY, body);
////        sendBroadcast(intent);
//    }
//
//    private void handleNotification(RemoteMessage remoteMessage) {
////        try {
////            String body = remoteMessage.getData().get("payload");
////            EventNotification not = new EventNotification();
////            if (body != null && !TextUtils.isEmpty(body)) {
////                not = new Gson().fromJson(body, EventNotification.class);
////            }
////
////            sendNotificationBroadcast(body);
////            // Create Notification
////            Intent intent = new Intent(this, NotificationActivity.class);
////            intent.putExtra(Constants.NOTIFICATION_BODY, body);
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
////                    PendingIntent.FLAG_ONE_SHOT);
////
////            AppLog.d(TAG, "body = >" + body);
////            AppLog.d(TAG, "Content = >" + not.getContent());
////            int color = ResourceUtils.getColor(this, R.color.colorPrimaryRedTheme);
////            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
////            if (remoteMessage.getNotification().getColor() != null) {
////                color = Color.parseColor(remoteMessage.getNotification().getColor());
////            }
////            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
////            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
////                    .setSmallIcon(R.drawable.ic_ctx_notification)
////                    .setLargeIcon(icon)
////                    .setContentTitle(not.getHeading())
////                    .setContentText(not.getTitle())
////                    .setAutoCancel(true)
////                    .setColor(color)
////                    .setSound(defaultSoundUri)
////                    .setContentIntent(pendingIntent);
////
////            NotificationManager notificationManager =
////                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////
////            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
////        } catch (JsonSyntaxException e) {
////            e.printStackTrace();
////        }
//    }
//}
