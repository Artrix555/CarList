package hr.tvz.android.listaloknerladjevic

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import hr.tvz.android.listaloknerladjevic.databinding.ActivityCarImageBinding

class CarImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageURL = intent.getStringExtra("imageURL")
        if (!imageURL.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageURL)
                .into(binding.imageImageView)
        }

        val animation = AnimationUtils.loadAnimation(this, R.anim.animation)
        binding.imageImageView.startAnimation(animation)
    }
}