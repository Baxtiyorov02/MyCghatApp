package com.example.mychatapp.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mychatapp.core.LoadingBox
import com.example.mychatapp.core.TextView
import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.presentation.auth.components.AuthTextField
import com.example.mychatapp.presentation.home.HomeViewModel
import com.example.mychatapp.presentation.profile.components.ProfileInfoItem
import com.example.mychatapp.ui.theme.BgColor
import com.example.mychatapp.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextView(
                        value = "ChatApp",
                        fonSize = 22,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                )
            )
        },
        containerColor = BgColor,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp)
        ) {
            when (val user = state.loggedInUser) {
                is NetworkResponse.Failure -> {
                    TextView(
                        value = user.error,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                is NetworkResponse.Success -> {
                    val userData = user.data
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .padding(top = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ProfileInfoItem(
                                    name = userData.name,
                                    email = userData.email
                                )
                            }
                        }

                        AuthTextField(
                            value = state.name,
                            onValueChange = {
                                viewModel.setName(it)
                            },
                            label = "Update your name",
                            leadingIcon = Icons.Default.Person,

                            )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                viewModel.updateName(state.name)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainColor
                            )
                        ) {
                            Text(text = "Save", fontWeight = FontWeight.Bold)
                        }
                    }

                }

                else -> null
            }
            if (state.loggedInUser is NetworkResponse.Loading) {
                LoadingBox()
            }
        }
    }
}