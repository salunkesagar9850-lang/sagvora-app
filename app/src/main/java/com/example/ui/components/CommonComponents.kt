package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon

fun launchUrlSafely(context: Context, url: String, fallbackDescription: String = "link") {
    try {
        if (url.isBlank() || url.startsWith("https://placeholder") || url == "placeholder") {
            Toast.makeText(context, "This $fallbackDescription is not configured yet. You can update it in Settings.", Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Unable to open $fallbackDescription: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
    }
}

fun launchEmailSafely(context: Context, email: String, subject: String = "Project Discussion - SAGVORA") {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Email client not found. You can reach us directly at $email", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun SagvoraTopBar(
    onSettingsClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background.copy(alpha = 0.95f),
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(0.5.dp, BorderGlass),
                RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.testTag("brand_header")
            ) {
                // Neon Logo Mark
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(CyanNeon, OrangeNeon)
                            )
                        )
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .background(DarkBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "S",
                            color = CyanNeon,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "SAGVORA",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Imagine. Create. Transform.",
                        style = MaterialTheme.typography.labelSmall,
                        color = CyanNeon,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // WhatsApp quick action
                IconButton(
                    onClick = onWhatsAppClick,
                    modifier = Modifier
                        .testTag("topbar_whatsapp_button")
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = "Chat on WhatsApp",
                        tint = OrangeNeon,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                // Settings
                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier
                        .testTag("topbar_settings_button")
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings & Config",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SagvoraBottomNav(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.98f),
        tonalElevation = 8.dp,
        modifier = modifier
            .navigationBarsPadding()
            .border(BorderStroke(0.5.dp, BorderGlass))
    ) {
        val items = listOf(
            Triple("Home", Icons.Filled.Home, Icons.Outlined.Home),
            Triple("Services", Icons.Filled.Widgets, Icons.Outlined.Widgets),
            Triple("Projects", Icons.Filled.Layers, Icons.Outlined.Layers),
            Triple("Start", Icons.Filled.RocketLaunch, Icons.Outlined.RocketLaunch),
            Triple("Contact", Icons.Filled.AlternateEmail, Icons.Outlined.AlternateEmail)
        )

        items.forEachIndexed { index, (label, filledIcon, outlinedIcon) ->
            val isSelected = selectedIndex == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledIcon else outlinedIcon,
                        contentDescription = label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CyanNeon,
                    selectedTextColor = CyanNeon,
                    indicatorColor = CyanNeon.copy(alpha = 0.15f),
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted
                ),
                modifier = Modifier.testTag("nav_item_$index")
            )
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    borderColor: Color = BorderGlass,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val clickableModifier = if (onClick != null) {
        modifier.clickable(onClick = onClick)
    } else {
        modifier
    }

    Card(
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = DarkCardSurface
        ),
        border = BorderStroke(1.dp, borderColor),
        modifier = clickableModifier
    ) {
        content()
    }
}

@Composable
fun GradientPill(
    text: String,
    gradient: Brush = Brush.horizontalGradient(listOf(CyanNeon, OrangeNeon)),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(gradient)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF07090F),
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = Icons.AutoMirrored.Filled.ArrowForward,
    isSecondary: Boolean = false
) {
    val backgroundBrush = if (isSecondary) {
        Brush.horizontalGradient(listOf(Color(0xFF192236), Color(0xFF131B2B)))
    } else {
        Brush.horizontalGradient(listOf(CyanNeon, Color(0xFF00B0FF)))
    }

    val textColor = if (isSecondary) TextPrimary else Color(0xFF040A12)
    val borderStroke = if (isSecondary) BorderStroke(1.dp, BorderGlass) else null

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .then(if (borderStroke != null) Modifier.border(borderStroke, RoundedCornerShape(14.dp)) else Modifier)
            .background(backgroundBrush)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = textColor,
                letterSpacing = 0.5.sp
            )
            if (icon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun WhatsAppCTAButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Chat with SAGVORA"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFF25D366), Color(0xFF128C7E))
                )
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Chat,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun SectionHeader(
    tag: String,
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(width = 16.dp, height = 3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(CyanNeon)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = tag.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = CyanNeon,
                letterSpacing = 1.5.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                lineHeight = 22.sp
            )
        }
    }
}
