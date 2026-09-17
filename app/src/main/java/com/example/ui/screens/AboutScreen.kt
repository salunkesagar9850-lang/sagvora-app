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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeader
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
fun AboutScreen(
    onStartProject: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .testTag("about_screen_root")
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        SectionHeader(
            tag = "Our Company",
            title = "About SAGVORA",
            subtitle = "Pioneering the intersection of artificial intelligence, creative media, and resilient technology engineering."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Statement Card
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
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(CyanNeon.copy(alpha = 0.12f))
                        .border(1.dp, CyanNeon.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "THE VISION",
                        style = MaterialTheme.typography.labelSmall,
                        color = CyanBright,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Building the Future with AI & Technology",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    color = TextPrimary,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "SAGVORA is an AI and technology company creating innovative digital experiences, AI-powered solutions, creative media, applications, websites and next-generation technology.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Mission & Vision Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MissionVisionCard(
                title = "Vision",
                description = "Building the future where intelligence meets creativity.",
                icon = Icons.Default.Visibility,
                accentColor = CyanNeon,
                modifier = Modifier.weight(1f)
            )
            MissionVisionCard(
                title = "Mission",
                description = "Empowering businesses and creators through powerful AI, cinematic visual content, and scalable software solutions.",
                icon = Icons.Default.RocketLaunch,
                accentColor = OrangeNeon,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Founder & Leadership Section
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
            border = BorderStroke(1.dp, BorderGlass),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(CyanNeon.copy(alpha = 0.15f))
                            .border(1.dp, CyanNeon.copy(alpha = 0.5f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SS",
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            color = CyanBright
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "Sagar Salunke",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Founder & CEO, SAGVORA",
                            style = MaterialTheme.typography.labelMedium,
                            color = CyanNeon,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "SAGVORA was founded by Sagar Salunke with the conviction that advanced artificial intelligence, cinematic media, and high-performance digital engineering can be synthesized into transformative experiences for visionary clients.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF0D1424))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "“Imagine. Create. Transform.”",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Core Values Section
        Text(
            text = "Core Values",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        val coreValues = listOf(
            Pair("Innovation", "Constantly exploring new horizons in generative AI, computational media, and modern architectures."),
            Pair("Quality", "Uncompromising attention to visual polish, responsive ergonomics, and robust performance."),
            Pair("Speed", "Rapid agile execution and streamlined delivery to transform ideas into working realities swiftly."),
            Pair("Creative Excellence", "Crafting world-class cinematic visuals and high-impact digital narratives."),
            Pair("Technological Precision", "Engineering clean, tested, resilient software infrastructure built to scale.")
        )

        coreValues.forEach { (name, desc) ->
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(CyanNeon)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Core Capabilities
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.WorkspacePremium,
                        contentDescription = null,
                        tint = CyanNeon,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Core Capabilities",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                val capabilities = listOf(
                    "Algorithmic Workflow Automation & Multi-Agent Orchestration",
                    "Cinematic Generative Video & Photorealistic Diffusion Media",
                    "Native Android & High-Throughput Cloud Applications",
                    "Fluid Responsive Web Flagships & Interactive Visual Experiences",
                    "Custom Technology Research & Proof-of-Concept Prototyping",
                    "Ethical, Secure & Enterprise-Compliant AI Integration"
                )

                capabilities.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(CyanNeon)
                                .padding(top = 6.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Why SAGVORA Pillars
        Text(
            text = "Guiding Philosophy",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        val philosophy = listOf(
            Triple(
                "AI-Driven Creativity",
                "Technology exists in service of visionary storytelling and meaningful human experiences.",
                Icons.Default.AutoAwesome
            ),
            Triple(
                "Modern Technology",
                "We write clean, tested, modern code that stands the test of rapid platform evolution.",
                Icons.Default.Code
            ),
            Triple(
                "Custom Solutions",
                "Every client challenge requires nuanced architectural precision rather than generic templates.",
                Icons.Default.DashboardCustomize
            ),
            Triple(
                "Future-Focused Thinking",
                "We build modular foundations designed to adopt tomorrow's computational breakthroughs smoothly.",
                Icons.Default.Lightbulb
            )
        )

        philosophy.forEach { (title, desc, icon) ->
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF141D30))
                            .border(1.dp, BorderGlass, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = CyanNeon,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun MissionVisionCard(
    title: String,
    description: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    GlassCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                lineHeight = 18.sp
            )
        }
    }
}
