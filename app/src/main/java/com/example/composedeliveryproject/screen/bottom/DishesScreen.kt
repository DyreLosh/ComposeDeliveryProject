package com.example.composedeliveryproject.screen.bottom

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composedeliveryproject.https.ApiService
import com.example.composedeliveryproject.https.model.Dishes
import com.example.composedeliveryproject.navigation.DishesScr
import com.example.composedeliveryproject.ui.theme.Orange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun DishesScreen(dishesController: NavHostController) {
    val context = LocalContext.current
    val list = remember { mutableStateListOf<Dishes>() }

    ApiService.retrofit.getDishes("1.01")
        .enqueue(object : Callback<List<Dishes>> {
            override fun onResponse(
                call: Call<List<Dishes>>,
                response: Response<List<Dishes>>
            ) {
                if (response.isSuccessful) {
                    list.addAll(response.body()!!)

                } else {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Dishes>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(
            list
        ) { index, item ->
            CardDishes(dishes = item, dishesController)
        }
    }
}

@Composable
fun CardDishes(dishes: Dishes, dishesController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .height(250.dp)
    ) {

        Card(
            shape = RoundedCornerShape(20.dp), modifier = Modifier
                .padding(20.dp)
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(220.dp)
                .clickable {

                }
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = dishes.nameDish,
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp), textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(50.dp))


            }
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://food.madskill.ru/up/images/${dishes.icon}")
                .crossfade(true)
                .build(), contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = dishes.price,
            color = Orange,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 10.dp)
                .padding(bottom = 30.dp),
            textAlign = TextAlign.Center
        )

    }
}