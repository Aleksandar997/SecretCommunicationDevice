package com.example.secretcommunicationdevice.components.peers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.secretcommunicationdevice.DependencyInjector
import com.example.secretcommunicationdevice.models.Peer

var Peers: @Composable () -> Unit = {
    val communicationService = DependencyInjector.getInstance().services.communicationService
    val peersViewModel: PeersViewModel = viewModel(
        factory = PeersViewModel.provideFactory(communicationService)
    )
    _Peers(peersViewModel)
}

private var _Peers: @Composable (peersViewModel: PeersViewModel) -> Unit = {
//    val coroutineScope = rememberCoroutineScope()
    var peers: List<Peer> = emptyList()

//    val homeViewModel: PeersViewModel = viewModel(
//        factory = PeersViewModel.provideFactory()
//    )

    var selectedItem by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
        )  {
            // Add 5 items
            items(10) { index ->
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
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = index.toString())

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
    }
}