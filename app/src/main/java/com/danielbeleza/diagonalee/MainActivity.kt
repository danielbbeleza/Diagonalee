package com.danielbeleza.diagonalee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielbeleza.diagonalee.entity.ProductGenerator
import com.danielbeleza.diagonalee.tracking.DiagonaleeTracker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        val mainFeedAdapter = MainFeedAdapter(
            content = ProductGenerator.generateProducts(10),
            onClick = {
                DiagonaleeTracker.trackProductClick(it)
            }
        )

        with(main_feed_content) {
            layoutManager = LinearLayoutManager(context.applicationContext)
            setHasFixedSize(true)
            adapter = mainFeedAdapter
        }
    }
}
