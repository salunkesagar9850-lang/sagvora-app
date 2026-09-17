package com.example.data.model

data class ProjectItem(
    val id: String,
    val title: String,
    val category: String, // "AI Advertising", "AI Films", "Apps", "Websites", "AI Experiments"
    val shortDescription: String,
    val fullDescription: String,
    val clientOrDomain: String,
    val deliverables: List<String>,
    val technologies: List<String>,
    val keyResults: String,
    val isFeatured: Boolean = false,
    val placeholderVisualType: String = "cyan" // "cyan", "orange", "violet" for abstract styling
)

object ProjectCatalog {
    val categories = listOf(
        "All",
        "AI Advertising",
        "AI Films",
        "Apps",
        "Websites",
        "AI Experiments"
    )

    val defaultProjects = listOf(
        ProjectItem(
            id = "proj_lumina_ai",
            title = "AuraSphere Multi-Modal AI Assistant",
            category = "Apps",
            shortDescription = "High-performance enterprise mobile application orchestrating multi-agent AI assistants.",
            fullDescription = "Engineered an edge-optimized native Android app allowing field engineers to query complex operational schematics using voice, visual capture, and contextual reasoning. Features offline caching, sub-second query response, and encrypted state persistence.",
            clientOrDomain = "Industrial Intelligence",
            deliverables = listOf(
                "Modern Android Mobile App (Jetpack Compose)",
                "On-device neural inference pipeline",
                "Encrypted offline Room database sync",
                "Full architectural documentation & test suite"
            ),
            technologies = listOf("Kotlin", "Jetpack Compose", "Coroutines", "Room", "TorchLite"),
            keyResults = "Reduced technician troubleshooting latency by 64% across 1,200 active field deployments.",
            isFeatured = true,
            placeholderVisualType = "cyan"
        ),
        ProjectItem(
            id = "proj_chronicles_void",
            title = "Chronicles of the Event Horizon",
            category = "AI Films",
            shortDescription = "Cinematic speculative sci-fi teaser film created through generative neural diffusion.",
            fullDescription = "A masterclass in AI-directed visual storytelling. SAGVORA directed an 8-minute speculative fiction short film featuring surreal exoplanetary environments, synthetic cinematic actors, and custom spatial sound synthesis.",
            clientOrDomain = "Independent Film & Digital Entertainment",
            deliverables = listOf(
                "4K Cinematic Master Video Asset",
                "Complete visual style guide & prompt bible",
                "Custom synthesized 5.1 spatial audio mix",
                "Exhibition poster suite and teaser cuts"
            ),
            technologies = listOf("Generative Video Diffusion", "Neural VFX", "Spatial Audio", "DaVinci Resolve"),
            keyResults = "Official showcase selection across international digital arts & innovative filmmaking festivals.",
            isFeatured = true,
            placeholderVisualType = "violet"
        ),
        ProjectItem(
            id = "proj_hypernova_ad",
            title = "HyperNova Kinetic Brand Launch",
            category = "AI Advertising",
            shortDescription = "Generative 3D advertising campaign with photorealistic product visual transformations.",
            fullDescription = "Conceived and executed a comprehensive multi-channel digital advertising suite for an electric performance automotive concept. Features dynamic lighting simulation, photorealistic carbon weave textures, and animated atmospheric elements.",
            clientOrDomain = "Automotive & Clean Energy Brand",
            deliverables = listOf(
                "15+ High-resolution 3D generative key visuals",
                "6 Distinct social-first video ad cuts (9:16 & 16:9)",
                "Dynamic A/B testing asset library",
                "Digital billboard animations"
            ),
            technologies = listOf("Neural 3D Rendering", "Motion Design", "Diffusion Enhancers", "Color Grading"),
            keyResults = "Achieved 3.2x higher click-through rate compared to conventional studio photo assets.",
            isFeatured = true,
            placeholderVisualType = "orange"
        ),
        ProjectItem(
            id = "proj_nexus_portal",
            title = "Nexus Corporate Digital Flagship",
            category = "Websites",
            shortDescription = "Ultra-responsive corporate digital presence with interactive WebGL shaders and live telemetry.",
            fullDescription = "Designed and developed an immersive company flagship web platform featuring smooth kinetic typography, dark glassmorphism, responsive micro-interactions, and accessible high-contrast design.",
            clientOrDomain = "Fintech & Algorithmic Trading",
            deliverables = listOf(
                "Responsive Next-Gen Web Application",
                "Custom interactive visual shaders",
                "Integrated lead generation funnels",
                "Core Web Vitals 99 performance score"
            ),
            technologies = listOf("TypeScript", "TailwindCSS", "WebGL", "Headless API", "Edge Hosting"),
            keyResults = "Generated a 140% increase in qualified inbound institutional sales leads in Q1.",
            isFeatured = false,
            placeholderVisualType = "cyan"
        ),
        ProjectItem(
            id = "proj_quantum_synth",
            title = "Neural Synthesia Experiment",
            category = "AI Experiments",
            shortDescription = "Real-time algorithmic visualizer transforming ambient audio frequencies into fluid geometry.",
            fullDescription = "An R&D experimental laboratory exploring the intersection of real-time audio analysis and generative latent space interpolation. Demonstrates how mathematical sound waveforms can drive coherent visual morphing.",
            clientOrDomain = "SAGVORA R&D Labs",
            deliverables = listOf(
                "Interactive proof-of-concept software build",
                "Open architecture technical whitepaper",
                "High-framerate video recordings & benchmarks"
            ),
            technologies = listOf("Latent Space Traversal", "FFT Audio Analysis", "Compute Shaders", "Python/C++"),
            keyResults = "Demonstrated stable 60 FPS neural rendering on consumer mobile silicon.",
            isFeatured = false,
            placeholderVisualType = "violet"
        ),
        ProjectItem(
            id = "proj_pulse_commerce",
            title = "Pulse Mobile Commerce & AR",
            category = "Apps",
            shortDescription = "Next-generation retail mobile app featuring 3D product previews and instant checkout.",
            fullDescription = "Crafted a seamless mobile shopping experience emphasizing micro-interactions, tactile haptic feedback, real-time inventory tracking, and streamlined checkout.",
            clientOrDomain = "Designer Apparel & Wearables",
            deliverables = listOf(
                "Android Application Architecture",
                "Secure Payment Gateway Integration",
                "Biometric One-Touch Authentication",
                "Push Notification Pipeline"
            ),
            technologies = listOf("Kotlin", "Jetpack Compose", "Coroutines", "OAuth2", "Stripe SDK"),
            keyResults = "Cart abandonment reduced by 28% compared to web baseline.",
            isFeatured = false,
            placeholderVisualType = "orange"
        )
    )
}
