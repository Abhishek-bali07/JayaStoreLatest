package com.jaya.app.store.presentation.ui.screens.Dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.resourceImage
import com.jaya.app.store.presentation.states.resourceString
import com.jaya.app.store.presentation.states.screenHeight
import com.jaya.app.store.presentation.theme.appTextStyles
import com.jaya.app.store.presentation.ui.view_models.DashboardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashBoardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
){
    val listState = rememberLazyListState()
    var rowSize by remember {
        mutableStateOf(IntSize.Zero)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        drawerContent = {SideBarContent(viewModel = viewModel)},
        drawerScrimColor = Color.Black.copy(alpha = .5f),
        drawerBackgroundColor = Color.White,
        scaffoldState = viewModel.scaffoldState,
        drawerGesturesEnabled = viewModel.drawerGuestureState.value,
        topBar = { AppBarContent(viewModel) },


    ) {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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
                        rowSize = rowSize )

                    CardItem(
                        name = R.string.itemIssued,
                        number = viewModel.totalIssued.value,
                        color = Color(0xff6A9E73),
                        rowSize = rowSize)
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
                    Surface(modifier = Modifier
                        .padding(10.dp)
                        .size(width = 180.dp, height = 24.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color(0xffFFEB56)),
                        onClick = {


                        })
                    {
                        Row(
                            modifier = Modifier.background(color =  Color(0xffFFEB56)),
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
                                contentDescription = "null", tint = Color.Black
                            )
                        }

                    }

                    Surface(modifier = Modifier
                        .padding(10.dp)
                        .size(width = 180.dp, height = 24.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color(0xffFFEB56)),
                        onClick = {


                        })
                    {
                        Row(
                            modifier = Modifier.background(color =  Color(0xffFFEB56)),
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
                                contentDescription = "null", tint = Color.Black
                            )
                        }

                    }
                }

                StatusSection(viewModel)

            }
        }

    }
}

@Composable
fun StatusSection(viewModel: DashboardViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .weight(2f),
            text = "Product List",
            color = Color.Black,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold

            )

        )

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {

        }
    }
}


@Composable
fun CardItem(
    @StringRes name: Int,
    number: Int?,
    color: Color,
    rowSize: IntSize,
){
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
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${number ?: 0}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                )


            Text(
                text = stringResource(id = name),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                lineHeight = 24.sp,
                modifier = Modifier
                )

        }
    }
}


@Composable
fun AppBarContent(viewModel: DashboardViewModel) {
    val uiScope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xffFFEB56),
        contentPadding = PaddingValues(8.dp),
    ) {
        IconButton(onClick = {
            uiScope.launch {
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
            uiScope.launch {
            }

        }) {
            Icon(
                painter = R.drawable.bell.resourceImage(),
                contentDescription ="" ,
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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {

        DrawerMenuLogoutItem(viewModel, uiScope)
    }

}



@Composable
private  fun DrawerMenuLogoutItem(viewModel: DashboardViewModel, uiScope: CoroutineScope) {
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

