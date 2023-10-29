package com.example.stockapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.Screen
import com.example.stockapp.authentication.EmailAuthManager
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun BuyScreen2(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize())
    {
        BuyScreen2Layout(navController)

    }

}
@Composable
fun BuyScreen2Layout(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        Spacer(modifier = Modifier.size(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    // Add code to navigate back to the previous page
                }, modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back")

            }
            Spacer(modifier = Modifier.weight(0.75f))
            Text(
                text = "Preview Buy",
                fontSize = 20.sp, fontWeight=FontWeight.Bold ,
                modifier = Modifier.padding(end=150.dp)

            )

        }


        Spacer(modifier = Modifier.size(10.dp))


        Row (modifier = Modifier
            .width(320.dp)
            .height(48.dp)){
            Column (modifier = Modifier.padding(end=5.dp)){
                Image(
                    painter = painterResource(id = R.drawable.dk),
                    contentDescription = "stringResource(id = R.string.dog_content_description)"
                )  }
    Spacer(modifier = Modifier.weight(1f))


    Column () {

        Row {
            Text(text = "OMX Copenhagen 25")
        }
Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(text = "Index NASDAQ: OMXC25")
        }
    }
    Spacer(modifier = Modifier.weight(1f))
Column(modifier = Modifier.height(48.dp), verticalArrangement=Arrangement.Center ){
Text(text = "Buy in DKK")
}


}
        Spacer(modifier = Modifier.size(10.dp))

        Divider(thickness = 1.dp, color = Color.Gray)

        Spacer(modifier = Modifier.weight(0.5f))
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(320.dp)
                .clip(shape)
                .align(Alignment.CenterHorizontally)
                .background(Color(0xFFFAFAFA))
        )

        // Set the background color to blue


        {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            ) {
                Column() {
                    Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                        Text(text = "Market Price",            modifier = Modifier.weight(1f) // Push the text to the end
                        )

                        Text(text = "DKK 1.726.88",fontWeight = FontWeight.Bold)
                    }
                    Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                        Text(text = "Number of Shares",            modifier = Modifier.weight(1f) // Push the text to the end
                        )

                        Text(text = "5,7907903271",fontWeight = FontWeight.Bold)
                    }
                    Divider(thickness = 1.dp, color = Color.Gray)

                    Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                        Text(text = "Amount",            modifier = Modifier.weight(1f) // Push the text to the end
                        )

                        Text(text = "DKK 10.000,00",fontWeight = FontWeight.Bold)
                    }
                    Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                        Text(text = "Trading Fee",           modifier = Modifier.weight(1f) // Push the text to the end
                        )

                        Text(text = "DKK 50,00",fontWeight = FontWeight.Bold)
                    }

                    Divider(thickness = 1.dp, color = Color.Gray)


                    Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                        Text(text = "Total Cost",            modifier = Modifier.weight(1f) // Push the text to the end
                        )

                        Text(text = "DKK 10.050,00",fontWeight = FontWeight.Bold)
                    }
                }

            }


        }
        Spacer(modifier = Modifier.weight(0.5f))

        Button(
            onClick = {
                // Add code to handle the buy action
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A65E7)
            ),
            modifier = Modifier.width(320.dp) // Set the width to 320.dp
        ) {
            Text(text = "Buy Now", modifier = Modifier.padding(7.dp))
        }

        Spacer(modifier = Modifier.size(50.dp))

    }}
@Composable
@Preview
fun BuyScreen2Preview() {
    val navController = rememberNavController()
    BuyScreen2(navController)
}