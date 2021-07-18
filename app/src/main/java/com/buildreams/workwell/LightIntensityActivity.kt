package com.buildreams.workwell

import android.hardware.Sensor
import android.hardware.Sensor.TYPE_LIGHT
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_NORMAL
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.buildreams.workwell.di.ViewModelFactory
import com.buildreams.workwell.ui.theme.WorkWellTheme
import com.buildreams.workwell.viewmodel.LightIntensityViewModel
import javax.inject.Inject


class LightIntensityActivity : ComponentActivity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager
    private lateinit var mLight: Sensor

    private var lightIntensity: Float = 0.0f
    private var accuracy: Int = 0

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LightIntensityViewModel>
    private lateinit var homeViewModel: LightIntensityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WorkWellApplication).appComponent.inject(this)
        homeViewModel =
            ViewModelProvider(this, viewModelFactory).get(LightIntensityViewModel::class.java)

        super.onCreate(savedInstanceState)

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mLight = mSensorManager.getDefaultSensor(TYPE_LIGHT)

        setContent {
            WorkWellTheme {
                // A surface container using the 'background' color from the theme
                LightIntensityView(lightIntensity = lightIntensity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mLight, SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        accuracy = event.accuracy
        lightIntensity = event.values[0]
    }

}

@Composable
fun LightIntensityView(lightIntensity: Float) {
    Column {
        Text(text = "$lightIntensity")
    }
}

@Preview(showBackground = true)
@Composable
fun LightIntensityPreview() {
    WorkWellTheme {
        LightIntensityView(1.2f)
    }
}