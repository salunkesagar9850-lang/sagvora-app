package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ServiceItem
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CyanBright
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon

@Composable
fun ServicesScreen(
    services: List<ServiceItem>,
    onLearnMore: (String) -> Unit,
    onRequestService: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("services_screen_root")
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            SectionHeader(
                tag = "Core Offerings",
                title = "Services & Solutions",
                subtitle = "Comprehensive technology and creative media capabilities engineered to transform modern business."
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(services, key = { it.id }) { service ->
            ServiceCard(
                service = service,
                onLearnMore = { onLearnMore(service.id) },
                onRequestService = { onRequestService(service.title) },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ServiceCard(
    service: ServiceItem,
    onLearnMore: () -> Unit,
    onRequestService: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (icon, accentColor) = when (service.id) {
        "ai_solutions" -> Pair(Icons.Default.SmartToy, CyanNeon)
        "ai_advertising" -> Pair(Icons.Default.Campaign, OrangeNeon)
        "ai_filmmaking" -> Pair(Icons.Default.Movie, VioletNeon)
        "app_development" -> Pair(Icons.Default.Code, CyanBright)
        "website_development" -> Pair(Icons.Default.Web, Color(0xFF00B0FF))
        "digital_innovation" -> Pair(Icons.Default.Lightbulb, Color(0xFFFF9100))
        else -> Pair(Icons.Default.Hub, CyanNeon)
    }

    GlassCard(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon Box
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(accentColor.copy(alpha = 0.12f))
                        .border(1.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = service.title,
                        tint = accentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = service.categoryBadge.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = accentColor,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = service.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = service.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Dual Action Buttons: Learn More & Request This Service
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onLearnMore,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, BorderGlass),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextPrimary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("learn_more_${service.id}")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Learn More",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = CyanNeon
                        )
                    }
                }

                Button(
                    onClick = onRequestService,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor,
                        contentColor = Color(0xFF07090F)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("request_service_${service.id}")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.RocketLaunch,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color(0xFF07090F)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Request Service",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
