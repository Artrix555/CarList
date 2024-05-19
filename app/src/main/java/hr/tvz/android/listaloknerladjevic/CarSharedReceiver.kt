package hr.tvz.android.listaloknerladjevic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CarSharedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "CAR_SHARED") {
            showShareConfirmation(context)
        }
    }

    private fun showShareConfirmation(context: Context) {
        val message = context.getString(R.string.share_confirmation_message)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}