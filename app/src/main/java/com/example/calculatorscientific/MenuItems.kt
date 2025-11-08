package com.example.calculatorscientific

//import android.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AspectRatio
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.HorizontalRule
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItems(
    val name: String,
    val icon: ImageVector,
    val color : Color
)

val menuItems = listOf(
    MenuItems(
        "Currency",
        Icons.Outlined.CurrencyExchange,
        Color(0xFF000000)
    ),
    MenuItems(
        "Length",
        Icons.Outlined.HorizontalRule,
        Color(0xFF000000)
    ),
    MenuItems(
        "Area",
        Icons.Outlined.AspectRatio,
        Color(0xFF000000)
    ),MenuItems(
        "Volume",
        Icons.Outlined.Square,
        Color(0xFF000000)
    ),MenuItems(
        "Weight",
        Icons.Outlined.Scale,
        Color(0xFF000000)
    ),MenuItems(
        "Temperature",
        Icons.Outlined.Thermostat,
        Color(0xFF000000)
    ),MenuItems(
        "Speed",
        Icons.Outlined.NetworkCheck,
        Color(0xFF000000)
    ),MenuItems(
        "Pressure",
        Icons.Outlined.Speed,
        Color(0xFF000000)
    ),MenuItems(
        "Power",
        Icons.Outlined.Bolt,
        Color(0xFF000000)
    ),MenuItems(
        "Binary",
        Icons.Outlined.Code,
        Color(0xFF000000)
    ),MenuItems(
        "Energy",
        Icons.Outlined.LocalFireDepartment,
        Color(0xFF000000)
    ),MenuItems(
        "Data",
        Icons.Outlined.Dataset,
        Color(0xFF000000)
    ),
    MenuItems(
        "Angle",
        Icons.Outlined.TextRotationAngleup,
        Color(0xFF000000)
    )
)
