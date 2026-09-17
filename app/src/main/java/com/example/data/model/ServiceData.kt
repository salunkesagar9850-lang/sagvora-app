package com.example.data.model

data class ServiceItem(
    val id: String,
    val title: String,
    val categoryBadge: String,
    val shortDescription: String,
    val overview: String,
    val whatWeCreate: List<String>,
    val exampleUseCases: List<String>,
    val highlights: List<String>
)

object ServiceCatalog {
    val services = listOf(
        ServiceItem(
            id = "ai_solutions",
            title = "AI Solutions",
            categoryBadge = "Automation & Intelligence",
            shortDescription = "AI-powered solutions designed to automate, optimize and transform digital workflows.",
            overview = "We engineer intelligent systems and algorithmic architectures that seamlessly integrate into your operational infrastructure, turning complex operational bottlenecks into automated, high-velocity workflows.",
            whatWeCreate = listOf(
                "Autonomous AI Agents & Multi-Agent Workflows",
                "Custom LLM & Retrieval-Augmented Generation (RAG) Pipelines",
                "Intelligent Document Processing & Semantic Search",
                "Predictive Analytics & Real-Time Decision Engines",
                "Enterprise Data Pipeline Integrations & Fine-Tuned Models"
            ),
            exampleUseCases = listOf(
                "Automating multi-tier customer support with contextual AI reasoning",
                "Synthesizing enterprise compliance documents into instant queryable knowledge",
                "Predictive supply-chain optimization and demand forecasting",
                "Automated anomaly detection across high-volume financial transactions"
            ),
            highlights = listOf(
                "Up to 80% repetitive task reduction",
                "Enterprise-grade security & sovereignty",
                "Custom fine-tuned architectures"
            )
        ),
        ServiceItem(
            id = "ai_advertising",
            title = "AI Advertising",
            categoryBadge = "Creative & Marketing",
            shortDescription = "Creative AI-powered advertising concepts, product visuals and promotional videos.",
            overview = "SAGVORA leverages cutting-edge generative models, photorealistic diffusion rendering, and prompt engineering to produce high-impact marketing visuals, brand narratives, and visual ad campaigns.",
            whatWeCreate = listOf(
                "Hyper-Realistic Product Renderings & Studio Showcases",
                "High-Converting Social Video Campaigns & Motion Ads",
                "Dynamic Personalized Creative Variations for A/B Testing",
                "Generative Key Visuals & Omni-Channel Brand Art",
                "Interactive Digital Ad Formats & Visual Concepts"
            ),
            exampleUseCases = listOf(
                "Launching global luxury product launches without physical studio staging",
                "Rapid prototyping of 50+ localized advertising variations in days",
                "Synthesizing high-fashion visual narratives for digital billboards",
                "Dynamic ad creative optimization based on real-time audience engagement"
            ),
            highlights = listOf(
                "10x Faster creative iteration cycle",
                "Photorealistic studio production quality",
                "Omnichannel visual consistency"
            )
        ),
        ServiceItem(
            id = "ai_filmmaking",
            title = "AI Filmmaking",
            categoryBadge = "Cinematic Media",
            shortDescription = "Cinematic AI-generated stories, advertisements, short films and visual experiences.",
            overview = "Pioneering the next horizon of cinematic storytelling. We fuse generative video diffusion, neural rendering, cinematic sound design, and narrative direction to craft unforgettable visual experiences.",
            whatWeCreate = listOf(
                "Cinematic Short Films & Narrative Concept Pilots",
                "AI-Generated Brand Epics & Cinematic Commercials",
                "Worldbuilding Concept Art & Visual Sequence Pre-Visualizations",
                "AI VFX & Neural Style Synthesis for Modern Media",
                "Immersive Audio-Visual Experiences & Music Videos"
            ),
            exampleUseCases = listOf(
                "Directing high-concept sci-fi and speculative design brand films",
                "Generating complex cinematic scenes that defy physical camera logistics",
                "Pre-visualizing multimillion-dollar movie scenes and storyboards",
                "Interactive cinematic sequences for exhibitions and virtual showrooms"
            ),
            highlights = listOf(
                "Unrestricted creative worldbuilding",
                "Cinematic 4K grading & spatial audio",
                "Story-first narrative direction"
            )
        ),
        ServiceItem(
            id = "app_development",
            title = "App Development",
            categoryBadge = "Mobile Engineering",
            shortDescription = "Modern Android and cross-platform applications built around real business needs.",
            overview = "We build responsive, robust, and fluid mobile applications engineered with modern native Android (Jetpack Compose, Kotlin Coroutines) and resilient cross-platform foundations, designed for scalability and user delight.",
            whatWeCreate = listOf(
                "Native Modern Android Applications (Jetpack Compose & Kotlin)",
                "Scalable Enterprise Mobile Dashboards & Field Tools",
                "AI-Integrated Mobile Apps (On-Device & Cloud AI Inference)",
                "High-Performance Consumer Apps & Subscription Platforms",
                "Offline-First Architectures with Secure Room & Cloud Sync"
            ),
            exampleUseCases = listOf(
                "Enterprise logistics management app with offline inventory synchronization",
                "On-device AI visual inspection application for manufacturing quality control",
                "SaaS mobile client with real-time biometric authentication and push alerting",
                "Content creator studio app with local media processing pipelines"
            ),
            highlights = listOf(
                "100% Kotlin & Modern Jetpack Compose",
                "Edge-to-edge adaptive layouts",
                "Engineered for sub-100ms response times"
            )
        ),
        ServiceItem(
            id = "website_development",
            title = "Website Development",
            categoryBadge = "Web & Digital Platforms",
            shortDescription = "Professional, responsive websites for businesses, brands and creators.",
            overview = "From sleek brand flagships to comprehensive SaaS web portals, SAGVORA creates performant, SEO-optimized, and visually stunning web destinations that convert visitors into lasting partners.",
            whatWeCreate = listOf(
                "Premium High-Converting Company Portals & Brand Flagships",
                "Interactive Web Apps with WebGL & 3D Interactive Elements",
                "SaaS Application Frontends & Client Dashboards",
                "Modern E-Commerce Architectures & Custom Checkouts",
                "Headless CMS Architectures & Performance-Tuned Platforms"
            ),
            exampleUseCases = listOf(
                "Next-generation digital showcase for a global robotics and hardware company",
                "Interactive investor relations portal with real-time metric visualizations",
                "Direct-to-consumer flagship storefront with 3D product previews",
                "Educational knowledge base with instant neural search capabilities"
            ),
            highlights = listOf(
                "Near-instantaneous load times (Core Web Vitals 95+)",
                "Full accessibility & mobile responsiveness",
                "Modern security and SSL hardening"
            )
        ),
        ServiceItem(
            id = "digital_innovation",
            title = "Digital Innovation",
            categoryBadge = "Next-Gen Tech",
            shortDescription = "Technology-driven ideas and custom digital experiences for emerging businesses.",
            overview = "Bridging visionary strategy and practical technological implementation. We explore emerging paradigms—spatial computing, generative pipelines, IoT edge devices, and custom algorithmic prototypes—to give your company a decisive advantage.",
            whatWeCreate = listOf(
                "Rapid Proof-of-Concept (PoC) & Interactive Prototypes",
                "Custom Algorithmic Engines & Proprietary Tools",
                "Hardware-Software IoT Integrations & Edge Compute",
                "Spatial & Immersive Digital Showrooms",
                "Technology Roadmap & Architectural Consulting"
            ),
            exampleUseCases = listOf(
                "Validating an unproven technology concept in 3 weeks before major capital allocation",
                "Custom edge-computing sensor dashboard for industrial monitoring",
                "Interactive physical-digital installation for flagship international conferences",
                "Transitioning legacy architecture onto modern modular microservices"
            ),
            highlights = listOf(
                "Accelerated prototype sprints",
                "Battle-tested technical feasibility",
                "Custom tailored IP ownership"
            )
        )
    )

    fun getServiceById(id: String): ServiceItem? {
        return services.find { it.id == id }
    }
}
