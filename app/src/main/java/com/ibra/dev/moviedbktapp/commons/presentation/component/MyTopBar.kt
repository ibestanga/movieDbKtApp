package com.ibra.dev.moviedbktapp.commons.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String,
    needBackNavigation: Boolean,
    actionIcon: ImageVector? = null,
    onBackPressClick:() -> Unit = {},
    actionClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        navigationIcon = {
            if (needBackNavigation) {
                IconButton(onClick = onBackPressClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "icon",
                        tint = Color.Black
                    )
                }
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = actionClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = "icon",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}