package com.example.lab13t_animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab13t_animaciones.ui.theme.Lab13T_animacionesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13T_animacionesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VisibilityAnimation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun VisibilityAnimation(modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Animación de visibilidad",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { isVisible = !isVisible },
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        ) {
            Text(if (isVisible) "Ocultar cuadro" else "Mostrar cuadro")
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(Color(0xFF6750A4), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "¡Hola!", color = Color.White)
            }
        }

        Text(
            text = "AnimatedVisibility muestra u oculta el contenido según un estado. " +
                "fadeIn y fadeOut animan gradualmente su opacidad.",
            modifier = Modifier.padding(top = 24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VisibilityAnimationPreview() {
    Lab13T_animacionesTheme {
        VisibilityAnimation()
    }
}