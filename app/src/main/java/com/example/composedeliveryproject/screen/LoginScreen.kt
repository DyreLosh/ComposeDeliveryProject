package com.example.composedeliveryproject.screen

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedeliveryproject.R
import com.example.composedeliveryproject.https.ApiService
import com.example.composedeliveryproject.https.model.Token
import com.example.composedeliveryproject.https.model.UserLogin
import com.example.composedeliveryproject.navigation.MainScreen
import com.example.composedeliveryproject.ui.theme.Back
import com.example.composedeliveryproject.ui.theme.Orange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection
import kotlin.math.log

@Composable
fun LoginScreen(mainController: NavHostController) {
    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val login = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxSize()
                .background(Back)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier.padding(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Password", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
            TextField(
                value = email.value,
                onValueChange = { new -> email.value = new },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = { Text(text = "dyrelsoh@gmail.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )



            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Login", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
            TextField(
                value = password.value,
                onValueChange = { new -> password.value = new },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = { Text(text = "********") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )



            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Email", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
            TextField(
                value = login.value,
                onValueChange = { new -> login.value = new },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = { Text(text = "dfsdsdf") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
        Button(
            onClick = {
                when {
                    !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
                        AlertBuilder("Введите правильную почту", context)
                    }
                    login.value.isEmpty() || password.value.isEmpty() -> {
                        AlertBuilder("Поля пустые", context)
                    }
                    else -> {
                        ApiService.retrofit.userLogin(
                            UserLogin(
                                email.value,
                                password.value,
                            )
                        ).enqueue(object : Callback<Token> {
                            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                                when (response.code()) {
                                    HttpsURLConnection.HTTP_OK -> {
                                        mainController.navigate(MainScreen.MainStartScreen.route)
                                    }
                                    HttpsURLConnection.HTTP_BAD_REQUEST -> {
                                        Toast.makeText(
                                            context,
                                            "Проблемы аунтефикации",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                                        Toast.makeText(
                                            context,
                                            "Пользователь не найден",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<Token>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Login", modifier = Modifier.padding(vertical = 15.dp))
        }
    }
}

fun AlertBuilder(text: String, context: Context) {
    android.app.AlertDialog.Builder(context).setTitle("Ошибка").setMessage(text).create().show()
}