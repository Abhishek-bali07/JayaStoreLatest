package com.jaya.app.store.presentation.ui.screens.issueProduct

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.resourceImage
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel
import com.jaya.app.store.presentation.ui.view_models.IssueProductViewModel

@Composable
fun IssueProductScreen(
    viewModel:IssueProductViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {paddingValues ->
       Column(modifier = Modifier
           .padding(paddingValues)
           .fillMaxSize()) {
           TopAppBar(
               backgroundColor = Color(0xffFFEB56),
               elevation = 2.dp, title = {
                   Text(
                       "Issue an item", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                           color = Color.Black, fontSize = 20.sp,
                       )
                   )
               },
               navigationIcon = {
                   IconButton(onClick = {

                   }) {
                       Icon( modifier = Modifier
                           .size(40.dp)
                           .padding(horizontal = 8.dp),
                           painter = R.drawable.backbutton.resourceImage(),
                           contentDescription ="" )
                   }
               })
           AddIssueSection(viewModel,baseViewModel)
       }
    }
}

@Composable
fun AddIssueSection(
    viewModel: IssueProductViewModel,
    baseViewModel: BaseViewModel)
{
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp

        Column(modifier = Modifier.fillMaxWidth().weight(1f),){
            val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = Modifier
                        .width(screenWidthDp * .90f)
                        .height(55.dp),

                    ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(
                            modifier = Modifier.padding(
                                vertical = 3.dp, horizontal = 10.dp
                            ),
                            text = "",
                            style = TextStyle(
                                color = Color(0xff212121),
                                fontSize = 13.sp,
                            ),
                        )
                        IconButton(onClick = {
                            viewModel.isExpandedItem.value =
                                !viewModel.isExpandedItem.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                            )
                        }
                    }

                    DropdownMenu(
                        expanded =  viewModel.isExpandedItem.value,
                        onDismissRequest = {  viewModel.isExpandedItem.value = false }) {



                    }
                }
            }
        }
    }
}
