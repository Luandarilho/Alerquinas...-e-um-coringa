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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            alerquinas()
        }
    }
}

//versao 0.1 -> Apenas exibir duas imagens de alerquinas e muda-las por meio de um botao
@Composable
fun alerquinas(){
    Column(modifier = Modifier.fillMaxSize()) {
        //declarando a lista de imagens da Harley
        val listaHarleys = mutableListOf(R.drawable.harley01, R.drawable.harley02,R.drawable.harley03, R.drawable.harley04, R.drawable.harley05, R.drawable.harley06)
        //variavel imagem que sera exibida na tela
        val harley = remember { mutableIntStateOf(listaHarleys.random())}

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
            Button(onClick = {harley.intValue = listaHarleys.random()},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)){
                    Text(text = "Mudar Harley", color = Color.Black)
            }
        }
    }
}