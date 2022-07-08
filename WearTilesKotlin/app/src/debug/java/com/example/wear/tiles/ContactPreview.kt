/*
 * Copyright 2022 The Android Open Source Project
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
package com.example.wear.tiles

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tiles.ActionBuilders
import androidx.wear.tiles.ModifiersBuilders
import androidx.wear.tiles.ResourceBuilders
import com.example.wear.tiles.messaging.MessagingRepo
import com.example.wear.tiles.messaging.MessagingTileRenderer
import com.example.wear.tiles.messaging.bitmapToImageResource
import com.google.android.horologist.compose.tools.LayoutElementPreview

val emptyClickable = ModifiersBuilders.Clickable.Builder()
    .setOnClick(ActionBuilders.LoadAction.Builder().build())
    .setId("")
    .build()

@Preview(
    backgroundColor = 0xff000000, showBackground = true, widthDp = 100, heightDp = 100
)
annotation class IconSizePreview

@IconSizePreview
@Composable
fun ContactPreview() {
    val context = LocalContext.current
    val renderer = remember { MessagingTileRenderer(context) }

    val layout = renderer.contactLayout(
        contact = MessagingRepo.knownContacts[0],
        clickable = emptyClickable
    )

    LayoutElementPreview(layout)
}

@IconSizePreview
@Composable
fun ContactWithImagePreview() {
    val context = LocalContext.current
    val renderer = remember { MessagingTileRenderer(context) }

    val contact = MessagingRepo.knownContacts[1]
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ali)

    val layout = renderer.contactLayout(
        contact = contact,
        clickable = emptyClickable
    )

    LayoutElementPreview(layout) {
        addIdToImageMapping(
            "${MessagingTileRenderer.ID_CONTACT_PREFIX}${contact.id}",
            bitmapToImageResource(bitmap)
        )
    }
}

// Shows a problem with no fallback if image is missing
@IconSizePreview
@Composable
fun ContactWithMissingImagePreview() {
    val context = LocalContext.current
    val renderer = remember { MessagingTileRenderer(context) }

    val contact = MessagingRepo.knownContacts[1]

    val layout = renderer.contactLayout(
        contact = contact,
        clickable = emptyClickable
    )

    LayoutElementPreview(layout)
}

@IconSizePreview
@Composable
fun SearchPreview() {
    val context = LocalContext.current
    val renderer = remember { MessagingTileRenderer(context) }

    val layout = renderer.searchLayout()

    LayoutElementPreview(layout) {
        addIdToImageMapping(
            MessagingTileRenderer.ID_IC_SEARCH,
            ResourceBuilders.ImageResource.Builder()
                .setAndroidResourceByResId(
                    ResourceBuilders.AndroidImageResourceByResId.Builder()
                        .setResourceId(R.drawable.ic_search)
                        .build()
                )
                .build()
        )
    }
}
