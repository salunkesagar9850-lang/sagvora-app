package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.CompanyConfig
import com.example.data.model.ProjectItem
import com.example.data.model.ServiceItem
import com.example.ui.components.GlassCard
import com.example.ui.components.PrimaryActionButton
import com.example.ui.components.SectionHeader
import com.example.ui.components.WhatsAppCTAButton
import com.example.ui.components.launchUrlSafely
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CyanBright
import com.example.ui.theme.CyanDark
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon

@Composable
fun HomeScreen(
    config: CompanyConfig,
    services: List<ServiceItem>,
    latestProjects: List<ProjectItem>,
    onExploreServices: () -> Unit,
    onStartProject: () -> Unit,
    onServiceClick: (String) -> Unit,
    onProjectClick: (ProjectItem) -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 32.dp)
            .testTag("home_screen_root")
    ) {
        // ----------------------------------------------------
        // HERO SECTION
        // ----------------------------------------------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
                border = BorderStroke(1.dp, BorderGlass),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Futuristic Badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .border(1.dp, CyanNeon.copy(alpha = 0.5f), RoundedCornerShape(30.dp))
                            .background(Color(0x3300E5FF))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(CyanNeon)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "AI & TECHNOLOGY LABS",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = CyanBright,
                            letterSpacing = 1.2.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "SAGVORA",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 3.sp,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "AI & Technology, Built for What's Next.",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = CyanNeon,
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "We create intelligent digital experiences, AI-powered solutions and innovative technology for businesses and creators.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Hero Graphic Banner
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, BorderGlass, RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_hero_ai_tech),
                            contentDescription = "SAGVORA AI Visual Art",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        // Gradient Overlay
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            Color(0xB307090F),
                                            Color(0x4007090F)
                                        )
                                    )
                                )
                        )
                        Text(
                            text = "Imagine. Create. Transform.",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(14.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Action Buttons: Primary & Secondary
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        PrimaryActionButton(
                            text = "Start a Project",
                            onClick = onStartProject,
                            icon = Icons.Default.RocketLaunch,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("hero_start_project_button")
                        )

                        PrimaryActionButton(
                            text = "Explore Services",
                            onClick = onExploreServices,
                            icon = Icons.AutoMirrored.Filled.ArrowForward,
                            isSecondary = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("hero_explore_services_button")
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ----------------------------------------------------
        // WHAT WE DO SECTION
        // ----------------------------------------------------
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SectionHeader(
                tag = "Capabilities",
                title = "What We Do",
                subtitle = "Empowering progressive enterprises with integrated AI intelligence and bespoke digital engineering."
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WhatWeDoCard(
                    title = "AI Solutions",
                    subtitle = "Automated Workflows & Intelligence",
                    icon = Icons.Default.SmartToy,
                    accentColor = CyanNeon,
                    modifier = Modifier.weight(1f)
                )
                WhatWeDoCard(
                    title = "Creative Media",
                    subtitle = "AI Films & Visual Ads",
                    icon = Icons.Default.Movie,
                    accentColor = OrangeNeon,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WhatWeDoCard(
                    title = "Modern Apps",
                    subtitle = "Android & Cloud Platforms",
                    icon = Icons.Default.Code,
                    accentColor = VioletNeon,
                    modifier = Modifier.weight(1f)
                )
                WhatWeDoCard(
                    title = "Digital Web",
                    subtitle = "Responsive Flagships & SaaS",
                    icon = Icons.Default.Web,
                    accentColor = CyanBright,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // FEATURED SERVICES SECTION
        // ----------------------------------------------------
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionHeader(
                    tag = "Expertise",
                    title = "Featured Services",
                    subtitle = "Architected for measurable enterprise scale."
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Show top 3 featured services
            services.take(3).forEach { service ->
                FeaturedServiceCard(
                    service = service,
                    onClick = { onServiceClick(service.id) },
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(BorderStroke(1.dp, BorderGlass), RoundedCornerShape(14.dp))
                    .background(Color(0xFF0F1626))
                    .clickable(onClick = onExploreServices)
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "View All 6 Specializations",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = CyanNeon
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = CyanNeon,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // WHY SAGVORA (4 Cards, strictly authentic values)
        // ----------------------------------------------------
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SectionHeader(
                tag = "Why Choose Us",
                title = "Why SAGVORA",
                subtitle = "Principled innovation focused on craftsmanship, reliable technology, and purposeful execution."
            )

            Spacer(modifier = Modifier.height(14.dp))

            val pillars = listOf(
                Triple(
                    "AI-Driven Creativity",
                    "Synthesizing algorithmic intelligence with high-fidelity design to craft distinct visual and functional experiences.",
                    Icons.Default.AutoAwesome
                ),
                Triple(
                    "Modern Technology",
                    "Engineered with current industry standards — Kotlin, Compose, reactive architectures, and secure clean code.",
                    Icons.Default.Code
                ),
                Triple(
                    "Custom Solutions",
                    "Tailored specifically around your real business workflows and audience goals, without cookie-cutter shortcuts.",
                    Icons.Default.DashboardCustomize
                ),
                Triple(
                    "Future-Focused Thinking",
                    "Designing adaptable systems prepared for emerging AI advancements and evolving market demands.",
                    Icons.Default.Lightbulb
                )
            )

            pillars.chunked(2).forEach { rowPillars ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowPillars.forEach { (title, desc, icon) ->
                        PillarCard(
                            title = title,
                            description = desc,
                            icon = icon,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ----------------------------------------------------
        // LATEST PROJECTS SECTION
        // ----------------------------------------------------
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SectionHeader(
                tag = "Showcase",
                title = "Latest Projects",
                subtitle = "Selected highlights of digital platforms, cinematic visuals, and AI systems."
            )

            Spacer(modifier = Modifier.height(14.dp))

            latestProjects.take(2).forEach { project ->
                LatestProjectCard(
                    project = project,
                    onClick = { onProjectClick(project) },
                    modifier = Modifier.padding(bottom = 14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ----------------------------------------------------
        // CONTACT / WHATSAPP CTA
        // ----------------------------------------------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0F1829)
                ),
                border = BorderStroke(1.dp, OrangeNeon.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(OrangeNeon)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "DIRECT COMMUNICATION",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = OrangeNeon,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Ready to Build Something Remarkable?",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Connect directly with SAGVORA to explore how AI-powered solutions, creative media, or custom apps can accelerate your vision.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    WhatsAppCTAButton(
                        onClick = onWhatsAppClick,
                        label = "Chat with SAGVORA on WhatsApp"
                    )
                }
            }
        }
    }
}

@Composable
private fun WhatWeDoCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier,
        borderColor = BorderGlass
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(accentColor.copy(alpha = 0.15f))
                    .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(10.dp)),
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

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun FeaturedServiceCard(
    service: ServiceItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = service.categoryBadge.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = CyanNeon,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = service.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = service.shortDescription,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun PillarCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    GlassCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CyanNeon,
                modifier = Modifier.size(24.dp)
            )

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

@Composable
private fun LatestProjectCard(
    project: ProjectItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Visual Banner Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF0F1A2E),
                                Color(0xFF1E1530)
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_service_showcase),
                    contentDescription = project.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color(0xCC0E131F))
                            )
                        )
                )
                // Category Chip
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xCC080B12))
                        .border(1.dp, BorderGlass, RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = project.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = CyanBright,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = project.shortDescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = project.clientOrDomain,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "View Case Details",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = CyanNeon
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = CyanNeon,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}
