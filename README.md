# Fiverr Gig Service (`fiverr-gig-service`)

Production-grade **Gig Catalog & 3-Tier Pricing Microservice** for Fiverr Club, built with **Java 21**, **Spring Boot 3.3.5**, **Domain-Driven Design (DDD)**, and **Hexagonal Architecture (Ports & Adapters)**.

## Architecture & Module Structure

```
club.fiverr.gig
├── presentation/rest/
│   ├── GigController.java
│   ├── request/                # CreateGigRequest, GigPackageRequest
│   ├── response/               # GigResponse, GigPackageResponse
│   └── mapper/                 # GigRestMapper
├── application/
│   ├── dto/
│   │   ├── command/            # CreateGigCommand, SearchGigsCommand
│   │   └── result/             # GigResult, GigPackageResult
│   ├── mapper/                 # GigResultMapper
│   ├── port/
│   │   ├── in/                 # CreateGigUseCase, GetGigByIdUseCase, SearchGigsUseCase
│   │   └── out/                # GigSearchIndexPort, GigEventPublisherPort (External Services ONLY)
│   └── usecase/                # CreateGigUseCaseImpl, GetGigByIdUseCaseImpl, SearchGigsUseCaseImpl
├── domain/
│   ├── model/                  # Gig, GigPackage, PackageTier (BASIC/STANDARD/PREMIUM), GigStatus
│   ├── repository/             # GigRepository (Domain Repository Interface)
│   ├── service/                # GigSlugService
│   └── exception/              # GigNotFoundException, InvalidGigException
└── infrastructure/
    ├── persistence/
    │   ├── entity/             # GigEntity, GigPackageEmbeddable
    │   ├── mapper/             # GigPersistenceMapper
    │   └── repository/         # JpaGigRepository, GigJpaRepository
    └── adapter/                # GigSearchIndexAdapter, GigEventPublisherAdapter
```

## Key Domain Capabilities
- **3-Tier Pricing Validation**: Every `Gig` enforces `BASIC`, `STANDARD`, and `PREMIUM` packages with monotonic pricing (`BASIC <= STANDARD <= PREMIUM`) and minimum \$5.00 pricing.
- **Unique SEO Slugs**: `GigSlugService` generates collision-safe URL slugs for every gig.
- **Lifecycle Management**: `DRAFT`, `ACTIVE`, and `PAUSED` gig states.

## REST Endpoints (Port `8083`)
- `POST /api/v1/gigs` — Create a new 3-tier Gig
- `GET /api/v1/gigs/{gigId}` — Retrieve a Gig by ID
- `GET /api/v1/gigs/search?keyword=...&category=...` — Search active Gigs
- `GET /actuator/health/liveness` — Kubernetes liveness probe
- `GET /actuator/health/readiness` — Kubernetes readiness probe

## Running Tests & Building

```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@21 mvn test
```
