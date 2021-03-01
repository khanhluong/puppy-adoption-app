/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.ListPuppyScreen
import com.example.androiddevchallenge.ui.PuppyDetailScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.PuppyViewModel

class MainActivity : AppCompatActivity() {

    // load view model
    private val viewModel: PuppyViewModel by viewModels<PuppyViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(viewModel = viewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(viewModel: PuppyViewModel) {

    val puppies: List<Puppy> by viewModel.puppies.observeAsState(listOf())
    var puppy: Puppy = Puppy()
    Log.d("puppies", "puppies " + puppies.size)

    val navController = rememberNavController()

    Surface(color = MaterialTheme.colors.background) {

        NavHost(navController, startDestination = "puppylist") {
            composable("puppylist") {
                ListPuppyScreen(puppies) {
                    puppy = it
                    navController.navigate("puppydetails")
                }
            }
            composable("puppydetails") { PuppyDetailScreen(puppy = puppy) }
        }
    }
}
