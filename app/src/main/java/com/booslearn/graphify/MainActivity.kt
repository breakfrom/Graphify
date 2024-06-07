package com.booslearn.graphify

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.booslearn.graphify.homework_one.CoordinatePlane
import com.booslearn.graphify.model.Coordinate
import com.booslearn.graphify.model.Point
import com.booslearn.graphify.model.Segment
import com.booslearn.graphify.ui.theme.GraphifyTheme
import com.booslearn.graphify.ui.theme.black
import com.booslearn.graphify.ui.theme.gray100
import com.booslearn.graphify.ui.theme.gray125
import com.booslearn.graphify.ui.theme.gray40
import com.booslearn.graphify.ui.theme.gray50
import com.booslearn.graphify.ui.theme.gray60
import com.booslearn.graphify.ui.theme.gray90
import com.booslearn.graphify.ui.theme.orange
import com.booslearn.graphify.ui.theme.white
import com.booslearn.graphify.utils.GRID_SIZE
import com.booslearn.graphify.utils.getX
import com.booslearn.graphify.utils.getY
import com.example.cronos.utils.modifier.clickRipple
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.smarttoolfactory.slider.ColorfulIconSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.SliderBrushColor
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pointA = Point('A', Color.Blue, Coordinate(-5f, 10f))
        val pointB = Point('B', Color.Red, Coordinate(3f, -2f))



        val segmentAB = Segment(
            initialPoint = pointB,
            finalPoint = pointA,
            color = Color.Black
        )
        setContent {
            GraphifyTheme {
                val systemBar = rememberSystemUiController()
                systemBar.setSystemBarsColor(
                    color = Color.Black,
                    darkIcons = false
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )   {
                    var correct by remember      {
                        mutableStateOf(false)
                    }
                    var coordinate by remember      {
                        mutableStateOf(false)
                    }
                    var razon by remember   {
                        mutableFloatStateOf(1f)
                    }
                    var pointP by remember  {
                        mutableStateOf(
                            Point('P', orange, Coordinate(
                                // SUMA DE LOS PUNTOS HORIZONTALES ENTRE DOS
                                (pointA.coordinate.x + pointB.coordinate.x) / 2,
                                // SUMA DE LOS PUNTOS VERTICALES ENTRE DOS
                                (pointA.coordinate.y + pointB.coordinate.y) / 2,
                            ))
                        )
                    }
                    // SACO LA RAIZ CUADRADA A LA SUMA DE LOS CUADRADOS DE LA DIFERENCIA ENTRE LOS HORIZONTALES Y VERTICALES
                    var topDivisor = (((sqrt((pointA.coordinate.x - pointP.coordinate.x).pow(2) + (pointA.coordinate.y - pointP.coordinate.y).pow(2))) * 10).toInt() / 10.0).toFloat()
                    var bottomDivisor = (((sqrt((pointB.coordinate.x - pointP.coordinate.x).pow(2) + (pointB.coordinate.y - pointP.coordinate.y).pow(2))) * 10).toInt() / 10.0).toFloat()

                    var listPoint by remember   {
                        mutableStateOf(
                            listOf(pointP)
                        )
                    }
                    LaunchedEffect(pointP)  {
                        listPoint = listOf(pointP)
                    }
                    LaunchedEffect(razon)  {
                        val newPointP = pointP.copy(
                            coordinate = pointP.coordinate.copy(
                                x = (pointA.coordinate.x + (razon*pointB.coordinate.x)) / (1 + razon),
                                y = (pointA.coordinate.y + (razon*pointB.coordinate.y)) / (1 + razon)
                            )
                        )
                        pointP = newPointP
                        topDivisor = ((((sqrt((pointA.coordinate.x - pointP.coordinate.x).pow(2) + (pointA.coordinate.y - pointP.coordinate.y).pow(2))) * 10).toInt() / 10.0).toFloat())
                        bottomDivisor = ((((sqrt((pointB.coordinate.x - pointP.coordinate.x).pow(2) + (pointB.coordinate.y - pointP.coordinate.y).pow(2))) * 10).toInt() / 10.0).toFloat())
                    }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar =     {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(175.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 30.dp,
                                            topEnd = 30.dp
                                        )
                                    )
                                    .background(black)
                                    .padding(6.dp)
                            )   {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(25.dp)
                                        .height(50.dp)
                                        .align(Alignment.CenterHorizontally)
                                )   {
                                    ColorfulIconSlider(
                                        value = razon,
                                        onValueChange = { value ->
                                            razon = (round(value * 100) / 100)
                                        },
                                        trackHeight = 14.dp,
                                        colors = MaterialSliderDefaults.materialColors(
                                            activeTrackColor = SliderBrushColor(gray90),
                                            inactiveTrackColor = SliderBrushColor(gray125)
                                        ),
                                        borderStroke = BorderStroke(2.dp, gray60),
                                        valueRange = -10f..10f
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.aa),
                                            contentDescription = null,
                                            tint = black,
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .clip(CircleShape)
                                                .size(dimensionResource(id = R.dimen.m29_5))
                                                .background(gray125)
                                                .clickRipple { }
                                                .padding(4.dp)
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 4.dp)
                                        .align(Alignment.CenterHorizontally)
                                    )  {
                                        Text(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            text = "R = ",
                                            color = white
                                        )

                                        DividerComposable(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            top = if(correct) "AP" else "PB",
                                            bottom = if(correct) "PB" else "AP"
                                        )
                                        Text(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            text = " = ",
                                            color = white
                                        )

                                        DividerComposable(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            top = if(correct) "$topDivisor" else "$bottomDivisor",
                                            bottom = if(correct) "$bottomDivisor" else "$topDivisor"
                                        )
                                        Text(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            text = " = ",
                                            color = white
                                        )
                                        Text(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            text = if(correct) "${(topDivisor/bottomDivisor)}" else "${(bottomDivisor/topDivisor)}",
                                            color = white
                                        )

                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .padding(start = 40.dp)
                                            .clickRipple {
                                                correct = !correct
                                            }
                                            .align(Alignment.CenterVertically),
                                    )   {
                                        Icon(
                                            painter = painterResource(id = R.drawable.sync),
                                            tint = white,
                                            contentDescription = null
                                        )
                                    }
                                }


                            }
                        }
                    ) {

                        CoordinatePlane(
                            coordinate = coordinate,
                            modifier = Modifier.padding(it),
                            segments = listOf(segmentAB),
                            points = listPoint,
                            gridSize = GRID_SIZE
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        )   {
                            Row(
                                modifier = Modifier
                                    .padding(top = 25.dp, end = 25.dp)
                                    .align(Alignment.TopEnd)
                            )   {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickRipple {
                                            coordinate = !coordinate
                                        }
                                        .align(Alignment.CenterVertically),
                                )   {
                                    Icon(
                                        painter = painterResource(id =  if(coordinate) R.drawable.eye_close else R.drawable.eye),
                                        contentDescription = null
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .padding(start = 4.dp)
                                        .align(Alignment.CenterVertically),
                                    text = "(x, y)"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DividerComposable(
    modifier : Modifier,
     top : String,
     bottom : String
)   {
    Column(
        modifier = modifier
            .width(50.dp),
    )   {
        AnimatedContent(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            targetState = top, label = "") {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = it,
            color = white
        )
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            thickness = 1.dp,
            color = gray100
        )
        AnimatedContent(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            targetState = bottom, label = "") {
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterHorizontally),
            text = it,
            color = white
        )
        }
    }
}
fun offsetFromCoordinate(coordinate: Coordinate, centerX: Float, centerY: Float, density: Density): Offset {
    return with(density) {
        Offset(
            x = centerX + coordinate.x.getX().dp.toPx(),
            y = centerY - coordinate.y.getY().dp.toPx()
        )
    }
}

