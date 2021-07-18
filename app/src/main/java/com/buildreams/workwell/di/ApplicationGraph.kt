package com.buildreams.workwell.di

import com.buildreams.workwell.HomeActivity
import com.buildreams.workwell.LightIntensityActivity
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(homeActivity: HomeActivity)
    fun inject(lightIntensityActivity: LightIntensityActivity)
}