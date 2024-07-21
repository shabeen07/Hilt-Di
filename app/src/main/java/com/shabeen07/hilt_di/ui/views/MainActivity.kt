package com.shabeen07.hilt_di.ui.views

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.shabeen07.hilt_di.MainViewModel
import com.shabeen07.hilt_di.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    @Inject
    @Named("base_url")
    lateinit var baseUrl: String

    @Inject
    lateinit var someString: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val  mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getUsers().observe(this) {
            it?.let {
                Log.d(TAG, "onCreate: $it")
            }
        }
        Log.d(TAG, "onCreate: $baseUrl")
        Log.d(TAG, "onCreate: $someString")
    }
}