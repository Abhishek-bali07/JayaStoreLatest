package com.jaya.app.store.presentation.ui.screens.addProduct

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.Image
import com.jaya.app.store.presentation.states.resourceImage
import com.jaya.app.store.presentation.states.statusBarColor
import com.jaya.app.store.presentation.ui.custom_composable.StrickyButton
import com.jaya.app.store.presentation.ui.view_models.AddProductViewModel
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel

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
                .statusBarColor(color = Color(0xffFFEB56))
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
    if (addProductViewModel.quotationsLoad){
           Column(
               modifier = Modifier.fillMaxSize(),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center
           ) {
               Box(
                   modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center
               ) {
                   Surface(modifier = Modifier.fillMaxSize(),
                       shape = CircleShape,
                       color = Color(0xFFF9F9F9),
                       content = {})
                   CircularProgressIndicator(
                       color = Color.Red, modifier = Modifier.fillMaxSize()
                   )
                   R.drawable.jayalogo.Image(modifier = Modifier.size(100.dp))
               }
           }
       }
       else{
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp


            val isVisible = remember {
                derivedStateOf {
                    addProductViewModel.basic.value.isEmpty()
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),)
            {
                val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp, vertical = 10.dp)
                        .size(height = 55.dp, width = 300.dp)
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                        ),
                    value =addProductViewModel.productName.value,
                    placeholder = {
                        Text(
                            text = "Enter Product Name", style = TextStyle(
                                color = Color.Gray.copy(alpha = 0.2f)

                            )
                        )
                    },
                    trailingIcon = {},
                    onValueChange = addProductViewModel::onChangeProduct,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor =  Color.LightGray,
                    )
                )


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp)
                        .size(height = 55.dp, width = 300.dp)
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                        ),
                    value =addProductViewModel.brandName.value,
                    placeholder = {
                        Text(
                            text = "Enter Brand Name", style = TextStyle(
                                color = Color.Gray.copy(alpha = 0.2f)

                            )
                        )
                    },
                    trailingIcon = {},
                    onValueChange = addProductViewModel::onChangeBrand,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor =  Color.LightGray,
                    )
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(2.dp, Color.LightGray),
                        modifier = Modifier
                            .width(screenWidthDp * .90f)
                            .height(55.dp),

                        ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (addProductViewModel.selectedSupplier.value != null) {
                                Text(
                                    modifier = Modifier.padding(
                                        vertical = 3.dp, horizontal = 10.dp
                                    ),
                                    text = addProductViewModel.selectedSupplier.value!!.supplierName,
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

                                    text = "Select Supplier",
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

                        DropdownMenu(modifier = Modifier
                            .width(screenWidthDp * .90f)
                            .padding(horizontal = 20.dp),
                            expanded = addProductViewModel.isSourceExpanded.value,
                            onDismissRequest = { addProductViewModel.isSourceExpanded.value = false }) {

                            addProductViewModel.supplierDetails.collectAsState().value.forEach { supplier ->
                                DropdownMenuItem(onClick = {

                                    addProductViewModel.selectedSupplier.value = supplier
                                    addProductViewModel.isSourceExpanded.value = false
                                }) {
                                    Text(text = supplier.supplierName)
                                }

                            }

                        }
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)){

                    OutlinedTextField(
                        modifier = Modifier
                            .width(screenWidthDp * .30f)
                            .padding(horizontal = 5.dp)
                            .height(height = 55.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                            ),
                        value =addProductViewModel.basic.value,
                        placeholder = {
                            Text(
                                text = "Basic", style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                )
                            )
                        },
                        trailingIcon = {
                            Icon(painter = R.drawable.rupee.resourceImage(),
                                contentDescription = "",
                                tint = Color.Gray.copy(alpha = 0.2f), modifier = Modifier.size(25.dp))
                        },
                        onValueChange = addProductViewModel::onChangeBasic,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor =  Color.LightGray,
                        )
                    )
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(2.dp, Color.LightGray),
                            modifier = Modifier
                                .width(screenWidthDp * .35f)
                                .height(55.dp),

                            ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (addProductViewModel.selectedGst.value != null) {
                                    Text(
                                        modifier = Modifier.padding(
                                            vertical = 3.dp, horizontal = 10.dp
                                        ),
                                        text = addProductViewModel.selectedGst.value!!.gstName,
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

                                        text = "Select Gst",
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

                            DropdownMenu(modifier = Modifier
                                .width(140.dp)
                                .padding(10.dp),
                                expanded = addProductViewModel.isItemExpanded.value,
                                onDismissRequest = { addProductViewModel.isItemExpanded.value = false },

                                ) {


                                addProductViewModel.gstDetails.collectAsState().value.forEach { gst ->
                                    DropdownMenuItem(onClick = {
                                        addProductViewModel.selectedGst.value = gst
                                        addProductViewModel.isItemExpanded.value = false
                                    }) {
                                        Text(text = gst.gstName)
                                    }

                                }

                            }
                        }
                    }

                    OutlinedTextField(
                        modifier = Modifier
                            .width(screenWidthDp * .30f)
                            .padding(horizontal = 5.dp)
                            .height(height = 55.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                            ),
                        value =addProductViewModel.rate.value,
                        placeholder = {
                            Text(
                                text = "Rate", style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                )
                            )
                        },
                        trailingIcon = {
                            Icon(painter = R.drawable.rupee.resourceImage(),
                                contentDescription = "",
                                tint = Color.Gray.copy(alpha = 0.2f), modifier = Modifier.size(25.dp))
                        },
                        onValueChange = addProductViewModel::onChangeRate,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor =  Color.LightGray,
                        )
                    )

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)){

                    OutlinedTextField(
                        modifier = Modifier
                            .width(screenWidthDp * .50f)
                            .padding(horizontal = 5.dp)
                            .height(height = 55.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                            ),
                        value =addProductViewModel.quantity.value,
                        placeholder = {
                            Text(
                                text = "Enter Quantity", style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                )
                            )
                        },
                        trailingIcon = {
                        }, maxLines = 1,
                        onValueChange = addProductViewModel::onChangeQuantity,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor =  Color.LightGray,
                        )
                    )


                    OutlinedTextField(
                        modifier = Modifier
                            .width(screenWidthDp * .50f)
                            .padding(horizontal = 5.dp)
                            .height(height = 55.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                            ),
                        value =addProductViewModel.state.value,
                        placeholder = {
                            Text(
                                text = "State", style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                )
                            )
                        },
                        trailingIcon = {
                        }, maxLines = 1,
                        onValueChange = addProductViewModel::onChangeState,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor =  Color.LightGray,
                        )
                    )
                }









            }

            StrickyButton(
                enable = addProductViewModel.enableBtn.value,
                loading = addProductViewModel.addLoading.value,
                action = addProductViewModel::uploadProductData,
                name = R.string.addProduct)
        }
       }




}
