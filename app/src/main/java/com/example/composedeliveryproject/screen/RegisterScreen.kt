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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedeliveryproject.R
import com.example.composedeliveryproject.https.ApiService
import com.example.composedeliveryproject.https.model.Token
import com.example.composedeliveryproject.https.model.UserCreate
import com.example.composedeliveryproject.https.model.UserLogin
import com.example.composedeliveryproject.navigation.MainScreen
import com.example.composedeliveryproject.ui.theme.Back
import com.example.composedeliveryproject.ui.theme.Orange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

@Composable
fun RegisterScreen(mainController: NavHostController) {
    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val login = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

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
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Login", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
            TextField(
                value = login.value,
                onValueChange = { new -> login.value = new },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = { Text(text = "dyrelsoh") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )



            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Email", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
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
            Text(text = "Password", color = Color.Black, modifier = Modifier.padding(start = 20.dp))
            TextField(
                value = password.value,
                onValueChange = { new -> password.value = new },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = { Text(text = "*****") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )


            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Confirm Password",
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp)
            )
            TextField(
                value = confirmPassword.value,
                onValueChange = { new -> confirmPassword.value = new },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = { Text(text = "*****") },
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
                    login.value.isEmpty() || password.value.isEmpty() || confirmPassword.value.isEmpty() -> {
                        AlertBuilder("Поля пустые", context)
                    }
                    password.value != confirmPassword.value -> {
                        AlertBuilder("Пароли не совпадают", context)
                    }
                    else -> {
                        ApiService.retrofit.userCreate(
                            UserCreate(
                                email.value,
                                password.value,
                                login.value
                            )
                        ).enqueue(
                            object : Callback<Unit> {
                                override fun onResponse(
                                    call: Call<Unit>,
                                    response: Response<Unit>
                                ) {
                                    if (response.isSuccessful) {
                                        mainController.navigate(MainScreen.LoginScreen.route)
                                        Toast.makeText(
                                            context,
                                            "Успешно зарегистрировано",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Проблемы при регистрации",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<Unit>, t: Throwable) {
                                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                                }

                            }
                        )
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
