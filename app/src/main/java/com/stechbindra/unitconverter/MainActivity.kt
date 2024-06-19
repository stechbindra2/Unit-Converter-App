package com.stechbindra.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stechbindra.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf((false)) }
    var oExpanded by remember { mutableStateOf((false)) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oconversionFcator = remember{ mutableStateOf(1.0) }


    fun UnitConverters(){
        //?: -> is Elvish operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100 / oconversionFcator.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Here all the element of UI will be stacked below each other
        Text(text = "Unit Converter", modifier = Modifier.height(50.dp), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            UnitConverters()
      //here goes what should happen, When the value of our OutLineTextFiled changes
        },label = {Text("Enetr value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
//            val context = LocalContext.current
//            Button(onClick = {
//                Toast.makeText(context, "Thanks for clicking", Toast.LENGTH_LONG).show() }) {
//                Text(text = "Click Me!")
//            }
            //Input box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeter"
                        conversionFactor.value = 0.01
                        UnitConverters()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        UnitConverters()
                    })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feets"
                        conversionFactor.value = 0.3048
                        UnitConverters()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Millimeters"
                        conversionFactor.value = 0.001
                        UnitConverters()
                    })
                    
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output box
            Box {
                //Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oconversionFcator.value = 0.01
                        UnitConverters()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oconversionFcator.value = 1.00
                        UnitConverters()
                    })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feets"
                        oconversionFcator.value = 0.3048
                        UnitConverters()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeters"
                        oconversionFcator.value = 0.001
                        UnitConverters()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result text
        Text(text = "Result : ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
     UnitConverter()
}