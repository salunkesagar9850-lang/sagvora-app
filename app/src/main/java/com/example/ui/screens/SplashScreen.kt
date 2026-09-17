package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CyanBright
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val scale = remember { Animatable(0.7f) }
    val alpha = remember { Animatable(0f) }
    val glowAlpha = remember { Animatable(0.2f) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    LaunchedEffect(Unit) {
        // Entrance animation
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 700, easing = LinearEasing)
        )
        glowAlpha.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

        // Hold and auto-advance
        delay(1600)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        DarkBackground,
                        Color(0xFF090D18),
                        Color(0xFF0D1424),
                        DarkBackground
                    )
                )
            )
            .clickable { onSplashFinished() }
            .testTag("splash_screen_root"),
        contentAlignment = Alignment.Center
    ) {
        // Decorative glowing aura background
        Box(
            modifier = Modifier
                .size(320.dp)
                .scale(pulseScale)
                .alpha(glowAlpha.value * 0.35f)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            CyanNeon.copy(alpha = 0.5f),
                            OrangeNeon.copy(alpha = 0.25f),
                            VioletNeon.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(32.dp)
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            // Emblem Container with neon gradient ring
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.sweepGradient(
                            listOf(
                                CyanNeon,
                                OrangeNeon,
                                VioletNeon,
                                CyanBright,
                                CyanNeon
                            )
                        )
                    )
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color(0xFF07090F)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_sagvora_logo),
                        contentDescription = "SAGVORA Logo",
                        modifier = Modifier
                            .size(124.dp)
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Company Name
            Text(
                text = "SAGVORA",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 6.sp,
                color = Color.White,
                modifier = Modifier.testTag("splash_title")
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = "Imagine. Create. Transform.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp,
                color = CyanNeon,
                modifier = Modifier.testTag("splash_tagline")
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Tap to continue hint
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(20.dp))
                    .background(Color(0x660F172A))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Tap anywhere to enter",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMuted,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = CyanNeon,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}
