package com.example.secretcommunicationdevice.components.communicate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.secretcommunicationdevice.DependencyInjector
import com.example.secretcommunicationdevice.models.Peer
import com.example.secretcommunicationdevice.services.IMorseCodeParser
import com.example.secretcommunicationdevice.services.implementation.MorseCodeParser
import com.example.secretcommunicationdevice.services.static.ReactiveService

var Communicate: @Composable () -> Unit = {
    val communicationService = DependencyInjector.getInstance().services.communicationService
    val morseCodeParser = DependencyInjector.getInstance().services.morseCodeParser
    val communicateViewModel: CommunicateViewModel = viewModel(
        factory = CommunicateViewModel.provideFactory(communicationService, morseCodeParser)
    )
    _Communicate(communicateViewModel.peers, communicateViewModel.morseCodeParse)
}

@Composable
fun _Communicate(
    peers: List<Peer>,
    morseCodeParser: IMorseCodeParser
) {
    var symbols by remember { mutableStateOf("") }
    var selectedItem by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
            modifier = Modifier.heightIn(0.dp, 240.dp)
        )  {
            // Add 5 items
            items(peers) { peer ->
                Surface(
                    shape = MaterialTheme.shapes.small,
                    border = BorderStroke(
                        width = 1.dp,
//                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .heightIn(53.dp, 53.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                onClick = {
//                                    checkedState = !checkedState
//                                    onAnswerSelected(option.value.second, checkedState)
                                }
                            )
//                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
//                        Text(text = peer..toString())

                        Checkbox(
                            checked = selectedItem,
                            onCheckedChange = { selected ->
                                selectedItem = selected
                            },
                        )
                    }
                }
            }
        }

        Row {
            OutlinedTextField(
                value = ReactiveService.text.value,
                onValueChange =
                {
                    ReactiveService.text.value = it
                    symbols = morseCodeParser.textToCode(it).map { it -> it.symbol}.filter { it -> it != "" }.joinToString("")
                },
                label = { Text("Send a message") }
            )
        }
        Row {
            Text(symbols)
        }
    }
}
