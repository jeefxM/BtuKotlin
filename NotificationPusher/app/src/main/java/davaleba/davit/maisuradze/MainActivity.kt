package davaleba.davit.maisuradze

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnPush: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPush = findViewById(R.id.btnPush)
        btnPush.setOnClickListener { push() }
    }

    private fun push() {
        val intent = Intent(this, MainActivity::class.java).apply {
            action = "CANCEL_NOTIFICATION"
            putExtra("notification_id", 1)
        }
        val cancelIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, "expandable_notification")
            .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Test")
            .setContentText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
            .setStyle(NotificationCompat.BigTextStyle()).setDeleteIntent(cancelIntent)
            .addAction(NotificationCompat.Action.Builder(null, "DELETE", cancelIntent).build())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Expandable Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("expandable_notification", name, importance)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(1, notification.build())
    }

    override fun onResume() {
        super.onResume()

        if (intent != null) {
            val notificationId = intent.getIntExtra("notification_id", -1)
            val action = intent.action

            if (action != null) {
                if (action == "CANCEL_NOTIFICATION") {
                    val notificationManager: NotificationManager =
                        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.cancel(notificationId)
                }
            }

            intent.extras?.clear()
        }
    }
}