package com.example.stockapp.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
                        border = BorderStroke(1.dp, Accent), // You can customize the border if needed
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

        /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
