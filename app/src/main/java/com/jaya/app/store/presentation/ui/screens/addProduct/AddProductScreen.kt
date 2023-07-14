package com.jaya.app.store.presentation.ui.screens.addProduct

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.resourceImage
import com.jaya.app.store.presentation.ui.view_models.AddProductViewModel
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import com.jaya.app.store.presentation.ui.custom_composable.StrickyButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    addProductViewModel: AddProductViewModel = hiltViewModel(),

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

                   }) {
                       Icon( modifier = Modifier
                           .size(40.dp)
                           .padding(horizontal = 8.dp),
                           painter = R.drawable.backbutton.resourceImage(),
                           contentDescription ="" )
                   }
               })
            
            AddProductSection(addProductViewModel)
    }

    }
}

@Composable
fun AddProductSection(addProductViewModel: AddProductViewModel) {
    Column( modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Bottom )
    {
        val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp

        Column(modifier = Modifier.fillMaxWidth(),)
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
                        if (addProductViewModel.productVendor.isNotEmpty()) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = 3.dp, horizontal = 10.dp
                                ),
                                text = addProductViewModel.vendorSelectedText.value,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 13.sp,
                                ),
                            )
                        } else {
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
                        onDismissRequest = { addProductViewModel.isSourceExpanded.value = false },

                        ) {


                        addProductViewModel.productVendor.forEach { text ->
                            DropdownMenuItem(onClick = {
                                addProductViewModel.vendorSelectedText.value = text
                                addProductViewModel.isSourceExpanded.value = false
                            }) {
                                Text(text = text)
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
                        if (addProductViewModel.selectedItem.isNotEmpty()) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = 3.dp, horizontal = 10.dp
                                ),
                                text = addProductViewModel.itemSelectedText.value,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 13.sp,
                                ),
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 10.dp
                                    ),

                                text = "Select Item",
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

                    DropdownMenu(
                        expanded = addProductViewModel.isItemExpanded.value,
                        onDismissRequest = { addProductViewModel.isItemExpanded.value = false },

                        ) {


                        addProductViewModel.selectedItem.forEach { text ->
                            DropdownMenuItem(onClick = {
                                addProductViewModel.itemSelectedText.value = text
                                addProductViewModel.isItemExpanded.value = false
                            }) {
                                Text(text = text)
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

        Surface(modifier = Modifier.fillMaxWidth()) {
            StrickyButton(
                enable = addProductViewModel.enableBtn.value,
                loading = addProductViewModel.addLoading.value,
                action = {},
                name = R.string.addNow)
        }
    }



}
