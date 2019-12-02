package com.danielbeleza.diagonalee

import android.app.Application
import com.danielbeleza.diagonalee.tracking.DiagonaleeTracker

class DiagonaleeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
    }

    private fun initializeFirebase() {
        DiagonaleeTracker.init(this)
    }
}