# Patrol Reporting API

## Overview
A Spring Boot REST API for managing security patrols, logging rounds and incidents, and generating end-of-shift reports.

## Key Features
- Patrol lifecycle (start > active > completed)
- Rounds and incidents linked to patrol
- Domain rules enforced (cannot add data after patrol ends)
- Report generation per patrol
- Clean API error handling

## Tech Stack
- Java, Spring Boot
- JPA / Hibernate
- MySQL
- REST API

## Example Flow
1. Start patrol → returns ID
2. Add rounds
3. Add incidents
4. End patrol
5. Generate report

## API Endpoints
POST   /api/patrols  
POST   /api/patrols/{id}/rounds  
POST   /api/patrols/{id}/incidents  
POST   /api/patrols/{id}/end  
GET    /api/patrols/{id}  
GET    /api/patrols/{id}/report

## Notes
- Security temporarily disabled for testing
- Basic web UI added for mobile interaction