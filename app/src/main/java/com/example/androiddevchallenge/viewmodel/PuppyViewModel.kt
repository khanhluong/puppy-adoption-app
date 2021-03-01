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
package com.example.androiddevchallenge.viewmodel

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.model.Puppy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

/**
 * Load puppy information from json file
 */
class PuppyViewModel(application: Application) : AndroidViewModel(application) {

    private val assetsManager: AssetManager = application.assets
    private val _puppies = MutableLiveData<List<Puppy>>()

    val puppies: LiveData<List<Puppy>> = _puppies

    init {
        viewModelScope.launch {
            val puppies = withContext(Dispatchers.IO) {
                val puppiesJsonValue = assetsManager.open("puppies.json").use {
                    it.reader().readText()
                }
                val listType: Type = object : TypeToken<List<Puppy>>() {}.type
                Gson().fromJson<List<Puppy>>(puppiesJsonValue, listType)
            }
            _puppies.value = puppies
        }
    }
}
