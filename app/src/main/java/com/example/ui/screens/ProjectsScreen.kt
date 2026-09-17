package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.ProjectCatalog
import com.example.data.model.ProjectItem
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CyanBright
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.OrangeNeon
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(
    projects: List<ProjectItem>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onStartProjectWithCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeModalProject by remember { mutableStateOf<ProjectItem?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val filteredProjects = remember(projects, selectedCategory) {
        if (selectedCategory == "All") {
            projects
        } else {
            projects.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("projects_screen_root")
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            SectionHeader(
                tag = "Portfolio",
                title = "Selected Works",
                subtitle = "Explore our case studies spanning generative video, algorithmic solutions, and modern software architectures."
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Category Filter Pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProjectCatalog.categories.forEach { category ->
                    val isSelected = selectedCategory == category
                    val chipBackground = if (isSelected) CyanNeon else DarkCardSurface
                    val chipTextColor = if (isSelected) Color(0xFF07090F) else TextSecondary
                    val borderStroke = if (isSelected) null else BorderStroke(1.dp, BorderGlass)

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .then(if (borderStroke != null) Modifier.border(borderStroke, RoundedCornerShape(20.dp)) else Modifier)
                            .background(chipBackground)
                            .clickable { onCategorySelected(category) }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                            .testTag("filter_chip_$category")
                    ) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = chipTextColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
        }

        if (filteredProjects.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Layers,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No projects in '$selectedCategory' yet",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextSecondary
                        )
                    }
                }
            }
        } else {
            items(filteredProjects, key = { it.id }) { project ->
                ProjectCardItem(
                    project = project,
                    onViewProject = { activeModalProject = project },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Modal Bottom Sheet for Project Details
    if (activeModalProject != null) {
        val project = activeModalProject!!
        ModalBottomSheet(
            onDismissRequest = { activeModalProject = null },
            sheetState = sheetState,
            containerColor = Color(0xFF0F1524),
            dragHandle = null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(CyanNeon.copy(alpha = 0.15f))
                            .border(1.dp, CyanNeon.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = project.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = CyanBright,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        onClick = { activeModalProject = null },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = project.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Domain: ${project.clientOrDomain}",
                    style = MaterialTheme.typography.labelMedium,
                    color = OrangeNeon
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = project.fullDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Key Deliverables:",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                project.deliverables.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = CyanNeon,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Technologies
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    project.technologies.forEach { tech ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF182033))
                                .border(1.dp, BorderGlass, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = tech,
                                style = MaterialTheme.typography.labelSmall,
                                color = CyanBright
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val cat = project.category
                        activeModalProject = null
                        onStartProjectWithCategory(cat)
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanNeon,
                        contentColor = Color(0xFF07090F)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.RocketLaunch,
                            contentDescription = null,
                            tint = Color(0xFF07090F),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Build a Similar Project",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectCardItem(
    project: ProjectItem,
    onViewProject: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradientColors = when (project.placeholderVisualType) {
        "cyan" -> listOf(Color(0xFF0A2233), Color(0xFF071320))
        "orange" -> listOf(Color(0xFF331D08), Color(0xFF1F0F03))
        "violet" -> listOf(Color(0xFF26103D), Color(0xFF140822))
        else -> listOf(Color(0xFF0F172A), Color(0xFF0A0F1D))
    }

    val accentColor = when (project.placeholderVisualType) {
        "cyan" -> CyanNeon
        "orange" -> OrangeNeon
        "violet" -> VioletNeon
        else -> CyanBright
    }

    GlassCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onViewProject
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Visual Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Brush.linearGradient(gradientColors))
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
                                listOf(Color(0x33000000), Color(0xEB0E131F))
                            )
                        )
                )

                // Category Badge
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xDD070A10))
                        .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = project.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = accentColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
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

                Spacer(modifier = Modifier.height(14.dp))

                // Bottom Action: View Project
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = project.clientOrDomain,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )

                    OutlinedButton(
                        onClick = onViewProject,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, BorderGlass),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = CyanNeon
                        ),
                        modifier = Modifier.testTag("view_project_${project.id}")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "View Project",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
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
        }
    }
}
