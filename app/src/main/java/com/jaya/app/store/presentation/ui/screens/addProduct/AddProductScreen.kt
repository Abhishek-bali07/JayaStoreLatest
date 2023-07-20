package com.jaya.app.store.presentation.ui.screens.addProduct

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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.presentation.states.resourceImage
import com.jaya.app.store.presentation.ui.custom_composable.StrickyButton
import com.jaya.app.store.presentation.ui.view_models.AddProductViewModel
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel
import kotlinx.coroutines.flow.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    addProductViewModel: AddProductViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel


){
    val scaffoldState = rememberScaffoldState()



    Scaffold(
        scaffoldState = scaffoldState
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
         TopAppBar(
                backgroundColor = Color(0xffFFEB56),
                elevation = 2.dp, title = {
                    Text(
                        "Add Product", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                            color = Color.Black, fontSize = 20.sp,
                        )
                    )
                },
               navigationIcon = {
                   IconButton(onClick = {
                         addProductViewModel.appNavigator.tryNavigateBack()
                   }) {
                       Icon( modifier = Modifier
                           .size(40.dp)
                           .padding(horizontal = 8.dp),
                           painter = R.drawable.backbutton.resourceImage(),
                           contentDescription ="" )
                   }
               })
            
            AddProductSection(addProductViewModel, baseViewModel)
    }

    }
}

@Composable
fun AddProductSection(
    addProductViewModel: AddProductViewModel,
    baseViewModel: BaseViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),)
        {
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
                        if (addProductViewModel.selectedVendor.value != null) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = 3.dp, horizontal = 10.dp
                                ),
                                text = addProductViewModel.selectedVendor.value!!.vendorName,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 13.sp,
                                ),
                            )
                        }
                        else {
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 10.dp
                                    ),

                                text = "Select Vendor",
                                style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                ),
                            )
                        }


                        IconButton(onClick = {
                            addProductViewModel.isSourceExpanded.value =
                                !addProductViewModel.isSourceExpanded.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = addProductViewModel.isSourceExpanded.value,
                        onDismissRequest = { addProductViewModel.isSourceExpanded.value = false }) {

                        addProductViewModel.vendorDetails.collectAsState().value.forEach { vendor ->
                            DropdownMenuItem(onClick = {

                                addProductViewModel.selectedVendor.value = vendor
                                addProductViewModel.isSourceExpanded.value = false
                            }) {
                                Text(text = vendor.vendorName)
                            }

                        }

                    }
                }
            }


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
                        if (addProductViewModel.selectedItem.value != null) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = 3.dp, horizontal = 10.dp
                                ),
                                text = addProductViewModel.selectedItem.value!!.stockName,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 13.sp,
                                ),
                            )
                        }
                        else {
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 10.dp
                                    ),

                                text = "Select Stock",
                                style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                ),
                            )
                        }


                        IconButton(onClick = {
                            addProductViewModel.isItemExpanded.value =
                                !addProductViewModel.isItemExpanded.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                            )
                        }
                    }

                    DropdownMenu(modifier = Modifier.width(150.dp),
                        expanded = addProductViewModel.isItemExpanded.value,
                        onDismissRequest = { addProductViewModel.isItemExpanded.value = false },

                        ) {


                        addProductViewModel.stockDetails.collectAsState().value.forEach { stock ->
                            DropdownMenuItem(onClick = {
                                addProductViewModel.selectedItem.value = stock
                                addProductViewModel.isItemExpanded.value = false
                            }) {
                                Text(text = stock.stockName)
                            }

                        }

                    }
                }
            }


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .size(height = 55.dp, width = 300.dp),
                value =addProductViewModel.productRate.value,
                placeholder = {
                    Text(
                        text = "Enter Rate", style = TextStyle(
                            color = Color.Gray.copy(alpha = 0.2f)

                        )
                    )
                },
                trailingIcon = {},
                onValueChange = addProductViewModel::onChangeRate,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor =  Color.LightGray,
                )
            )



            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 10.dp)
                    .size(height = 55.dp, width = 300.dp),
                value =addProductViewModel.stockItem.value,
                placeholder = {
                    Text(
                        text = "Number of stock", style = TextStyle(
                            color = Color.Gray.copy(alpha = 0.2f)

                        )
                    )
                },
                trailingIcon = {},
                onValueChange = addProductViewModel::onChangeStck,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor =  Color.LightGray,
                )
            )
        }

        StrickyButton(
            enable = addProductViewModel.enableBtn.value,
            loading = addProductViewModel.addLoading.value,
            action = addProductViewModel::uploadProductData,
            name = R.string.addNow)
    }



}
