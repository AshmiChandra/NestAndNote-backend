# Render and Aiven Deployment

## 1. Create the Aiven database

Create an Aiven for MySQL service and database, then note its host, port, database name, username, and password. Keep the service credentials private.

## 2. Push the app changes

The backend and frontend are separate GitHub repositories. Commit and push the deployment changes in each repository, then create a Render Blueprint from the backend repository's `render.yaml` on its `main` branch. The Blueprint configures the frontend service to build from the frontend repository's default branch.

## 3. Configure the Aiven connection

During initial Blueprint creation, Render prompts for the backend variables marked `sync: false`. Set them as follows:

- `JDBC_DATABASE_URL`: `jdbc:mysql://<AIVEN_HOST>:<AIVEN_PORT>/<DATABASE_NAME>?sslMode=REQUIRED`
- `DB_USERNAME`: the Aiven database username
- `DB_PASSWORD`: the Aiven database password
- `CORS_ALLOWED_ORIGINS`: the frontend's exact Render origin, for example `https://nest-and-note-frontend.onrender.com`

Ensure Aiven's public endpoint is enabled and its network allowlist permits connections from the Render service.

The Blueprint supplies the API's public Render hostname to the frontend build. Its API URL is compiled into the static build, so redeploy the frontend after changing the API hostname or its environment settings.

## Local development

The backend retains local MySQL defaults. The frontend uses `http://localhost:8080/api` unless `REACT_APP_API_URL` or `REACT_APP_API_HOST` is set.