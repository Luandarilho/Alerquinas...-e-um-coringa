package com.luan.alerquinascoringa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            alerquinas()
        }
    }
}

//versao 0.2 -> adição dos botões check, back e next
@Composable
fun alerquinas(){
    Column(modifier = Modifier.fillMaxSize()) {
        //declarando a lista de imagens da Harley
        val listaHarleys = mutableListOf(R.drawable.harley01, R.drawable.harley02,R.drawable.harley03, R.drawable.harley04, R.drawable.harley05, R.drawable.harley06)
        var harleyIndex = listaHarleys.random()
        //variavel imagem que sera exibida na tela
        val harley = remember { mutableIntStateOf(listaHarleys[harleyIndex])}

        // box superior com as duas imagens exibidas
        Box(modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(1f).background(Color.Red),
            contentAlignment = Alignment.Center){
            Row{
                for(x in 0..1){
                    Image(painter = painterResource(harley.intValue),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp).padding(20.dp).border(width = 5.dp, color = Color.Black))
                }
            }

        }

        //box inferior com o botao para trocar de imagem no box superior
        Box(modifier = Modifier.fillMaxHeight(1f).fillMaxWidth(1f).background(Color.Black),
            contentAlignment = Alignment.Center){
            ConstraintLayout(modifier = Modifier.fillMaxSize()){
                val topmarg = 80
                val buttonmarg = 30
                val check = createRef()
                val back = createRef()
                val next = createRef()

                //botao de check
                FloatingActionButton(onClick = {
                    harleyIndex = listaHarleys.indices.random()
                    harley.intValue = listaHarleys[harleyIndex]
                },
                    containerColor = Color.Red,
                    modifier = Modifier.constrainAs(check){
                        top.linkTo(parent.top, margin = topmarg.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }){
                    Icon(imageVector = Icons.Outlined.Check,
                        contentDescription = null)
                }

                //botao de back
                FloatingActionButton(onClick = {
                    if(harleyIndex == 0){
                        harleyIndex = listaHarleys.size -1
                    } else {
                        harleyIndex -=1
                    }
                },
                    containerColor = Color.Red,
                    modifier = Modifier.constrainAs(back){
                        top.linkTo(parent.top, margin = topmarg.dp)
                        end.linkTo(check.start, margin = buttonmarg.dp)
                    }){
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = null)
                }

                //botao de next
                FloatingActionButton(onClick = {
                    if(harleyIndex == listaHarleys.size -1){
                        harleyIndex = 0
                    } else {
                        harleyIndex += 1
                    }
                },
                    containerColor = Color.Red,
                    modifier = Modifier.constrainAs(next){
                        top.linkTo(parent.top, margin = topmarg.dp)
                        start.linkTo(check.end, margin = buttonmarg.dp)
                    }){
                    Icon(imageVector = Icons.Outlined.ArrowForwardIos,
                        contentDescription = null)
                }

            }
        }
    }
}