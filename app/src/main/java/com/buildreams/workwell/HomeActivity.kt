package com.buildreams.workwell

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.buildreams.workwell.di.ViewModelFactory
import com.buildreams.workwell.ui.theme.WorkWellTheme
import com.buildreams.workwell.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WorkWellApplication).appComponent.inject(this)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContent {
            WorkWellTheme {
                // A surface container using the 'background' color from the theme
                mainView()
            }
        }
    }

    @Composable
    fun mainView() {
        Surface(color = MaterialTheme.colors.primary) {
            Greeting("Android")
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                startActivity(Intent(this, LightIntensityActivity::class.java))
            }) {
                Text(text = "Show Light Intensity")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        WorkWellTheme {
            mainView()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        Text(text = "Hello $name!")
    }
}

