package com.jaya.app.store.presentation.ui.screens.Dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jaya.app.store.R
import com.jaya.app.store.core.entities.Product
import com.jaya.app.store.presentation.states.resourceImage
import com.jaya.app.store.presentation.states.resourceString
import com.jaya.app.store.presentation.states.screenHeight
import com.jaya.app.store.presentation.theme.appTextStyles
import com.jaya.app.store.presentation.ui.view_models.DashboardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import com.jaya.app.store.presentation.states.DrawerMenuItem
import com.jaya.app.store.presentation.states.DrawerMenus
import com.jaya.app.store.presentation.states.Image
import com.jaya.app.store.presentation.states.Text
import com.jaya.app.store.presentation.states.screenWidth
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashBoardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    var rowSize by remember {
        mutableStateOf(IntSize.Zero)
    }
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp

    val dashboardDrawerState =
        androidx.compose.material3.rememberDrawerState(initialValue = DrawerValue.Closed)
    val uiScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            DashboardDrawerSection(drawerMenus = viewModel.drawerMenus.collectAsState(),
                onSelect = viewModel::onDrawerMenuClicked,
                viewModel = viewModel,
                closeDrawer = {
                    uiScope.launch {
                        when (dashboardDrawerState.currentValue) {
                            DrawerValue.Closed -> {}
                            DrawerValue.Open -> {
                                dashboardDrawerState.animateTo(
                                    targetValue = DrawerValue.Closed, anim = tween(500)
                                )
                            }
                        }
                    }
                })
        }, drawerState = dashboardDrawerState
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            drawerContent = { SideBarContent(viewModel = viewModel) },
            drawerScrimColor = Color.Black.copy(alpha = .5f),
            drawerBackgroundColor = Color.White,
            scaffoldState = viewModel.scaffoldState,
            drawerGesturesEnabled = viewModel.drawerGuestureState.value,
            topBar = { AppBarContent(viewModel, dashboardDrawerState, uiScope) },


            ) { paddingValues ->
            if (viewModel.quotationsLoading) {
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
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.TopStart
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .onGloballyPositioned {
                                    rowSize = it.size
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CardItem(
                                name = R.string.totalItem,
                                number = viewModel.totalItem.value,
                                color = Color(0xffD62B2B),
                                rowSize = rowSize
                            )

                            CardItem(
                                name = R.string.itemIssued,
                                number = viewModel.totalIssued.value,
                                color = Color(0xff6A9E73),
                                rowSize = rowSize
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .onGloballyPositioned {
                                    rowSize = it.size
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier
                                    .clickable { viewModel.addProduct() }
                                    .padding(10.dp)
                                    .width(screenWidthDp * .44f)
                                    .height(screenHeightDp * .06f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(color = Color(0xffFFEB56)),
                                /*onClick = {
                                    viewModel.addProduct()

                                }*/
                            ) {
                                Row(
                                    modifier = Modifier.background(color = Color(0xffFFEB56)),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = Modifier.padding(
                                            vertical = 3.dp, horizontal = 5.dp
                                        ),
                                        text = "Add Product",
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )


                                    Icon(
                                        modifier = Modifier,
                                        painter = R.drawable.plus.resourceImage(),
                                        contentDescription = "null",
                                        tint = Color.Black
                                    )
                                }

                            }

                            Surface(
                                modifier = Modifier
                                    .clickable { viewModel.issueProduct() }
                                    .padding(10.dp)
                                    .width(screenWidthDp * .60f)
                                    .height(screenHeightDp * .06f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(color = Color(0xffFFEB56)),
                                /*onClick = {
                                    viewModel.issueProduct()
                                }*/

                            ) {
                                Row(
                                    modifier = Modifier.background(color = Color(0xffFFEB56)),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = Modifier.padding(
                                            vertical = 3.dp, horizontal = 5.dp
                                        ),
                                        text = "Issue an item",
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )


                                    Icon(
                                        modifier = Modifier,
                                        painter = R.drawable.plus.resourceImage(),
                                        contentDescription = "null",
                                        tint = Color.Black
                                    )
                                }

                            }
                        }

                        StatusSection(viewModel)/*  Spacer(modifier = Modifier.height(10.dp))*/
                        Divider(
                            color = Color.LightGray, modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp)
                        )

                        FeatureSection(products = viewModel.products, viewModel)

                    }
                }


            }

        }
    }

}

