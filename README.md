# Patrol Reporting API

## Overview
REST API for managing security patrols, logging rounds and incidents, and generating end-of-shift reports.

## Features
- Patrol lifecycle (start > active > completed)
- Rounds and incidents linked to patrol
- Report generation per patrol
- Validation and domain rule enforcement (cannot add after end)
- Clean error handling (400 with message)

## Tech Stack
- Java, Spring Boot
- JPA/Hibernate
- MySQL (or H2 for local)
- Docker (optional)

## API Flow
1. Start patrol > returns ID
2. Add rounds
3. Add incidents
4. End patrol
5. Get report

## Endpoints
- POST /api/patrols
- POST /api/patrols/{id}/rounds
- POST /api/patrols/{id}/incidents
- POST /api/patrols/{id}/end
- GET  /api/patrols/{id}
- GET  /api/patrols/{id}/report

## Example Requests

### Start patrol
POST /api/patrols

### Add round
POST /api/patrols/{id}/rounds
{
"location": "Main entrance"
}

### Add incident
POST /api/patrols/{id}/incidents
{
"description": "Broken window"
}

### Get report
GET /api/patrols/{id}/report

## Notes
- Security temporarily disabled for testing (permitAll). JWT can be added later.