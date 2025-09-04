package com.example.mychatapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mychatapp.core.TextView
import com.example.mychatapp.R
import com.example.mychatapp.core.LoadingBox
import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.presentation.home.components.UserCard
import com.example.mychatapp.ui.theme.BgColor
import com.example.mychatapp.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    navToChat: (String, String) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextView(
                        value = "ChatApp", fonSize = 22, color = Color.White
                    )
                }, actions = {
                    Image(
                        painter = painterResource(R.drawable.ic_logout),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(CircleShape)
                            .clickable {
                                viewModel.signOut()
                                popBackStack.invoke()
                            },
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                )
            )
        }, containerColor = BgColor, modifier = modifier
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (val response = state.users) {
                is NetworkResponse.Failure -> {

                }

                NetworkResponse.Idle -> {

                }

                NetworkResponse.Loading -> {
                    item {
                        LoadingBox()
                    }
                }

                is NetworkResponse.Success -> {
                    items(response.data) { model ->
                        UserCard(
                            user = model, navToChat = {
                                navToChat.invoke(model.name,model.id)
                            })
                    }
                }
            }
        }
    }
}