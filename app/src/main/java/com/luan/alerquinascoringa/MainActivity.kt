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
            Alerquinas()
        }
    }
}

//versao 0.2.2 -> correção de logica e atualização de imagem
@Composable
fun Alerquinas(){
    // lista com as imagens de alerquinas
    val listaHarleys =  mutableListOf(R.drawable.harley01, R.drawable.harley02, R.drawable.harley03, R.drawable.harley04, R.drawable.harley05, R.drawable.harley06)
    //variavel para atualizar imagem conforme index
    val harleyIndex = remember { mutableIntStateOf(listaHarleys.indices.random() )}
    //imagem a ser exibida
    var harley = listaHarleys[harleyIndex.intValue]


    Column(modifier = Modifier.fillMaxSize()){

        //box superior aonde as imagens sao exibidas
        Box(modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.5f).background(Color.Red),
            contentAlignment = Alignment.Center){
            Row {
                for (x in 0..1) {
                    Image(painter = painterResource(id = harley),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp).padding(20.dp).border(width = 5.dp, color = Color.Black))
                }
            }
        }

        //box inferior aonde fica os botões de manipulação
        Box(modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(1f).background(Color.Black),
            contentAlignment = Alignment.Center){
            ConstraintLayout(modifier = Modifier.fillMaxSize()){
                val topmarg = 80
                val buttonsmarg = 40
                val check = createRef()
                val back = createRef()
                val next = createRef()

                //botao de check
                FloatingActionButton(onClick = {
                    harleyIndex.intValue = listaHarleys.indices.random()
                    harley= listaHarleys[harleyIndex.intValue]
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
                    harleyIndex.intValue = (harleyIndex.intValue -1 + listaHarleys.size) % listaHarleys.size
                    harley = listaHarleys[harleyIndex.intValue]
                },
                    containerColor = Color.Red,
                    modifier = Modifier.constrainAs(back){
                        top.linkTo(parent.top, margin = topmarg.dp)
                        end.linkTo(check.start, margin = buttonsmarg.dp)
                    }){
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = null)

                }

                //botao de next
                FloatingActionButton(onClick = {
                    harleyIndex.intValue = (harleyIndex.intValue +1) % listaHarleys.size
                    harley = listaHarleys[harleyIndex.intValue]
                },
                    containerColor = Color.Red,
                    modifier = Modifier.constrainAs(next){
                        top.linkTo(parent.top, margin = topmarg.dp)
                        start.linkTo(check.end, margin = buttonsmarg.dp)
                    }){
                    Icon(imageVector = Icons.Outlined.ArrowForwardIos,
                        contentDescription = null)

                }

            }

        }
    }
}