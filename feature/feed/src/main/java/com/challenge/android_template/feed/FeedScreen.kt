package com.challenge.android_template.feed

import androidx.compose.foundation.Image
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.challenge.android_template.model.Foo

/**
 * Feed screen.
 */
@Composable
fun Feed(
  foodViewModel: FeedViewModel
) {
  val uiState by foodViewModel.fooItems.collectAsState()

  Scaffold(
    topBar = { FeedTopBar() },
    content = {
      FeedFooContent(
        feedUiState = uiState,
        onFooSelected = {

        },
        modifier = Modifier.fillMaxSize(),
      )
    }
  )
}

@Composable
fun FeedTopBar() {
  TopAppBar(
    title = { R.string.app_name },
    backgroundColor = MaterialTheme.colors.surface,
    elevation = 0.dp,
  )
}

@Composable
fun FeedFooContent(
  feedUiState: FeedUiState,
  onFooSelected: (Foo) -> Unit,
  modifier: Modifier
) {
  LazyColumn(modifier = modifier) {

    item {
      FooHeading(
        text = "Some random text",
        modifier = Modifier
          .padding(start = 16.dp)
      )
    }

    if (feedUiState.isLoading) {
      item {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.fillMaxWidth()
        ) {
          CircularProgressIndicator(
            modifier = Modifier.padding(top = 16.dp)
          )
        }
      }
    } else {
      items(feedUiState.foos) { foo ->
        FooItem(
          foo = foo,
          onClick = onFooSelected,
          modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        )
      }
    }

  }

}

@Composable
fun FooHeading(
  text: String,
  modifier: Modifier = Modifier
) {
  Text(
    text = text,
    style = MaterialTheme.typography.subtitle1,
    modifier = modifier
  )
}

@Composable
fun FooItem(
  foo: Foo,
  onClick: (Foo) -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = Modifier.clickable(
      enabled = true,
      onClick = { onClick(foo) }
    )
  ) {
    Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {

      FooImage(
        modifier = Modifier.requiredSize(70.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      FooName(
        modifier = Modifier
          .weight(1f)
          .padding(end = 8.dp),
        foo = foo
      )
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        IconButton(onClick = { /*TODO*/ }) {
          Icon(
            painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
            contentDescription = "More icon"
          )
        }
      }
    }
  }
}

@Composable
fun FooName(
  modifier: Modifier = Modifier,
  foo: Foo
) {
  Column(modifier = modifier) {
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = foo.name,
      style = MaterialTheme.typography.body1,
      maxLines = 1
    )
    Spacer(modifier = Modifier.height(4.dp))
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
      Text(
        text = foo.name,
        style = MaterialTheme.typography.body2,
      )
    }
  }
}

@Composable
fun FooImage(
  modifier: Modifier = Modifier,
  shape: RoundedCornerShape = RoundedCornerShape(4.dp),
  elevation: Dp = 0.dp
) {
  Surface(shape = shape, elevation = elevation) {
    Image(
      painter = rememberImagePainter(
        data = "https://picsum.photos/200/300",
        builder = {
          crossfade(true)
          error(R.drawable.ic_launcher_foreground)
          placeholder(R.drawable.ic_launcher_foreground)
        }
      ),
      contentDescription = "Foo Image",
      modifier = modifier,
      contentScale = ContentScale.Crop,
      alignment = Alignment.TopCenter
    )
  }
}