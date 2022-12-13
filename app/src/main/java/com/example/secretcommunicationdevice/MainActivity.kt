package com.example.secretcommunicationdevice

import android.bluetooth.BluetoothManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LeakAdd
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.secretcommunicationdevice.bluetooth.BluetoothService
import com.example.secretcommunicationdevice.communication.Communication
import com.example.secretcommunicationdevice.layout.BottomNavigation
import com.example.secretcommunicationdevice.layout.NavigationGraph
import com.example.secretcommunicationdevice.services.static.ReactiveService
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.example.secretcommunicationdevice.communication.ThreadState
import com.example.secretcommunicationdevice.loader.Loader
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as SecretCommunicationDeviceApplication).container

        DependencyInjector.initialize(appContainer)
        appContainer.identifierService.initializeIdentity(this@MainActivity)
        appContainer.templateService.initializeTemplates(this@MainActivity)
        BluetoothService.initialize(this@MainActivity, getSystemService(BluetoothManager::class.java))

        val communication = Communication(this@MainActivity, ReactiveService.id.value)
//        LaunchedEffect {
//
//        }

        setContent {
            MaterialTheme {
                fun onFloatingActionButton() {
                    when (ReactiveService.activeRoute.value) {
                        "templates" -> TODO()
                        "communicate" -> TODO()
                        "peers" -> TODO()
                        "history" -> TODO()
                    }
                }
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(communication.threadState) {
                    communication.threadState.collect {
                        if (it == ThreadState.PeerNotFound) {
                            scaffoldState.snackbarHostState.showSnackbar("peer_not_found")
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            // onFloatingActionButton()
                        }) {
                            Icon(Icons.Filled.Add, contentDescription = "Localized description")
                        }
                    },

                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true,
                    topBar = {
                        TopAppBar {
                            val value by ReactiveService.id.collectAsState()
                            Text(text = value.toString())
                            Spacer(Modifier.weight(1f))
                            IconButton(onClick = {
                                val threadState = communication.threadState.value
                                if (threadState == ThreadState.NotStarted) {
                                    scope.launch {
                                        communication.initializeClient()
                                    }
                                }
                            }) {
                                val threadState by communication.threadState.collectAsState()
                                when (threadState) {
                                    ThreadState.PeerNotFound -> Icon(imageVector = Icons.Default.LeakAdd, "")
                                    ThreadState.NotStarted -> Icon(imageVector = Icons.Default.LeakAdd, "")
                                    ThreadState.FindingPeer -> Loader()
                                }
                            }
                        }
                    },
                    bottomBar = {BottomNavigation(navController)}
                ) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}



