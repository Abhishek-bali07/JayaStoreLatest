package com.jaya.app.store.presentation.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jaya.app.store.navigation.MainNavGraph
import com.jaya.app.store.presentation.theme.JayaStoreLatestTheme
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    private val baseViewModel by viewModels<BaseViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           JayaStoreLatestTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,

                    ) { paddingValues ->
                    MainNavGraph(
                        navHostController = rememberNavController(),
                        navigationChannel = baseViewModel.appNavigator.navigationChannel,
                        paddingValues = paddingValues,
                        baseViewModel = baseViewModel
                    )
                }
            }
        }
    }

}