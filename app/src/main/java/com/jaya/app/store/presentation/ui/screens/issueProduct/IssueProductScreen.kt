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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.jaya.app.store.presentation.states.statusBarColor
import com.jaya.app.store.presentation.ui.custom_composable.StrickyButton
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel
import com.jaya.app.store.presentation.ui.view_models.IssueProductViewModel
import java.util.Locale.filter

@Composable
fun IssueProductScreen(
    viewModel:IssueProductViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {paddingValues ->
       Column(modifier = Modifier.statusBarColor(Color(0xffFFEB56))
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
                        viewModel.appNavigator.tryNavigateBack()
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



        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),){
            val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 5.dp), horizontalArrangement = Arrangement.SpaceBetween
                )
            {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {

                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, Color.LightGray),
                        modifier = Modifier
                            .width(screenWidthDp * .60f)
                            .height(55.dp),

                        ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            if (viewModel.selectedProduct.value != null) {
                                Text(
                                    modifier = Modifier.padding(
                                        vertical = 3.dp, horizontal = 10.dp
                                    ),
                                    text = viewModel.selectedProduct.value!!.productName,
                                    style = TextStyle(
                                        color = Color(0xff212121),
                                        fontSize = 13.sp,
                                    ),
                                )
                            }
                            else {
                                Text(
                                    modifier = Modifier
                                        .padding(
                                            vertical = 3.dp, horizontal = 10.dp
                                        ),

                                    text = "Select Product",
                                    style = TextStyle(
                                        color = Color.Gray.copy(alpha = 0.2f)

                                    ),
                                )
                            }
                            IconButton(onClick = {
                                viewModel.isExpandedItem.value =
                                    !viewModel.isExpandedItem.value
                            }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                                )
                            }

                        }

                        DropdownMenu(modifier = Modifier.width(250.dp),
                            expanded =  viewModel.isExpandedItem.value,
                            onDismissRequest = {  viewModel.isExpandedItem.value = false })
                        {
                            OutlinedTextField(
                                value =  viewModel.searchTxt.value,
                                onValueChange = viewModel::onChangeSearchTxt,
                                modifier = Modifier,
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(15.dp)
                                            .size(24.dp)
                                    )
                                },
                                singleLine = true,
                            )

                          /*   EmitType.productDetails.collectAsState().value.forEach { product ->
                                DropdownMenuItem(onClick = {
                                    viewModel.selectedProduct.value = product
                                    viewModel.isExpandedItem.value = false
                                }) {
                                    Text(text = product.productName)
                                }

                            }*/
                            viewModel.products.forEach {product ->
                                DropdownMenuItem(onClick = {
                                            viewModel.selectedProduct.value = product
                                            viewModel.isExpandedItem.value = false
                                }) {
                                    Text(text = product.productName)
                                }
                            }


                        }



                    }



                }


                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(screenWidthDp * .30f)
                        .height(55.dp),
                    value =viewModel.itemNumber.value,
                    placeholder = {
                        Text(
                            text = "Enter NOS", style = TextStyle(
                                color = Color.Gray.copy(alpha = 0.2f)

                            )
                        )
                    }, maxLines = 1,
                    trailingIcon = {},
                    onValueChange = viewModel::onChangeItemNumber,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor =  Color.LightGray,
                    )
                )
            }


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 10.dp)
                    .height(height = 55.dp),
                value =viewModel.orderBy.value,
                placeholder = {
                    Text(
                        text = "Order By", style = TextStyle(
                            color = Color.Gray.copy(alpha = 0.2f)

                        )
                    )
                },
                maxLines = 1,
                trailingIcon = {},
                onValueChange = viewModel::onChangeOrder,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor =  Color.LightGray,
                )
            )
            




            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 5.dp)
                    .size(height = 55.dp, width = 300.dp),
                value =viewModel.takingName.value,
                maxLines = 1,
                placeholder = {
                    Text(
                        text = "Taken By Name", style = TextStyle(
                            color = Color.Gray.copy(alpha = 0.2f)

                        )
                    )
                },
                trailingIcon = {},
                onValueChange = viewModel::onChangeTakingName,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor =  Color.LightGray,
                )
            )



            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 5.dp)
                    .size(height = 55.dp, width = 300.dp),
                value =viewModel.where.value, maxLines = 1,
                placeholder = {
                    Text(
                        text = "Where", style = TextStyle(
                            color = Color.Gray.copy(alpha = 0.2f)

                        )
                    )
                },
                trailingIcon = {},
                onValueChange = viewModel::onChangeAskedName,
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
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = Modifier
                        .width(screenWidthDp * .90f)
                        .height(55.dp),

                    ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (viewModel.selectPlant.value != null) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = 3.dp, horizontal = 10.dp
                                ),
                                text = viewModel.selectPlant.value!!.plantName,
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

                                text = "Select Plant",
                                style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.2f)

                                ),
                            )
                        }


                        IconButton(onClick = {
                            viewModel.isExpandedPlant.value =
                                !viewModel.isExpandedPlant.value
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                            )
                        }
                    }

                    DropdownMenu(modifier = Modifier
                        .width(screenWidthDp * .90f)
                        .padding(horizontal = 20.dp),
                        expanded = viewModel.isExpandedPlant.value,
                        onDismissRequest = { viewModel.isExpandedPlant.value = false }) {

                        OutlinedTextField(
                            value =  viewModel.plantSearchTxt.value,
                            onValueChange = viewModel::onChangePlantTxt,
                            modifier = Modifier.width(screenWidthDp * .90f),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .size(24.dp)
                                )
                            },
                            singleLine = true,
                        )

                       viewModel.plants.forEach { plant ->
                            DropdownMenuItem(onClick = {

                                viewModel.selectPlant.value = plant
                               viewModel.isExpandedPlant.value = false
                            }) {
                                Text(text = plant.plantName)
                            }

                        }

                    }
                }
            }

             Row(modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 18.dp, vertical = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {



                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center
                 ) {

                     Surface(
                         shape = RoundedCornerShape(4.dp),
                         border = BorderStroke(1.dp, Color.LightGray),
                         modifier = Modifier
                             .width(screenWidthDp * .45f)
                             .height(55.dp),

                         ) {
                         Row(
                             horizontalArrangement = Arrangement.SpaceBetween,
                             verticalAlignment = Alignment.CenterVertically
                         ) {


                             if (viewModel.selectSection.value != null) {
                                 Text(
                                     modifier = Modifier.padding(
                                         vertical = 3.dp, horizontal = 10.dp
                                     ),
                                     text = viewModel.selectSection.value!!.sectionName,
                                     style = TextStyle(
                                         color = Color(0xff212121),
                                         fontSize = 13.sp,
                                     ),
                                 )
                             }
                             else {
                                 Text(
                                     modifier = Modifier
                                         .padding(
                                             vertical = 3.dp, horizontal = 10.dp
                                         ),

                                     text = "Select Section",
                                     style = TextStyle(
                                         color = Color.Gray.copy(alpha = 0.2f)

                                     ),
                                 )
                             }
                             IconButton(onClick = {
                                 viewModel.isExpandedSection.value =
                                     !viewModel.isExpandedSection.value
                             }) {
                                 Icon(
                                     imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                                 )
                             }

                         }

                         DropdownMenu(modifier = Modifier.width(screenWidthDp * .45f),
                             expanded =  viewModel.isExpandedSection.value,
                             onDismissRequest = {  viewModel.isExpandedSection.value = false })
                         {
                            /* OutlinedTextField(
                                 value =  viewModel.searchTxt.value,
                                 onValueChange = viewModel::onChangeSearchTxt,
                                 modifier = Modifier,
                                 leadingIcon = {
                                     Icon(
                                         Icons.Default.Search,
                                         contentDescription = "",
                                         modifier = Modifier
                                             .padding(15.dp)
                                             .size(24.dp)
                                     )
                                 },
                                 singleLine = true,
                             )*/

                            /* viewModel.products.forEach {product ->
                                 DropdownMenuItem(onClick = {
                                     viewModel.selectedProduct.value = product
                                     viewModel.isExpandedItem.value = false
                                 }) {
                                     Text(text = product.productName)
                                 }
                             }*/

                                viewModel.sectionDetails.collectAsState().value.forEach { section ->
                                   DropdownMenuItem(onClick = {
                                       viewModel.selectSection.value = section
                                       viewModel.isExpandedSection.value = false
                                   }) {
                                       Text(text = section.sectionName)
                                   }

                               }



                         }



                     }



                 }


                 Box(
                     modifier = Modifier.weight(1f),
                     contentAlignment = Alignment.Center
                 ) {

                     Surface(
                         shape = RoundedCornerShape(4.dp),
                         border = BorderStroke(1.dp, Color.LightGray),
                         modifier = Modifier
                             .width(screenWidthDp * .45f)
                             .height(55.dp),

                         ) {
                         Row(
                             horizontalArrangement = Arrangement.SpaceBetween,
                             verticalAlignment = Alignment.CenterVertically
                         ) {


                             if (viewModel.selectedItem.value != null) {
                                 Text(
                                     modifier = Modifier.padding(
                                         vertical = 3.dp, horizontal = 10.dp
                                     ),
                                     text = viewModel.selectedItem.value!!.subsectionName,
                                     style = TextStyle(
                                         color = Color(0xff212121),
                                         fontSize = 13.sp,
                                     ),
                                 )
                             }
                             else {
                                 Text(
                                     modifier = Modifier
                                         .padding(
                                             vertical = 3.dp, horizontal = 10.dp
                                         ),

                                     text = "Select Subsection",
                                     style = TextStyle(
                                         color = Color.Gray.copy(alpha = 0.2f)

                                     ),
                                 )
                             }
                             IconButton(onClick = {
                                 viewModel.isExpandedSubsection.value =
                                     !viewModel.isExpandedSubsection.value
                             }) {
                                 Icon(
                                     imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                                 )
                             }

                         }

                         DropdownMenu(modifier = Modifier.width(screenWidthDp * .45f),
                             expanded =  viewModel.isExpandedSubsection.value,
                             onDismissRequest = {  viewModel.isExpandedSubsection.value = false })
                         {
                            /* OutlinedTextField(
                                 value =  viewModel.searchTxt.value,
                                 onValueChange = viewModel::onChangeSearchTxt,
                                 modifier = Modifier,
                                 leadingIcon = {
                                     Icon(
                                         Icons.Default.Search,
                                         contentDescription = "",
                                         modifier = Modifier
                                             .padding(15.dp)
                                             .size(24.dp)
                                     )
                                 },
                                 singleLine = true,
                             )*/

                            /* viewModel.products.forEach {product ->
                                 DropdownMenuItem(onClick = {
                                     viewModel.selectedProduct.value = product
                                     viewModel.isExpandedItem.value = false
                                 }) {
                                     Text(text = product.productName)
                                 }
                             }*/


                                viewModel.subsectionDetails.collectAsState().value.forEach { subsection ->
                                   DropdownMenuItem(onClick = {
                                       viewModel.selectedItem.value = subsection
                                       viewModel.isExpandedSubsection.value = false
                                   }) {
                                       Text(text = subsection.subsectionName)
                                   }

                               }


                         }



                     }



                 }
             }

        }



        StrickyButton(
            enable = viewModel.enableBtn.value,
            loading = viewModel.addLoading.value,
            action = viewModel::SubmitIssue,
            name = R.string.submitNow)
    }
}


