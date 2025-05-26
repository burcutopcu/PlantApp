package com.burcutopcu.plantapp.ui.features.home

import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.burcutopcu.plantapp.R
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.ui.features.home.components.CategoryCard
import com.burcutopcu.plantapp.ui.features.home.components.QuestionCard
import com.burcutopcu.plantapp.ui.navigation.Navigator

@Composable
fun HomeScreen(
    navigator: Navigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is HomeState.Loading -> {
            AndroidView(
                factory = { context ->
                    LayoutInflater.from(context).inflate(R.layout.loading_screen, null)
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        is HomeState.Error -> {
            AndroidView(
                factory = { context ->
                    LayoutInflater.from(context).inflate(R.layout.error_screen, null)
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        is HomeState.Success -> {
            HomeContent(
                categories = (uiState as HomeState.Success).categories,
                questions = (uiState as HomeState.Success).questions,
            )
        }
    }
}

@Composable
fun HomeContent(
    categories: List<CategoryItemEntity>,
    questions: List<QuestionEntity>,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_search_background),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.home_welcome_text),
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.home_good_afternoon),
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.home_cloud),
                            fontSize = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(
                            Color.White.copy(alpha = 0.9f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(R.string.home_search_placeholder),
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable { /* Premium action */ },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2D2D2D)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_message),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(R.string.home_premium_available_title),
                                color = Color(0xFFE4B046),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = stringResource(R.string.home_premium_available_description),
                                color = Color(0xFFE4B046),
                                fontSize = 12.sp
                            )
                        }

                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_forward),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                }
            }

            item {
                if (questions.isNotEmpty()) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp)
                    ) {
                        items(questions) { question ->
                            QuestionCard(
                                question = question,
                                onClick = { /* Question detail */ }
                            )
                        }
                    }
                }
            }

            item {
                if (categories.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.height(400.dp)
                    ) {
                        items(categories) { category ->
                            CategoryCard(
                                category = category,
                                onClick = { /* Category detail */ }
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
