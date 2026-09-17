package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ProjectInquiryEntity
import com.example.data.model.CompanyConfig
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SettingsScreen(
    config: CompanyConfig,
    inquiries: List<ProjectInquiryEntity>,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onSaveConfig: (CompanyConfig) -> Unit,
    onDeleteInquiry: (Int) -> Unit,
    onBack: () -> Unit,
    onNavigateAbout: () -> Unit = {},
    onNavigateContact: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var notificationsEnabled by remember { mutableStateOf(true) }
    var comingSoonDialogTitle by remember { mutableStateOf<String?>(null) }

    var whatsappInput by remember(config.whatsappNumber) { mutableStateOf(config.whatsappNumber) }
    var emailInput by remember(config.email) { mutableStateOf(config.email) }
    var websiteInput by remember(config.websiteUrl) { mutableStateOf(config.websiteUrl) }
    var instagramInput by remember(config.instagramUrl) { mutableStateOf(config.instagramUrl) }
    var linkedinInput by remember(config.linkedinUrl) { mutableStateOf(config.linkedinUrl) }
    var youtubeInput by remember(config.youtubeUrl) { mutableStateOf(config.youtubeUrl) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("settings_screen_root")
    ) {
        // Top Back Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(DarkCardSurface)
                    .border(BorderStroke(1.dp, BorderGlass), CircleShape)
                    .testTag("settings_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextPrimary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Back",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.clickable { onBack() }
            )
        }

        SectionHeader(
            tag = "Settings & Administration",
            title = "Settings",
            subtitle = "Preferences, legal notices, company links, and client inquiry pipeline."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Appearance & Display: Dark Mode / Light Mode
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Appearance",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.Brightness4 else Icons.Default.Brightness7,
                            contentDescription = null,
                            tint = CyanNeon,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (isDarkTheme) "Dark Mode" else "Light Mode",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = if (isDarkTheme) "Active (Dark futuristic neon)" else "Active (Clean modern light)",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMuted
                            )
                        }
                    }

                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { onToggleTheme() },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF07090F),
                            checkedTrackColor = CyanNeon,
                            uncheckedThumbColor = TextMuted,
                            uncheckedTrackColor = Color(0xFF1E293B)
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Notifications Preference
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Preferences",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(CyanNeon.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔔",
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Notifications",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = if (notificationsEnabled) "Project updates and alerts enabled" else "Notifications paused",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMuted
                            )
                        }
                    }

                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = {
                            notificationsEnabled = it
                            Toast.makeText(
                                context,
                                if (it) "Notifications enabled" else "Notifications muted",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF07090F),
                            checkedTrackColor = CyanNeon,
                            uncheckedThumbColor = TextMuted,
                            uncheckedTrackColor = Color(0xFF1E293B)
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Information & Legal Section: About SAGVORA, Privacy Policy, Terms, Contact
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Company & Legal",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                // About SAGVORA
                SettingsItemRow(
                    title = "About SAGVORA",
                    subtitle = "Story, vision, mission, and leadership",
                    onClick = onNavigateAbout
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Contact
                SettingsItemRow(
                    title = "Contact",
                    subtitle = "Direct communication channels and social hub",
                    onClick = onNavigateContact
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Privacy Policy (Shows 'Coming soon')
                SettingsItemRow(
                    title = "Privacy Policy",
                    subtitle = "Coming soon",
                    onClick = { comingSoonDialogTitle = "Privacy Policy" }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Terms of Service (Shows 'Coming soon')
                SettingsItemRow(
                    title = "Terms",
                    subtitle = "Coming soon",
                    onClick = { comingSoonDialogTitle = "Terms of Service" }
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Communication Configuration Form
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Company Communication Links",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = OrangeNeon,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "These numbers and links power the direct buttons and WhatsApp triggers across the app.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // WhatsApp Number (Primary)
                ConfigField(
                    label = "WhatsApp Business Number (with country code)",
                    value = whatsappInput,
                    onValueChange = { whatsappInput = it },
                    placeholder = "+91 9226553511"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Email
                ConfigField(
                    label = "Official Contact Email",
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    placeholder = "sagvoraofficial@gmail.com"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Website
                ConfigField(
                    label = "Website URL",
                    value = websiteInput,
                    onValueChange = { websiteInput = it },
                    placeholder = "https://sagvoraofficial.website3.me"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Instagram
                ConfigField(
                    label = "Instagram URL",
                    value = instagramInput,
                    onValueChange = { instagramInput = it },
                    placeholder = "https://www.instagram.com/sagvoraofficial?stkn=MXVyNnczemllM2huMA=="
                )

                Spacer(modifier = Modifier.height(12.dp))

                // LinkedIn
                ConfigField(
                    label = "LinkedIn URL",
                    value = linkedinInput,
                    onValueChange = { linkedinInput = it },
                    placeholder = "https://linkedin.com/company/sagvora"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // YouTube
                ConfigField(
                    label = "YouTube URL",
                    value = youtubeInput,
                    onValueChange = { youtubeInput = it },
                    placeholder = "https://www.youtube.com/channel/UCaoh6O4PppVAKpJYL4Rv-aA"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val updated = config.copy(
                            whatsappNumber = whatsappInput.trim(),
                            email = emailInput.trim(),
                            websiteUrl = websiteInput.trim(),
                            instagramUrl = instagramInput.trim(),
                            linkedinUrl = linkedinInput.trim(),
                            youtubeUrl = youtubeInput.trim()
                        )
                        onSaveConfig(updated)
                        Toast.makeText(context, "Company links & WhatsApp updated!", Toast.LENGTH_SHORT).show()
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanNeon,
                        contentColor = Color(0xFF07090F)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Save Configuration Changes",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Saved Inquiries / Lead Generation Pipeline (Room DB)
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ListAlt,
                            contentDescription = null,
                            tint = CyanBright,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Received Inquiries",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(CyanNeon.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${inquiries.size} Leads",
                            style = MaterialTheme.typography.labelSmall,
                            color = CyanNeon,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Inquiries submitted via 'Start a Project' stored in local database.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                if (inquiries.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No inquiries submitted yet. Try the 'Start a Project' form!",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                } else {
                    inquiries.forEach { inquiry ->
                        val dateFormatted = remember(inquiry.createdAt) {
                            val sdf = SimpleDateFormat("MMM d, yyyy · HH:mm", Locale.getDefault())
                            sdf.format(Date(inquiry.createdAt))
                        }

                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF090E18)),
                            border = BorderStroke(1.dp, BorderGlass),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = inquiry.fullName,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                    IconButton(
                                        onClick = { onDeleteInquiry(inquiry.id) },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = TextMuted,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }

                                Text(
                                    text = "Company: ${inquiry.companyName} · Service: ${inquiry.selectedService}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = CyanNeon
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = inquiry.projectDescription,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary,
                                    maxLines = 3
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Contact: ${inquiry.phoneWhatsApp} (${inquiry.email})",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextMuted
                                    )
                                    Text(
                                        text = dateFormatted,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextMuted
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }

    if (comingSoonDialogTitle != null) {
        AlertDialog(
            onDismissRequest = { comingSoonDialogTitle = null },
            containerColor = Color(0xFF0E1424),
            title = {
                Text(
                    text = comingSoonDialogTitle ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            },
            text = {
                Text(
                    text = "Coming soon",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = { comingSoonDialogTitle = null },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanNeon, contentColor = Color(0xFF07090F))
                ) {
                    Text("OK", fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

@Composable
private fun SettingsItemRow(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF090E1A))
            .border(1.dp, BorderGlass, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMuted
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = CyanNeon,
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
private fun ConfigField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = TextMuted) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CyanNeon,
                unfocusedBorderColor = BorderGlass,
                focusedContainerColor = Color(0xFF090D17),
                unfocusedContainerColor = Color(0xFF090D17),
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
