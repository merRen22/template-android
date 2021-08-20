package com.challenge.android_template.baseui

import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * TopAppBar for screens that need a back button.
 *
 * @param onUpPress function to be called when the back/up button is clicked
 */
@Composable
fun GenericToolbar(onUpPress: () -> Unit) {
  TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
    IconButton(onClick = onUpPress, modifier = Modifier.align(Alignment.CenterVertically)) {
      Icon(
        imageVector = Icons.Rounded.ArrowBack,
        contentDescription = stringResource(id = R.string.back_arrow_content_description)
      )
    }
  }
}
