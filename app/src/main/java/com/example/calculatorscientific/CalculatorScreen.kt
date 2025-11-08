package com.example.calculatorscientific

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorscientific.ui.theme.CalculatorViewModel
import com.example.calculatorscientific.ui.theme.ManropeFamily

val standardButtonList  = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "AC", "0", ".", "="
)

val scientificButtonList = listOf(
    "sin", "cos", "tan", "rad", "deg",
    "log", "ln", "(", ")", "inv",
    "!", "AC", "%", "C", "/",
    "√", "7", "8", "9", "*",
    "^", "4", "5", "6", "+",
    "π", "1", "2", "3", "-",
    "e", "00", "0", ".", "="
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel,
    onOpenDrawer: () -> Unit
) {
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
//            .padding(innerPadding)
            .background(color = Color(0xFF1E2732))
    ) {
        // Display Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = equationText.value ?: "",
                style = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.End,
                    color = Color.White.copy(alpha = 0.7f)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = resultText.value ?: "",
                style = TextStyle(
                    fontSize = 48.sp,
                    textAlign = TextAlign.End,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        var selectedTab by remember { mutableStateOf("Standard") }

        TabRow(
            selectedTabIndex = if (selectedTab == "Standard") 0 else 1,
            containerColor = Color(0xFF1E2732),
            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                text = { Text("Standard") },
                selected = selectedTab == "Standard",
                onClick = { selectedTab = "Standard" }
            )
            Tab(
                text = { Text("Scientific") },
                selected = selectedTab == "Scientific",
                onClick = { selectedTab = "Scientific" }
            )
        }
        Box(
            modifier = Modifier
                .height(520.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if (selectedTab == "Standard") {
                StandardCalculatorGrid(viewModel = viewModel)
            } else {
                ScientificCalculatorGrid(viewModel = viewModel)
            }
        }
    }

//    Scaffold(
//        topBar = {
//            CalculatorTopAppBar(
//                onMenuClick = {
//                    onOpenDrawer
//                }
//            )
//        },
//        containerColor = Color(0xFF1E2732)
//    ) { innerPadding ->
//
//    }

}

@Composable
fun StandardCalculatorGrid(viewModel: CalculatorViewModel) {
    // Standard Calculator - 20 buttons (4x5 grid)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val rows = standardButtonList.chunked(4)

        rows.forEach { rowButtons ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowButtons.forEach { button ->
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        CalculatorButton(
                            button = button,
                            onClick = { viewModel.onButtonClick(button) },
                            isScientific = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScientificCalculatorGrid(viewModel: CalculatorViewModel) {
    // Scientific Calculator - 35 buttons (5x7 grid)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val rows = scientificButtonList.chunked(5)

        rows.forEach { rowButtons ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowButtons.forEach { button ->
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        CalculatorButton(
                            button = button,
                            onClick = { viewModel.onButtonClick(button) },
                            isScientific = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    button: String,
    onClick: () -> Unit,
    isScientific: Boolean
) {
    val buttonColor = when {
        button in listOf("C", "AC") -> Color(0xFFFF9500)
        button in listOf("=", "+", "-", "×", "÷", "/", "*") -> Color(0xFF007AFF)
        button in listOf("sin", "cos", "tan", "log", "ln", "√", "π", "e", "x²", "x'", "!", "inv", "rad", "deg") ->
            Color(0xFF505050)
        else -> Color(0xFF505050)
    }

    val textColor = Color.White
    val fontSize = if (isScientific) {
        when {
            button.length <= 2 -> 16.sp
            button.length == 3 -> 14.sp
            else -> 12.sp
        }
    } else {
        20.sp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .shadow(4.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(buttonColor)
            .clickable(
                indication = rememberRipple(),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
//            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = button,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTopAppBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit
) {
    SmallTopAppBar(
        title = {
            Text(
                "Calculator",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = ManropeFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    tint = Color.White,
                    contentDescription = "Menu",
                    modifier = Modifier.size(34.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    tint = Color.White,
                    contentDescription = "Calculate",
                    modifier = Modifier.size(34.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1E2732)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    onBackClick: () -> Unit,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFF101922))
            .padding(16.dp)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Unit Converters",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFF101922)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(

        ) {
            items(menuItems) { items ->
                MenuListItem(
                    item = items,
                    onItemClick = {
                        onItemSelected(items.name)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun MenuListItem(
    item: MenuItems,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF18212D), shape = RoundedCornerShape(16.dp))
//            .clickable { onItemClick() }
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onItemClick()
            }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon di kiri
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF2196F3), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.name,
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }

        // Nama converter
        Text(
            text = item.name,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )

        // Chevron kanan
        Icon(
            imageVector = Icons.Outlined.ArrowForwardIos,
            contentDescription = "Go",
            tint = Color.Gray
        )
    }
}

@Preview
@Composable
fun cobaprev(modifier: Modifier = Modifier) {
//    val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
//    CobaCalculator()
}