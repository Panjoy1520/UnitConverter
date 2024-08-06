package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier) {

    var InputValue by remember { mutableStateOf("") }
    var OutputValue by remember { mutableStateOf("") }
    var InputUnit by remember { mutableStateOf("Meters") }
    var OutputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val ConversionFactor= remember { mutableStateOf(1.00) }
    val oConversionFactor= remember { mutableStateOf(1.00) }

    fun convertunits(){

        val InputValueDouble =InputValue.toDoubleOrNull() ?: 0.0
        val result = (InputValueDouble * ConversionFactor.value *100 /oConversionFactor.value).roundToInt()/100.0
        OutputValue=result.toString()

    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        Text(text = "UNIT CONVERTER",style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = InputValue, onValueChange = {InputValue=it
                                                              convertunits()},
            label = { Text(text = "Enter the Value")})

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
            Box {
                //inout button
                Button(onClick = {iExpanded=true}) {
                    Text(text = InputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded=false}) {
                    //items of the drop down menu
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {iExpanded=false;InputUnit="Centimeters";ConversionFactor.value=0.01;convertunits()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {iExpanded=false;InputUnit="Meters";ConversionFactor.value=1.0;convertunits()})
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {iExpanded=false;InputUnit="Feet";ConversionFactor.value=0.3048;convertunits()})
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {iExpanded=false;InputUnit="Millimeters";ConversionFactor.value=0.001;convertunits()})
                }


            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                Button(onClick = {oExpanded=true}) {
                    Text(text = OutputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                    DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded=false}) {
                        //items of the drop down menu
                        DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {oExpanded=false;OutputUnit="Centimeters";oConversionFactor.value=0.01;convertunits()})
                        DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {oExpanded=false;OutputUnit="Meters";oConversionFactor.value=1.00;convertunits()})
                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {oExpanded=false;OutputUnit="Feet";oConversionFactor.value=0.3048;convertunits()})
                        DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {oExpanded=false;OutputUnit="Millimeters";oConversionFactor.value=0.001;convertunits()})


                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "RESULT: $OutputValue $OutputUnit",
            style = MaterialTheme.typography.headlineSmall)
    }
   
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter(modifier = Modifier)
}