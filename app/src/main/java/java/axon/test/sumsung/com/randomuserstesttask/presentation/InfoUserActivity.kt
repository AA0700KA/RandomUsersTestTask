package java.axon.test.sumsung.com.randomuserstesttask.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.info_user_layout.*
import java.axon.test.sumsung.com.randomuserstesttask.R
import java.axon.test.sumsung.com.randomuserstesttask.loadImage
import java.text.SimpleDateFormat

class InfoUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_user_layout)
        setTitle(resources.getString(R.string.profile))
        val intent = intent;
        val dateTxt = intent.getStringExtra("date")
        val userName = intent.getStringExtra("user_name")
        val phoneTxt = intent.getStringExtra("phone")
        val emailTxt = intent.getStringExtra("email")
        val pictureUrl = intent.getStringExtra("picture")
        val genderTxt = intent.getStringExtra("gender")
        logo.loadImage(pictureUrl)
        user_name.text = userName
        phone.text = phoneTxt
        email.text = emailTxt
        gender.text = genderTxt
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = simpleDateFormat.parse(dateTxt)
        val dateResult = simpleDateFormat.format(date)
        years_old.text = dateResult
    }

}