@Composable
fun DashboardDrawerSection(
    drawerMenus: State<List<DrawerMenuItem>>,
    onSelect: (Int, DrawerMenuItem) -> Unit,
    viewModel: DashboardViewModel,
    closeDrawer: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(
        modifier = Modifier.width(screenWidth * .75f), drawerContainerColor = Color.LightGray
    ) {
        Spacer(Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = R.drawable.jayalogo.resourceImage(),
                contentDescription = ""
            )


        }
        Divider(color = Color.White)
        Spacer(Modifier.height(12.dp))
        drawerMenus.value.forEachIndexed { index, drawerMenuItem ->
            NavigationDrawerItem(label = {
                drawerMenuItem.menu.mName.Text(
                    style = MaterialTheme.typography.h6
                )
            }, selected = drawerMenuItem.selected, onClick = {
                coroutineScope.launch {
                    closeDrawer()
                    delay(700)
                    onSelect(index, drawerMenuItem)
                }
            }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding), icon = {
                if (drawerMenuItem.menu == DrawerMenus.Logout) {
                    Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                } else {
                    drawerMenuItem.menu.mIcon.Image(modifier = Modifier.size(15.dp))
                }
            })
            if (drawerMenus.value.last() != drawerMenuItem) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun FeatureSection(products: List<Product>, viewModel: DashboardViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.isshowSearch.value) OutlinedTextField(
            value = viewModel.searchTxt.value,
            onValueChange = viewModel::onChangeSearchTxt,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (viewModel.searchTxt.value != "") {
                    IconButton(onClick = {
                        viewModel.searchTxt.value = ""
                        viewModel.products.clear()
                        viewModel.products.addAll(viewModel.searchProduct.toList())
                        //viewModel.getStockData()
                    }) {
                        androidx.compose.material3.Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                leadingIconColor = Color.White,
                trailingIconColor = Color.White,
                backgroundColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), contentPadding = PaddingValues(
                start = 7.5.dp, end = 7.5.dp, bottom = 20.dp, top = 10.dp
            ), modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            items(products.size) {
                ProductItem(product = products[it], viewModel)
            }
        }
    }
}


@Composable
fun StatusSection(viewModel: DashboardViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(2f),
            text = "Product List",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold

            )
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                viewModel.isshowSearch.value = !viewModel.isshowSearch.value
            }) {
                Icon(
                    painter = R.drawable.search.resourceImage(),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }


            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = R.drawable.filter.resourceImage(),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}


@Composable
fun ProductItem(
    product: Product, viewModel: DashboardViewModel
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp), elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(modifier = Modifier.align(alignment = Alignment.Start)) {
                Text(
                    text = product.productQty, style = TextStyle(
                        color = Color(0xff036509), fontSize = 10.sp,
                    )
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(product.productImage)
                    .crossfade(enable = true).size(200).build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = product.productTitle, maxLines = 1, style = TextStyle(
                        color = Color(0xff222222), fontSize = 12.sp, fontWeight = FontWeight.W500
                    )
                )


                Text(
                    text = product.productValue, style = TextStyle(
                        color = Color(0xffFF4155), fontSize = 10.sp,
                    )
                )
            }/*Row(modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(5.dp)) {
                Text(
                    text = product.productValue,
                    style = TextStyle(
                        color = Color(0xffFF4155),fontSize = 10.sp,), textAlign = TextAlign.Center
                )
            }*/


        }
    }
}


@Composable
fun CardItem(
    @StringRes name: Int,
    number: String,
    color: Color,
    rowSize: IntSize,
) {
    Card(
        modifier = Modifier
            .width(with(LocalDensity.current) {
                (rowSize.width * .50f).toDp()
            })
            .height(screenHeight * 0.15f)
            .padding(12.dp),
        backgroundColor = color,
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${number ?: 0}", style = TextStyle(
                    color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold
                ), modifier = Modifier
            )


            Text(
                text = stringResource(id = name), style = TextStyle(
                    color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold
                ), lineHeight = 24.sp, modifier = Modifier
            )

        }
    }
}


@Composable
fun AppBarContent(
    viewModel: DashboardViewModel,
    dashboardDrawerState: DrawerState,
    uiScope: CoroutineScope,
) {
    val uiScope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xffFFEB56),
        contentPadding = PaddingValues(8.dp),
    ) {
        IconButton(onClick = {
            uiScope.launch {
                when (dashboardDrawerState.currentValue) {
                    DrawerValue.Closed -> dashboardDrawerState.animateTo(
                        targetValue = DrawerValue.Open, anim = tween(700)
                    )

                    DrawerValue.Open -> dashboardDrawerState.animateTo(
                        targetValue = DrawerValue.Closed, anim = tween(700)
                    )
                }

            }

        }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }



        DrawerImageSection(R.drawable.jayalogo)



        IconButton(onClick = {
            uiScope.launch {}

        }) {
            Icon(
                painter = R.drawable.bell.resourceImage(),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )

        }

    }
}


@Composable
fun DrawerImageSection(@DrawableRes jayalogo: Int) {

    Image(
        painter = painterResource(id = jayalogo),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(fraction = 0.8f)
    )

}

@Composable
private fun SideBarContent(viewModel: DashboardViewModel) {

    val uiScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top
    ) {

        DrawerMenuLogoutItem(viewModel, uiScope)
    }

}


@Composable
private fun DrawerMenuLogoutItem(viewModel: DashboardViewModel, uiScope: CoroutineScope) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 25.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.jayalogo),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 15.dp)
        )

        Text(
            text = R.string.logout.resourceString(),
            style = MaterialTheme.appTextStyles.drawerMenuItemStyle
        )
    }
}

