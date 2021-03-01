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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ListPuppyScreen(puppies: List<Puppy> = listOf(Puppy()), navigateTo: (Puppy) -> Unit) {
    PuppiesList(puppies = puppies, navigateTo)
}

@Composable
fun PuppyItem(
    puppy: Puppy,
    navigateTo: (Puppy) -> Unit
) {
    Row(
        modifier = Modifier
            .height(96.dp)
            .clickable { navigateTo(puppy) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
        ) {

            Row() {
                CoilImage(
                    data = puppy.imageUrl,
                    null,
                    modifier = Modifier.width(100.dp).padding(10.dp, 10.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = puppy.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun PuppiesList(puppies: List<Puppy>, navigateTo: (Puppy) -> Unit) {
    LazyColumn {
        items(items = puppies) { puppy ->
            PuppyItem(puppy = puppy, navigateTo)
            Divider(color = Color.Black)
        }
    }
}

@Preview("List", widthDp = 360, heightDp = 640)
@Composable
fun ListViewPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            ListPuppyScreen(navigateTo = {})
        }
    }
}
