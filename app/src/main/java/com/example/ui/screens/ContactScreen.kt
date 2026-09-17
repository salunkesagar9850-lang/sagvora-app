package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CompanyConfig
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeader
import com.example.ui.components.WhatsAppCTAButton
import com.example.ui.components.launchEmailSafely
import com.example.ui.components.launchUrlSafely
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CyanBright
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon

@Composable
fun ContactScreen(
    config: CompanyConfig,
    onStartProject: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .testTag("contact_screen_root")
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        SectionHeader(
            tag = "Direct Contact",
            title = "Let's Create Something Extraordinary.",
            subtitle = "Reach out directly to partner on AI solutions, cinematic media, or custom engineering."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Official Company Card with Founder & Official Information
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
            border = BorderStroke(1.dp, BorderGlass),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = config.companyName,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "${config.founderRole}: ${config.founderName}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = CyanNeon
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(CyanNeon.copy(alpha = 0.15f))
                            .border(1.dp, CyanNeon.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "VERIFIED",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyanBright,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Detail Items
                CompanyDetailRow(
                    label = "WhatsApp",
                    value = config.whatsappNumber,
                    icon = Icons.AutoMirrored.Filled.Chat,
                    accentColor = Color(0xFF25D366),
                    onClick = {
                        val url = config.getWhatsAppIntentUrl()
                        launchUrlSafely(context, url, "WhatsApp")
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                CompanyDetailRow(
                    label = "Email",
                    value = config.email,
                    icon = Icons.Default.Email,
                    accentColor = CyanNeon,
                    onClick = {
                        launchEmailSafely(context, config.email, "Inquiry for SAGVORA")
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                CompanyDetailRow(
                    label = "Website",
                    value = config.websiteUrl,
                    icon = Icons.Default.Language,
                    accentColor = Color(0xFF00B0FF),
                    onClick = {
                        launchUrlSafely(context, config.websiteUrl, "Website")
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                CompanyDetailRow(
                    label = "Instagram",
                    value = "@sagvoraofficial",
                    icon = Icons.Default.Share,
                    accentColor = Color(0xFFE1306C),
                    onClick = {
                        launchUrlSafely(context, config.instagramUrl, "Instagram")
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                CompanyDetailRow(
                    label = "YouTube",
                    value = "SAGVORA Official Channel",
                    icon = Icons.Default.PlayArrow,
                    accentColor = Color(0xFFFF0000),
                    onClick = {
                        launchUrlSafely(context, config.youtubeUrl, "YouTube")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // SCREEN 10: Large Action Buttons (WhatsApp, Email, Website, Instagram, YouTube)
        Text(
            text = "Direct Actions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Large WhatsApp Button
            LargeActionButton(
                label = "Chat on WhatsApp",
                sublabel = config.whatsappNumber,
                icon = Icons.AutoMirrored.Filled.Chat,
                backgroundColor = Color(0xFF1B4D3E),
                borderColor = Color(0xFF25D366),
                textColor = Color.White,
                onClick = {
                    val url = config.getWhatsAppIntentUrl("Hello SAGVORA, I would like to discuss a project.")
                    launchUrlSafely(context, url, "WhatsApp")
                },
                modifier = Modifier.testTag("button_large_whatsapp")
            )

            // Large Email Button
            LargeActionButton(
                label = "Send an Email",
                sublabel = config.email,
                icon = Icons.Default.Email,
                backgroundColor = Color(0xFF0D253A),
                borderColor = CyanNeon,
                textColor = Color.White,
                onClick = {
                    launchEmailSafely(context, config.email, "Project Inquiry for SAGVORA")
                },
                modifier = Modifier.testTag("button_large_email")
            )

            // Large Website Button
            LargeActionButton(
                label = "Visit Official Website",
                sublabel = "sagvoraofficial.website3.me",
                icon = Icons.Default.Language,
                backgroundColor = Color(0xFF141F36),
                borderColor = Color(0xFF00B0FF),
                textColor = Color.White,
                onClick = {
                    launchUrlSafely(context, config.websiteUrl, "Website")
                },
                modifier = Modifier.testTag("button_large_website")
            )

            // Large Instagram Button
            LargeActionButton(
                label = "Follow on Instagram",
                sublabel = "@sagvoraofficial",
                icon = Icons.Default.Share,
                backgroundColor = Color(0xFF361426),
                borderColor = Color(0xFFE1306C),
                textColor = Color.White,
                onClick = {
                    launchUrlSafely(context, config.instagramUrl, "Instagram")
                },
                modifier = Modifier.testTag("button_large_instagram")
            )

            // Large YouTube Button
            LargeActionButton(
                label = "Subscribe on YouTube",
                sublabel = "Watch AI Films & Showcases",
                icon = Icons.Default.PlayArrow,
                backgroundColor = Color(0xFF381014),
                borderColor = Color(0xFFFF334B),
                textColor = Color.White,
                onClick = {
                    launchUrlSafely(context, config.youtubeUrl, "YouTube")
                },
                modifier = Modifier.testTag("button_large_youtube")
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // SCREEN 11 — SOCIAL HUB: "Connect With SAGVORA"
        SectionHeader(
            tag = "Social Hub",
            title = "Connect With SAGVORA",
            subtitle = "Join our community across official digital channels and stay updated on breakthrough AI media and software releases."
        )

        Spacer(modifier = Modifier.height(14.dp))

        val socialHubCards = listOf(
            SocialHubItem(
                name = "Instagram",
                handle = "@sagvoraofficial",
                actionLabel = "Open Instagram",
                icon = Icons.Default.Share,
                accentColor = Color(0xFFE1306C),
                onClick = { launchUrlSafely(context, config.instagramUrl, "Instagram") }
            ),
            SocialHubItem(
                name = "YouTube",
                handle = "Official Video & AI Films",
                actionLabel = "Watch on YouTube",
                icon = Icons.Default.PlayArrow,
                accentColor = Color(0xFFFF0000),
                onClick = { launchUrlSafely(context, config.youtubeUrl, "YouTube") }
            ),
            SocialHubItem(
                name = "Website",
                handle = "sagvoraofficial.website3.me",
                actionLabel = "Open Portfolio",
                icon = Icons.Default.Language,
                accentColor = Color(0xFF00B0FF),
                onClick = { launchUrlSafely(context, config.websiteUrl, "Website") }
            ),
            SocialHubItem(
                name = "WhatsApp",
                handle = "+91 9226553511",
                actionLabel = "Direct Chat",
                icon = Icons.AutoMirrored.Filled.Chat,
                accentColor = Color(0xFF25D366),
                onClick = {
                    val url = config.getWhatsAppIntentUrl()
                    launchUrlSafely(context, url, "WhatsApp")
                }
            ),
            SocialHubItem(
                name = "Email",
                handle = "sagvoraofficial@gmail.com",
                actionLabel = "Compose Email",
                icon = Icons.Default.Email,
                accentColor = CyanNeon,
                onClick = { launchEmailSafely(context, config.email, "Inquiry from SAGVORA App") }
            )
        )

        socialHubCards.forEach { item ->
            SocialHubCard(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Quick Navigation to About & Settings
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onOpenAbout,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, BorderGlass),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = CyanNeon, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("About SAGVORA", fontSize = 13.sp)
            }

            OutlinedButton(
                onClick = onOpenSettings,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, BorderGlass),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Settings, contentDescription = null, tint = OrangeNeon, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Settings & Links", fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

private data class SocialHubItem(
    val name: String,
    val handle: String,
    val actionLabel: String,
    val icon: ImageVector,
    val accentColor: Color,
    val onClick: () -> Unit
)

@Composable
private fun SocialHubCard(
    item: SocialHubItem,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier,
        onClick = item.onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(item.accentColor.copy(alpha = 0.15f))
                        .border(1.dp, item.accentColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        tint = item.accentColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = item.handle,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.actionLabel,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = item.accentColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = item.accentColor,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
private fun LargeActionButton(
    label: String,
    sublabel: String,
    icon: ImageVector,
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(borderColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = borderColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = sublabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = borderColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun CompanyDetailRow(
    label: String,
    value: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF0A0F1D))
            .border(1.dp, BorderGlass, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = accentColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = CyanNeon,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}
