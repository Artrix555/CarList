package hr.tvz.android.listaloknerladjevic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import hr.tvz.android.listaloknerladjevic.databinding.ActivityCarDetailsBinding

class CarDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarDetailsBinding
    private lateinit var carSharedReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carSharedReceiver = object : BroadcastReceiver() {
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

        val intentFilter = IntentFilter("CAR_SHARED")
        ContextCompat.registerReceiver(this, carSharedReceiver, intentFilter, ContextCompat.RECEIVER_NOT_EXPORTED)


        val car = intent.getParcelableExtra<Car?>("selectedCar")
        if (car != null) {
            showCarDetails(car)
        } else {
            finish()
        }
    }

    private fun showCarDetails(car: Car) {
        binding.modelTextView.text = car.model
        binding.manufacturerTextView.text = car.manufacturer
        binding.yearTextView.text = car.year.toString()
        binding.descriptionTextView.text = car.description

        Glide.with(this)
            .load(car.imageURL)
            .into(binding.imageImageView)

        binding.imageImageView.setOnClickListener {
            val intent = Intent(this, CarImageActivity::class.java)
            intent.putExtra("imageURL", car.imageURL)
            startActivity(intent)
        }

        binding.websiteButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(car.websiteURL))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.car_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                showShareDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showShareDialog() {
        val builder = AlertDialog.Builder(this)
        val title = getString(R.string.share_dialog_title)
        val message = getString(R.string.share_dialog_message)
        val positiveButtonText = getString(R.string.share_dialog_positive_button)
        val negativeButtonText = getString(R.string.share_dialog_negative_button)

        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { _, _ ->
                val intent = Intent("CAR_SHARED")
                sendBroadcast(intent)
            }
            .setNegativeButton(negativeButtonText, null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(carSharedReceiver)
    }
}