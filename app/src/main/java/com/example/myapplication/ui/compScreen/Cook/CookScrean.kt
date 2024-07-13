package com.example.myapplication.ui.compScreen.Cook

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.Navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun CookScreen(navController: NavHostController, viewModel: CookViewModel) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    val textValue1 by viewModel.ingredients.collectAsState()
    val textValue2 by viewModel.timeLimit.collectAsState()
    val onOptionSelecteds by viewModel.selected.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colors.background),
                title = {
                    Text(
                        text = stringResource(R.string.what_to_cook),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Justify

                    )
                },

                )
        },

        ) {
        var showMoreInfo by remember {
            mutableStateOf(true)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
            )
            {
                if (showMoreInfo) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.hint),
                            modifier = Modifier
                                .padding(top = 15.dp, start = 15.dp),
                            style = MaterialTheme.typography.body1
                        )
                        IconButton(onClick = { showMoreInfo = false }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "")
                        }
                    }
                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = stringResource(R.string.more_info),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Ingredients(
                ingredients = { viewModel.updateTextValue1(it) },
                value = textValue1,
                viewModel
            )
            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = stringResource(R.string.separate_with_comma),
                style = MaterialTheme.typography.body1
            )
            Spacer(Modifier.height(10.dp))
            TimeLimit(
                limitation = { viewModel.updateTextValue2(it) },
                value = textValue2.toString(),
                viewModel
            )
            Spacer(Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = stringResource(R.string.how_much_hard),
                style = MaterialTheme.typography.body1
            )
            Spacer(Modifier.height(10.dp))
            onOptionSelecteds?.let { it1 ->
                RadioButton(
                    selectedOptions = onOptionSelecteds,
                    options = { viewModel.updateRadioButton(it) },
                    optionText = it1.name
                )
            }
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                isButtonEnabled = textValue1.isNotEmpty() && textValue2.toString().isNotEmpty()
                Button(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 17.dp)
                        .fillMaxWidth()
                        .height(48.dp),
                    onClick = {
                        if (textValue1.length < 3) {
                            Toast.makeText(
                                context,
                                "تعداد کارکتر مواد اولیه بیشتر از 3 عدد باشد",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            navController.navigate(
                                Screen.CookResult.createRoute(
                                    textValue1,
                                    textValue2.toInt(),
                                    onOptionSelecteds!!.optionText
                                )
                            )
                        }

                    }, enabled = isButtonEnabled
                ) {
                    Text(
                        text = stringResource(R.string.search),
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
private fun Ingredients(
    ingredients: (String) -> Unit,
    value: String,
    viewModel: CookViewModel
) {
    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent
    )
    OutlinedTextField(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            )
            .width(344.dp),
        value = value,
        placeholder = {
            Text(
                stringResource(R.string.what_you_have),
                style = MaterialTheme.typography.body1
            )
        },
        shape = MaterialTheme.shapes.medium,
        onValueChange = { newtext -> viewModel.updateQueryIngredients(newtext) },
        colors = customTextFieldColors
    )
}

@Composable
private fun TimeLimit(
    limitation: (String) -> Unit,
    value: String,
    viewModel: CookViewModel
) {
    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent
    )
    OutlinedTextField(
        keyboardOptions =
        KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(55.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            )
            .width(300.dp),
        value = value,
        placeholder = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(R.string.how_much_time),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.minute),
                    style = MaterialTheme.typography.body1
                )
            }
        },
        shape = MaterialTheme.shapes.medium,
        onValueChange = { newtext -> viewModel.updateQueryTimeLimit(newtext) },
        colors = customTextFieldColors
    )
}

@Composable
fun RadioButton(
    selectedOptions: RadioButtonOption?,
    options: (RadioButtonOption) -> Unit,
    optionText: String
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButtonOption.values().forEach { optionText ->
            Row(modifier = Modifier
                .selectable(
                    selected = (selectedOptions == optionText),
                    onClick = {
                        options(optionText)
                    }
                )) {
                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = (
                            if (selectedOptions == optionText)
                                painterResource(id = R.drawable.check_circle_outline)
                            else
                                painterResource(id = R.drawable.check_circle_outline__1_)
                            ),
                    contentDescription = " ",
                    tint = if (selectedOptions == optionText)
                        Color.Green
                    else Color.White
                )
            }
            Text(
                text = optionText.optionText,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

enum class RadioButtonOption(val optionText: String) {
    OPTION1("مهم نیست"),
    OPTION2("آسان"),
    OPTION3("متوسط"),
    OPTION4("سخت")
}

