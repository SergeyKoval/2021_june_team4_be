# 2021_june_team4_be

## Getting started with docker-compose.yml
These instructions will get you through the bootstrap phase of creating and deploying samples of containerized applications with Docker Compose.
### Prerequisites
For work with .yml files you need to install and use Docker Desktop.

Installation instructions can be found here: 
  - Windows or macOS:
    [Install Docker Desktop](https://www.docker.com/get-started)
  - Linux: [Install Docker](https://www.docker.com/get-started) and then
    [Docker Compose](https://github.com/docker/compose)
    
If you need more information, see Docker Compose docs: [Docker Compose documentation](https://docs.docker.com/compose/)   
### Running a sample

The root directory of each sample contains the `docker-compose.yaml` which
describes the configuration of service components. All samples can be run in
a local environment by going into the root directory of each one and executing:

```console
docker-compose up -d --build
```


To stop and remove all containers of the sample application run:

```console
docker-compose down
```


The compose file defines an application with two services `backend` and `postgres`.
When deploying the application, docker-compose maps port 8080 of the backend service container to port 8080 of the host as specified in the file.
Make sure port 8080 on the host is not already being in use.

After the application starts, navigate to `http://localhost:8080/swagger-ui/` in your web browse
