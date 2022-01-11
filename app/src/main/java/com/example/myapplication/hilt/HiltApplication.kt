package com.example.myapplication.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class HiltApplication: Application() {
}