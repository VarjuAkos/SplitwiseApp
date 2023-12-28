package hu.bme.aut.android.hf.splitwise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.hf.splitwise.data.database.AppDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}