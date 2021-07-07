package com.buildreams.workwell

import android.app.Application
import com.buildreams.workwell.di.DaggerApplicationComponent

class WorkWellApplication: Application() {

    val appComponent = DaggerApplicationComponent.create()
}