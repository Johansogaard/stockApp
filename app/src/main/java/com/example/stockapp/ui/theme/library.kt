package com.example.stockapp.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Set of Material typography styles to start with
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
        value: MutableState<TextFieldValue>,
        label: String,
        isPassword: Boolean = false,
        modifier: Modifier = Modifier

) {
        OutlinedTextField(

                value = value.value,
                onValueChange = { value.value = it },
                label = { Text(label) },
                singleLine = true,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                        .fillMaxWidth()
        )
}

@Composable
fun CustomButton(
        onClick: () -> Unit,
        text: String,
        modifier: Modifier = Modifier,
        outlined: Boolean = false
) {
        if (outlined) {
                OutlinedButton(
                        onClick = onClick,
                        modifier = modifier.then(Modifier.width(350.dp).height(50.dp)),
                        border = BorderStroke(1.dp, Accent),
                        colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Accent)
                ) {
                        Text(
                                text = text,
                                style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                )
                        )
                }
        } else {
                Button(
                        onClick = onClick,
                        modifier = modifier.then(Modifier.width(350.dp).height(50.dp)),
                        colors = ButtonDefaults.buttonColors(Accent)
                ) {
                        Text(
                                text = text,
                                style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                )
                        )
                }
        }
}

@Composable
fun Stock(
        picture: String,
        text:String,
        price: String,
        perftdy:String,
        navController: NavController
) {
        Row(
                modifier = Modifier.padding(16.dp)
                        .fillMaxWidth()
                        .clickable { navController.navigate("StockViewScreen/$text") },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

        ) {
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp),

                        ) {
                        Image(
                                painter = painterResource(id = com.example.stockapp.R.drawable::class.java.getDeclaredField(picture.substringBeforeLast(".")).getInt(null)),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                        .size(46.dp)
                                        .clip(CircleShape)
                                        .background(Color.White, CircleShape)
                        )
                        Text(
                                text = text
                                ,
                                style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                ),
                        )
                }
                Column(
                        horizontalAlignment = Alignment.End

                )
                {
                        Text(
                                text="Perf. TDY",
                                style = TextStyle(
                                        fontSize = 15.sp,
                                        color = Color.Gray
                                ),
                        )
                        Text(
                                text=perftdy+"%",
                                color = if (perftdy.startsWith("-")) Color.Red else Color.Green
                                ,
                                style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                ),

                                )
                }
                Column(
                        horizontalAlignment = Alignment.End
                )
                {
                        Text(
                                text="Price"
                                ,
                                style = TextStyle(
                                        fontSize = 15.sp,
                                ),
                                color = Color.Gray
                        )
                        Text(
                                text=price,

                                style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                ),
                        )
                }
        }

        Divider(
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
        )
}

@Composable
fun Transaction(
        name: String,
        status: String,
        quantity: String,
        rate: String,
        amount: String
) {Column(
        modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
) {
        Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
                Text(
                        text = name,
                        style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                )


                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                ) {
                        Canvas(modifier = Modifier.size(12.dp)) {
                                drawCircle(
                                        color = when (status) {
                                                "sold" -> Color.Green
                                                "bought" -> Color.Blue
                                                "order" -> Color.Gray
                                                else -> Color.Gray
                                        }
                                )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                                text = status,
                                style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                )
                        )
                }
                Text(
                        text = "date",
                        style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Gray
                        ),
                )
        }


        Row(
                modifier = Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {

                        Column(
                                horizontalAlignment = Alignment.End

                        ) {
                                Text(
                                        text = "quantity",
                                        style = TextStyle(
                                                fontSize = 15.sp,
                                                color = Color.Gray
                                        ),
                                )
                                Text(
                                        text = quantity,
                                        style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                        ),
                                )
                        }

                        Column(
                                horizontalAlignment = Alignment.End

                        )
                        {
                                Text(
                                        text = "Exchange rate(DKK)",
                                        style = TextStyle(
                                                fontSize = 15.sp,
                                                color = Color.Gray
                                        ),
                                )
                                Text(
                                        text = rate,

                                        style = TextStyle(
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold
                                        ),

                                        )
                        }
                        Column(
                                horizontalAlignment = Alignment.End
                        )
                        {
                                Text(
                                        text = "Amount(DKK)",
                                        style = TextStyle(
                                                fontSize = 15.sp,
                                        ),
                                        color = Color.Gray
                                )
                                Text(
                                        text = amount,

                                        style = TextStyle(
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold
                                        ),
                                )
                        }
                }

        }
        Divider(
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
        )
}
@Composable
fun ClickableText(
        normalText: String,
        clickableText: String,
        onClick: () -> Unit,
        color: Color = Accent,
        fontSize: TextUnit = 15.sp
) {
        val context = LocalContext.current
        val annotatedString = buildAnnotatedString {
                append(normalText)
                withStyle(style = SpanStyle(color = color)) {
                        val start = length
                        append(clickableText)
                        addStringAnnotation(
                                tag = "URL",
                                annotation = "goToOtherActivity",
                                start = start,
                                end = length
                        )
                }
        }

        ClickableText(
                text = annotatedString,
                style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                ),
                onClick = { offset ->
                        annotatedString.getStringAnnotations("URL", offset, offset).firstOrNull()?.let {
                                if (it.tag == "URL" && it.item == "goToOtherActivity") {
                                        onClick.invoke()
                                }
                        }
                }
        )
}

@Composable
fun TopBarGoBack(
        title: String,
        navController: NavController
) {
        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
        ) {
                Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Go Back",
                        Modifier
                                .size(45.dp)
                                .align(Alignment.CenterStart)
                                .clickable { navController.popBackStack() }
                )

                Text(
                        text = title,
                        style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = robotoFontFamily,
                        ),
                        modifier = Modifier.align(Alignment.Center)
                )
        }
}

