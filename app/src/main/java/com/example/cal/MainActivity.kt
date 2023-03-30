package com.example.cal


import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cal.ui.theme.CalTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipCalculatorScreen()
                }
            }
        }
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0
): Double {
    return tipPercent / 100 * amount
}

private fun randomNumber(): Double {
    return 1 + Math.random() * 200
}

@Composable
fun TipCalculatorScreen() {
    var serviceCostAmountInput by remember { mutableStateOf("") }
    val amount = serviceCostAmountInput.toDoubleOrNull() ?: 0.0
    var tip by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.tip_calculator_heading),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                serviceCostAmountInput = String.format("%.2f", randomNumber())
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(39, 210, 236))
        ) {
            Text(text = "Generate!")
        }

        EditServiceCostField(
            value = serviceCostAmountInput,
            onValueChange = { serviceCostAmountInput = it }
        )

        Spacer(Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = {
                        tip = calculateTip(amount, 10.0)
                    },
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(39, 210, 236))
                ) {
                    Text(text = "10%")
                }
                Button(
                    onClick = {
                        tip = calculateTip(amount, 15.0)
                    },
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(39, 210, 236))
                ) {
                    Text(text = "15%")
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = {
                        tip = calculateTip(amount, 20.0)
                    },
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(39, 210, 236))
                ) {
                    Text(text = "20%")
                }
                Button(
                    onClick = {
                        tip = calculateTip(amount, 15.0)
                    },
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(39, 210, 236))
                ) {
                    Text(text = "25%")
                }
            }
        }

        Column(
            modifier = Modifier.padding(32.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = stringResource(R.string.bill_amount, amount),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.tip_amount, String.format("%.2f", tip)),
                modifier = Modifier.align(Alignment.End),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Divider(color = Color.DarkGray, thickness = 1.dp)
            Text(
                text = stringResource(R.string.total, String.format("%.2f", (tip + amount))),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun EditServiceCostField(
    value: String,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.service_cost)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalTheme {
        TipCalculatorScreen()
    }
}