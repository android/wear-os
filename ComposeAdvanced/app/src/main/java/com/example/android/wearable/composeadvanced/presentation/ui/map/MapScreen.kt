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
package com.example.android.wearable.composeadvanced.presentation.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.example.android.wearable.composeadvanced.BuildConfig
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Displays a Google Map focused on Singapore.
 *
 * Get a key with https://developers.google.com/maps/documentation/android-sdk/get-api-key
 * and put it in local.properties with key `mapsApiKey`.
 */
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
) {
    if (BuildConfig.mapsApiKey.isEmpty()) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = "Set mapsApiKey in local.properties"
            )
        }
    } else {
        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState,
            // disabled until correct handling is implemented inside a swipe dismissable nav host
            // possible related to https://github.com/googlemaps/android-maps-compose/issues/78
            uiSettings = MapUiSettings(scrollGesturesEnabled = false)
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
        }
    }
}
