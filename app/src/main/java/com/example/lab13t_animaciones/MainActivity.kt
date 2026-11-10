package com.example.lab13t_animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab13t_animaciones.ui.theme.Lab13T_animacionesTheme

private enum class ContentState(val label: String) {
    Loading("Cargando"),
    Content("Contenido"),
    Error("Error")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13T_animacionesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimationExercises(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AnimationExercises(modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(false) }
    var isGreen by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    var contentState by remember { mutableStateOf(ContentState.Loading) }
    val animatedColor by animateColorAsState(
        targetValue = if (isGreen) Color(0xFF00A676) else Color(0xFF2979FF),
        animationSpec = tween(durationMillis = 700),
        label = "boxColor"
    )
    val animatedSize by animateDpAsState(
        targetValue = if (isExpanded) 176.dp else 112.dp,
        animationSpec = tween(durationMillis = 650),
        label = "boxSize"
    )
    val animatedOffset by animateDpAsState(
        targetValue = if (isExpanded) 44.dp else 0.dp,
        animationSpec = tween(durationMillis = 650),
        label = "boxOffset"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF3E5F5), Color(0xFFE3F2FD), Color(0xFFFFF3E0))
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF512DA8), RoundedCornerShape(24.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Rodrigo Marcos Ttito Suni",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Laboratorio de animaciones",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFFFD180),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "1. Animación de visibilidad",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF512DA8),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { isVisible = !isVisible },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
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
                        .size(150.dp)
                        .background(Color(0xFFFF7043), RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "¡Hola!", color = Color.White)
                }
            }

            Text(
                text = "AnimatedVisibility muestra u oculta el cuadro con fadeIn y fadeOut.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8F5E9), RoundedCornerShape(24.dp))
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "2. Cambio de color",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF2E7D32),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { isGreen = !isGreen },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
            ) {
                Text(if (isGreen) "Cambiar a azul" else "Cambiar a verde")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(animatedColor, RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isGreen) "Verde" else "Azul",
                    color = Color.White
                )
            }
            Text(
                text = "animateColorAsState interpola el color durante 700 ms.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF3E0), RoundedCornerShape(24.dp))
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "3. Tamaño y posición",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFEF6C00),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { isExpanded = !isExpanded },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF6C00))
            ) {
                Text(if (isExpanded) "Restablecer cuadro" else "Mover y agrandar")
            }
            Box(
                modifier = Modifier
                    .offset(x = animatedOffset)
                    .size(animatedSize)
                    .background(Color(0xFFD81B60), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isExpanded) "¡Grande!" else "¡Hola!",
                    color = Color.White
                )
            }
            Text(
                text = "animateDpAsState anima el tamaño y offset desplaza el cuadro. " +
                    "Aquí offset va antes de size y background, por eso se mueve todo el cuadro. " +
                    "Cambiar el orden puede cambiar qué parte se desplaza.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDE7F6), RoundedCornerShape(24.dp))
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "4. Cambio de contenido",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF4527A0),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ContentState.values().forEach { state ->
                    Button(
                        onClick = { contentState = state },
                        modifier = Modifier.weight(1f),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            horizontal = 4.dp,
                            vertical = 8.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (contentState == state) {
                                Color(0xFF5E35B1)
                            } else {
                                Color(0xFF9575CD)
                            }
                        )
                    ) {
                        Text(
                            text = state.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            AnimatedContent(
                targetState = contentState,
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = 600)) togetherWith
                        fadeOut(animationSpec = tween(durationMillis = 350))
                },
                label = "contentStateTransition"
            ) { state ->
                val (message, color) = when (state) {
                    ContentState.Loading -> "Cargando información..." to Color(0xFF1976D2)
                    ContentState.Content -> "¡Contenido listo para mostrar!" to Color(0xFF388E3C)
                    ContentState.Error -> "Ocurrió un error. Inténtalo de nuevo." to Color(0xFFD32F2F)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp)
                        .background(color, RoundedCornerShape(18.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = message,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Text(
                text = "Elige un estado para cambiar el mensaje con fadeIn y fadeOut.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationExercisesPreview() {
    Lab13T_animacionesTheme {
        AnimationExercises()
    }
}